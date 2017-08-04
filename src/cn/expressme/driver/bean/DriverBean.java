package cn.expressme.driver.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import cn.expressme.other.res.FinalInfo;
import cn.expressme.other.res.VerifyState;
import cn.expressme.other.util.Util;

@Entity
@Table(name = "driver")
public class DriverBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2826937468539335001L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2")
	@GeneratedValue(generator = "generator")
	private String ID;
	private String phone;
	private String wechat;

	private String userName;
	private String password;
	private String photo;

	private String city;
	private String street;

	@Column(name = "credit")
	private float avgRang = 10;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registerTime = new Date();
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@PrimaryKeyJoinColumn
	private DriverVerifyBean verify;

	public DriverBean() {
		super();
	}

	public DriverBean(String userID) {
		super();
		// this.userID=userID;
		this.phone = userID; // 前提是使用手机号作为用户id
		this.userName = userID;
		this.verify = new DriverVerifyBean();
		this.verify.setDriver(this);
	}

	public String getUserID() {
		return phone;// userID;
	}

	// public void setUserID(String userID) {
	// this.userID = userID;
	// }
	public String getPhoto() {
		if (photo == null) {
			return FinalInfo.DEFAULT_PHOTO_URL;
		}
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getVerifyState() {
		return verify.getVerifyState().getValue();
	}

	/*
	 * public String getPhoto() { if(photo==null){
	 * 
	 * } return Util.toBase64Url(photo); }
	 * 
	 * public void setPhoto(byte[] photo) { this.photo = photo; }
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public float getAvgRang() {
		return avgRang;
	}

	public void setAvgRang(float avgRang) {
		this.avgRang = avgRang;
	}

	public String getID() {
		return ID;
	}

	/*
	 * public void setID(String id) { this.ID = id; }
	 * 
	 * 
	 * public void setOld(DriverBean userInfo) {
	 * 
	 * for (Field field : DriverBean.class.getDeclaredFields()) {
	 * 
	 * try { if (field.get(this) == null && field.get(userInfo) != null)
	 * field.set(this, field.get(userInfo)); } catch (IllegalArgumentException |
	 * IllegalAccessException e) { e.printStackTrace(); } } }
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Util.MD5(password);
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public VerifyState getVerifyStateE() {

		return verify.getVerifyState();
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public DriverVerifyBean getVerify() {
		return verify;
	}

	public void setVerify(DriverVerifyBean verify) {
		this.verify = verify;
	}

	@Override
	public int hashCode() {
		if (ID == null)
			return super.hashCode();
		return ID.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof DriverBean)) {
			return false;
		}
		DriverBean driverBean=(DriverBean)obj;
		
		return Objects.equals(ID,driverBean.ID);
	}
}
