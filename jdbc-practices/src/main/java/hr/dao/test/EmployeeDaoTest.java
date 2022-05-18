package hr.dao.test;

import java.util.List;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;

public class EmployeeDaoTest {

	public static void main(String[] args) {
//		testFindByFirstNameOrLastName("ken");
		testfindBySalary(20000, 40000);
	}

	public static void testFindByFirstNameOrLastName(String name) {
		List<EmployeeVo> list = new EmployeeDao().findByFirstNameOrLastName(name);
		for (EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public static void testfindBySalary(int minSalary, int maxSalary) {
		List<EmployeeVo> list = new EmployeeDao().findBySalary(minSalary, maxSalary);
		for (EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}
}