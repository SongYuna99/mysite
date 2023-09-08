package com.poscodx.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Access Control
		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute("authUser");

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/user?a=login");
			return;
		}
		
		user = new UserDao().findByNo(user.getNo());
		session.setAttribute("user", user);
		
		WebUtil.forward("user/updateform", request, response);
	}

}