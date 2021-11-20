package com.mem.model;

import java.sql.Date;

public class MemVO implements java.io.Serializable{
	private String action;
	private Integer member_no;
	private String mb_name;
	private String mb_email;
	private String mb_pwd;
	private String mb_phone;
	private String mb_address;
	private Date crt_dt;
	private byte[] mb_pic;
	private String status;
	private String membership;
	private float balance;
	
	
	public MemVO () {};
	
	public Date getCrt_dt() {
		return crt_dt;
	}

	public void setCrt_dt(Date crt_dt) {
		this.crt_dt = crt_dt;
	}

	public byte[] getMb_pic() {
		return mb_pic;
	}

	public void setMb_pic(byte[] mb_pic) {
		this.mb_pic = mb_pic;
	}
	
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public String getMb_pwd() {
		return mb_pwd;
	}
	public void setMb_pwd(String mb_pwd) {
		this.mb_pwd = mb_pwd;
	}
	public String getMb_phone() {
		return mb_phone;
	}
	public void setMb_phone(String mb_phone) {
		this.mb_phone = mb_phone;
	}
	public String getMb_address() {
		return mb_address;
	}
	public void setMb_address(String mb_address) {
		this.mb_address = mb_address;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	

}
