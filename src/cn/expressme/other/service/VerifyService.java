package cn.expressme.other.service;

import org.hibernate.loader.custom.Return;

import cn.expressme.client.bean.ClientVerifyBean;
import cn.expressme.driver.bean.DriverVerifyBean;
import cn.expressme.other.res.VerifyFile;
import cn.expressme.other.util.IDCard;
import cn.expressme.other.util.Util;

public class VerifyService {

	public static boolean isLegal(ClientVerifyBean vBean) {
		return IDCard.isValidate(vBean.getCardID())
				&& vBean.getRealName().length() > 1;

	}

	public static boolean isLegal(DriverVerifyBean vBean) {

		return IDCard.isValidate(vBean.getCardID())
				&& vBean.getRealName().length() > 1;
	}

	/**
	 * 生成验证码
	 * @return
	 */
	public static String createVerifyCode() {
		String result = "";
		String strTable = "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		int index;
		for (int i = 0; i < 4; i++) {
			index = (int)(Math.random() * len);
			result += strTable.charAt(index);
		}

		return result;

	}
    /**
     * 校验手机
     * @param phone
     * @return
     */
	public static boolean isPhone(String phone) {
		//校验手机
		if (Util.isNullOrEmpty(phone)) {
			return false;
		}
		return true;
	}
}
