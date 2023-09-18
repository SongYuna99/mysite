package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.GuestbookVo;

@Repository
public class GuestBookRepository {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	// insert
	public void insert(GuestbookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

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
			conn = dataSource.getConnection();

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
			conn = dataSource.getConnection();

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
	public List<GuestbookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}
}
