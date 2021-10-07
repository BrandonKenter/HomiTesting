package com.bal.model;

import java.util.List;

import com.apt.model.AptVO;

public class BalService {
	
	private BalDAO_interface dao;
	
	public BalService() {
		dao = new BalDAO();
	}
	
	public BalVO addBal(Integer member_no, Integer land_no, String type, Float price) {

		
		BalVO balVO = new BalVO();
		balVO.setMember_no(member_no);
		balVO.setLand_no(land_no);
		balVO.setType(type);
		balVO.setPrice(price);

		dao.insert(balVO);
		
		return balVO;
	}
	public List<BalVO> getAllBalByMemNo(Integer member_no){
		return dao.getAllByMemNo(member_no);
	}
}
