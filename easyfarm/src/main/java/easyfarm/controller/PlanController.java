package easyfarm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import easyfarm.service.PgsPlanService;

@Controller
public class PlanController {
	
	@Autowired
	private PgsPlanService pgsPlanService;
	
	/* 계획차수관리 */
	@GetMapping("/plan")
	public String planMain(Model model
						  ,@RequestParam(value = "projectCode", required = false) String projectCode
						  ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		if(projectCode == null) {
			projectCode = "project_1";
		}
		//프로젝트정보조회
		if(projectCode != null && !"".equals(projectCode.trim())) {
			Map<String, Object> farmProjectInfo = pgsPlanService.getFarmProjectInfo(projectCode);
			String projectName = (String) farmProjectInfo.get("projectName");
			model.addAttribute("projectName", projectName);
		}
		
		return "views/plan/planMain";
	}
	
	/* 월켈린더조회 */
	@GetMapping("/plan/getSchedule")
	public String getSchedule(Model model
							 ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		if(projectPlanCode == null) {
			projectPlanCode = "project_plan_1";
		}
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			/* 계획정보조회 */
			Map<String, Object> projectPlanInfo = pgsPlanService.getProjectPlanInfo(projectPlanCode);
			String projectPlanN = (String)projectPlanInfo.get("projectPlanN");
			String projectCode =  (String)projectPlanInfo.get("projectCode");
			
			/* 계획차수 리스트 조회 */
			List<Map<String, Object>> projectPlanNList = pgsPlanService.getProjectPlanNList(projectCode);
			
			model.addAttribute("projectPlanCode", projectPlanCode);
			model.addAttribute("projectPlanN", projectPlanN);
			model.addAttribute("projectCode", projectCode);
			model.addAttribute("projectPlanNList", projectPlanNList);
		}
		
		return "views/plan/getSchedule";
	}
	
	/* 계획등록 */
	@GetMapping("/plan/addSpend")
	public String addSpend(Model model
						  ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode
						  ,@RequestParam(value = "stockItemCode", required = false) String stockItemCode) {
		/* 임시값세팅 */
		if(projectPlanCode == null) {
			projectPlanCode = "project_plan_1";
		}
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			
			/* 계획정보조회 */
			Map<String, Object> projectPlanInfo = pgsPlanService.getProjectPlanInfo(projectPlanCode);
			String projectPlanN = (String) projectPlanInfo.get("projectPlanN");
			String projectName = (String) projectPlanInfo.get("projectName");
			String projectCode = (String) projectPlanInfo.get("projectCode");
			String farmCode = (String) projectPlanInfo.get("farmCode");
			String cropCode = (String) projectPlanInfo.get("cropCode");
			
			Map<String, Object> projectData = new HashMap<String, Object>();
			projectData.put("projectPlanN", projectPlanN);
			projectData.put("projectName", projectName);
			projectData.put("projectCode", projectCode);
			projectData.put("farmCode", farmCode);
			projectData.put("cropCode", cropCode);
			
			if(stockItemCode != null && !"".equals(stockItemCode.trim())) {
				projectData.put("stockItemCode", stockItemCode);
			}
			
			model.addAttribute("projectPlanN", projectPlanN);
			model.addAttribute("projectPlanCode", projectPlanCode);
			
			/* 작업단계 */
			List<Map<String, Object>> workphaseNameList = pgsPlanService.getWorkphaseName(projectData);
			System.out.println(workphaseNameList);
			model.addAttribute("workphaseNameList", workphaseNameList);
			
			/* 상세작업항목조회 */
			List<Map<String, Object>> workphaseCateNameList = pgsPlanService.getWorkphaseCateName(projectData);
			model.addAttribute("workphaseCateNameList", workphaseCateNameList);
			
			/* 거래처항목조회 */
			List<Map<String, Object>> clientNameList = pgsPlanService.getClientName(projectData);
			model.addAttribute("clientNameList", clientNameList);
			
			/* 농기계즐겨찾기조회 */
			List<Map<String, Object>> farmBookmarkMachineList = pgsPlanService.getFarmBookmarkMachine(projectData);
			model.addAttribute("farmBookmarkMachineList", farmBookmarkMachineList);
			
			/* 보유농기계조회 */
			List<Map<String, Object>> farmRetainMachineList = pgsPlanService.getFarmRetainMachine(projectData);
			model.addAttribute("farmRetainMachineList", farmRetainMachineList);
			
			/* 품목조회 */
			List<Map<String, Object>> stockItemList = pgsPlanService.getStockItem(projectData);
			model.addAttribute("stockItemList", stockItemList);
			
			/* 공과금항목조회 */
			List<Map<String, Object>> taxPayCateCodeList = pgsPlanService.getTaxPayCateCode();
			System.out.println(taxPayCateCodeList);
			model.addAttribute("taxPayCateCodeList", taxPayCateCodeList);
			
		}
		
		return "views/plan/addSpend";
	}
	
	@GetMapping("/plan/resultPlan")
	public String resultPlan() {
		return "views/plan/resultPlan";
	}
	
	
	@PostMapping("/ajax/getStockItemInfo")
	@ResponseBody
	public Map<String, Object> getStockItemInfo(@RequestParam(value = "resourceStockItemCode", required = false) String resourceStockItemCode) {
		
		Map<String, Object> stockItemInfo = null;
		if(resourceStockItemCode != null && !"".equals(resourceStockItemCode.trim())) {
			stockItemInfo = pgsPlanService.getStockItemInfo(resourceStockItemCode);
		}
		return stockItemInfo;
	}
	
	
}
