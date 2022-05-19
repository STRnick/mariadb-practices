package dao.test;

import java.util.List;

import dao.CategoryDao;
import vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		testInsert();
		testFindAll();
	}

	private static void testFindAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testInsert() {
		CategoryDao dao = new CategoryDao();
		CategoryVo vo = new CategoryVo();

		vo.setNo(1L);
		vo.setGenre("과학");
		dao.insert(vo);
		
		vo.setNo(2L);
		vo.setGenre("IT");
		dao.insert(vo);
		
		vo.setNo(3L);
		vo.setGenre("문학");
		dao.insert(vo);
	}
}