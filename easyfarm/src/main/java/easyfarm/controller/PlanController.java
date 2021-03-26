package easyfarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {

	/* 월켈린더조회 */
	@GetMapping("/plan/getSchedule")
	public String getSchedule(Model model) {
		return "views/plan/getSchedule";
	}
	
	/* 계획등록 */
	@GetMapping("/plan/addSpend")
	public String addSpend() {
		return "views/plan/addSpend";
	}
}
