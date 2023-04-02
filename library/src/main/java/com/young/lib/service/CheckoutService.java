package com.young.lib.service;

import java.util.ArrayList;

import com.young.lib.dao.CheckoutDAO;
import com.young.lib.dto.CheckoutDTO;

public class CheckoutService {

	private CheckoutDAO checkoutDAO;
	private BookService bookService;
	
	public CheckoutService() {
		checkoutDAO = new CheckoutDAO();
		bookService = new BookService();
	}
	
	// 대여하기
	public int checkoutBook(CheckoutDTO checkoutDTO) {
		int resultCount = 0;
		resultCount = checkoutDAO.insert(checkoutDTO);
		
		// 대여량 갱신
		bookService.updateCheckoutCount(checkoutDTO.getBookId());
		
		// 대여 가능 여부 갱신
		bookService.updateIsAvailable(checkoutDTO.getBookId());
		
		return resultCount;
	}
	
	public ArrayList<CheckoutDTO> viewCheckoutByUserId(String userId) {
		ArrayList<CheckoutDTO> resultList = new ArrayList<>();
		resultList = checkoutDAO.select(userId);
		return resultList;
	}
}
