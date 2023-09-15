package com.poscodx.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.repository.UserRepository;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		Long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

		UserVo userVo = new UserVo();
		userVo.setNo(no);
		userVo.setName(name);
		userVo.setPassword(password);
		userVo.setGender(gender);

		if (password == null) {
			new UserRepository().updateExceptPassword(userVo);
		}
		new UserRepository().update(userVo);
		
		session.removeAttribute("user");
		session.invalidate();
		
		authUser.setName(name);
		session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());

	}

}
