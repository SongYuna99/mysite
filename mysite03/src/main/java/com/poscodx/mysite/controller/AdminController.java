package com.poscodx.mysite.controller;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.service.FileUploadService;
import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

@Auth(Role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ApplicationContext applicationContext;
	
//	@Autowired
//	private ServletContext servletContext;
	
	@Autowired
	private SiteService siteService;

	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.getSite();
		model.addAttribute("siteVo", siteVo);
		return "/admin/main";
	}

	@RequestMapping("/update")
	public String update(SiteVo siteVo, @RequestParam("file") MultipartFile file, Model model) {
		// image file upload
		String url = fileUploadService.restore(file);

		if (url != null) {
			siteVo.setProfile(url);
		}
		siteService.UpdateSite(siteVo);

		model.addAttribute("url", url);
		return "redirect:/admin/";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "/admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "/admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "/admin/user";
	}
}
