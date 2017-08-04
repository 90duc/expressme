package cn.expressme.other.res;

public enum DeliveryType {
    NONE("不限"),
    CAR("轿车"),
    NON_MOTOR("非机动车");

    private String value;
    private  DeliveryType(String value) {
		this.value=value;
	}
	public String getValue() {
	
		return value;
	}
    
}
