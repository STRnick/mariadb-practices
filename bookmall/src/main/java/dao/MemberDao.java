package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CategoryVo;
import vo.MemberVo;


public class MemberDao {
	
	public List<MemberVo> findAll() {
		List<MemberVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql = " select name, email" + 
						 " from member";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				String name = rs.getString(1);
				String email = rs.getString(2);

				MemberVo vo = new MemberVo();
				vo.setName(name);
				vo.setEmail(email);

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
	
	public void insert(String name, String tell, String email, String password) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setTell(tell);
		vo.setEmail(email);
		vo.setPassword(password);
		
		insert(vo);
	}
	
	public boolean DeleteAll() {
		boolean result = false;
		Connection connecion = null;
		PreparedStatement pstmt = null;
		
		try {
			connecion = getConnection();
			
			String sql = "delete from member";
			pstmt = connecion.prepareStatement(sql);
					
			int count =pstmt.executeUpdate();
			result = count == 1;
				
		}  catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(connecion != null)
					connecion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean insert(MemberVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();
			String sql = "insert into member values(null, ?, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTell());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());

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
