package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.GuestBookVo;

@Repository
public class GuestBookRepository {
	// DB Connection
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.186:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// insert
	public void insert(GuestBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into guestbook(name, password, contents, reg_date) " + "values(?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQLException : " + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException : " + e);
			}
		}
	}

	// delete
	public void delete(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from guestbook where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQLException : " + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException : " + e);
			}
		}
	}

	// insert
	public boolean checkPassword(int no, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			conn = getConnection();

			String sql = "select if(no=? and password=?, true, false) " + "from guestbook where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			pstmt.setInt(3, no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 1) {
					result = true;
				}
			}

		} catch (SQLException e) {
			System.out.println("SQLException : " + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException : " + e);
			}
		}
		return result;
	}

	// select
	public List<GuestBookVo> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestBookVo> result = new ArrayList<GuestBookVo>();

		try {
			conn = getConnection();

			String sql = "select no, name, contents, reg_date from guestbook order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("SQLException : " + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException : " + e);
			}
		}
		return result;
	}
}
