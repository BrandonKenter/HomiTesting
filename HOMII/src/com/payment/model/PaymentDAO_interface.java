package com.payment.model;

import java.util.List;


public interface PaymentDAO_interface {
	
	public int insert(PaymentVO paymentVO);
    public boolean delete(Integer pay_no);
    public List<PaymentVO> getOneAll(Integer mb_no);

}
