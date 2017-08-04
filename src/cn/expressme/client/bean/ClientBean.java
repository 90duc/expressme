package cn.expressme.client.bean;

import java.io.Serializable;
import java.util.Date;

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
import cn.expressme.other.util.HibernateUtil;
import cn.expressme.other.util.Util;

@Entity
@Table(name="client")
public class ClientBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6934263644462114963L;

	
	@Id	
	@GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
	private String ID;
	private String phone;
	private String wechat;
	private String userName;
	private String password;
	private String photo;
	//private byte[] photo;
	//private String userID;
	
	private String city;
	private String street;
	
	@Column(name="credit")
	private float avgRang = 10;
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerTime=new Date();
	
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private ClientVerifyBean verifyBean;
	
	//private String name;
	
	public ClientBean() {
		super();
	}

	public ClientBean(String userID) {
		super();
		//this.userID=userID;
		this.phone = userID;  //
		this.userName = userID;
		this.verifyBean=new ClientVerifyBean();
		this.verifyBean.setClient(this);
	}

	public String getUserID() {
		return phone;//userID;
	}
	
	public String getVerifyState() {
		return verifyBean.getVerifyState().getValue();
	}
	
	public String getPhoto() {	
		if(photo==null){
			return FinalInfo.DEFAULT_PHOTO_URL;
		}
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
/*
	public String getPhoto() {	
		if(photo==null){
			return FinalInfo.DEFAULT_PHOTO_URL;
		}
		return Util.toBase64Url(photo);
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
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
	public void setID(String id) {
		this.ID = id;
	}

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

	public ClientVerifyBean getVerify() {
		return verifyBean;
	}

	public VerifyState getVerifyStateE() {
		
		return verifyBean.getVerifyState();
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public ClientVerifyBean getVerifyBean() {
		return verifyBean;
	}

	public void setVerifyBean(ClientVerifyBean verifyBean) {
		this.verifyBean = verifyBean;
	}

	
}
