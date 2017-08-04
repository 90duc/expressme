package cn.expressme.driver.action;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.expressme.driver.bean.DriverBean;
import cn.expressme.driver.dao.DriverDao;
import cn.expressme.other.bean.VerifyCodeBean;
import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.VerifyCodeType;
import cn.expressme.other.service.UserService;
import cn.expressme.other.util.Util;

@Controller("driverPersonAction")
@RequestMapping("/**/driver")
public class PersonAction {

	private static final String modifyInfoFailPage = "modifyInfoFail.jsp"; // 修改信息失败
	private static final String modifyInfoSuccessPage = "modifyInfoSuccess.jsp"; // 修改信息成功

	@Autowired
	private UserService userService;

	/**
	 * 修改个人信息处理
	 * 
	 * @param modifyInfo
	 * @param photo
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping("/**/MPInfo.do")
	public String ModifyPersonInfo(String phone,String userName,String city,String street, @RequestParam(value = "photo", required = false)CommonsMultipartFile photo,
			String verifyCode) {

		String path = modifyInfoFailPage;

		VerifyCodeBean vCode = userService.getVerifyCode();

		if (phone == null // 没有修改手机号码
				|| VerifyCodeBean.equals(vCode, phone,
						verifyCode, VerifyCodeType.MODIFY_PHONE)) {

			DriverBean driver = userService.getDriverBean();
			
			//手机号
			if(!Util.isNullOrEmpty(phone))
				driver.setPhone(phone);
			//用户名
			if(!Util.isNullOrEmpty(userName))
				driver.setUserName(userName);
			//城市
			if(!Util.isNullOrEmpty(city))
				driver.setCity(userName);
			//街道
			if(!Util.isNullOrEmpty(street))
				driver.setStreet(street);
			// 判断是否上传图片
			if (photo != null) {
				try {
					///byte[] data = Util.toByteArray(photo.getInputStream(), FinalInfo.HEAD_IMAGE_WIDTH, FinalInfo.HEAD_IMAGE_HEIGHT);
					//modifyInfo.setPhoto(data);
					String url = Util.savePhoto(userService.getRootURL(),FinalInfo.DRIVER_PHOTO_PATH+driver.getID()+"."+FinalInfo.IMAGE_TYPE, 
							photo.getInputStream(),FinalInfo.HEAD_IMAGE_WIDTH, FinalInfo.HEAD_IMAGE_HEIGHT);
					driver.setPhoto(url);
				} catch (IOException e) {
					
					e.printStackTrace();
				}		
			}

			DriverDao userInfoDao = userService.getDriverDao();

			if (userInfoDao.update(driver)) { // 更新数据库				
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
	@RequestMapping("/**/MPPwd.do")
	public String modifyPassword(String verifyCode, String oldPwd,
			String firstPwd, String secondPwd) {

		DriverBean userInfo = userService.getDriverBean();

		String path = modifyPasswordFailPage; // 修改密码错误页
		if (Objects.equals(firstPwd, secondPwd) && // 两个新密码相同
				(oldPwd == null
						&& VerifyCodeBean.equals(
								userService.getVerifyCode(),
								userInfo.getPhone(), verifyCode,
								VerifyCodeType.MODIFY_PASSWORD) // 验证码正确
				|| UserService.isSamePassword(userInfo.getPassword(), oldPwd))) // 旧密码匹配

			if (userService.getDriverDao().update(userInfo)) { // 保存数据库
				userInfo.setPassword(firstPwd); // 更新session的用户信息
				path = modifyPasswordSuccessPage; // 修改密码成功页

			}

		return path;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param phone
	 * @param response
	 */
	@RequestMapping("/**/checkPhone.do")
	public void checkPhone(String phone, HttpServletResponse response) {
		// 检查手机，加区号

		// 发送验证码YES,NO,PHONE_ERROR
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
