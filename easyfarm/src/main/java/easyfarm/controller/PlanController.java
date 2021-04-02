package easyfarm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import easyfarm.domain.plan.InsurancePay;
import easyfarm.domain.plan.ProjectPlan;
import easyfarm.service.PlanService;

@Controller
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	/* 프로젝트별 통합계획 차수조회 */
	@GetMapping("/plan")
	public String planMain(Model model
						  ,@RequestParam(value = "projectCode", required = false) String projectCode
						  ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		if(projectCode == null) {
			projectCode = "project_1";
		}
		//프로젝트정보조회
		if(projectCode != null && !"".equals(projectCode.trim())) {
			Map<String, Object> farmProjectInfo = planService.getFarmProjectInfo(projectCode);
			String projectName = (String) farmProjectInfo.get("projectName");
			model.addAttribute("projectCode", projectCode);
			model.addAttribute("projectName", projectName);
			
			List<Map<String, Object>> projectPlanNList = planService.getProjectPlanNList(projectCode);
			System.out.println(projectPlanNList);
			model.addAttribute("projectPlanNList", projectPlanNList);
		}
		
		return "views/plan/planMain";
	}
	
	/* 프로젝트별 통합계획생성 */
	@PostMapping("/plan/addProjectPlan")
	@ResponseBody
	public int addProjectPlan(HttpSession session
							 ,@RequestParam(value = "projectCode", required = false) String projectCode) {
		
		Map<String, Object> projectPlanData = null;
		int result = 0;
		if(projectCode != null && !"".equals(projectCode.trim())) {
			Map<String, Object> maxPlanNum = planService.getMaxProjectPlanNum(projectCode);
			
			String maxProjectPlanNum = (String) maxPlanNum.get("maxProjectPlanNum");
			String memberId = (String) session.getAttribute("SID");
			
			projectPlanData = new HashMap<String, Object>();
			projectPlanData.put("maxProjectPlanNum", maxProjectPlanNum);
			projectPlanData.put("projectCode", projectCode);
			projectPlanData.put("memberId", memberId);
			
			int count = planService.addProjectPlan(projectPlanData);
			if(count > 0) {
				result = count;
			}
		}
		return result;
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
			Map<String, Object> projectPlanInfo = planService.getProjectPlanInfo(projectPlanCode);
			String projectPlanN = (String)projectPlanInfo.get("projectPlanN");
			String projectCode =  (String)projectPlanInfo.get("projectCode");
			
			/* 계획차수 리스트 조회 */
			List<Map<String, Object>> projectPlanNList = planService.getProjectPlanNList(projectCode);
			
			model.addAttribute("projectPlanCode", projectPlanCode);
			model.addAttribute("projectPlanN", projectPlanN);
			model.addAttribute("projectCode", projectCode);
			model.addAttribute("projectPlanNList", projectPlanNList);
		}
		
		return "views/plan/getSchedule";
	}
	
	/*프로젝트별 보험료지출계획 조회 */
	@GetMapping("/plan/insurePayList")
	public String getInsurePayList(Model model
			  					  ,@RequestParam(value = "projectCode", required = false) String projectCode) {
		List<InsurancePay> insurancePayList = null;
		if(projectCode != null && !"".equals(projectCode.trim())) {
			
			Map<String, Object> farmProjectInfo = planService.getFarmProjectInfo(projectCode);
			insurancePayList = planService.getInsurePayList(projectCode);
			
			String projectName = (String) farmProjectInfo.get("projectName");
			System.out.println(insurancePayList);
			model.addAttribute("projectName", projectName);
			model.addAttribute("insurancePayList", insurancePayList);
		}
		return "views/plan/insurePayList";
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
			Map<String, Object> projectPlanInfo = planService.getProjectPlanInfo(projectPlanCode);
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
			List<Map<String, Object>> workphaseNameList = planService.getWorkphaseName(projectData);
			System.out.println(workphaseNameList);
			model.addAttribute("workphaseNameList", workphaseNameList);
			
			/* 상세작업항목조회 */
			List<Map<String, Object>> workphaseCateNameList = planService.getWorkphaseCateName(projectData);
			model.addAttribute("workphaseCateNameList", workphaseCateNameList);
			
			/* 거래처항목조회 */
			List<Map<String, Object>> clientNameList = planService.getClientName(projectData);
			model.addAttribute("clientNameList", clientNameList);
			
			/* 농기계즐겨찾기조회 */
			List<Map<String, Object>> farmBookmarkMachineList = planService.getFarmBookmarkMachine(projectData);
			model.addAttribute("farmBookmarkMachineList", farmBookmarkMachineList);
			
			/* 보유농기계조회 */
			List<Map<String, Object>> farmRetainMachineList = planService.getFarmRetainMachine(projectData);
			model.addAttribute("farmRetainMachineList", farmRetainMachineList);
			
			/* 품목조회 */
			List<Map<String, Object>> stockItemList = planService.getStockItem(projectData);
			model.addAttribute("stockItemList", stockItemList);
			
			/* 공과금항목조회 */
			List<Map<String, Object>> taxPayCateCodeList = planService.getTaxPayCateCode();
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
			stockItemInfo = planService.getStockItemInfo(resourceStockItemCode);
		}
		return stockItemInfo;
	}
	
	@GetMapping("/plan/test")
	public String test() {
		return "views/plan/test";
	}
	
}
