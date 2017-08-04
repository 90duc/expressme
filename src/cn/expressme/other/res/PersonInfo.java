package cn.expressme.other.res;

import javax.persistence.Embeddable;

@Embeddable
public class PersonInfo  {
	private String shipper;
	private String shipperPhone;
	private String receiver;
	private String receiverPhone;
	
	public PersonInfo() {
	
	}


	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}


	public String getShipperPhone() {
		return shipperPhone;
	}

	public void setShipperPhone(String shipperPhone) {
		this.shipperPhone = shipperPhone;
	}


	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	
	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

}