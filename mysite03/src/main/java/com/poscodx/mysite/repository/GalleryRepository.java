package com.poscodx.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;
}
