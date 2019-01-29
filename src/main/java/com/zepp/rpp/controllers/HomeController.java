package com.zepp.rpp.controllers;

import com.zepp.rpp.common.PageWrapper;
import com.zepp.rpp.domains.Notice;
import com.zepp.rpp.exceptions.NotFoundException;
import com.zepp.rpp.services.notice.NoticeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {
	
	private final NoticeService noticeService;

	@Autowired
	public HomeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@GetMapping("/home")
	public String homePage(Model model, Pageable pageable) {
		Page<Notice> noticePage = noticeService.findNotices(pageable);
		log.debug("Getting Index page");
		PageWrapper<Notice> page = new PageWrapper<>(noticePage, "/home");
		model.addAttribute("notices", page.getContent());
		model.addAttribute("page", page);
		return "common/homepage";
	}
	
	@GetMapping("/about")
	public String about() {
		return "common/about";
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MASTERMIND', 'USER')")
	@GetMapping("/admin")
	public String adminPage() {
		return "common/admin";
	}

	@GetMapping("/contact")
	public String contact() {
		return "common/contact";
	}
	
	@GetMapping("/login")
    public String login(){
        return "common/login";
    }

	@GetMapping("/403")
	public String error403() {
		return "error/403";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception){

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/error/404error");
		modelAndView.addObject("exception", exception);

		return modelAndView;
	}

}
