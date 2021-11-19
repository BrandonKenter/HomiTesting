package com.comp.model;

import java.util.List;

public interface CompDAO_interface {
    public int insert(CompVO CompVO);
	public List<CompVO> getAllByLandName(String landlord_name);
	public CompVO findByPrimaryKey(Integer complaint_no);
	public CompVO getOnePic(Integer complaint_no);
	public int updateRes(CompVO compVO);
	public List<CompVO> getAllByMemNo(Integer member_no);
}
