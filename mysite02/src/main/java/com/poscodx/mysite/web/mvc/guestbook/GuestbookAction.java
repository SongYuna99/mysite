package com.poscodx.mysite.web.mvc.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.GuestBookDao;
import com.poscodx.mysite.vo.GuestBookVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.utils.WebUtil;

public class GuestbookAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<GuestBookVo> list = new GuestBookDao().selectAll();

		request.setAttribute("list", list);
		WebUtil.forward("guestbook/list", request, response);
	}

}
