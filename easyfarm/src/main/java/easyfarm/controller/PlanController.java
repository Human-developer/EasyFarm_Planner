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
	
	/* 월켈린더조회 */
	@GetMapping("/plan/getSchedule")
	public String getSchedule(Model model
							 ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		if(projectPlanCode == null) {
			projectPlanCode = "1";
		}
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			/* 계획정보조회 */
			Map<String, Object> projectPlanInfo = pgsPlanService.getProjectPlanInfo(projectPlanCode);
			String projectPlanN = (String)projectPlanInfo.get("projectPlanN");
			int projectCode =  (int)projectPlanInfo.get("projectCode");
			
			model.addAttribute("projectPlanCode", projectPlanCode);
			model.addAttribute("projectPlanN", projectPlanN);
			model.addAttribute("projectCode", projectCode);
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
			projectPlanCode = "1";
		}
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			
			/* 계획정보조회 */
			Map<String, Object> projectPlanInfo = pgsPlanService.getProjectPlanInfo(projectPlanCode);
			String projectPlanN = (String) projectPlanInfo.get("projectPlanN");
			String projectName = (String) projectPlanInfo.get("projectName");
			int projectCode = (int) projectPlanInfo.get("projectCode");
			int farmCode = (int) projectPlanInfo.get("farmCode");
			int cropCode = (int) projectPlanInfo.get("cropCode");
			
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
			
		}
		
		return "views/plan/addSpend";
	}
	
	@PostMapping("/ajax/getStockItemInfo")
	@ResponseBody
	public Map<String, Object> getStockItemInfo(@RequestParam(value = "stockItemCode", required = false) String stockItemCode) {
		
		Map<String, Object> stockItemInfo = null;
		if(stockItemCode != null && !"".equals(stockItemCode.trim())) {
			stockItemInfo = pgsPlanService.getStockItemInfo(stockItemCode);
		}
		return stockItemInfo;
	}
	
	@PostMapping("/ajax/touchSpinActive")
	@ResponseBody
	public String test(@RequestParam(value = "touchSpinActive", required = false) String touchSpinActive) {
		return touchSpinActive;
	}
	
	
	
}
