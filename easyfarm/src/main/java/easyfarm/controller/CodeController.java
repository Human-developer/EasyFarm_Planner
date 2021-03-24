package easyfarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CodeController {

	@GetMapping("/code")
	public String codeMain(Model model) {
		
		return "views/code/codeMain";
	}
}
