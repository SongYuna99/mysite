package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		if (title.isBlank() || content.isBlank()) {
			WebUtil.forward("board/writeform", request, response);
			return;
		}

		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContents(content);
		boardVo.setUserNo(no);
		boardVo.setgNo(new BoardDao().findMaxGNo());

		new BoardDao().insert(boardVo);
		response.sendRedirect(request.getContextPath() + "/board");
	}

}
