package cn.expressme.client.action;

import java.io.IOException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.client.dao.ClientDao;
import cn.expressme.other.bean.VerifyCodeBean;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.VerifyCodeType;
import cn.expressme.other.service.UserService;
import cn.expressme.other.service.VerifyService;
import cn.expressme.other.util.Util;

@Controller
@RequestMapping("/client")
public class PersonAction {

	@Autowired
	private UserService userService;

	private String personInfo = "person_info.jsp";
	/**
	 * 显示个人信息处理
	 * 
	 * @param modifyInfo
	 * @param photo
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping("/personinfo.xhtm")
	public ModelAndView showPersonInfo() {

		
		ModelAndView mv = new ModelAndView();
		ClientBean user = userService.getClientBean();
		mv.addObject(FinalInfo.USER_INFO, user);
		mv.setViewName(personInfo);

		return mv;
	}
	
	/**
	 * 显示个人信息处理
	 * 
	 * @param modifyInfo
	 * @param photo
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping("/personpage.xhtm")
	public ModelAndView showHome() {

		
		ModelAndView mv = new ModelAndView();
		ClientBean user = userService.getClientBean();
		mv.addObject(FinalInfo.USER_INFO, user);
		mv.setViewName("user_info.jsp");

		return mv;
	}

	private static final String modifyInfoFailPage = "redirect:modifyInfoFail.jsp"; // 修改信息失败
	private static final String modifyInfoSuccessPage = "redirect:personInfo.xhtm"; // 修改信息成功

	/**
	 * 修改个人信息处理
	 * 
	 * @param modifyInfo
	 * @param photo
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping("/MPInfo.do")
	public String ModifyPersonInfo(
			String phone,
			String userName,
			String city,
			String street,
			@RequestParam(value = "photo", required = false) CommonsMultipartFile photo,
			String verifyCode) {

		String path = modifyInfoFailPage;

		VerifyCodeBean vCode = userService.getVerifyCode();

		if (Util.isNullOrEmpty(phone) // 没有修改手机号码
				|| VerifyCodeBean.equals(vCode, phone, verifyCode,
						VerifyCodeType.MODIFY_PHONE)) { // 有验证码

			ClientBean client = userService.getClientBean();

			// 手机号
			if (!Util.isNullOrEmpty(phone))
				client.setPhone(phone);
			// 用户名
			if (!Util.isNullOrEmpty(userName))
				client.setUserName(userName);
			// 城市
			if (!Util.isNullOrEmpty(city))
				client.setCity(userName);
			// 街道
			if (!Util.isNullOrEmpty(street))
				client.setStreet(street);
			// 判断是否上传图片
			if (!Util.isNullOrEmpty(photo)) {
				
				try {
					String url = Util.savePhoto(userService.getRootURL(),
							FinalInfo.CLIENT_PHOTO_PATH + client.getID() + "."
									+ FinalInfo.IMAGE_TYPE,
							photo.getInputStream(), FinalInfo.HEAD_IMAGE_WIDTH,
							FinalInfo.HEAD_IMAGE_HEIGHT);
					client.setPhoto(url);
				} catch (IOException e) {

					e.printStackTrace();
				}

			}

			ClientDao userDao = userService.getClientDao();

			// modifyInfo.setOld(userInfo);
			if (userDao.update(client)) { // 更新数据库
				path = modifyInfoSuccessPage;
			}
		}

		return path;
	}

	private static final String modifyPasswordFailPage = "modifyPasswordFail.jsp"; // 修改信息失败
	private static final String modifyPasswordSuccessPage = "modifyPasswordSuccess.jsp"; // 修改信息成功

	/**
	 * 修改用户密码
	 * 
	 * @param verifyCode
	 * @param oldPwd
	 * @param firstPwd
	 * @param secondPwd
	 * @return
	 */
	@RequestMapping("/MPPwd.do")
	public String modifyPassword(String verifyCode, String oldPwd,
			String firstPwd, String secondPwd) {

		ClientBean userInfo = userService.getClientBean();

		String path = modifyPasswordFailPage; // 修改密码错误页
		if (Objects.equals(firstPwd, secondPwd)// 两个新密码相同
				&& (Util.isNullOrEmpty(oldPwd) 
						&& VerifyCodeBean.equals(userService.getVerifyCode(),
								userInfo.getPhone(), verifyCode,VerifyCodeType.MODIFY_PASSWORD) // 验证码正确
				|| UserService.isSamePassword(userInfo.getPassword(), oldPwd))) // 旧密码匹配
		{
			userInfo.setPassword(firstPwd);
			if (userService.getClientDao().update(userInfo)) { // 保存数据库
				// 更新session的用户信息
				path = modifyPasswordSuccessPage; // 修改密码成功页

			}
		}
		return path;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param phone
	 * @param response
	 */
	@RequestMapping("/checkphone.do")
	@ResponseBody
	public String checkPhone(String type, String phone) {
		VerifyCodeType codeType = VerifyCodeType.valueOf(VerifyCodeType.class,
				type);
		// 检查手机，加区号
		// if(!VerifyService.isPhone(phone)){
		// System.err.println("输入手机号！");
		// return ;
		// }
		if (Util.isNullOrEmpty(phone))
			phone = userService.getClientBean().getPhone();

		// 发送验证码YES,NO,PHONE_ERROR
		VerifyCodeBean vCodeBean = new VerifyCodeBean(codeType, phone,
				VerifyService.createVerifyCode());
		userService.getSession().setAttribute(FinalInfo.VERIFY_CODE, vCodeBean);
		System.out.println(vCodeBean.getVerifyCode());
       return "{state:\"YES\"}";
	}

}
