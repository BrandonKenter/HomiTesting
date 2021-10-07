package com.apt.model;

import java.util.List;

public interface AptDAO_interface {
	public AptVO findByPrimaryKey(Integer apt_no);
	public AptVO findByPrimaryKeyByApName(String ap_name);
	public AptVO getOnePicByApName (String ap_name);
	public void insert(AptVO aptVO);
	public List<AptVO> getAllByMemNo(Integer member_no);
}
