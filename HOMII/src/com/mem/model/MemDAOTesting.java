package com.mem.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.payment.model.PaymentDAO;
import com.payment.model.PaymentVO;
import com.reg.model.RegVO;

class MemDAOTesting {
	MemService memSvc = new MemService();
	MemDAO dao;
	MemVO memvo;
	
	@Test
	void test_insert() {
		int min = 1;
        int max = 100;
        int randomNumber = (int) (Math.random()*(max-min)) + min;
		//memSvc.addMem("Eason", "mem"+randomNumber+"@wisc.edu", "1111", null, "1111", "uwm", "1");
		dao = new MemDAO();
		memvo = new MemVO();
		memvo.setMb_name("Eason");
		memvo.setMb_email("mem"+ (randomNumber++) +"@wisc.edu");
		memvo.setMb_pwd("1111");
		memvo.setMb_phone("1111");
		memvo.setMb_address("uwm");
		memvo.setMembership("1");
		int num = dao.insert(memvo);
		assertEquals(1,num);
	}
	@Test
	void test_findByPrimaryKey()  {
		MemVO memvo = memSvc.getOneMem(1);
		assertEquals("Gary",memvo.getMb_name());
		assertEquals("mem1@wisc.edu",memvo.getMb_email());
		assertEquals("1111",memvo.getMb_pwd());
		assertEquals("2123456789",memvo.getMb_phone());
		assertEquals("4000 University Ave",memvo.getMb_address());
		assertEquals("1",memvo.getStatus());
		assertEquals("0",memvo.getMembership());
		assertEquals(1.0f,memvo.getBalance());
	}
	@Test
	void test_findByPrimaryKeyByMbName() {
		MemVO memvo = memSvc.getOneMem("Gary");
		assertEquals("Gary",memvo.getMb_name());
		assertEquals("mem1@wisc.edu",memvo.getMb_email());
		assertEquals("1111",memvo.getMb_pwd());
		assertEquals("2123456789",memvo.getMb_phone());
		assertEquals("4000 University Ave",memvo.getMb_address());
		assertEquals("1",memvo.getStatus());
		assertEquals("0",memvo.getMembership());
		assertEquals(1.0f,memvo.getBalance());
	}
	@Test
	void test_getAll() {
		List<MemVO> memvo = memSvc.getAll();
		assertEquals("Gary",memvo.get(0).getMb_name());
		assertEquals("mem1@wisc.edu",memvo.get(0).getMb_email());
		assertEquals("1111",memvo.get(0).getMb_pwd());
		assertEquals("2123456789",memvo.get(0).getMb_phone());
		assertEquals("4000 University Ave",memvo.get(0).getMb_address());
		assertEquals("1",memvo.get(0).getStatus());
		assertEquals("0",memvo.get(0).getMembership());
		assertEquals(1.0f,memvo.get(0).getBalance());

	}
	@Test
	void test_updatePwd() {
		memSvc.updatePwd(1, "1111");
		dao = new MemDAO();
		memvo = new MemVO();
		memvo.setMember_no(1);
		memvo.setMb_pwd("1111");
		int num = dao.updatePwd(memvo);
		assertEquals(1,num);
	}
	
	@Test
	void test_login_check() {
		MemVO memvo = memSvc.loginCheck("mem1@wisc.edu", "1111");
		assertEquals("Gary",memvo.getMb_name());
		assertEquals("mem1@wisc.edu",memvo.getMb_email());
		assertEquals("1111",memvo.getMb_pwd());
		assertEquals("2123456789",memvo.getMb_phone());
		assertEquals("4000 University Ave",memvo.getMb_address());
		assertEquals("1",memvo.getStatus());
		assertEquals("0",memvo.getMembership());
		assertEquals(1.0f,memvo.getBalance());
	}
	
	@Test
	void test_account_activate() {
		int num = memSvc.accountActivate("mem1@wisc.edu");
		assertEquals(0,num);
	}
	@Test
	void test_emailCheck() {
		MemVO memvo = memSvc.emailCheck("mem10000@wisc.edu");
	}
	
	@Test
	void test_getPassword() {
		MemVO memvo = memSvc.getPassword("mem1@wisc.edu");
		assertEquals("Gary",memvo.getMb_name());
		assertEquals("mem1@wisc.edu",memvo.getMb_email());
		assertEquals("1111",memvo.getMb_pwd());
		assertEquals("2123456789",memvo.getMb_phone());
		assertEquals("4000 University Ave",memvo.getMb_address());
		assertEquals("0",memvo.getStatus());
//		assertEquals("0",memvo.getMembership());
//		assertEquals(1.0f,memvo.getBalance());
	}
	
	@Test
	void test_updateRandomPws() {
		memSvc.updateRandomPws("mem1@wisc.edu", "1111");
		dao = new MemDAO();
		memvo = new MemVO();
		int num = dao.updateRandomPws("mem1@wisc.edu","1111");
		assertEquals(1,num);
	}
	@Test
	void test_updateBalance() {
		memSvc.updateBalance(1, 1.0f);
		dao = new MemDAO();
		memvo = new MemVO();
		memvo.setMember_no(1);
		memvo.setBalance(1.0f);
		int num = dao.updateBalance(memvo);
		assertEquals(1,num);
	}
	
	@Test
	void test_getPic() {
		dao = new MemDAO();
		memvo = dao.getOnePic(1);
		assertEquals(null, memvo.getMb_pic());
		
		
	}
	
	@Test
	void test_updatePic() {
		int i = 22;
		BigInteger bigInt = BigInteger.valueOf(i); 
		byte[] by = bigInt.toByteArray();
		MemVO memvo = memSvc.getOnePic(1);
		memvo.setMb_pic(by);
		System.out.println(memvo.getMb_pic());
		dao.updatePic(new MemVO());
		assertEquals(by, memvo.getMb_pic());
		
	}
}
