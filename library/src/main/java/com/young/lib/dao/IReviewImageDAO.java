package com.young.lib.dao;

import com.young.lib.dto.ReviewImageDTO;

public interface IReviewImageDAO {

	int insert(ReviewImageDTO reviewImageDTO);
	
	ReviewImageDTO select(int reviewId);
}
