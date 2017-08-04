package cn.expressme.other.bean;


import cn.expressme.other.res.VerifyCodeType;

public class VerifyCodeBean{
	
	private String phone;
	private VerifyCodeType type;
	private String verifyCode;
	private long lastTime;

	public VerifyCodeBean(VerifyCodeType type,String phone, String verifyCode) {
		super();
		this.type = type;
		this.phone=phone;
		this.verifyCode = verifyCode;
		this.lastTime = System.currentTimeMillis()+10*60*1000;
	}
	public VerifyCodeBean(VerifyCodeType type, String phone,String verifyCode, long lastTime) {
		super();
		this.type = type;
		this.phone=phone;
		this.verifyCode = verifyCode;
		this.lastTime = lastTime;
	}
    /**
     * ��֤��
     * @return
     */
	public String getVerifyCode() {
		return verifyCode;
	}
	/**
	 * �ж���֤�Ϸ���
	 * @param code  ��֤��
	 * @param type  ��֤�������
	 * @return
	 */
	public static boolean equals(VerifyCodeBean vCode,String phone,String code,VerifyCodeType type){
		return vCode!=null&&
			   vCode.phone.equals(phone)&&
			   vCode.verifyCode.equals(code)&&
			   vCode.type.equals(type)&&
			   System.currentTimeMillis() < vCode.lastTime;
		
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


}
