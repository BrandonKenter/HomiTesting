package com.reg.model;

import java.sql.Date;

public class RegVO {
	private Integer reg_no;
	private Integer member_no;
	private String ap_name;
	private String ap_address;
	private String land_name;
	private String status;
	private Date start_dt;
	private Date end_dt;
	public Integer getReg_no() {
		return reg_no;
	}
	public void setReg_no(Integer reg_no) {
		this.reg_no = reg_no;
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
	public String getLand_name() {
		return land_name;
	}
	public void setLand_name(String land_name) {
		this.land_name = land_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStart_dt() {
		return start_dt;
	}
	public void setStart_dt(Date start_dt) {
		this.start_dt = start_dt;
	}
	public Date getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(Date end_dt) {
		this.end_dt = end_dt;
	}
	

}
