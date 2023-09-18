package com.poscodx.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;

	@RequestMapping("")
	public String main() {
		return "redirect:/board/page/1";
	}

	@RequestMapping(value = "/page/{no}", method = RequestMethod.GET)
	public String page(@PathVariable("no") int no, Model model) {
		List<BoardVo> list = boardService.getBoardPage(no);
		List<Integer> pagelist = boardService.getPageList(no);

		model.addAttribute("list", list);
		model.addAttribute("pagelist", pagelist);
		model.addAttribute("selectedPage", no);

		return "board/main";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			return "user/login";
		}

		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, BoardVo boardVo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			return "user/login";
		}

		boardVo.setUserNo(authUser.getNo());
		boardVo.setgNo(boardService.getGno() + 1);
		boardVo.setoNo(1);
		boardVo.setDepth(1);

		boardService.insert(boardVo);

		return "redirect:/board";
	}

	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(@PathVariable("no") int no, Model model) {
		BoardVo boardVo = boardService.findViewByNo(no);

		model.addAttribute("boardVo", boardVo);
		return "board/view";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") int no, Model model, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			return "user/login";
		}
		
		Long userNo = boardService.findUserNoByNo(no);
		if(userNo != authUser.getNo()) {
			return "redirect:/board";
		}
	
		boardService.delete(no);
		return "redirect:/board";
	}

//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public String search(@RequestParam("keyword") String keyword, Model model) {
//		if("".equals(keyword)) {
//			return "redirect:/board";
//		}
//
//		return "";
//	}
//
//	@RequestMapping(value = "/search/{keyword}/{no}", method = RequestMethod.GET)
//	public String search(@PathVariable("no") int no, Model model) {
//
//		return "";
//	}
//
//	@RequestMapping(value = "/search/{keyword}/{no}", method = RequestMethod.GET)
//	public String delete(HttpSession session) {
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//
//		if (authUser == null) {
//			return "user/login";
//		}
//
//		return "";
//	}
}
