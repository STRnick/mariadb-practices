package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CartVo;

public class CartDao {
	
	public void insert(Long customer_no, Long book_no, Long count) {
		CartVo vo = new CartVo();
		vo.setCustomer_no(customer_no);
		vo.setBook_no(book_no);
		vo.setCount(count);

		insert(vo);
	}

	public boolean insert(CartVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();
			String sql = "insert into cart values(?, ?, ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, vo.getCustomer_no());
			pstmt.setLong(2, vo.getBook_no());
			pstmt.setLong(3, vo.getCount());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<CartVo> findAll() {
		List<CartVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql = " select m.name, b.title, c.count" + " from member m, book b, cart c"
					+ " where m.no = c.customer_no" + " and b.no = c.book_no" + " group by b.title"
					+ " order by count desc";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				String member_name = rs.getString(1);
				String book_title = rs.getString(2);
				long count = rs.getLong(3);

				CartVo vo = new CartVo();
				vo.setMember_name(member_name);
				vo.setBook_title(book_title);
				vo.setCount(count);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean deleteAll() {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;

		try {
			connecion = getConnection();

			String sql = "delete from cart";
			pstmt = connecion.prepareStatement(sql);

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (connecion != null)
					connecion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean autoIncrementRestore() {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;

		try {
			connecion = getConnection();

			String sql = "alter table cart auto_increment = 1";
			pstmt = connecion.prepareStatement(sql);

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (connecion != null)
					connecion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.10.37:3306/bookmall?charset=utf8";
			connection = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return connection;
	}
}
