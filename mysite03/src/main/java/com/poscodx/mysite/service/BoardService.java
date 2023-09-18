package com.poscodx.mysite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	private static int boardPerPage = 5; // 한 페이지 당 표시할 게시물 개수

	public List<BoardVo> getBoardPage(int selectedPage) {
		int startIndex = boardPerPage * (selectedPage - 1);

		return boardRepository.selectByPage(startIndex, boardPerPage);
	}

	public List<Integer> getPageList(int selectedPage) {
		int lastPage;
		int rowCount = boardRepository.getRowCount();
		if (rowCount % boardPerPage != 0) {
			lastPage = rowCount / boardPerPage + 1;
		} else {
			lastPage = rowCount / boardPerPage;
		}

		int flag = 5 * (selectedPage / 5) + 1;
		List<Integer> pagelist = new ArrayList<Integer>();

		for (int i = flag; i < flag + 5; i++) {
			if (i <= lastPage) {
				pagelist.add(i);
			}
		}

		return pagelist;
	}

	public int getGno() {
		return boardRepository.getGno();
	}

	public boolean insert(BoardVo boardVo) {
		return boardRepository.insert(boardVo);
	}

	public BoardVo findViewByNo(int no) {
		return boardRepository.findByNo(no);
	}

	public Long findUserNoByNo(int no) {
		return boardRepository.findUserNoByNo(no);
	}

	public boolean delete(int no) {
		return boardRepository.delete(no);
	}
}
