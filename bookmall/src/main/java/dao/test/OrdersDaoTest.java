package dao.test;

import java.util.List;

import dao.BookDao;
import dao.OrdersDao;
import vo.BookVo;
import vo.OrdersVo;

public class OrdersDaoTest {

	public static void main(String[] args) {
//		testInsert();
		testFindAll();
	}

	private static void testFindAll() {
		List<OrdersVo> list = new OrdersDao().findAll();
		for (OrdersVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testInsert() {
		OrdersDao dao = new OrdersDao();
		OrdersVo vo = new OrdersVo();

		vo.setNo("20220519-0001");
		vo.setPayment(3000);
		vo.setAddress("센텀시티");
		vo.setMember_no(1L);
		dao.insert(vo);
	}
}
