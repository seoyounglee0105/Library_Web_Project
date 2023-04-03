package com.young.lib.dto;

public class ReviewDTO extends BookDTO {

	private int id;
	private String userId;
	private int bookId;
	private int star;
	private String title;
	private String content;
	private String writeDate;
	
	public ReviewDTO(String userId, int bookId, int star, String title, String content) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.star = star;
		this.title = title;
		this.content = content;
	}
	
	public ReviewDTO() {
		// TODO Auto-generated constructor stub
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

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", star=" + star + ", title="
				+ title + ", content=" + content + "]";
	}
	
	
}
