package com.young.lib.service;

import com.young.lib.dao.ReviewDAO;
import com.young.lib.dto.ReviewDTO;

public class ReviewService {
	
	private ReviewDAO reviewDAO;
	
	public ReviewService() {
		reviewDAO = new ReviewDAO();
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
	
}
