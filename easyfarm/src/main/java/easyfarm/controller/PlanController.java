package easyfarm.controller;

import java.util.ArrayList;
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

import easyfarm.domain.plan.CommonMachine;
import easyfarm.domain.plan.InsurancePay;
import easyfarm.domain.plan.PlanWorkphase;
import easyfarm.domain.plan.PlanWorkphaseCate;
import easyfarm.domain.plan.ProjectPlan;
import easyfarm.domain.plan.StockCate;
import easyfarm.domain.plan.StockItem;
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
			  					  ,@RequestParam(value = "projectCode", required = false) String projectCode
			  					  ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		
		List<InsurancePay> insurancePayList = null;
		Map<String, Object> paramMap = null;
		
		if(projectCode != null && !"".equals(projectCode.trim()) &&
			projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			
			Map<String, Object> farmProjectInfo = planService.getFarmProjectInfo(projectCode);
			String projectName = (String) farmProjectInfo.get("projectName");
			
			paramMap = new HashMap<String, Object>();
			paramMap.put("projectCode", projectCode);
			insurancePayList = planService.getInsurePayList(paramMap);
			
			model.addAttribute("projectName", projectName);
			model.addAttribute("projectCode", projectCode);
			model.addAttribute("projectPlanCode", projectPlanCode);
			model.addAttribute("insurancePayList", insurancePayList);
		}
		return "views/plan/insurePayList";
	}
	
	/* 프로젝트별 보험료지출계획 등록화면 */
	@GetMapping("/plan/addInsurePay")
	public String addInsurePay(Model model
							  ,@RequestParam(value = "projectCode", required = false) String projectCode
							  ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		if(projectCode != null && !"".equals(projectCode.trim())) {
			model.addAttribute("projectCode", projectCode);
			model.addAttribute("projectPlanCode", projectPlanCode);
		}
		return "views/plan/addInsurePay";
	}
	
	/* 프로젝트별 보험지출계획 등록 */
	@PostMapping("/plan/addInsurePay")
	public String addInsurePay(InsurancePay insurePay, HttpSession session, RedirectAttributes redirectAttributes
							  ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		int result = 0;
		Map<String, Object> paramMap = null;
		String projectCode = null;
		
		if(insurePay.getProjectCode() != null && !"".equals(insurePay.getProjectCode())) {
			
			projectCode = insurePay.getProjectCode();
			
			paramMap = new HashMap<String, Object>();
			paramMap.put("insurePay", insurePay);
			paramMap.put("regMemberId", session.getAttribute("SID"));
			
			result = planService.addInsurePay(paramMap);
		}
		
		if(result > 0) {
			return "redirect:/plan/insurePayList?projectCode=" + projectCode + "&projectPlanCode=" + projectPlanCode;
		}else {
			return "redirect:/plan/addInsurePay?projectCode=" + projectCode + "&projectPlanCode=" + projectPlanCode;
		}
	}
	
	/* 프로젝트별 보험지출계획 수정화면 */
	@GetMapping("/plan/modifyInsurePay")
	public String modifyInsurePay(Model model
								 ,@RequestParam(value = "insurePayCode", required = false) String insurePayCode
								 ,@RequestParam(value = "projectCode", required = false) String projectCode
								 ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		
		Map<String, Object> paramMap = null;
		List<InsurancePay> insurancePayList = null;
		
		if(insurePayCode != null && !"".equals(insurePayCode.trim()) &&
				projectCode != null && !"".equals(projectCode.trim())) {
			
			paramMap = new HashMap<String, Object>();
			paramMap.put("insurePayCode", insurePayCode);
			paramMap.put("projectCode", projectCode);
			
			insurancePayList = planService.getInsurePayList(paramMap);
			
			model.addAttribute("insurancePayList", insurancePayList.get(0));
			model.addAttribute("projectPlanCode", projectPlanCode);
		}
		
		return "views/plan/modifyInsurePay";
	}
	
	/* 프로젝트별 보험지출계획 수정 */
	@PostMapping("/plan/modifyInsurePay")
	public String modifyInsurePay(InsurancePay insurePay, RedirectAttributes redirectAttributes
								 ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		
		int result = 0;
		String projectCode = null;
		String insurePayCode = null;
		
		if(insurePay.getProjectCode() != null && !"".equals(insurePay.getProjectCode())) {
			projectCode = insurePay.getProjectCode();
			insurePayCode = insurePay.getInsurePayCode();
			
			result = planService.modifyInsurePay(insurePay);
		}
		if(result > 0) {
			return "redirect:/plan/insurePayList?projectCode=" + projectCode + "&projectPlanCode=" + projectPlanCode;
		}else {
			return "redirect:/plan/modifyInsurePay?projectCode=" + projectCode + "&insurePayCode=" + insurePayCode + "&projectPlanCode=" + projectPlanCode;
		}
		
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
			projectData.put("availableStatus", "Y");
			
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
			
			/* 농가별 사용가능한 품목조회 */
			List<Map<String, Object>> stockItemList = planService.getStockItem(projectData);
			model.addAttribute("stockItemList", stockItemList);
			
			/* 공과금항목조회 */
			List<Map<String, Object>> taxPayCateCodeList = planService.getTaxPayCateCode();
			model.addAttribute("taxPayCateCodeList", taxPayCateCodeList);
			
			/* 품목카테고리조회 */
			List<StockCate> stockCateList = planService.getStockCateList();
			model.addAttribute("stockCateList", stockCateList);
			
			/* 공통농기계목록조회 */
			List<CommonMachine> commonMachineList = planService.getCommonMachineList();
			model.addAttribute("commonMachineList", commonMachineList);
			
			/* 품목리스트조회 */
			List<StockItem> farmStockItemList = planService.getStockItemList(farmCode);
			model.addAttribute("farmStockItemList", farmStockItemList);
			
			/* 농자재소모현황리스트 */
			Map<String, Object> resourceUsecapacityData = new HashMap<String, Object>();
			resourceUsecapacityData.put("farmCode", farmCode);
			List<Map<String, Object>> resourceUsecapacityList = planService.getStockItem(resourceUsecapacityData);
			model.addAttribute("resourceUsecapacityList", resourceUsecapacityList);
			
		}
		
		return "views/plan/addSpend";
	}
	
	@GetMapping("/plan/addWorkphasePlan")
	public String addWorkphasePlan(Model model
			  					  ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
		
			/* 계획정보조회 */
			Map<String, Object> projectPlanInfo = planService.getProjectPlanInfo(projectPlanCode);
			String projectPlanN = (String) projectPlanInfo.get("projectPlanN");
			String projectCode = (String) projectPlanInfo.get("projectCode");
			model.addAttribute("projectPlanCode", projectPlanCode);
			model.addAttribute("projectPlanN", projectPlanN);
			
			Map<String, Object> projectData = new HashMap<String, Object>();
			projectData.put("projectCode", projectCode);
			
			/* 작업단계 */
			List<Map<String, Object>> workphaseNameList = planService.getWorkphaseName(projectData);
			System.out.println(workphaseNameList);
			model.addAttribute("workphaseNameList", workphaseNameList);
			
			/* 상세작업항목조회 */
			List<Map<String, Object>> workphaseCateNameList = planService.getWorkphaseCateName(projectData);
			model.addAttribute("workphaseCateNameList", workphaseCateNameList);
			
		}
		return "views/plan/addWorkphasePlan";
	}
	
	@PostMapping("/plan/addWorkphasePlan")
	public String addWorkphasePlan(PlanWorkphase planWorkphase, PlanWorkphaseCate planWorkphaseCate, HttpSession session) {
		int result = 0;
		String projectPlanCode = null;
		String memberId = (String) session.getAttribute("SID");
		if(planWorkphase.getPlanWorkphaseCode() != null && !"".equals(planWorkphase.getPlanWorkphaseCode().trim())
				&& planWorkphaseCate.getPlanWorkphaseCateCode() != null && !"".equals(planWorkphaseCate.getPlanWorkphaseCateCode().trim())) {
			
			planWorkphaseCate.setRegMemberId(memberId);
			planWorkphaseCate.setRunStatus("실행전");
			
			result = planService.addPlanWorkphaseCate(planWorkphaseCate);
			projectPlanCode = planWorkphaseCate.getProjectPlanCode();
		}else {
			planWorkphase.setRegMemberId(memberId);
			planWorkphase.setRunStatus("실행전");
			
			result = planService.addPlanWorkphase(planWorkphase);
			projectPlanCode = planWorkphase.getProjectPlanCode();
		}
		
		return "/plan/getSchedule?projectPlanCode=" + projectPlanCode;
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
	
	@PostMapping("/plan/calendarDataList")
	@ResponseBody
	public List<Map<String, Object>> getCalendarDataList(@RequestParam(value = "projectPlanCode", required = false)String projectPlanCode) {
		
		List<Map<String, Object>> workphaseSchedule = null;
		List<Map<String,Object>> calList = new ArrayList<Map<String,Object>>();
		Map<String, Object> workphaseData = null;
		Map<String,Object> data = null;
		String workphaseName = null;
		
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			workphaseSchedule = planService.getPlanWorkphaseSchedule(projectPlanCode);
			
			for(int i = 0; i < workphaseSchedule.size(); i++) {
				data = new HashMap<String,Object>();
			
				workphaseData = workphaseSchedule.get(i);
				workphaseName = workphaseData.get("workphase") + " : " + workphaseData.get("workphaseName");
				
				data.put("title", 		workphaseName);
				data.put("start", 		workphaseData.get("planWorkphaseBegin"));
				data.put("end", 		workphaseData.get("planWorkphaseEnd"));
				data.put("color",		workphaseData.get("planWorkphaseColor"));
				data.put("textColor", 	workphaseData.get("planWorkphaseTextColor"));
				data.put("planWorkphaseCode", 	workphaseData.get("planWorkphaseCode"));
				data.put("projectPlanCode", 	projectPlanCode);
				calList.add(data);
			}
			
			planService.getPlanWorkphaseCateSchedule(projectPlanCode);
			
			System.out.println(calList);
		}
		
		
		
		return calList;
	}
	
	@GetMapping("/plan/test")
	public String test() {
		return "views/plan/test";
	}
	
}
