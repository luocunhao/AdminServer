package com.pulan.model;

public class WkUser {
	private String userid;
	private String username;
	private String sex;
	private String pic;
	private String idcard;
	private String phone;
	private String idka;
	private String vip;
	private String faceId;
	public WkUser(){}
	public WkUser(String userid, String username, String sex, String pic, String idcard, String phone, String idka,
			String vip,String faceId) {
		this.userid = userid;
		this.username = username;
		this.sex = sex;
		this.pic = pic;
		this.idcard = idcard;
		this.phone = phone;
		this.idka = idka;
		this.vip = vip;
		this.faceId=faceId;
	}
	public WkUser(String username, String sex, String pic, String idcard, String phone, String idka,
			String vip,String faceId) {
		this.username = username;
		this.sex = sex;
		this.pic = pic;
		this.idcard = idcard;
		this.phone = phone;
		this.idka = idka;
		this.vip = vip;
		this.faceId=faceId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdka() {
		return idka;
	}
	public void setIdka(String idka) {
		this.idka = idka;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	
	public String getFaceId() {
		return faceId;
	}
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	@Override
	public String toString() {
		return "WkUser [userid=" + userid + ", username=" + username + ", sex=" + sex + ", pic=" + pic + ", idcard="
				+ idcard + ", phone=" + phone + ", idka=" + idka + ", vip=" + vip + ", faceId=" + faceId + "]";
	}
	
	
}
