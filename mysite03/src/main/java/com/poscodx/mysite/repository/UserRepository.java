package com.poscodx.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;

	// insert
	public boolean insert(UserVo userVo) {
		int count = sqlSession.insert("user.insert", userVo);

		return count == 1;
	}

	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);

		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}

	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.findByNo", no);
	}

	public boolean update(UserVo userVo) {
		int count = sqlSession.update("user.update", userVo);

		return count == 1;
	}

}
