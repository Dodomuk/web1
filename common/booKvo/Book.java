package mukroject_library.common.booKvo;

import java.sql.Date;

public class Book {

	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	int bIdx;
	private String isbn;
	private String category;
	private String title;
	private String author;
	private String info;
	private int book_amt;
	private Date regDate;
	private int rentCnt;
	
	public int getbIdx() {
		return bIdx;
	}
	public void setbIdx(int bIdx) {
		this.bIdx = bIdx;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getBook_amt() {
		return book_amt;
	}
	public void setBook_amt(int book_amt) {
		this.book_amt = book_amt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getRentCnt() {
		return rentCnt;
	}
	public void setRentCnt(int rentCnt) {
		this.rentCnt = rentCnt;
	}
	@Override
	public String toString() {
		return "Book [bIdx=" + bIdx + ", isbn=" + isbn + ", category=" + category + ", title=" + title + ", author="
				+ author + ", info=" + info + ", book_amt=" + book_amt + ", regDate=" + regDate + ", rentCnt=" + rentCnt
				+ "]";
	}
	
}
