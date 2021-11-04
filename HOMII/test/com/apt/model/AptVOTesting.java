package com.apt.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AptVOTesting {
	AptVO apttest = new AptVO();
	
	@Test
	void test_apt_no() {
		apttest.setAp_no(10);
		Integer result = apttest.getAp_no();
		assertEquals(10, result);
	}
	
	@Test
	void test_member_no() {
		apttest.setMember_no(900);
		Integer result = apttest.getMember_no();
		assertEquals(900,result);
	} 
	
	@Test
	void test_apt_pic1() {
		byte[] pic = "picture1".getBytes();
		apttest.setAp_pic1(pic);
		byte[] result = apttest.getAp_pic1();
		assertEquals(pic,result);
	}
	
	@Test
	void test_apt_pic2() {
		byte[] pic = "picture2".getBytes();
		apttest.setAp_pic2(pic);
		byte[] result = apttest.getAp_pic2();
		assertEquals(pic,result);
	}
	
	@Test
	void test_apt_pic3() {
		byte[] pic = "picture3".getBytes();
		apttest.setAp_pic3(pic);
		byte[] result = apttest.getAp_pic3();
		assertEquals(pic,result);
	}
	
	
	@Test
	void test_apt_name() {
		apttest.setAp_name("House");
		String result = apttest.getAp_name();
		assertEquals("House",result);
	}
	
	@Test
	void test_apt_address() {
		apttest.setAp_address("Wilson St");
		String result = apttest.getAp_address();
		assertEquals("Wilson St",result);
	}
	
	@Test
	void test_apt_rating() {
		apttest.setRating(3);
		float result = apttest.getRating();
		assertEquals(3,result);
	}
	
	
	
}
