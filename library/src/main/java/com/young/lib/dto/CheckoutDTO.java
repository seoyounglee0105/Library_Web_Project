package com.young.lib.dto;

public class CheckoutDTO extends BookDTO {

	private int id;
	private String userId;
	private int bookId;
	private String checkoutDate;
	private boolean isReturn;
	
	public CheckoutDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CheckoutDTO(String userId, int bookId) {
		this.userId = userId;
		this.bookId = bookId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public boolean getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}

	@Override
	public String toString() {
		return "CheckoutBookDTO [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", checkoutDate="
				+ checkoutDate + ", isReturn=" + isReturn + "]";
	}

	
	
	
}
