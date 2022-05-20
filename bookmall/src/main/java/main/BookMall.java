package main;

import java.util.List;

import dao.BookDao;
import dao.CartDao;
import dao.CategoryDao;
import dao.MemberDao;
import dao.OrdersDao;
import vo.BookVo;
import vo.CartVo;
import vo.CategoryVo;
import vo.MemberVo;
import vo.OrdersVo;

public class BookMall {

	public static void main(String[] args) {
		deleteAllTables();
		member();
		category();
		book();
		cart();
		order();
		bookOrder();
	}

	private static void deleteAllTables() {
		// 테이블 초기화
		new OrdersDao().deleteAll();
		new CartDao().deleteAll();
		new BookDao().deleteAll();
		new CategoryDao().deleteAll();
		new MemberDao().deleteAll();

		// autoincrement 값 초기화
		new OrdersDao().autoIncrementRestore();
		new CartDao().autoIncrementRestore();
		new BookDao().autoIncrementRestore();
		new CategoryDao().autoIncrementRestore();
		new MemberDao().autoIncrementRestore();
	}

	private static void member() {
		// 데이터 삽입
		new MemberDao().insert("둘리", "010-1234-5678", "둘리@gmail.com", "123456");
		new MemberDao().insert("도우너", "010-2345-6789", "도우너@gmail.com", "abcd");
		// 조회
		List<MemberVo> list = new MemberDao().findAll();
		System.out.println("========================");
		for (MemberVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("========================");
	}

	private static void category() {
		// 데이터 삽입
		new CategoryDao().insert("과학");
		new CategoryDao().insert("IT");
		new CategoryDao().insert("문학");

		// 조회
		List<CategoryVo> list = new CategoryDao().findAll();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("========================");
	}

	private static void book() {
		// 데이터 삽입
		new BookDao().insert("이것이 MariaDB다", 1000L, 2L);
		new BookDao().insert("러닝 Rect.JS", 2000L, 2L);
		new BookDao().insert("코스모스", 1000L, 1L);

		// 조회
		List<BookVo> list = new BookDao().findAll();
		for (BookVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getPrice() + " " + vo.getCategory_genre());
		}
		System.out.println("========================");
	}

	private static void cart() {
		// 데이터 삽입
		new CartDao().insert(1L, 1L, 2L);
		new CartDao().insert(1L, 3L, 1L);

		// 조회
		List<CartVo> list = new CartDao().findAll();
		for (CartVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("========================");
	}

	private static void order() {
		// 데이터 삽입
		new OrdersDao().insert("20220519-0001", 3000, "센텀시티", 1L);

		// 조회
		List<OrdersVo> list = new OrdersDao().findAll();
		for (OrdersVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("========================");
	}

	private static void bookOrder() {
		List<BookVo> list = new BookDao().bookOrderfindAll();
		for (BookVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getCount() + " " + vo.getPrice());
		}
	}

}
