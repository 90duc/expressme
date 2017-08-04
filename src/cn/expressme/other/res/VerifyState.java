package cn.expressme.other.res;

public enum VerifyState {
       NONE("未实名"),      
       VERIFYING("正在实名"),
       VERIFYED("已实名");

    private String value;

	private VerifyState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
       
       
}
