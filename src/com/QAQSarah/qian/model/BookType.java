package com.QAQSarah.qian.model;

public class BookType {

	private int id; // 涔︾睄绫诲瀷缂栧彿
	private String bookTypeName; // 涔︾睄绫诲瀷鍚�
	private String bookTypeDesc; // 涔︾睄绫诲瀷璇︽儏
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
	public String getBookTypeDesc() {
		return bookTypeDesc;
	}
	public void setBookTypeDesc(String bookTypeDesc) {
		this.bookTypeDesc = bookTypeDesc;
	}
	public BookType(int id, String bookTypeName, String bookTypeDesc) {
		super();
		this.id = id;
		this.bookTypeName = bookTypeName;
		this.bookTypeDesc = bookTypeDesc;
	}
	public BookType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookType(String bookTypeName, String bookTypeDesc) {
		super();
		this.bookTypeName = bookTypeName;
		this.bookTypeDesc = bookTypeDesc;
	}
	@Override
	public String toString() {
		return bookTypeName;
	}

}
