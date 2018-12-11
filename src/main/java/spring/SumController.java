package spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SumController {
	
	@PostMapping("/sum")
	public String sum(@RequestParam(name = "getal1") int getal1, @RequestParam(name = "getal2") int getal2, Model model) {
		model.addAttribute("getal1", getal1);
		model.addAttribute("getal2", getal2);
		model.addAttribute("sum", getal1 + getal2);
		return "sum";
		
	}
	

}
