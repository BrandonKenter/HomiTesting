package com.payment.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentVOTesting {
	
	PaymentVO vo = new PaymentVO();
	
	@Test
	void test_Payno() {
		Integer no = 4;
		vo.setPay_no(no);
		Integer result = vo.getPay_no();
		assertEquals(no, result);
	}
	
	@Test
	void test_Memno() {
		Integer no = 100;
		vo.setMember_no(no);
		Integer result = vo.getMember_no();
		assertEquals(no, result);
	}
	
	@Test
	void test_Cardno() {
		String no = "44006680";
		vo.setCard_no(no);
		String result = vo.getCard_no();
		assertEquals(no, result);
	}
	
	@Test
	void test_CardName() {
		String name = "Huang";
		vo.setCard_name(name);
		String result = vo.getCard_name();
		assertEquals(name, result);
	}
	
	@Test
	void test_ExpYr() {
		String no = "25";
		vo.setExp_year(no);
		String result = vo.getExp_year();
		assertEquals(no, result);
	}
	
	@Test
	void test_ExpMo() {
		String no = "10";
		vo.setExp_mon(no);
		String result = vo.getExp_mon();
		assertEquals(no, result);
	}
	
	@Test
	void test_Csc() {
		String no = "777";
		vo.setCsc(no);
		String result = vo.getCsc();
		assertEquals(no, result);
	}
	

}
