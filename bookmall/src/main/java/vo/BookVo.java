package vo;

public class BookVo {
	private Long no;
	private String title;
	private Long price;
	private Long category_no;
	private String category_genre;
	private Long cart_count;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getCount() {
		return cart_count;
	}

	public void setCount(Long cart_count) {
		this.cart_count = cart_count;
	}

	public Long getCategory_no() {
		return category_no;
	}

	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}

	public String getCategory_genre() {
		return category_genre;
	}

	public void setCategory_genre(String category_genre) {
		this.category_genre = category_genre;
	}

	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", price=" + price + ", category_no=" + category_no
				+ ", category_genre=" + category_genre + ", cart_count=" + cart_count + "]";
	}

}
