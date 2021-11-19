package com.rate.model;

import java.util.List;

public interface RateDAO_interface {

	public void insert(RateVO RateVO);
	public RateVO findByPrimaryKey(Integer rate_no);
	public List<RateVO> getAllByAptNo(Integer apt_no);
}
