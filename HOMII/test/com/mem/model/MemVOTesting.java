package com.mem.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemVOTesting {
	
	MemVO vo = new MemVO();
	
	@Test
	void test_CrtDate() {
		java.sql.Date date = new java.sql.Date(2021, 11, 01);
		vo.setCrt_dt(date);
		java.sql.Date result = vo.getCrt_dt();
		assertEquals(date, result);
	}
	
	@Test
	void test_CompPic() {
		byte[] pic = "Picture1".getBytes(); 
		vo.setMb_pic(pic);
		byte[] result = vo.getMb_pic();
		assertEquals(pic, result);
	}
	
	@Test
	void test_MemNo() {
		vo.setMember_no(100);
		Integer result = vo.getMember_no();
		assertEquals(100, result);
	}
	
	@Test
	void test_MbName() {
		vo.setMb_name("Kelly");
		String result = vo.getMb_name();
		assertEquals("Kelly", result);
	}
	
	@Test
	void test_Mbmail() {
		String mail = "mem1@wisc.edu";
		vo.setMb_email(mail);
		String result = vo.getMb_email();
		assertEquals(mail, result);
	}
	
	@Test
	void test_Mbpwd() {
		String pwd = "11111";
		vo.setMb_pwd(pwd);
		String result = vo.getMb_pwd();
		assertEquals(pwd, result);
	}
	
	
	@Test
	void test_MbPhone() {
		String phone = "3206785";
		vo.setMb_phone(phone);
		String result = vo.getMb_phone();
		assertEquals(phone, result);
	}
	
	@Test
	void test_MbAdress() {
		String address = "State St";
		vo.setMb_address(address);
		String result = vo.getMb_address();
		assertEquals(address, result);
	}
	
	@Test
	void test_MbAction() {
		String action = "Move out";
		vo.setAction(action);
		String result = vo.getAction();
		assertEquals(action, result);
	}
	
	@Test
	void test_MbStatus() {
		String sta = "procced";
		vo.setStatus(sta);
		String result = vo.getStatus();
		assertEquals(sta, result);
	}
	
	@Test
	void test_MbShip() {
		String mship = "yes";
		vo.setMembership(mship);
		String result = vo.getMembership();
		assertEquals(mship, result);
	}
	
	@Test
	void test_MbBal() {
		float bal = 1000;
		vo.setBalance(bal);
		float result = vo.getBalance();
		assertEquals(bal, result);
	}

}
