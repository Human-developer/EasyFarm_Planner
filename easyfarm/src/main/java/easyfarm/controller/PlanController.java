package easyfarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {

	@GetMapping("/plan")
	public String planMain(Model model) {
		
		
		return "views/plan/planMain";
	}
}
