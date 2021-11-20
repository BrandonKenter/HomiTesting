package com.apt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


class AptDAOTesting {
	AptService aptSvc = new AptService();
	AptDAO dao;
	AptVO aptvo;
	
	@Test
	void test_insert() {
		aptSvc.addApt(1, "House", "University Ave", null, null, null);
		dao = new AptDAO();
		aptvo = new AptVO();
		aptvo.setMember_no(1);
		aptvo.setAp_name("House");
		aptvo.setAp_address("University Ave");
		int num = dao.insert(aptvo);
		assertEquals(1,num);
	}
	@Test
	void test_findByPrimaryKey() {

		AptVO aptvo = aptSvc.getOneApt(1);
		assertEquals("300 University Ave",aptvo.getAp_address());
		assertEquals("UWM",aptvo.getAp_name());
		assertEquals(1,aptvo.getMember_no());
	}
	@Test
	void test_findByPrimaryKeyByApName() {
		AptVO aptvo = aptSvc.getOneAptByApName("WalMart");
		assertEquals("42000 University Ave",aptvo.getAp_address());
		assertEquals(1,aptvo.getMember_no());
	}
	@Test
	void test_getAllByMemNo() {
		List<AptVO> aptvo = aptSvc.getAllAptByMemNo(1);
		assertEquals("UWM",aptvo.get(0).getAp_name());
		assertEquals("WalMart",aptvo.get(1).getAp_name());
	}
	
	@Test
	void test_getPic() {
		AptVO aptvo = aptSvc.getOnePicByApName("WalMart");
		assertNotEquals(null, aptvo.getAp_pic1());
	}
	
	

}
