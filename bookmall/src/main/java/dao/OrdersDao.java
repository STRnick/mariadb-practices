package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVo;
import vo.OrdersVo;

public class OrdersDao {

	public void insert(String no, int payment, String address, Long member_no) {
		OrdersVo vo = new OrdersVo();
		vo.setNo(no);
		vo.setPayment(payment);
		vo.setAddress(address);
		vo.setMember_no(member_no);

		insert(vo);
	}

	public boolean insert(OrdersVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();
			String sql = "insert into orders values(?, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getNo());
			pstmt.setLong(2, vo.getPayment());
			pstmt.setString(3, vo.getAddress());
			pstmt.setLong(4, vo.getMember_no());

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

	public List<OrdersVo> findAll() {
		List<OrdersVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql = "select o.no, m.name, m.email, o.payment, o.address" + " from member m, orders o"
					+ " where m.no = o.member_no";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				String orders_no = rs.getString(1);
				String member_name = rs.getString(2);
				String member_email = rs.getString(3);
				int orders_payment = rs.getInt(4);
				String orders_address = rs.getString(5);

				OrdersVo vo = new OrdersVo();
				vo.setNo(orders_no);
				vo.setMember_name(member_name);
				vo.setMember_email(member_email);
				vo.setPayment(orders_payment);
				vo.setAddress(orders_address);

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

			String sql = "delete from orders";
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

			String sql = "alter table orders auto_increment = 1";
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
