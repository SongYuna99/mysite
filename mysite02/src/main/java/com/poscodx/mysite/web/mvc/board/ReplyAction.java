package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			WebUtil.forward("user/login", request, response);
			return;
		}

		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		if (title.isBlank() || content.isBlank()) {
			WebUtil.forward("board/writeform", request, response);
			return;
		}

		BoardVo originalBoard = new BoardDao().findByNo(no);

		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContents(content);
		boardVo.setgNo(originalBoard.getgNo());
		boardVo.setoNo(originalBoard.getoNo() + 1);
		boardVo.setDepth(originalBoard.getDepth() + 1);
		boardVo.setUserNo(authUser.getNo());

		new BoardDao().increaseONo(originalBoard.getgNo(), originalBoard.getoNo());
		new BoardDao().insertReply(boardVo);
		
		response.sendRedirect(request.getContextPath() + "/board");
	}

}
