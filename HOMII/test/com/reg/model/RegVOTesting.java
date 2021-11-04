package com.reg.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegVOTesting {
	
	RegVO vo = new RegVO();
	
	
	@Test
	void test_RegNo() {
		Integer no = 99;
		vo.setReg_no(no);
		Integer result = vo.getReg_no();
		assertEquals(no, result);
	}
	
	@Test
	void test_MemNo() {
		Integer no = 101;
		vo.setMember_no(no);
		Integer result = vo.getMember_no();
		assertEquals(no, result);
	}
	
	@Test
	void test_Apname() {
		String name = "Nice";
		vo.setAp_name(name);
		String result = vo.getAp_name();
		assertEquals(name, result);
	}
	
	@Test
	void test_ApAddress() {
		String addr = "State St";
		vo.setAp_address(addr);
		String result = vo.getAp_address();
		assertEquals(addr, result);
	}
	
	@Test
	void test_Landname() {
		String name = "Well";
		vo.setLand_name(name);
		String result = vo.getLand_name();
		assertEquals(name, result);
	}
	
	@Test
	void test_Status() {
		String sta = "processed";
		vo.setStatus(sta);
		String result = vo.getStatus();
		assertEquals(sta, result);
	}
	
	@Test
	void test_StartDate() {
		java.sql.Date date = new java.sql.Date(2021, 11, 02);
		vo.setStart_dt(date);
		java.sql.Date result = vo.getStart_dt();
		assertEquals(date, result);
	}
	
	@Test
	void test_EndDate() {
		java.sql.Date date = new java.sql.Date(2022, 11, 01);
		vo.setEnd_dt(date);
		java.sql.Date result = vo.getEnd_dt();
		assertEquals(date, result);
	}
	
	

}
