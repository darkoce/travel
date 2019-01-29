package com.zepp.rpp.controllers;

import com.zepp.rpp.domains.Travels;
import com.zepp.rpp.domains.User;
import com.zepp.rpp.exceptions.NotFoundException;
import com.zepp.rpp.services.TravelsService;
import com.zepp.rpp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminTravellsController {
	
	private final TravelsService travelsService;
	private final UserService userService;

	@Autowired
	public AdminTravellsController(TravelsService travelsService, UserService userService) {
		this.travelsService = travelsService;
		this.userService = userService;
	}

    @PreAuthorize("hasAnyRole('ADMIN', 'MASTERMIND', 'USER')")
    @GetMapping("/travelsinventory")
	public String travelsInventory(Model model){
	    model.addAttribute("travels", travelsService.findAll());
	    return "administrator/travelsinventory";
    }

	@PreAuthorize("hasAnyRole('ADMIN', 'MASTERMIND', 'USER')")
	@GetMapping("/travelsnew")
	public String addTravels(Model model) {
		model.addAttribute("travels", new Travels());
		return "administrator/travelsnew";
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MASTERMIND', 'USER')")
	@PostMapping("/travelsnew")
	public String addTravelsPost(@ModelAttribute("travels") Travels travels, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		travels.setUser(user);
		travelsService.save(travels);
		return "redirect:/admin/travelsinventory";
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MASTERMIND', 'USER')")
	@GetMapping("/travels/show/{travelsId}")
	public String showTravels(@PathVariable int travelsId, Model model) {
		Travels travels = travelsService.findOne(travelsId);
		model.addAttribute("travels", travels);
		String userName = travels.getUser().getUsername();
		model.addAttribute("userName", userName);
		return "administrator/travelsshow";
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MASTERMIND', 'USER')")
	@GetMapping("/travels/edit/{travelsId}")
	public String travelsEdit(@PathVariable int travelsId, Model model) {
		model.addAttribute("travels", travelsService.findOne(travelsId));
		return "administrator/travelsnew";
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
