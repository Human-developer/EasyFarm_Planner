package easyfarm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import easyfarm.service.PgsPlanService;

@Controller
public class PlanController {
	
	@Autowired
	private PgsPlanService pgsPlanService;
	
	/* 월켈린더조회 */
	@GetMapping("/plan/getSchedule")
	public String getSchedule(Model model
							 ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		if(projectPlanCode == null) {
			projectPlanCode = "project_plan_1";
		}
		if(projectPlanCode != null && !"".equals(projectPlanCode)) {
			/* 계획차수조회 */
			Map<String, Object> projectPlanInfo = pgsPlanService.getProjectPlanInfo(projectPlanCode);
			String projectPlanN = (String) projectPlanInfo.get("projectPlanN");
			String projectCode = (String) projectPlanInfo.get("projectCode");
			model.addAttribute("projectPlanCode", projectPlanCode);
			model.addAttribute("projectPlanN", projectPlanN);
			model.addAttribute("projectCode", projectCode);
		}
		
		return "views/plan/getSchedule";
	}
	
	/* 계획등록 */
	@GetMapping("/plan/addSpend")
	public String addSpend(Model model
						  ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		/* 임시값세팅 */
		if(projectPlanCode == null) {
			projectPlanCode = "project_plan_1";
		}
		if(projectPlanCode != null && !"".equals(projectPlanCode)) {
			
			/* 계획차수조회 */
			Map<String, Object> projectPlanInfo = pgsPlanService.getProjectPlanInfo(projectPlanCode);
			String projectPlanN = (String) projectPlanInfo.get("projectPlanN");
			model.addAttribute("projectPlanN", projectPlanN);
			
			/* 작업단계 */
			String projectCode = (String) projectPlanInfo.get("projectCode");
			List<Map<String, Object>> workphaseNameList = pgsPlanService.getWorkphaseName(projectCode);
			System.out.println(workphaseNameList);
			model.addAttribute("workphaseNameList", workphaseNameList);
			
			/* 상세작업항목조회 */
			List<Map<String, Object>> workphaseCateNameList = pgsPlanService.getWorkphaseCateName(projectCode);
			model.addAttribute("workphaseCateNameList", workphaseCateNameList);
			
			/* 거래처항목조회 */
			List<Map<String, Object>> clientNameList = pgsPlanService.getClientName(projectCode);
			model.addAttribute("clientNameList", clientNameList);
			
			/* 농기계즐겨찾기조회 */
			List<Map<String, Object>> farmBookmarkMachineList = pgsPlanService.getFarmBookmarkMachine(projectCode);
			model.addAttribute("farmBookmarkMachineList", farmBookmarkMachineList);
			
			/* 보유농기계조회 */
			List<Map<String, Object>> farmRetainMachineList = pgsPlanService.getFarmRetainMachine(projectCode);
			model.addAttribute("farmRetainMachineList", farmRetainMachineList);
		}
		
		return "views/plan/addSpend";
	}
}
