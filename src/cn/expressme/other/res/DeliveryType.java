package cn.expressme.other.res;

public enum DeliveryType {
    NONE("����"),
    CAR("�γ�"),
    NON_MOTOR("�ǻ�����");

    private String value;
    private  DeliveryType(String value) {
		this.value=value;
	}
	public String getValue() {
	
		return value;
	}
    
}
