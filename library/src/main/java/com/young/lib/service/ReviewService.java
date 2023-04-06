package com.young.lib.service;

import java.util.ArrayList;

import com.young.lib.dao.ReviewDAO;
import com.young.lib.dao.ReviewImageDAO;
import com.young.lib.dto.ReviewDTO;
import com.young.lib.dto.ReviewImageDTO;

public class ReviewService {
	
	private ReviewDAO reviewDAO;
	private ReviewImageDAO reviewImageDAO;
	
	public ReviewService() {
		reviewDAO = new ReviewDAO();
		reviewImageDAO = new ReviewImageDAO();
	}

	// 리뷰 작성
	public int writeReview(ReviewDTO reviewDTO) {
		int resultCount = 0;
		resultCount = reviewDAO.insert(reviewDTO);
		return resultCount;
	}
	
	// 리뷰 작성 권한 관련 - 회원+도서별 리뷰 조회
	public ReviewDTO selectReviewByUserAndBook(String userId, int bookId) {
		ReviewDTO resultDto = null;
		resultDto = reviewDAO.select(userId, bookId);
		return resultDto;
	}
	
	// 도서별 리뷰 조회
	public ArrayList<ReviewDTO> viewReviewByBook(int bookId) {
		ArrayList<ReviewDTO> resultList = new ArrayList<>();
		resultList = reviewDAO.select(bookId);
		return resultList;
	}
	
	// 회원별 리뷰 조회
	public ArrayList<ReviewDTO> viewReviewByUser(String userId) {
		ArrayList<ReviewDTO> resultList = new ArrayList<>();
		resultList = reviewDAO.select(userId);
		return resultList;
	}
	
	// 특정 리뷰 조회
	public ReviewDTO reviewInfo(int reviewId) {
		ReviewDTO resultDto = null;
		resultDto = reviewDAO.selectById(reviewId);
		return resultDto;
	}
	
	// 리뷰 삭제
	public int deleteReview(int reviewId) {
		int resultCount = 0;
		resultCount = reviewDAO.delete(reviewId);
		return resultCount;
	}
	
	// 리뷰 이미지 추가
	public int insertReviewImage(ReviewImageDTO reviewImageDTO) {
		int resultCount = 0;
		resultCount = reviewImageDAO.insert(reviewImageDTO);
		return resultCount;
	}
	
	// 리뷰 이미지 조회
	public ReviewImageDTO selectReviewImage(int reviewId) {
		ReviewImageDTO resultDto = null;
		resultDto = reviewImageDAO.select(reviewId);
		return resultDto;
	}
	
}
