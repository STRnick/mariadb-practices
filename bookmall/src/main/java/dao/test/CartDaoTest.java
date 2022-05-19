package dao.test;

import java.util.List;

import dao.CartDao;
import vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		testInsert();
		testFindAll();
	}

	private static void testFindAll() {
		List<CartVo> list = new CartDao().findAll();
		for (CartVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testInsert() {
		CartDao dao = new CartDao();
		CartVo vo = new CartVo();
		
		vo.setCustomer_no(1L);
		vo.setBook_no(1L);
		vo.setCount(2L);
		dao.insert(vo);
		
		vo.setCustomer_no(1L);
		vo.setBook_no(3L);
		vo.setCount(1L);
		dao.insert(vo);
	}
}
