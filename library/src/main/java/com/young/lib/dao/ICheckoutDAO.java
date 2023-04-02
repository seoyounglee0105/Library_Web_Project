package com.young.lib.dao;

import java.util.ArrayList;

import com.young.lib.dto.CheckoutDTO;

public interface ICheckoutDAO {
	
	int insert(CheckoutDTO checkoutDTO);
	
	// 아이디별 조회
	ArrayList<CheckoutDTO> select(String userId);
	
	// 아이디 + 책별 조회
	ArrayList<CheckoutDTO> select(String userId, int bookId);
	
	// 반납 시 isReturn 갱신 (false에서 true로)
	int update(boolean isReturn);
	
}
