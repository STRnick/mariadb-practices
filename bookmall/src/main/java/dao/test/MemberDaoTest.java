package dao.test;

import java.util.List;

import dao.MemberDao;
import vo.MemberVo;


public class MemberDaoTest {
	
	public static void main(String[] args) {
		testInsert();
		testFindAll();
	}

	private static void testFindAll() {
		List<MemberVo> list = new MemberDao().findAll();
		for (MemberVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void testInsert() {
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();

		vo.setNo(1L);
		vo.setName("둘리");
		vo.setTell("010-1234-5678");
		vo.setEmail("doolri@gmail.com");
		vo.setPassword("1234");
		dao.insert(vo);
		
		vo.setNo(2L);
		vo.setName("마이콜");
		vo.setTell("010-2345-6789");
		vo.setEmail("micol@gmail.com");
		vo.setPassword("1234");
		dao.insert(vo);
	}
}
