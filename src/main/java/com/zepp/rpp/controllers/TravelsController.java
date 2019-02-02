package com.zepp.rpp.controllers;

import com.zepp.rpp.domains.Helper;
import com.zepp.rpp.domains.Travels;
import com.zepp.rpp.exceptions.NotFoundException;
import com.zepp.rpp.services.TravelsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.security.config.http.MatcherType.ant;

@Slf4j
@Controller
public class TravelsController {

private final TravelsService travelsService;

	@Autowired
	public TravelsController(TravelsService travelsService) {
		this.travelsService = travelsService;
	}

	@GetMapping("/travelslist")
	public String listTravels(Model model) {
		model.addAttribute("travels", travelsService.findAll());
		return "travels/travels";
	}

	@GetMapping("/travels/show/{travelsId}")
	public String showTravels(@PathVariable int travelsId, Model model) {
		Travels travels = travelsService.findOne(travelsId);
		model.addAttribute("travels", travels);
		return "travels/travelsShow";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception){

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("error/404error");
		modelAndView.addObject("exception", exception);

		return modelAndView;
	}


	@GetMapping("/travelsstats")
	public String travelsStats(Model model) {
		Helper helper = new Helper();
		model.addAttribute("helper", helper);
		return "travels/travelsstats";
	}

	@PostMapping("/travelsstats")
	public String travelsStatsPost(@ModelAttribute("helper") Helper helper, Model model) {
		log.debug("vrednost starta je " + helper.getStart());
		//prva kolona u bazi predstavlja datum u šablonu yyyymm
		String start = helper.getStart().replace("-", "").substring(0, 6);
		String finish = helper.getFinish().replace("-", "").substring(0, 6);
		int dateStart = Integer.parseInt(start);
		int dateFinish = Integer.parseInt(finish);
		//provera da li je početni period veći od krajnjeg, moglo je i u javascript-u u templejtu
		if(dateStart >= dateFinish){
			model.addAttribute("msg", "Početni period mora biti pre krajnjeg.");
			return "travels/travelsstats";
		}

		List<Travels> list = travelsService.findByCountryAndPeriodNQ(helper.getCountry(), dateStart, dateFinish);
		if(list.size() > 0){
			log.debug("Lista nije prazna ima " + list.size());
			int dolasci = 0;
			int odlasci = 0;
			int ukupno = 0;
			for(Travels t: list){
				if(t.getDirection().toUpperCase().equals("ARRIVAL"))
					dolasci += t.getCounter();
				else
					odlasci += t.getCounter();
			}
			ukupno = dolasci + odlasci;
			model.addAttribute("dolasci", dolasci);
			model.addAttribute("odlasci", odlasci);
			model.addAttribute("ukupno", ukupno);
			return "travels/travelsStatsResult";
		}
		model.addAttribute("msg", "Nema rezultata za vaš upit.");
		return "travels/travelsstats";
	}

	@GetMapping("/travelsStatsResult")
	public String travelsStatsResult(Model model) {

		return "travels/travelsStatsResult";
	}

}
