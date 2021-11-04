package com.apt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.*;
import org.junit.jupiter.api.Test;

class AptDAOTestings{
	
	private static DataSource ds = null;
	
	private static final String INSERT_STMT = "INSERT INTO apartment (member_no, ap_name, ap_address, ap_pic1, ap_pic2, ap_pic3) VALUES (?, ?, ?, ?, ?, ?)";

	
//	private AptVO vo2 = new AptVO();
//	private AptVO vo3 = new AptVO();
	
	
	@BeforeClass
	void setup() throws NamingException, SQLException {
		
		
		
	}
	
	@Test
	void test() throws ClassNotFoundException, SQLException {
		
		
		AptDAO dao = new AptDAO();
		AptVO vo1 = new AptVO();
		
		vo1.setAp_name("House");
		vo1.setAp_address("University Ave");
		vo1.setMember_no(101);
		dao.insert(vo1);
		
		AptVO sample = dao.findByPrimaryKey(101);
		assertEquals(sample, vo1);
	}
	
//	@Test
//	void test_findByPK() {
//		
//		AptVO result1 = dao.findByPrimaryKey(101);
//		assertEquals(vo1, result1);
//		
//		AptVO result2 = dao.findByPrimaryKey(102);
//		assertEquals(vo2, result2);
//		
//		AptVO result3 = dao.findByPrimaryKey(103);
//		assertEquals(vo3, result3);
//		
//	}
//	
//	@Test
//	void test_findByPKApName() {
//		AptVO result1 = dao.findByPrimaryKeyByApName("House");
//		assertEquals(vo1, result1);
//		
//		AptVO result2 = dao.findByPrimaryKeyByApName("Lucky");
//		assertEquals(vo2, result2);
//		
//		AptVO result3 = dao.findByPrimaryKeyByApName("Jamess");
//		assertEquals(vo3, result3);
//	}
//	
//	@Test
//	void test_getPicFrApName() {
//		AptVO result1 = dao.getOnePicByApName("House");
//		assertEquals(vo1, result1);
//		
//		AptVO result2 = dao.getOnePicByApName("Lucky");
//		assertEquals(vo2, result2);
//		
//		AptVO result3 = dao.getOnePicByApName("Jamess");
//		assertEquals(vo3, result3);
//	}

	
	
//	vo1.setAp_no(101);
//	vo1.setAp_name("House");
//	vo1.setAp_address("University Ave");
//	vo1.setMember_no(101);
//	vo1.setRating(1);
//	dao.insert(vo1);
//	
//	vo2.setAp_no(102);
//	vo2.setAp_name("Lucky");
//	vo2.setAp_address("Dayton Ave");
//	vo2.setMember_no(102);
//	vo2.setRating(2);
//	dao.insert(vo2);
//	
//	vo3.setAp_no(103);
//	vo3.setAp_name("James");
//	vo3.setAp_address("State Ave");
//	vo3.setMember_no(103);
//	vo3.setRating(3);
//	dao.insert(vo3);
}
