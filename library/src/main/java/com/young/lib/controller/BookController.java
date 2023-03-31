package com.young.lib.controller;

import java.util.ArrayList;

import com.young.lib.dto.BookDTO;
import com.young.lib.service.BookService;

public class BookController {

	private BookService bookService;
	
	public BookController() {
		bookService = new BookService();
	}
	
	public ArrayList<BookDTO> requestViewAll() {
		ArrayList<BookDTO> responseList = new ArrayList<>();
		responseList = bookService.viewAllBooks();
		return responseList;
	}
	
	public ArrayList<BookDTO> requestViewCategory(int categoryId) {
		ArrayList<BookDTO> responseList = new ArrayList<>();
		responseList = bookService.viewCategoryBooks(categoryId);
		return responseList;		
	}
	
	public ArrayList<BookDTO> requestViewSearch(String search) {
		ArrayList<BookDTO> responseList = new ArrayList<>();
		responseList = bookService.searchBooks(search);
		return responseList;
	}
	
	public ArrayList<BookDTO> requestOrderByAll(String orderColumn) {
		ArrayList<BookDTO> responseList = new ArrayList<>();
		responseList = bookService.orderByAllBooks(orderColumn);
		return responseList;
	}
	
	public ArrayList<BookDTO> requestOrderByCategory(int categoryId, String orderColumn) {
		ArrayList<BookDTO> responseList = new ArrayList<>();
		responseList = bookService.orderByCategoryBooks(categoryId, orderColumn);
		return responseList;		
	}
	
	public BookDTO requestBookInfo(int bookId) {
		BookDTO responseDto = null;
		responseDto = bookService.bookInfo(bookId);
		return responseDto;
	}
	
}
