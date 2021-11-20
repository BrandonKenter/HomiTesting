<<<<<<< HEAD
package com.reg.model;

import java.util.List;

public interface RegDAO_interface {
	public int insert(RegVO regVO);
	public RegVO findByPrimaryKey(Integer member_no);
	public RegVO findByPrimaryKeyByRegNo(Integer reg_no);
	public List<RegVO> getAllByMemNo(Integer member_no);
	public List<RegVO> getAllByLandName(String land_name);
	public int updateRes(RegVO regVO);
	public List<RegVO> getAllByLandNameWithApproval(String land_name);
	public List<RegVO> getAllByApName(String ap_name);
}
=======
package com.reg.model;

import java.util.List;

public interface RegDAO_interface {
	public void insert(RegVO regVO);
	public RegVO findByPrimaryKey(Integer member_no);
	public RegVO findByPrimaryKeyByRegNo(Integer reg_no);
	public List<RegVO> getAllByMemNo(Integer member_no);
	public List<RegVO> getAllByLandName(String land_name);
	public void updateRes(RegVO regVO);
	public List<RegVO> getAllByLandNameWithApproval(String land_name);
	public List<RegVO> getAllByApName(String ap_name);
}
>>>>>>> main
