package dao.test;

import java.util.List;

import dao.BookDao;
import vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
//		testInsert();
//		testFindAll();
		testbookOrderfindAll();
	}

	private static void testFindAll() {
		List<BookVo> list = new BookDao().findAll();
		for (BookVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void testbookOrderfindAll() {
		List<BookVo> list = new BookDao().bookOrderfindAll();
		for (BookVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getCount() + " " + vo.getPrice());
		}
	}

	public static void testInsert() {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();

		vo.setNo(1L);
		vo.setTitle("이것이 MariaDB다");
		vo.setPrice(1000L);
		vo.setCategory_no(2L);
		dao.insert(vo);
		
		vo.setNo(2L);
		vo.setTitle("러닝 Rect.JS");
		vo.setPrice(2000L);
		vo.setCategory_no(2L);
		dao.insert(vo);
		
		vo.setNo(3L);
		vo.setTitle("코스모스");
		vo.setPrice(1000L);
		vo.setCategory_no(1L);
		dao.insert(vo);
	}
}
