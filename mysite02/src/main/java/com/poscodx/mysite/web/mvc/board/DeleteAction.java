package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long no = Long.parseLong(request.getParameter("no"));
		Long userNo = Long.parseLong(request.getParameter("userNo"));
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if(authUser == null || authUser.getNo() != userNo) {
			response.sendRedirect(request.getContextPath() + "/board");
			return;
		}
		
		new BoardDao().delete(no);
		response.sendRedirect(request.getContextPath() + "/board");
	}

}
