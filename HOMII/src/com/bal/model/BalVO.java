package com.bal.model;

import java.sql.Date;

public class BalVO {
	private Integer bal_no;
	private Integer member_no;
	private Integer land_no;
	private String type;
	private Float price;
	private Date crt_dt;
	public Integer getBal_no() {
		return bal_no;
	}
	public void setBal_no(Integer bal_no) {
		this.bal_no = bal_no;
	}
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	public Integer getLand_no() {
		return land_no;
	}
	public void setLand_no(Integer land_no) {
		this.land_no = land_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Date getCrt_dt() {
		return crt_dt;
	}
	public void setCrt_dt(Date crt_dt) {
		this.crt_dt = crt_dt;
	}
}
