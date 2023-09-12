package com.poscodx.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if(authUser == null) {
			WebUtil.forward("user/login", request, response);
			return;
		}
		request.setAttribute("no", request.getParameter("no"));
		
		WebUtil.forward("board/replyform", request, response);
	}

}
