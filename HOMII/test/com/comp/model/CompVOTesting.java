package com.comp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CompVOTesting {
	
	CompVO vo = new CompVO();
	
	@Test
	void test_ComNo() {
		vo.setComplaint_no(101);
		Integer result = vo.getComplaint_no();
		assertEquals(101, result);
	}
	
	@Test
	void test_MemNo() {
		vo.setMember_no(100);
		Integer result = vo.getMember_no();
		assertEquals(100, result);
	}
	
	@Test
	void test_Apname() {
		vo.setAp_name("House");
		String result = vo.getAp_name();
		assertEquals("House", result);
	}
	
	@Test
	void test_ApAddress() {
		vo.setAp_address("State St");
		String result = vo.getAp_address();
		assertEquals("State St", result);
	}
	
	@Test
	void test_LandName() {
		vo.setLand_name("Rose");
		String result = vo.getLand_name();
		assertEquals("Rose", result);
	}
	
	@Test
	void test_CaseTitle() {
		vo.setCase_title("Leak");;
		String result = vo.getCase_title();
		assertEquals("Leak", result);
	}
	
	@Test
	void test_Descri() {
		vo.setDescription("Broken sink");
		String result = vo.getDescription();
		assertEquals("Broken sink", result);
	}
	
	@Test
	void test_PubType() {
		vo.setPubtype("A");
		String result = vo.getPubtype();
		assertEquals("A", result);
	}
	
	@Test
	void test_CompPic() {
		byte[] pic = "Picture1".getBytes(); 
		vo.setComp_pic(pic);
		byte[] result = vo.getComp_pic();
		assertEquals(pic, result);
	}
	
	@Test
	void test_COmpVid() {
		byte[] vid = "Video".getBytes(); 
		vo.setComp_vid(vid);
		byte[] result = vo.getComp_vid();
		assertEquals(vid, result);
	}
	
	@Test
	void test_Status() {
		vo.setStatus("Processed");
		String result = vo.getStatus();
		assertEquals("Processed", result);
	}
	
	@Test
	void test_Res() {
		vo.setResponse("In Progress");
		String result = vo.getResponse();
		assertEquals("In Progress", result);
	}
	
	@Test
	void test_Prioritgy() {
		vo.setPriority("5");
		String result = vo.getPriority();
		assertEquals("5", result);
	}
	

}
