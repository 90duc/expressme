package cn.expressme.other.res;

public enum VerifyState {
       NONE("δʵ��"),      
       VERIFYING("����ʵ��"),
       VERIFYED("��ʵ��");

    private String value;

	private VerifyState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
       
       
}
