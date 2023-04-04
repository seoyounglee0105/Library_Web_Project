package com.young.lib.service;

import java.util.ArrayList;

import com.young.lib.dao.BookDAO;
import com.young.lib.dto.BookDTO;

public class BookService {

	private BookDAO bookDAO;
	
	public BookService() {
		bookDAO = new BookDAO();
	}
	
	// 전체 책 조회
	public ArrayList<BookDTO> viewAllBooks() {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		resultList = bookDAO.select();
		return resultList;
	}
	
	// 카테고리별 책 조회
	public ArrayList<BookDTO> viewCategoryBooks(int categoryId) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		resultList = bookDAO.select("category_id", categoryId);
		return resultList;		
	}
	
	// 검색어에 따른 책 조회
	public ArrayList<BookDTO> searchBooks(String search) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		resultList = bookDAO.select(search);
		return resultList;				
	}
	
	// 전체 책 조회 - 정렬
	public ArrayList<BookDTO> orderByAllBooks(String orderColumn) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		resultList = bookDAO.selectOrder(orderColumn);
		return resultList;			
	}
	
	// 카테고리별 책 조회 - 정렬
	public ArrayList<BookDTO> orderByCategoryBooks(int categoryId, String orderColumn) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		resultList = bookDAO.selectOrder("category_id", categoryId, orderColumn);
		return resultList;	
	}
	
	// 특정 책 정보 가져오기
	public BookDTO bookInfo(int bookId) {
		BookDTO resultDto = null;
		resultDto = bookDAO.select("b.id", bookId).get(0);
		return resultDto;
	}
	
	// 대여량 갱신
	public int updateCheckoutCount(int bookId) {
		int resultCount = 0;
		
		// 현재 대여량 가져오기
		int currentCount = bookInfo(bookId).getCheckOutCount();
		
		resultCount = bookDAO.update(currentCount + 1, bookId);		
		return resultCount;
	}
	
	// 대여 가능 여부 갱신
	public int updateIsAvailable(boolean isAvailable, int bookId) {
		int resultCount = 0;
		resultCount = bookDAO.update(isAvailable, bookId);		
		return resultCount;
	}
	
	// 대여량 순 top n 도서 리스트 가져오기
	public ArrayList<BookDTO> viewTopBook(int count) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		resultList = bookDAO.select(count);
		return resultList;
	}
}
