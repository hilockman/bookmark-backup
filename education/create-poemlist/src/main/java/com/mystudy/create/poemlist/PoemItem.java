package com.mystudy.create.poemlist;

public class PoemItem {
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "PoemItem [author=" + author + ", title=" + title + "]";
	}
	private String author;
	private String title;
}