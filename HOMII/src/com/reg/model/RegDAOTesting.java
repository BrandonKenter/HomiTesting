package com.reg.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;


class RegDAOTesting {
	RegService regSvc = new RegService();
	RegDAO dao;
	RegVO regvo;

	@Test
	void test_insert() {
		Date start_dt= new java.sql.Date(System.currentTimeMillis());
		Date end_dt = new java.sql.Date(System.currentTimeMillis());
		regSvc.addReg(7, "WalMart", "42000 University Ave", "Henry", start_dt, end_dt);
		dao = new RegDAO();
		regvo = new RegVO();
		regvo.setMember_no(1);
		regvo.setAp_name("WalMart");
		regvo.setAp_address("42000 University Ave");
		regvo.setLand_name("Henry");
		regvo.setStart_dt(start_dt);
		regvo.setEnd_dt(end_dt);
		int num = dao.insert(regvo);
		assertEquals(1,num);
	}
	
	@Test
	void test_findByPrimaryKey() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date start_date = df.parse("2021-10-01 00:00:00");
		java.sql.Date start_dt= new java.sql.Date(start_date.getTime());
		java.util.Date end_date = df.parse("2022-10-01 00:00:00");
		java.sql.Date end_dt= new java.sql.Date(end_date.getTime());
		RegVO regvo = regSvc.getOneRegister(2);
		assertEquals("WalMart",regvo.getAp_name());
		assertEquals("42000 University Ave",regvo.getAp_address());
		assertEquals("Henry",regvo.getLand_name());
		assertEquals(2,regvo.getMember_no());
		assertEquals(start_dt,regvo.getStart_dt());
		assertEquals(end_dt,regvo.getEnd_dt());
	}
	@Test
	void test_findByPrimaryKeyByRegNo() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date start_date = df.parse("2021-10-01 00:00:00");
		java.sql.Date start_dt= new java.sql.Date(start_date.getTime());
		java.util.Date end_date = df.parse("2022-10-01 00:00:00");
		java.sql.Date end_dt= new java.sql.Date(end_date.getTime());
		RegVO regvo = regSvc.getOneRegisterByRegNo(2);
		assertEquals("WalMart",regvo.getAp_name());
		assertEquals("42000 University Ave",regvo.getAp_address());
		assertEquals("Henry",regvo.getLand_name());
		assertEquals(2,regvo.getMember_no());
		assertEquals(start_dt,regvo.getStart_dt());
		assertEquals(end_dt,regvo.getEnd_dt());
	}
	@Test
	void test_getAllByMemNo() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date start_date = df.parse("2021-10-01 00:00:00");
		java.sql.Date start_dt= new java.sql.Date(start_date.getTime());
		java.util.Date end_date = df.parse("2022-10-01 00:00:00");
		java.sql.Date end_dt= new java.sql.Date(end_date.getTime());
		List<RegVO> regvo = regSvc.getAllRegisterByMemNo(2);
		assertEquals("WalMart",regvo.get(0).getAp_name());
		assertEquals("42000 University Ave",regvo.get(0).getAp_address());
		assertEquals("Henry",regvo.get(0).getLand_name());
		assertEquals(2,regvo.get(0).getMember_no());
		assertEquals(start_dt,regvo.get(0).getStart_dt());
		assertEquals(end_dt,regvo.get(0).getEnd_dt());
	}
	@Test
	void test_getAllByLandName() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date start_date = df.parse("2021-10-01 00:00:00");
		java.sql.Date start_dt= new java.sql.Date(start_date.getTime());
		java.util.Date end_date = df.parse("2022-10-01 00:00:00");
		java.sql.Date end_dt= new java.sql.Date(end_date.getTime());
		List<RegVO> regvo = regSvc.getAllRegisterByLandName("Henry");
		assertEquals("WalMart",regvo.get(1).getAp_name());
		assertEquals("42000 University Ave",regvo.get(1).getAp_address());
		assertEquals("Henry",regvo.get(1).getLand_name());
		assertEquals(2,regvo.get(1).getMember_no());
		assertEquals(start_dt,regvo.get(1).getStart_dt());
		assertEquals(end_dt,regvo.get(1).getEnd_dt());
	}
	@Test
	void test_getAllByLandNameWithApproval() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date start_date = df.parse("2021-10-01 00:00:00");
		java.sql.Date start_dt= new java.sql.Date(start_date.getTime());
		java.util.Date end_date = df.parse("2022-10-01 00:00:00");
		java.sql.Date end_dt= new java.sql.Date(end_date.getTime());
		List<RegVO> regvo = regSvc.getAllRegisterByLandNameWithApproval("Henry");
		assertEquals("WalMart",regvo.get(1).getAp_name());
		assertEquals("42000 University Ave",regvo.get(1).getAp_address());
		assertEquals("Henry",regvo.get(1).getLand_name());
		assertEquals(2,regvo.get(1).getMember_no());
		assertEquals(start_dt,regvo.get(1).getStart_dt());
		assertEquals(end_dt,regvo.get(1).getEnd_dt());
	}
	@Test
	void test_getAllByApName() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date start_date = df.parse("2021-10-01 00:00:00");
		java.sql.Date start_dt= new java.sql.Date(start_date.getTime());
		java.util.Date end_date = df.parse("2022-10-01 00:00:00");
		java.sql.Date end_dt= new java.sql.Date(end_date.getTime());
		List<RegVO> regvo = regSvc.getAllRegisterByApName("WalMart");
		assertEquals("WalMart",regvo.get(0).getAp_name());
		assertEquals("42000 University Ave",regvo.get(0).getAp_address());
		assertEquals("Henry",regvo.get(0).getLand_name());
		assertEquals(2,regvo.get(0).getMember_no());
		assertEquals(start_dt,regvo.get(0).getStart_dt());
		assertEquals(end_dt,regvo.get(0).getEnd_dt());
	}
	@Test
	void test_updateRes() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date start_date = df.parse("2021-10-01 00:00:00");
		java.sql.Date start_dt= new java.sql.Date(start_date.getTime());
		java.util.Date end_date = df.parse("2022-10-01 00:00:00");
		java.sql.Date end_dt= new java.sql.Date(end_date.getTime());
		regSvc.updateResponse(5,"WalMart", "42000 University Ave", start_dt, end_dt, "1");
		dao = new RegDAO();
		regvo = new RegVO();
		regvo.setMember_no(1);
		regvo.setAp_name("WalMart");
		regvo.setAp_address("42000 University Ave");
		regvo.setLand_name("Henry");
		regvo.setStart_dt(start_dt);
		regvo.setEnd_dt(end_dt);
		regvo.setStatus("1");
		int num = dao.insert(regvo);
		assertEquals(1,num);
	}

}
