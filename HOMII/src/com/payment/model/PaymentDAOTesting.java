package com.payment.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.apt.model.AptDAO;
import com.apt.model.AptVO;

class PaymentDAOTesting {
	PaymentService pySvc = new PaymentService();
	PaymentDAO dao;
	PaymentVO pyvo;

	@Test
	void test_insert() {
		pySvc.addCard(2, "1111222233334444", "Gary", "5", "33", "101");
		dao = new PaymentDAO();
		pyvo = new PaymentVO();
		pyvo.setMember_no(2);
		pyvo.setCard_no("1111222233334444");
		pyvo.setCard_name("Gary");
		pyvo.setExp_mon("5");
		pyvo.setExp_year("33");
		pyvo.setCsc("101");
		int num = dao.insert(pyvo);
		assertEquals(1,num);
	}
	
	@Test
	void test_delete() {
		//need to ++
		boolean res = pySvc.deleteCard(11);
		assertEquals(false,res);
	}
	
	@Test
	void test_getAllByMemNo() {
		List<PaymentVO> pyvo = pySvc.getAllCard(1);
		assertEquals("Gary",pyvo.get(0).getCard_name());
		assertEquals("8084183920340853",pyvo.get(0).getCard_no());
		assertEquals(1,pyvo.get(0).getMember_no());
		assertEquals("3",pyvo.get(0).getExp_mon());
		assertEquals("23",pyvo.get(0).getExp_year());
		assertEquals("334",pyvo.get(0).getCsc());
	}

}
