package spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "firstName") String first, @RequestParam(name = "lastName") String last, Model model) {
		model.addAttribute("firstName", first);
		model.addAttribute("lastName", last);
		model.addAttribute("fullName", last + ", " + first);
		return "greeting";
		
	}
	

}
