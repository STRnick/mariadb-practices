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
		memberFindAll();
		categoryFindAll();
		bookFindAll();
		cartFindAll();
		orderFindAll();
	}

	private static void memberFindAll() {
		List<MemberVo> list = new MemberDao().findAll();
		System.out.println("========================");
		for (MemberVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("========================");
	}

	private static void categoryFindAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("========================");
	}
	
	private static void bookFindAll() {
		List<BookVo> list = new BookDao().findAll();
		for (BookVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("========================");
	}
	
	private static void cartFindAll() {
		List<CartVo> list = new CartDao().findAll();
		for (CartVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("========================");
	}

	private static void orderFindAll() {
		List<OrdersVo> list = new OrdersDao().findAll();
		for (OrdersVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("========================");
	}
}
