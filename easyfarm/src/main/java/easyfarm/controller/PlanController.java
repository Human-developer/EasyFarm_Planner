package easyfarm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import easyfarm.service.PgsPlanService;

@Controller
public class PlanController {
	
	@Autowired
	private PgsPlanService pgsPlanService;
	
	/* 월켈린더조회 */
	@GetMapping("/plan/getSchedule")
	public String getSchedule(Model model) {
		return "views/plan/getSchedule";
	}
	
	/* 계획등록 */
	@GetMapping("/plan/addSpend")
	public String addSpend(Model model) {
		List<Map<String, Object>> workphaseCateNameList = pgsPlanService.getWorkphaseCateName();
		System.out.println(workphaseCateNameList);
		model.addAttribute("workphaseCateNameList", workphaseCateNameList);
		
		return "views/plan/addSpend";
	}
}
