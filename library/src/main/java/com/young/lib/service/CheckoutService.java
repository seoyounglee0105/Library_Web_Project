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
		bookService.updateIsAvailable(false, checkoutDTO.getBookId());
		
		return resultCount;
	}
	
	// 회원별 대여 기록 조회
	public ArrayList<CheckoutDTO> viewCheckoutByUserId(String userId) {
		ArrayList<CheckoutDTO> resultList = new ArrayList<>();
		resultList = checkoutDAO.select(userId);
		return resultList;
	}
	
	// 특정 대여 기록 조회
	public CheckoutDTO viewCheckoutById(int id) {
		CheckoutDTO resultDto = null;
		resultDto = checkoutDAO.select(id);
		return resultDto;
	}
	
	// 리뷰 작성 권한 관련 : 회원 + 도서별 대여 기록 조회
	public ArrayList<CheckoutDTO> selectCheckoutByUserAndBook(String userId, int bookId) {
		ArrayList<CheckoutDTO> resultList = new ArrayList<>();
		resultList = checkoutDAO.select(userId, bookId);
		return resultList;
	}
	
	// 반납하기
	public int returnBook(CheckoutDTO checkoutDTO) {
		int resultCount = 0;
		resultCount = checkoutDAO.update(checkoutDTO.getId());
		
		// 대여 가능 여부 갱신
		bookService.updateIsAvailable(true, checkoutDTO.getBookId());
		
		return resultCount;
	}
	
	
}
