package cn.expressme.driver.bean;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import cn.expressme.client.bean.ClientBean;
import cn.expressme.other.res.VerifyState;
import cn.expressme.other.util.Util;
@Entity
@Table(name = "driverVerify")
public class DriverVerifyBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3847891745591558264L;
	@Id  
	@GenericGenerator(name="generator",strategy="foreign",
	parameters=@Parameter(name="property",value="driver"))
	@GeneratedValue(generator="generator")
	private String ID;
	private String cardID;
	private String realName;
	// private String city;
	// private String street;
	private byte[] cardPhoto0;
	private byte[] cardPhoto1;
	private byte[] PACPhoto;
	
	
	private String carNumber;
	private String carOwner;
	private String driverPhoto0;
	private String driverPhoto1;
	private String drivingPhoto0;
	private String drivingPhoto1;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date verifyTime;
	@Enumerated(EnumType.STRING)
	private VerifyState verifyState = VerifyState.NONE;
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private DriverBean driver;
	
	/*
	public String getDriverPhoto0() {
		return Util.toBase64Url(driverPhoto0);
	}
	public void setDriverPhoto0(byte[] driverPhoto0) {
		this.driverPhoto0 = driverPhoto0;
	}
	public String getDriverPhoto1() {
		return Util.toBase64Url(driverPhoto1);
	}
	public void setDriverPhoto1(byte[] driverPhoto1) {
		this.driverPhoto1 = driverPhoto1;
	}
	public String getDrivingPhoto0() {
		return Util.toBase64Url(drivingPhoto0);
	}
	public void setDrivingPhoto0(byte[] drivingPhoto0) {
		this.drivingPhoto0 = drivingPhoto0;
	}
	public String getDrivingPhoto1() {
		return Util.toBase64Url(drivingPhoto1);
	}
	public void setDrivingPhoto1(byte[] drivingPhoto1) {
		this.drivingPhoto1 = drivingPhoto1;
	}
    */
	
	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCardPhoto0() {
		return Util.toBase64Url(cardPhoto0);
	}
	public void setCardPhoto0(byte[] cardPhoto0) {
		this.cardPhoto0 = cardPhoto0;
	}
	public String getCardPhoto1() {
		return Util.toBase64Url(cardPhoto1);
	}
	public void setCardPhoto1(byte[] cardPhoto1) {
		this.cardPhoto1 = cardPhoto1;
	}
	public String getPACPhoto() {
		return Util.toBase64Url(PACPhoto);
	}
	public void setPACPhoto(byte[] pACPhoto) {
		PACPhoto = pACPhoto;
	}
	public DriverBean getDriver() {
		return driver;
	}
	public void setDriver(DriverBean dBean) {
		this.driver = dBean;
	}
	public String getID() {
		return ID;
	}
	public void setVerify(VerifyState verify) {
		this.verifyState=verify;		
	}

	public String getDriverPhoto0() {
		return driverPhoto0;
	}

	public void setDriverPhoto0(String driverPhoto0) {
		this.driverPhoto0 = driverPhoto0;
	}

	public String getDriverPhoto1() {
		return driverPhoto1;
	}

	public void setDriverPhoto1(String driverPhoto1) {
		this.driverPhoto1 = driverPhoto1;
	}

	public String getDrivingPhoto0() {
		return drivingPhoto0;
	}

	public void setDrivingPhoto0(String drivingPhoto0) {
		this.drivingPhoto0 = drivingPhoto0;
	}

	public String getDrivingPhoto1() {
		return drivingPhoto1;
	}

	public void setDrivingPhoto1(String drivingPhoto1) {
		this.drivingPhoto1 = drivingPhoto1;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public VerifyState getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(VerifyState verifyState) {
		this.verifyState = verifyState;
	}
	
}
