package bookshop.example;

public class Book {
	private int bookNo;
	private String title, author, state;
	private int stateCode = 1; // 0: 대여중, 1: 재고 있음

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
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

	public Book(int bookNo, String title, String author) {
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
	}

	public void ck_rent() {
		stateCode = 0;
		System.out.println(title + "(가) 대여 됐습니다.");
	}

	public void print() {
		if (stateCode == 0) {
			state = "대여중";
		} else {
			state = "재고있음";
		}
		System.out.println("[" + bookNo + "] " + "제목:" + title + ", 작가:" + author + ", 대여 유무:" + state);

	}

}
