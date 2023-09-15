package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class PreviousPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int rowCount = new BoardRepository().getRowCount(); // 총 게시물 개수
		int boardNum = 5; // 한 페이지 당 표시할 게시물 개수
		int startCount = 1; // 페이지 시작 1, 6, 11 ...
		int selectedPage = Integer.parseInt(request.getParameter("page")); // 선택된 페이지

		if (selectedPage == 1) {
			return;
		}
		selectedPage--;

		int maxPageCount; // 가장 마지막 페이지
		if (rowCount % boardNum != 0) {
			maxPageCount = rowCount / boardNum + 1;
		} else {
			maxPageCount = rowCount / boardNum;
		}

		int endCount;
		if (startCount + 4 < maxPageCount) {
			endCount = startCount + 4;
		} else {
			endCount = maxPageCount;
		}

		List<Integer> pagelist = new ArrayList<Integer>();
		for (int i = startCount; i < endCount + 1; i++) {
			pagelist.add(i);
		}

		List<BoardVo> list = new BoardRepository().selectByPage(boardNum * (selectedPage - 1), boardNum);

		request.setAttribute("list", list);
		request.setAttribute("pagelist", pagelist);
		request.setAttribute("selectedPage", selectedPage);
		WebUtil.forward("board/list", request, response);
	}

}
