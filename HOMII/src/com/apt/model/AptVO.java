package com.apt.model;

public class AptVO {
	private Integer ap_no;
	private Integer member_no;
	private String ap_name;
	private String ap_address;
	private byte[] ap_pic1;
	private byte[] ap_pic2;
	private byte[] ap_pic3;
	private float rating;
	public Integer getAp_no() {
		return ap_no;
	}
	public void setAp_no(Integer ap_no) {
		this.ap_no = ap_no;
	}
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	public String getAp_name() {
		return ap_name;
	}
	public void setAp_name(String ap_name) {
		this.ap_name = ap_name;
	}
	public String getAp_address() {
		return ap_address;
	}
	public void setAp_address(String ap_address) {
		this.ap_address = ap_address;
	}
	public byte[] getAp_pic1() {
		return ap_pic1;
	}
	public void setAp_pic1(byte[] ap_pic1) {
		this.ap_pic1 = ap_pic1;
	}
	public byte[] getAp_pic2() {
		return ap_pic2;
	}
	public void setAp_pic2(byte[] ap_pic2) {
		this.ap_pic2 = ap_pic2;
	}
	public byte[] getAp_pic3() {
		return ap_pic3;
	}
	public void setAp_pic3(byte[] ap_pic3) {
		this.ap_pic3 = ap_pic3;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
}
