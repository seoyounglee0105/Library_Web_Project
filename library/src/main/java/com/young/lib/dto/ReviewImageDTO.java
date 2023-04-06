package com.young.lib.dto;

public class ReviewImageDTO {
	private int reviewId;
	private String originImage;
	private String uuidImage;
	
	public ReviewImageDTO() {
	}

	public ReviewImageDTO(int reviewId, String originImage, String uuidImage) {
		this.reviewId = reviewId;
		this.originImage = originImage;
		this.uuidImage = uuidImage;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getOriginImage() {
		return originImage;
	}

	public void setOriginImage(String originImage) {
		this.originImage = originImage;
	}

	public String getUuidImage() {
		return uuidImage;
	}

	public void setUuidImage(String uuidImage) {
		this.uuidImage = uuidImage;
	}

	@Override
	public String toString() {
		return "reviewImageDTO [reviewId=" + reviewId + ", originImage=" + originImage + ", uuidImage=" + uuidImage
				+ "]";
	}

}

