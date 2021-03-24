package easyfarm.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "main";
	}
	
	
}
