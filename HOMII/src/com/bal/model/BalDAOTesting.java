package com.bal.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.apt.model.AptDAO;
import com.apt.model.AptVO;

class BalDAOTesting {
	BalService balSvc = new BalService();
	BalDAO dao;
	BalVO balvo;

	@Test
	void test_insert() {
		balSvc.addBal(1, 5, "0", 3.0f);
		dao = new BalDAO();
		balvo = new BalVO();
		balvo.setMember_no(1);
		balvo.setLand_no(5);
		balvo.setType("0");
		balvo.setPrice(3.0f);

		int num = dao.insert(balvo);
		assertEquals(1,num);
	}
	@Test
	void test_getAllBalByMemNo() {
		List<BalVO> balvo = balSvc.getAllBalByMemNo(1);
		assertEquals(3,balvo.get(0).getPrice());
		assertEquals(2,balvo.get(1).getPrice());
		assertEquals(4,balvo.get(2).getPrice());
	}
	@Test
	void test_getAllBalByLandNo() {
		List<BalVO> balvo = balSvc.getAllBalByLandNo(5);
		assertEquals(3,balvo.get(0).getPrice());
		assertEquals(2,balvo.get(1).getPrice());
		assertEquals(4,balvo.get(2).getPrice());
	}


}
