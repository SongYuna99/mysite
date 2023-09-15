package com.poscodx.mysite.web.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.repository.GuestBookRepository;
import com.poscodx.web.mvc.Action;

public class DelteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		int num = Integer.parseInt(no);

		if (new GuestBookRepository().checkPassword(num, password)) {
			new GuestBookRepository().delete(num);
			response.sendRedirect("/mysite02/guestbook");
		} else {
			response.sendRedirect("/mysite02/guestbook?a=deleteform&no=" + no);
		}
	}

}
