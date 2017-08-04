package cn.expressme.client.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

import cn.expressme.other.res.VerifyState;

@Entity
@Table(name = "clientVerify")
public class ClientVerifyBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 9109690625969844311L;
	@Id  
	@GenericGenerator(name="generator",strategy="foreign",
	parameters=@Parameter(name="property",value="clientBean"))
	@GeneratedValue(generator="generator")
	private String ID;
	private String cardID;
	private String realName;
	
	private String cardPhoto0;
	private String cardPhoto1;
	private String PACPhoto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date verifyTime;
	@Enumerated(EnumType.STRING)
	private VerifyState verifyState = VerifyState.NONE;

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private ClientBean clientBean;
	
	private String location;
	

	public String getID() {
		return ID;
	}
/*
	public void setID(String iD) {
		ID = iD;
	}
*/
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

	/*public void setVerifyState(String verify) {
		client.setVerify(verify);
	}*/
/*
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
    public void setVerify(VerifyState verify) {
	      this.client.setVerify(verify);
    }*/
	public ClientBean getClient() {
		return clientBean;
	}
    /**
     * 与客户id关联
     * @param client
     */
	public void setClient(ClientBean client) {
		
		this.clientBean = client;
	}
	public String getCardPhoto0() {
		return cardPhoto0;
	}
	public void setCardPhoto0(String cardPhoto0) {
		this.cardPhoto0 = cardPhoto0;
	}
	public String getCardPhoto1() {
		return cardPhoto1;
	}
	public void setCardPhoto1(String cardPhoto1) {
		this.cardPhoto1 = cardPhoto1;
	}
	public String getPACPhoto() {
		return PACPhoto;
	}
	public void setPACPhoto(String pACPhoto) {
		PACPhoto = pACPhoto;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	public void setVerifyState(VerifyState verifyState) {
		this.verifyState = verifyState;
	}
	public VerifyState getVerifyState() {
		
		return verifyState;
	}
	public void setLocation(String city) {

         this.location=city;
		
	}
	public String getLocation() {
		return location;
	}
	
	public boolean isVerified() {
		return verifyState==VerifyState.VERIFYED;
	}
	public boolean isVerifying() {
		return verifyState==VerifyState.VERIFYING;
	}
}
