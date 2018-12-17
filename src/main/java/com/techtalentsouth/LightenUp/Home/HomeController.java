package com.techtalentsouth.LightenUp.Home;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@Autowired
	private HomeRepository homeRepository;
	
	@GetMapping("/")
	public ModelAndView home(Home home) {
		ModelAndView mv = new ModelAndView("home/home");
		return mv;
	}

	@GetMapping("/all")
	public ModelAndView index(Home home) {
		ModelAndView mv = new ModelAndView("home/index");
		mv.addObject("homes", homeRepository.findAll());
		return mv;
	}
	
	@GetMapping("/home/show/{id}")
	public ModelAndView showHomes(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("home/show");
		Optional<Home> home = homeRepository.findById(id);
		mv.addObject("home", home.get());
		return mv;
	}
	
	@GetMapping("/home/create")
	public ModelAndView createHome(Home home) {
		ModelAndView mv = new ModelAndView("home/create");
		return mv;
	}
	
	@PostMapping("/home/create")
	public ModelAndView result(Home home) {
		ModelAndView mv = new ModelAndView("home/result");
		Home myHome = homeRepository.save(new Home(home.getStreetAddress(), home.getZipCode(), home.getCity(), home.getRating(), home.getFeatures()));
		mv.addObject("home", myHome);
		return mv;
	}
	
	@DeleteMapping("/home/show/{id}")
	public ModelAndView deleteHome(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("redirect:/all");
		homeRepository.deleteById(id);
		return mv;
	}
	
	@GetMapping("/home/edit/{id}")
	public ModelAndView updateHome(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("home/edit");
		Optional<Home> home = homeRepository.findById(id);
		mv.addObject("home", home);
		return mv;
	}
	
	@PutMapping("/home/edit")
	public ModelAndView updateHome(Home home) {
		ModelAndView mv = new ModelAndView("redirect:/all");
		homeRepository.save(home);
		return mv;
	}
}