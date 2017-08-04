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

	private static final String modifyInfoFailPage = "modifyInfoFail.jsp"; // �޸���Ϣʧ��
	private static final String modifyInfoSuccessPage = "modifyInfoSuccess.jsp"; // �޸���Ϣ�ɹ�

	@Autowired
	private UserService userService;

	/**
	 * �޸ĸ�����Ϣ����
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

		if (phone == null // û���޸��ֻ�����
				|| VerifyCodeBean.equals(vCode, phone,
						verifyCode, VerifyCodeType.MODIFY_PHONE)) {

			DriverBean driver = userService.getDriverBean();
			
			//�ֻ���
			if(!Util.isNullOrEmpty(phone))
				driver.setPhone(phone);
			//�û���
			if(!Util.isNullOrEmpty(userName))
				driver.setUserName(userName);
			//����
			if(!Util.isNullOrEmpty(city))
				driver.setCity(userName);
			//�ֵ�
			if(!Util.isNullOrEmpty(street))
				driver.setStreet(street);
			// �ж��Ƿ��ϴ�ͼƬ
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

			if (userInfoDao.update(driver)) { // �������ݿ�				
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
	@RequestMapping("/**/MPPwd.do")
	public String modifyPassword(String verifyCode, String oldPwd,
			String firstPwd, String secondPwd) {

		DriverBean userInfo = userService.getDriverBean();

		String path = modifyPasswordFailPage; // �޸��������ҳ
		if (Objects.equals(firstPwd, secondPwd) && // ������������ͬ
				(oldPwd == null
						&& VerifyCodeBean.equals(
								userService.getVerifyCode(),
								userInfo.getPhone(), verifyCode,
								VerifyCodeType.MODIFY_PASSWORD) // ��֤����ȷ
				|| UserService.isSamePassword(userInfo.getPassword(), oldPwd))) // ������ƥ��

			if (userService.getDriverDao().update(userInfo)) { // �������ݿ�
				userInfo.setPassword(firstPwd); // ����session���û���Ϣ
				path = modifyPasswordSuccessPage; // �޸�����ɹ�ҳ

			}

		return path;
	}

	/**
	 * ��֤�ֻ�����
	 * 
	 * @param phone
	 * @param response
	 */
	@RequestMapping("/**/checkPhone.do")
	public void checkPhone(String phone, HttpServletResponse response) {
		// ����ֻ���������

		// ������֤��YES,NO,PHONE_ERROR
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
