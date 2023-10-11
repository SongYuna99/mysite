package com.poscodx.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.GuestbookVo;

@Repository
public class GuestBookRepository {
	@Autowired
	private SqlSession sqlSession;

	// insert
	public boolean insert(GuestbookVo guestbookVo) {
		int count = sqlSession.insert("guestbook.insert", guestbookVo);
		return count == 1;
	}

	// deleteByNoAndPassword
	public boolean deleteByNoAndPassword(Long no, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);
		int count = sqlSession.delete("guestbook.deleteByNoAndPassword", map);
		return count == 1;
	}

	// select : findAll
	public List<GuestbookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}
}
