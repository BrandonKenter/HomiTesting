package com.comp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.apt.model.AptDAO;
import com.apt.model.AptVO;

class CompDAOTesting {
	
	CompService compSvc = new CompService();
	CompDAO dao;
	CompVO compvo;

	@Test
	void test_insert() {
		compSvc.addComp(1, "UWM", "University Ave", "Henry", "Test", "Test", "0", null, null, "5");
		dao = new CompDAO();
		compvo = new CompVO();
		compvo.setMember_no(1);
		compvo.setAp_name("UWM");
		compvo.setAp_address("University Ave");
		compvo.setLand_name("Henry");
		compvo.setCase_title("Test");
		compvo.setDescription("Test");
		compvo.setPubtype("0");
		compvo.setComp_pic(null);
		compvo.setComp_vid(null);
		compvo.setPriority("5");
		int num = dao.insert(compvo);
		assertEquals(1,num);
	}
	@Test
	void test_findByPrimaryKey() {

		CompVO compvo = compSvc.getOneComplaint(1);
		assertEquals("University Ave",compvo.getAp_address());
		assertEquals("UWM",compvo.getAp_name());
		assertEquals(1,compvo.getMember_no());
		assertEquals("Henry",compvo.getLand_name());
		assertEquals("Bathroom is leaking.",compvo.getCase_title());
		assertEquals("Bathroom is always leaking, WTF!",compvo.getDescription());
		assertEquals("0",compvo.getPubtype());
		assertEquals("4",compvo.getPriority());
	}
	@Test
	void test_getAllByMemNo() {
		
		List<CompVO> compvo = compSvc.getAllCompByMemNo(1);
		assertEquals("University Ave",compvo.get(0).getAp_address());
		assertEquals("UWM",compvo.get(0).getAp_name());
		assertEquals(1,compvo.get(0).getMember_no());
		assertEquals("Henry",compvo.get(0).getLand_name());
		assertEquals("Bathroom is leaking.",compvo.get(0).getCase_title());
		assertEquals("Bathroom is always leaking, WTF!",compvo.get(0).getDescription());
		assertEquals("0",compvo.get(0).getPubtype());
		assertEquals("4",compvo.get(0).getPriority());
	}
	@Test
	void test_getAllByLandName() {
		
		List<CompVO> compvo = compSvc.getAllCompByLand("Henry");
		assertEquals("University Ave",compvo.get(0).getAp_address());
		assertEquals("UWM",compvo.get(0).getAp_name());
		assertEquals(1,compvo.get(0).getMember_no());
		assertEquals("Henry",compvo.get(0).getLand_name());
		assertEquals("Bathroom is leaking.",compvo.get(0).getCase_title());
		assertEquals("Bathroom is always leaking, WTF!",compvo.get(0).getDescription());
		assertEquals("0",compvo.get(0).getPubtype());
		assertEquals("4",compvo.get(0).getPriority());
	}
	@Test
	void test_updateRes() {
		
		CompVO compvo = compSvc.updateResponse(2, "0", "OK");
		dao = new CompDAO();
		compvo = new CompVO();
		compvo.setComplaint_no(2);
		compvo.setStatus("0");
		compvo.setResponse("OK");
		int num = dao.updateRes(compvo);
		assertEquals(1,num);
	}

}
