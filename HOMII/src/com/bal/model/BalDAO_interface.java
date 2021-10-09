package com.bal.model;

import java.util.List;

public interface BalDAO_interface {
	public List<BalVO> getAllByMemNo(Integer member_no);
	public void insert(BalVO balVO);
	public List<BalVO> getAllByLandNo(Integer member_no);

}
