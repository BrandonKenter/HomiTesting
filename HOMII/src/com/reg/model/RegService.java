package com.reg.model;

import java.sql.Date;
import java.util.List;

import com.comp.model.CompVO;


public class RegService {
	private RegDAO_interface dao;
	public RegService() {
		dao = new RegDAO();
	}
	
	public RegVO addReg(Integer member_no, String ap_name, String ap_address, 
			 String land_name, Date start_dt, Date end_dt) {
		
		RegVO regVO = new RegVO();
		
		regVO.setMember_no(member_no);
		regVO.setAp_name(ap_name);
		regVO.setAp_address(ap_address);
		regVO.setLand_name(land_name);
		regVO.setStart_dt(start_dt);
		regVO.setEnd_dt(end_dt);


		dao.insert(regVO);
		
		return regVO;
	}
	public RegVO getOneRegister(Integer member_no) {
		return dao.findByPrimaryKey(member_no);
	}
	public RegVO getOneRegisterByRegNo(Integer reg_no) {
		return dao.findByPrimaryKeyByRegNo(reg_no);
	}
	public List<RegVO> getAllRegisterByMemNo(Integer member_no){
		return dao.getAllByMemNo(member_no);
	}
	public List<RegVO> getAllRegisterByLandName(String land_name){
		return dao.getAllByLandName(land_name);
	}
	public List<RegVO> getAllRegisterByLandNameWithApproval(String land_name){
		return dao.getAllByLandNameWithApproval(land_name);
	}
	public RegVO updateResponse(Integer reg_no, String ap_name, String ap_address, Date start_dt, Date end_dt, String restype) {
		RegVO regVO = new RegVO();
		regVO.setReg_no(reg_no);
		regVO.setAp_name(ap_name);
		regVO.setAp_address(ap_address);
		regVO.setStart_dt(start_dt);
		regVO.setEnd_dt(end_dt);
		regVO.setStatus(restype);
		dao.updateRes(regVO);
		return regVO;
	}

}
