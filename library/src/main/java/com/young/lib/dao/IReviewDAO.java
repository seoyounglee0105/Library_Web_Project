package com.young.lib.dao;

import java.util.ArrayList;

import com.young.lib.dto.ReviewDTO;

public interface IReviewDAO {

	// 리뷰 작성
	int insert(ReviewDTO reviewDTO);
	
	// 회원별 리뷰 조회
	ArrayList<ReviewDTO> select(String userId);
	
	// 도서별 리뷰 조회
	ArrayList<ReviewDTO> select(int bookId);
	
	// 회원+도서별 리뷰 조회 (단일 객체)
	ReviewDTO select(String userId, int bookId);
	
	// 특정 리뷰 조회
	ReviewDTO selectById(int reviewId);

	// 리뷰 삭제
	int delete(int reviewId);
	
}
