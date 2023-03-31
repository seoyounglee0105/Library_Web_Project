package com.young.lib.dao;

import java.util.ArrayList;

import com.young.lib.dto.BookDTO;

public interface IBookDAO {
	// 전체 조회
	ArrayList<BookDTO> select();
	
	// 하나의 조건에 대해 조회
	ArrayList<BookDTO> select(String column, int value);
	
	// 전체 조회 - 정렬
	ArrayList<BookDTO> selectOrder(String orderColumn);
	
	// 하나의 조건에 대해 조회 - 정렬
	ArrayList<BookDTO> selectOrder(String column, int value, String orderColumn);
	
	// 검색 조회
	ArrayList<BookDTO> select(String search);
	
	// 대여량 갱신
	int update(int checkOutCount, int bookId);
	
}
