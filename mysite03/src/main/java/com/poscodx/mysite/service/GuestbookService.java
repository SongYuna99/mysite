package com.poscodx.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.GuestBookRepository;
import com.poscodx.mysite.vo.GuestBookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestBookRepository guestbookRepository;

	public List<GuestBookVo> getContentsList() {
		return guestbookRepository.selectAll();
	}
	
	public boolean deleteContents(Long no, String password) {
		return false;
	}
	
	public boolean addContents(GuestBookVo vo) {
		return false;
	}
}
