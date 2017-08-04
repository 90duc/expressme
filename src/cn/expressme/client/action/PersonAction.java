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
	 * ��ʾ������Ϣ����
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
	 * ��ʾ������Ϣ����
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

	private static final String modifyInfoFailPage = "redirect:modifyInfoFail.jsp"; // �޸���Ϣʧ��
	private static final String modifyInfoSuccessPage = "redirect:personInfo.xhtm"; // �޸���Ϣ�ɹ�

	/**
	 * �޸ĸ�����Ϣ����
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

		if (Util.isNullOrEmpty(phone) // û���޸��ֻ�����
				|| VerifyCodeBean.equals(vCode, phone, verifyCode,
						VerifyCodeType.MODIFY_PHONE)) { // ����֤��

			ClientBean client = userService.getClientBean();

			// �ֻ���
			if (!Util.isNullOrEmpty(phone))
				client.setPhone(phone);
			// �û���
			if (!Util.isNullOrEmpty(userName))
				client.setUserName(userName);
			// ����
			if (!Util.isNullOrEmpty(city))
				client.setCity(userName);
			// �ֵ�
			if (!Util.isNullOrEmpty(street))
				client.setStreet(street);
			// �ж��Ƿ��ϴ�ͼƬ
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
			if (userDao.update(client)) { // �������ݿ�
				path = modifyInfoSuccessPage;
			}
		}

		return path;
	}

	private static final String modifyPasswordFailPage = "modifyPasswordFail.jsp"; // �޸���Ϣʧ��
	private static final String modifyPasswordSuccessPage = "modifyPasswordSuccess.jsp"; // �޸���Ϣ�ɹ�

	/**
	 * �޸��û�����
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

		String path = modifyPasswordFailPage; // �޸��������ҳ
		if (Objects.equals(firstPwd, secondPwd)// ������������ͬ
				&& (Util.isNullOrEmpty(oldPwd) 
						&& VerifyCodeBean.equals(userService.getVerifyCode(),
								userInfo.getPhone(), verifyCode,VerifyCodeType.MODIFY_PASSWORD) // ��֤����ȷ
				|| UserService.isSamePassword(userInfo.getPassword(), oldPwd))) // ������ƥ��
		{
			userInfo.setPassword(firstPwd);
			if (userService.getClientDao().update(userInfo)) { // �������ݿ�
				// ����session���û���Ϣ
				path = modifyPasswordSuccessPage; // �޸�����ɹ�ҳ

			}
		}
		return path;
	}

	/**
	 * ��֤�ֻ�����
	 * 
	 * @param phone
	 * @param response
	 */
	@RequestMapping("/checkphone.do")
	@ResponseBody
	public String checkPhone(String type, String phone) {
		VerifyCodeType codeType = VerifyCodeType.valueOf(VerifyCodeType.class,
				type);
		// ����ֻ���������
		// if(!VerifyService.isPhone(phone)){
		// System.err.println("�����ֻ��ţ�");
		// return ;
		// }
		if (Util.isNullOrEmpty(phone))
			phone = userService.getClientBean().getPhone();

		// ������֤��YES,NO,PHONE_ERROR
		VerifyCodeBean vCodeBean = new VerifyCodeBean(codeType, phone,
				VerifyService.createVerifyCode());
		userService.getSession().setAttribute(FinalInfo.VERIFY_CODE, vCodeBean);
		System.out.println(vCodeBean.getVerifyCode());
       return "{state:\"YES\"}";
	}

}
