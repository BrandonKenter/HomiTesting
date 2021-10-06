package com.apt.model;

public interface AptDAO_interface {
	public AptVO findByPrimaryKey(Integer apt_no);
	public AptVO findByPrimaryKeyByApName(String ap_name);
	public AptVO getOnePicByApName (String ap_name);
	public void insert(AptVO aptVO);
}
