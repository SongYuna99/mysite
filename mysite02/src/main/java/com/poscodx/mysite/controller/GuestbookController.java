package com.poscodx.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.GuestBookDao;
import com.poscodx.mysite.vo.GuestBookVo;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("a");

		if ("insert".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("content");

			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContens(contents);

			new GuestBookDao().insert(vo);
			response.sendRedirect("/mysite02/guestbook");

		} else if ("deleteform".equals(action)) {
			String no = request.getParameter("no");
			request.setAttribute("no", no);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(request, response);

		} else if ("delete".equals(action)) {
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			int num = Integer.parseInt(no);

			if (new GuestBookDao().checkPassword(num, password)) {
				new GuestBookDao().delete(num);
				response.sendRedirect("/mysite02/guestbook");
			} else {
				response.sendRedirect("/mysite02/guestbook?a=deleteform&no=" + no);
			}

		} else {
			List<GuestBookVo> list = new GuestBookDao().selectAll();

			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/guestbook/list.jsp");
			rd.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
