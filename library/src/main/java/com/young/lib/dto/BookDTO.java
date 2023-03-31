package com.young.lib.dto;

public class BookDTO {
	private int id;
	private String name;
	private String writer;
	private String publisher;
	private String image;
	private String description;
	private int checkOutCount;
	private boolean isAvailable;
	private int categoryId;
	
	private String categoryName;
	
	public BookDTO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", name=" + name + ", writer=" + writer + ", publisher=" + publisher + ", image="
				+ image + ", description=" + description + ", checkOutCount=" + checkOutCount + ", isAvailable="
				+ isAvailable + ", categoryId=" + categoryId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCheckOutCount() {
		return checkOutCount;
	}

	public void setCheckOutCount(int checkOutCount) {
		this.checkOutCount = checkOutCount;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
