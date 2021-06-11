package com.saidspringproject.SpringBootRestService.controller;

public class UpdateResponse {
private String msg;
private String book_name = "N/A";
private String isbn = "N/A";
private String id;
private int aisle = 0;
private String author = "N/A";

public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public String getBook_name() {
	return book_name;
}
public void setBook_name(String book_name) {
	this.book_name = book_name;
}
public String getIsbn() {
	return isbn;
}
public void setIsbn(String isbn) {
	this.isbn = isbn;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getAisle() {
	return aisle;
}
public void setAisle(int aisle) {
	this.aisle = aisle;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
}
