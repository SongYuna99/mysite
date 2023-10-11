package com.poscodx.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.GuestBookRepository;
import com.poscodx.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestBookRepository guestbookRepository;

	public List<GuestbookVo> getContentsList() {
		return guestbookRepository.findAll();
	}

	public boolean deleteContents(Long no, String password) {
		return guestbookRepository.deleteByNoAndPassword(no, password);
	}

	public boolean addContents(GuestbookVo guestbookVo) {
		return guestbookRepository.insert(guestbookVo);
	}
}
