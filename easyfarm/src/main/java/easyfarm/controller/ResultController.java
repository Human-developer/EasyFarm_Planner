package easyfarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultController {

	@GetMapping("/result")
	public String resultMain(Model model) {
		
		
		return "views/result/resultMain";
	}
}
