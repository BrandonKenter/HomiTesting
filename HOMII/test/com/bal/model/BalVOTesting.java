package com.bal.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import oracle.sql.DATE;

class BalVOTesting {
	
	BalVO vo = new BalVO();
	
	@Test
	void test_BalNo() {
		vo.setBal_no(77);
		Integer result = vo.getBal_no();
		assertEquals(77, result);
		
	}
	
	@Test
	void test_MemNo() {
		vo.setMember_no(99);
		Integer result = vo.getMember_no();
		assertEquals(99, result);
		
	}
	
	@Test
	void test_LandNo() {
		vo.setLand_no(100);
		Integer result = vo.getLand_no();
		assertEquals(100, result);
		
	}
	
	@Test
	void test_Type() {
		vo.setType("occupied");;
		String result = vo.getType();
		assertEquals("occupied", result);
		
	}
	
	@Test
	void test_price() {
		vo.setPrice((float) 100);
		Float result = vo.getPrice();
		assertEquals(100, result);
		
	}
	
	@Test
	void test_Crt_dt() {
		java.sql.Date date = new java.sql.Date(2021, 11, 01);
		vo.setCrt_dt(date);
		java.sql.Date result = vo.getCrt_dt();
		assertEquals(date, result);
		
	}

}
