package com.comp.model;

import java.sql.Date;

public class CompVO implements java.io.Serializable{
	private Integer complaint_no;
	private Integer member_no;
	private String ap_name;
	private String ap_address;
	private String land_name;
	private String case_title;
	private String description;
	private String pubtype;
	private byte[] comp_pic;
	private byte[] comp_vid;
	private Date crt_dt;
	private String status;
	private String response;
	private String priority;
	
	public Integer getComplaint_no() {
		return complaint_no;
	}
	public void setComplaint_no(Integer complaint_no) {
		this.complaint_no = complaint_no;
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
	public String getCase_title() {
		return case_title;
	}
	public void setCase_title(String case_title) {
		this.case_title = case_title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubtype() {
		return pubtype;
	}
	public void setPubtype(String pubtype) {
		this.pubtype = pubtype;
	}
	public byte[] getComp_pic() {
		return comp_pic;
	}
	public void setComp_pic(byte[] comp_pic) {
		this.comp_pic = comp_pic;
	}
	public byte[] getComp_vid() {
		return comp_vid;
	}
	public void setComp_vid(byte[] comp_vid) {
		this.comp_vid = comp_vid;
	}
	public Date getCrt_dt() {
		return crt_dt;
	}
	public void setCrt_dt(Date crt_dt) {
		this.crt_dt = crt_dt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

}
