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

import easyfarm.domain.plan.Client;
import easyfarm.domain.plan.CommonMachine;
import easyfarm.domain.plan.FarmBookmarkMachine;
import easyfarm.domain.plan.FarmRetainMachine;
import easyfarm.domain.plan.InsurancePay;
import easyfarm.domain.plan.MachineLeasePay;
import easyfarm.domain.plan.MachineUsePay;
import easyfarm.domain.plan.PlanWorkphase;
import easyfarm.domain.plan.PlanWorkphaseCate;
import easyfarm.domain.plan.ProjectPlan;
import easyfarm.domain.plan.ResourcePay;
import easyfarm.domain.plan.ResourceUsePlan;
import easyfarm.domain.plan.StockCate;
import easyfarm.domain.plan.StockItem;
import easyfarm.domain.plan.WorkForcePay;
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
		return "views/plan/insurePay/insurePayList";
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
		return "views/plan/insurePay/addInsurePay";
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
		
		return "views/plan/insurePay/modifyInsurePay";
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
			model.addAttribute("farmCode", farmCode);
			
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
			
			/* 농자재매입지출 조회 */
			List<Map<String, Object>> resourcePayList = planService.getResourcePayList(farmCode);
			model.addAttribute("resourcePayList", resourcePayList);
			
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
		
		int result 				= 0;
		String memberId 		= (String) session.getAttribute("SID");
		String projectPlanCode  = planWorkphase.getProjectPlanCode();
		
		if(planWorkphase.getProjectWorkphaseCode() != null && !"".equals(planWorkphase.getProjectWorkphaseCode().trim())) {
			planWorkphase.setRegMemberId(memberId);
			planWorkphaseCate.setRegMemberId(memberId);
			
			result = planService.addPlanWorkphase(planWorkphase, planWorkphaseCate);
		}
		
		if(result != 0) {
			return "redirect:/plan/getSchedule?projectPlanCode=" + projectPlanCode;
		}else {
			return "redirect:/plan/addWorkphasePlan?projectPlanCode=" + projectPlanCode;
		}
	}
	
	@GetMapping("/plan/modifyWorkphasePlan")
	public String modifyWorkphasePlan(Model model
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
		return "views/plan/modifyWorkphasePlan";
	}
	
	
	@GetMapping("/plan/planScheduleList")
	public String planScheduleList(Model model
								  ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		List<Map<String, Object>> workphaseSchedule = null;
		List<Map<String, Object>> workphaseCateSchedule = null;
		
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			workphaseSchedule 		= planService.getPlanWorkphaseSchedule(projectPlanCode);
			workphaseCateSchedule	= planService.getPlanWorkphaseCateSchedule(projectPlanCode);
			
			model.addAttribute("workphaseSchedule", workphaseSchedule);
			model.addAttribute("workphaseCateSchedule", workphaseCateSchedule);
			model.addAttribute("projectPlanCode", projectPlanCode);
		}
		
		return "views/plan/planScheduleList";
	}
	
	@GetMapping("/plan/planSimpleSchedul")
	public String planSimpleSchedul(Model model
								   ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		
		List<Map<String, Object>> workphaseSchedule = null;
		List<Map<String, Object>> workphaseCateSchedule = null;
		
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			workphaseSchedule 		= planService.getPlanWorkphaseSchedule(projectPlanCode);
			workphaseCateSchedule	= planService.getPlanWorkphaseCateSchedule(projectPlanCode);
			
			model.addAttribute("workphaseSchedule", workphaseSchedule);
			model.addAttribute("workphaseCateSchedule", workphaseCateSchedule);
			model.addAttribute("projectPlanCode", projectPlanCode);
		}
		
		return "views/plan/planSimpleSchedul";
	}
	
	@GetMapping("/plan/planScheduleDetail")
	public String planScheduleDetail(Model model
			  						,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode
			  						,@RequestParam(value = "stockItemCode", required = false) String stockItemCode
			  						,@RequestParam(value = "planWorkphaseCode", required = false)String planWorkphaseCode
			  						,@RequestParam(value = "planWorkphaseCateCode", required = false)String planWorkphaseCateCode) {
		
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
			model.addAttribute("farmCode", farmCode);
			
			/* 작업단계 */
			List<Map<String, Object>> workphaseNameList = planService.getWorkphaseName(projectData);
			System.out.println(workphaseNameList);
			model.addAttribute("workphaseNameList", workphaseNameList);
			
			if(planWorkphaseCode != null && planWorkphaseCateCode == null) {
				PlanWorkphase planWorkphaseInfo = null;
				planWorkphaseInfo = planService.getPlanWorkphaseInfo(planWorkphaseCode);
				model.addAttribute("planWorkphaseInfo", planWorkphaseInfo);
				model.addAttribute("planWorkphaseCode", planWorkphaseCode);
			}
			
			if(planWorkphaseCateCode != null && !"".equals(planWorkphaseCateCode.trim())) {
				/* 상세작업항목조회 */
				List<Map<String, Object>> workphaseCateNameList = planService.getWorkphaseCateName(projectData);
				model.addAttribute("workphaseCateNameList", workphaseCateNameList);
				
				Map<String, Object> planWorkphaseCateInfo = null;
				planWorkphaseCateInfo = planService.getPlanWorkphaseCateInfo(planWorkphaseCateCode);
				model.addAttribute("planWorkphaseCateInfo", planWorkphaseCateInfo);
				model.addAttribute("planWorkphaseCode", planWorkphaseCode);
				model.addAttribute("planWorkphaseCateCode", planWorkphaseCateCode);
			}
			
			//작업단계별, 상세항목별 지출계획 전체 조회
			Map<String,List<Map<String, Object>>> allPlanSchedule = null;
			allPlanSchedule = planService.getAllPlanSchedule(planWorkphaseCode, planWorkphaseCateCode);
			model.addAttribute("allPlanSchedule", allPlanSchedule);
			System.out.println(allPlanSchedule + " <<<<<<<<<<<<<<< allPlanSchedule");
			
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
			
			/* 농자재매입지출 조회 */
			List<Map<String, Object>> resourcePayList = planService.getResourcePayList(farmCode);
			model.addAttribute("resourcePayList", resourcePayList);
			
			/* 농자재소모현황리스트 */
			Map<String, Object> resourceUsecapacityData = new HashMap<String, Object>();
			resourceUsecapacityData.put("farmCode", farmCode);
			List<Map<String, Object>> resourceUsecapacityList = planService.getStockItem(resourceUsecapacityData);
			model.addAttribute("resourceUsecapacityList", resourceUsecapacityList);
			
		}
		
		return "views/plan/planScheduleDetail";
	}
	
	//거래처 조회
	@GetMapping("/plan/planClientList")
	public String planClientList(Model model
								,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
			if(farmCode != null && !"".equals(farmCode.trim())) {
				
				Map<String, Object> projectData = new HashMap<String, Object>();
				projectData.put("farmCode", farmCode);
				
				/* 거래처항목조회 */
				List<Map<String, Object>> clientNameList = planService.getClientName(projectData);
				model.addAttribute("clientNameList", clientNameList);
				model.addAttribute("farmCode", farmCode);
			}
		
		return"views/plan/client/planClientList";
	}
	
	//거래처등록
	@GetMapping("/plan/addPlanClient")
	public String addPlanClient(Model model
							   ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		if(farmCode != null && !"".equals(farmCode.trim())) {
			model.addAttribute("farmCode", farmCode);
		}
		return "views/plan/client/addPlanClient";
	}
	
	//거래처등록
	@PostMapping("/plan/addPlanClient")
	public String addPlanClient(Client client, HttpSession session
							   ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
		int result = 0;
		String memberId = (String) session.getAttribute("SID");
		
		if(client.getFarmCode() != null && !"".equals(client.getFarmCode().trim()) && memberId != null) {
			
			client.setRegMemberId(memberId);
			
			result = planService.addClient(client);
		}
		
		return "redirect:/plan/planClientList?farmCode=" + farmCode;
	}
	
	//거래처수정
	@GetMapping("/plan/modifyPlanClient")
	public String modifyPlanClient(Model model
							      ,@RequestParam(value = "farmCode", required = false) String farmCode
							      ,@RequestParam(value = "clientCode", required = false) String clientCode) {
		Client client = null;
		
		if(clientCode != null && !"".equals(clientCode.trim())) {
			
			client = planService.getClientInfo(clientCode);
			
			model.addAttribute("farmCode", farmCode);
			model.addAttribute("client", client);
		}
		return "views/plan/client/modifyPlanClient";
	}
	
	//거래처수정
	@PostMapping("/plan/modifyPlanClient")
	public String modifyPlanClient(Client client
							      ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		int result = 0;
		
		if(client.getFarmCode() != null && !"".equals(client.getFarmCode().trim())) {
			
			result = planService.modifyClient(client);
		}
		
		return "redirect:/plan/planClientList?farmCode=" + farmCode;
	}
	
	//거래처삭제
	@GetMapping("/plan/removeClient")
	public String removeClient(@RequestParam(value = "clientCode", required = false) String clientCode
						      ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
		int result = 0;
		if(clientCode != null && !"".equals(clientCode.trim())) {
			
			result = planService.removeClient(clientCode);
		}
		return "redirect:/plan/planClientList?farmCode=" + farmCode;
	}
	
	//품목 조회
	@GetMapping("/plan/planStockItemList")
	public String planStockItemList(Model model
									,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
			if(farmCode != null && !"".equals(farmCode.trim())) {
				
				/* 품목리스트조회 */
				List<StockItem> farmStockItemList = planService.getStockItemList(farmCode);
				model.addAttribute("farmStockItemList", farmStockItemList);
				model.addAttribute("farmCode", farmCode);
			}
		
		return"views/plan/stockItem/planStockItemList";
	}
	
	//품목등록
	@GetMapping("/plan/addPlanStockItem")
	public String addPlanStockItem(Model model
							      ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
		if(farmCode != null && !"".equals(farmCode.trim())) {
			/* 품목카테고리조회 */
			List<StockCate> stockCateList = planService.getStockCateList();
			model.addAttribute("stockCateList", stockCateList);
			model.addAttribute("farmCode", farmCode);
		}
		return "views/plan/stockItem/addPlanStockItem";
	}
	
	//품목등록
	@PostMapping("/plan/addPlanStockItem")
	public String addPlanStockItem(StockItem stockItem, HttpSession session
								  ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
		String memberId = (String) session.getAttribute("SID");
		int result = 0;
		
		if(stockItem.getFarmCode() != null && !"".equals(stockItem.getFarmCode().trim()) && memberId != null) {
			
			stockItem.setRegMemberId(memberId);
			
			result = planService.addStockItem(stockItem);
		}
		return "redirect:/plan/planStockItemList?farmCode=" + farmCode;
	}
	
	//품목수정
	@GetMapping("/plan/modifyPlanStockItem")
	public String modifyPlanStockItem(Model model
							      ,@RequestParam(value = "farmCode", required = false) String farmCode
							      ,@RequestParam(value = "stockItemCode", required = false) String stockItemCode) {
		StockItem stockItem = null;
		
		if(stockItemCode != null && !"".equals(stockItemCode.trim())) {
			
			stockItem = planService.getStockItemInfoByCode(stockItemCode);
			
			/* 품목카테고리조회 */
			List<StockCate> stockCateList = planService.getStockCateList();
			model.addAttribute("stockCateList", stockCateList);
			
			model.addAttribute("farmCode", farmCode);
			model.addAttribute("stockItem", stockItem);
		}
		return "views/plan/stockItem/modifyPlanStockItem";
	}
	
	//품목수정
	@PostMapping("/plan/modifyPlanStockItem")
	public String modifyPlanStockItem(StockItem stockItem
							      	 ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		int result = 0;
		
		if(stockItem.getStockItemCode() != null && !"".equals(stockItem.getStockItemCode().trim())) {
			
			result = planService.modifyStockItem(stockItem);
		}
		return "redirect:/plan/planStockItemList?farmCode=" + farmCode;
	}
	
	//품목삭제
	@GetMapping("/plan/removePlanStockItem")
	public String removePlanStockItem(@RequestParam(value = "stockItemCode", required = false) String stockItemCode
							         ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		int result = 0;
		
		if(stockItemCode != null && !"".equals(stockItemCode.trim())) {
			
			result = planService.removeStockItem(stockItemCode);
		}
		return "redirect:/plan/planStockItemList?farmCode=" + farmCode;
	}
	
	//농자재매입 조회
	@GetMapping("/plan/planResourcePayList")
	public String planResourcePayList(Model model
									,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
			if(farmCode != null && !"".equals(farmCode.trim())) {
				
				/* 농자재매입지출 조회 */
				List<Map<String, Object>> resourcePayList = planService.getResourcePayList(farmCode);
				model.addAttribute("resourcePayList", resourcePayList);
				
				model.addAttribute("farmCode", farmCode);
			}
		
		return"views/plan/resourcePay/planResourcePayList";
	}
	
	//농자재매입 등록
	@GetMapping("/plan/addPlanResourcePay")
	public String addPlanResourcePay(Model model
							      ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
		if(farmCode != null && !"".equals(farmCode.trim())) {
			
			Map<String, Object> projectData = new HashMap<String, Object>();
			projectData.put("farmCode", farmCode);
			
			/* 거래처항목조회 */
			List<Map<String, Object>> clientNameList = planService.getClientName(projectData);
			model.addAttribute("clientNameList", clientNameList);
			
			/* 품목리스트조회 */
			List<StockItem> farmStockItemList = planService.getStockItemList(farmCode);
			model.addAttribute("farmStockItemList", farmStockItemList);
			
			model.addAttribute("farmCode", farmCode);
		}
		return "views/plan/resourcePay/addPlanResourcePay";
	}
	
	//농자재매입 등록
	@PostMapping("/plan/addPlanResourcePay")
	public String addPlanResourcePay(ResourcePay resourcePay, HttpSession session
								  ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
		String memberId = (String) session.getAttribute("SID");
		int result = 0;
		
		if(resourcePay.getFarmCode() != null && !"".equals(resourcePay.getFarmCode().trim()) && memberId != null) {
			
			resourcePay.setRegMemberId(memberId);
			
			result = planService.addResourcePay(resourcePay);
		}
		return "redirect:/plan/planResourcePayList?farmCode=" + farmCode;
	}
	
	//농자재매입 수정
	@GetMapping("/plan/modifyPlanResourcePay")
	public String modifyPlanResourcePay(Model model
							      ,@RequestParam(value = "farmCode", required = false) String farmCode
							      ,@RequestParam(value = "resourcePayCode", required = false) String resourcePayCode) {
		ResourcePay resourcePay = null;
		
		if(resourcePayCode != null && !"".equals(resourcePayCode.trim())) {
			
			resourcePay = planService.getResourcePayInfo(resourcePayCode);
			
			Map<String, Object> projectData = new HashMap<String, Object>();
			projectData.put("farmCode", farmCode);
			
			/* 거래처항목조회 */
			List<Map<String, Object>> clientNameList = planService.getClientName(projectData);
			model.addAttribute("clientNameList", clientNameList);
			
			/* 품목리스트조회 */
			List<StockItem> farmStockItemList = planService.getStockItemList(farmCode);
			model.addAttribute("farmStockItemList", farmStockItemList);
			
			model.addAttribute("farmCode", farmCode);
			model.addAttribute("resourcePay", resourcePay);
		}
		return "views/plan/resourcePay/modifyPlanResourcePay";
	}
	
	//농자재매입 수정
	@PostMapping("/plan/modifyPlanResourcePay")
	public String modifyPlanResourcePay(ResourcePay resourcePay
							      	 ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		int result = 0;
		
		if(resourcePay.getResourcePayCode() != null && !"".equals(resourcePay.getResourcePayCode().trim())) {
			
			result = planService.modifyPlanResourcePay(resourcePay);
		}
		return "redirect:/plan/planResourcePayList?farmCode=" + farmCode;
	}
	
	//농자재매입 삭제
	/*@GetMapping("/plan/removePlanStockItem")
	public String removePlanStockItem(@RequestParam(value = "stockItemCode", required = false) String stockItemCode
							         ,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode
							         ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		int result = 0;
		
		if(stockItemCode != null && !"".equals(stockItemCode.trim())) {
			
			result = planService.removeStockItem(stockItemCode);
		}
		return "redirect:/plan/planStockItemList?projectPlanCode=" + projectPlanCode + "&farmCode=" + farmCode;
	}*/
	
	//농기계 즐겨찾기 조회
	@GetMapping("/plan/planFarmBookmarkMachineList")
	public String planFarmBookmarkMachineList(Model model
									,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
			if(farmCode != null && !"".equals(farmCode.trim())) {
				
				Map<String, Object> projectData = new HashMap<String, Object>();
				projectData.put("farmCode", farmCode);
				
				/* 농기계즐겨찾기조회 */
				List<Map<String, Object>> farmBookmarkMachineList = planService.getFarmBookmarkMachine(projectData);
				model.addAttribute("farmBookmarkMachineList", farmBookmarkMachineList);
				
				model.addAttribute("farmCode", farmCode);
			}
		
		return"views/plan/farmBookmarkMachine/planFarmBookmarkMachineList";
	}
	
	//농기계 즐겨찾기 등록
	@GetMapping("/plan/addFarmBookmarkMachine")
	public String addFarmBookmarkMachine(Model model
							      ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
		if(farmCode != null && !"".equals(farmCode.trim())) {
			
			/* 공통농기계목록조회 */
			List<Map<String, Object>> commonMachineList = planService.getFarmCommonMachineList(farmCode);
			model.addAttribute("commonMachineList", commonMachineList);
			
			model.addAttribute("farmCode", farmCode);
		}
		return "views/plan/farmBookmarkMachine/addFarmBookmarkMachine";
	}
	
	//농기계 즐겨찾기 등록
	@PostMapping("/plan/addFarmBookmarkMachine")
	public String addFarmBookmarkMachine(HttpSession session
								  ,@RequestParam(value = "farmCode", required = false) String farmCode
								  ,@RequestParam(value = "commonMachineCode", required = false) List<String> commonMachineCode) {
		
		String memberId = (String) session.getAttribute("SID");
		FarmBookmarkMachine farmBookmarkMachine = null;
		int result = 0;
		
		if(commonMachineCode.size() > 0 && memberId != null && farmCode != null) {
			
			farmBookmarkMachine = new FarmBookmarkMachine();
			
			farmBookmarkMachine.setRegMemberId(memberId);
			farmBookmarkMachine.setFarmCode(farmCode);
			
			for(int i = 0; i < commonMachineCode.size(); i++) {
				farmBookmarkMachine.setCommonMachineCode(commonMachineCode.get(i));
				result = planService.addFarmBookmarkMachine(farmBookmarkMachine);
			}
		}
		return "redirect:/plan/planFarmBookmarkMachineList?farmCode=" + farmCode;
	}
	
	//농기계 즐겨찾기 삭제
	@GetMapping("/plan/removePlanFarmBookmarkMachine")
	public String removeFarmBookmarkMachine(@RequestParam(value = "farmBookmarkMachineCode", required = false) String farmBookmarkMachineCode
							         	   ,@RequestParam(value = "farmCode", required = false) String farmCode) {
		int result = 0;
		
		if(farmBookmarkMachineCode != null && !"".equals(farmBookmarkMachineCode.trim())) {
			
			result = planService.removeFarmBookmarkMachine(farmBookmarkMachineCode);
		}
		return "redirect:/plan/planFarmBookmarkMachineList?farmCode=" + farmCode;
	}
	
	//보유 농기계 조회
	@GetMapping("/plan/getPlanFarmRetainMachineList")
	public String getPlanFarmRetainMachineList(Model model
											,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
		if(farmCode != null && !"".equals(farmCode.trim())) {
			
			Map<String, Object> projectData = new HashMap<String, Object>();
			projectData.put("farmCode", farmCode);
			
			/* 보유농기계조회 */
			List<Map<String, Object>> farmRetainMachineList = planService.getFarmRetainMachine(projectData);
			model.addAttribute("farmRetainMachineList", farmRetainMachineList);
			
			model.addAttribute("farmCode", farmCode);
		}
		
		return"views/plan/farmRetainMachine/getPlanFarmRetainMachineList";
	}
	
	//보유 농기계 등록
	@GetMapping("/plan/addPlanFarmRetainMachine")
	public String addPlanFarmRetainMachine(Model model
										,@RequestParam(value = "farmCode", required = false) String farmCode) {
		
		if(farmCode != null && !"".equals(farmCode.trim())) {
			
			Map<String, Object> projectData = new HashMap<String, Object>();
			projectData.put("farmCode", farmCode);
			
			/* 보유농기계에 등록한 항목 제외한 농기계 즐겨찾기 리스트  */
			List<Map<String, Object>> farmBookmarkMachineList = planService.getFarmBookmarkMachineList(farmCode);
			model.addAttribute("farmBookmarkMachineList", farmBookmarkMachineList);
			
			model.addAttribute("farmCode", farmCode);
		}
		return "views/plan/farmRetainMachine/addPlanFarmRetainMachine";
	}
	
	
	//보유 농기계 등록
	@PostMapping("/plan/addPlanFarmRetainMachine")
	public String addPlanFarmRetainMachine(HttpSession session
			,@RequestParam(value = "farmCode", required = false) String farmCode
			,@RequestParam(value = "farmBookmarkMachineCode", required = false) List<String> farmBookmarkMachineCode) {
		
		String memberId = (String) session.getAttribute("SID");
		FarmRetainMachine farmRetainMachine = null;
		int result = 0;
		
		if(farmBookmarkMachineCode.size() > 0 && memberId != null && farmCode != null) {
			
			farmRetainMachine = new FarmRetainMachine();
			
			farmRetainMachine.setRegMemberId(memberId);
			farmRetainMachine.setFarmCode(farmCode);
			
			for(int i = 0; i < farmBookmarkMachineCode.size(); i++) {
				farmRetainMachine.setFarmBookmarkMachineCode(farmBookmarkMachineCode.get(i));
				result = planService.addPlanFarmRetainMachine(farmRetainMachine);
			}
		}
		return "redirect:/plan/getPlanFarmRetainMachineList?farmCode=" + farmCode;
	}
	
	//보유 농기계 삭제
	@GetMapping("/plan/removePlanFarmRetainMachine")
	public String removePlanFarmRetainMachine(@RequestParam(value = "farmRetainMachineCode", required = false) String farmRetainMachineCode
											,@RequestParam(value = "farmCode", required = false) String farmCode) {
		int result = 0;
		
		if(farmRetainMachineCode != null && !"".equals(farmRetainMachineCode.trim())) {
			
			result = planService.removePlanFarmRetainMachine(farmRetainMachineCode);
		}
		return "redirect:/plan/getPlanFarmRetainMachineList?farmCode=" + farmCode;
	}
	
	//인건비 지출계획 등록
	@PostMapping("/plan/addWorkforcePay")
	public String addWorkforcePay(HttpSession session, WorkForcePay workForcePay
								,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		String memberId = (String) session.getAttribute("SID");
		String urlParam = "";
		int result = 0;
		
		if(workForcePay.getPlanWorkphaseCode() != null && !"".equals(workForcePay.getPlanWorkphaseCode()) && memberId != null) {
			workForcePay.setRegMemberId(memberId);
			result = planService.addWorkforcePay(workForcePay);
		}
			
		if(workForcePay.getPlanWorkphaseCateCode() != null && !"".equals(workForcePay.getPlanWorkphaseCateCode())) {
			urlParam = "&planWorkphaseCateCode=" + workForcePay.getPlanWorkphaseCateCode();
		}
		return "redirect:/plan/planScheduleDetail?projectPlanCode=" + projectPlanCode + "&planWorkphaseCode=" + workForcePay.getPlanWorkphaseCode() + urlParam;
	}
	
	//농기계 대여 지출계획 등록
	@PostMapping("/plan/addMachineLeasePay")
	public String addMachineLeasePay(HttpSession session, MachineLeasePay machineLeasePay
									,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		
		String memberId = (String) session.getAttribute("SID");
		String urlParam = "";
		int result = 0;
		
		if(machineLeasePay.getPlanWorkphaseCode() != null && !"".equals(machineLeasePay.getPlanWorkphaseCode()) && memberId != null) {
			machineLeasePay.setRegMemberId(memberId);
			result = planService.addMachineLeasePay(machineLeasePay);
		}
			
		if(machineLeasePay.getPlanWorkphaseCateCode() != null && !"".equals(machineLeasePay.getPlanWorkphaseCateCode())) {
			urlParam = "&planWorkphaseCateCode=" + machineLeasePay.getPlanWorkphaseCateCode();
		}
		return "redirect:/plan/planScheduleDetail?projectPlanCode=" + projectPlanCode + "&planWorkphaseCode=" + machineLeasePay.getPlanWorkphaseCode() + urlParam;
	}
	
	//보유농기계 지출계획 등록
	@PostMapping("/plan/addMachineUsePay")
	public String addMachineUsePay(HttpSession session, MachineUsePay machineUsePay
								,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		
		String memberId = (String) session.getAttribute("SID");
		String urlParam = "";
		int result = 0;
		
		if(machineUsePay.getPlanWorkphaseCode() != null && !"".equals(machineUsePay.getPlanWorkphaseCode()) && memberId != null) {
			machineUsePay.setRegMemberId(memberId);
			result = planService.addMachineUsePay(machineUsePay);
		}
		
		if(machineUsePay.getPlanWorkphaseCateCode() != null && !"".equals(machineUsePay.getPlanWorkphaseCateCode())) {
			urlParam = "&planWorkphaseCateCode=" + machineUsePay.getPlanWorkphaseCateCode();
		}
		return "redirect:/plan/planScheduleDetail?projectPlanCode=" + projectPlanCode + "&planWorkphaseCode=" + machineUsePay.getPlanWorkphaseCode() + urlParam;
	}
	
	//농자재사용 지출계획 등록
	@PostMapping("/plan/addResourceUsePlan")
	public String addResourceUsePlan(HttpSession session, ResourceUsePlan resourceUsePlan
									,@RequestParam(value = "projectPlanCode", required = false) String projectPlanCode) {
		
		String memberId = (String) session.getAttribute("SID");
		String urlParam = "";
		int result = 0;
		
		if(resourceUsePlan.getPlanWorkphaseCode() != null && !"".equals(resourceUsePlan.getPlanWorkphaseCode()) && memberId != null) {
			resourceUsePlan.setRegMemberId(memberId);
			result = planService.addResourceUsePlan(resourceUsePlan);
		}
		
		if(resourceUsePlan.getPlanWorkphaseCateCode() != null && !"".equals(resourceUsePlan.getPlanWorkphaseCateCode())) {
			urlParam = "&planWorkphaseCateCode=" + resourceUsePlan.getPlanWorkphaseCateCode();
		}
		return "redirect:/plan/planScheduleDetail?projectPlanCode=" + projectPlanCode + "&planWorkphaseCode=" + resourceUsePlan.getPlanWorkphaseCode() + urlParam;
	}
	
	
	@GetMapping("/plan/resultPlan")
	public String resultPlan() {
		return "views/plan/resultPlan";
	}
	
	@GetMapping("/plan/result")
	public String result() {
		return "views/plan/result";
	}
	
	@PostMapping("/plan/calendarDataList")
	@ResponseBody
	public List<Map<String, Object>> getCalendarDataList(@RequestParam(value = "projectPlanCode", required = false)String projectPlanCode) {
		
		List<Map<String, Object>> workphaseSchedule = null;
		List<Map<String, Object>> workphaseCateSchedule = null;
		List<Map<String,Object>> calList = new ArrayList<Map<String,Object>>();
		Map<String, Object> workphaseData = null;
		Map<String,Object> data = null;
		String workphaseName = null;
		
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			workphaseSchedule 		= planService.getPlanWorkphaseSchedule(projectPlanCode);
			workphaseCateSchedule	= planService.getPlanWorkphaseCateSchedule(projectPlanCode);
			
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
			
			for(int i = 0; i < workphaseCateSchedule.size(); i++) {
				data = new HashMap<String,Object>();
				
				workphaseData = workphaseCateSchedule.get(i);
				workphaseName = workphaseData.get("workphase") + " : " + workphaseData.get("workphaseName");
				
				data.put("workphaseName", 			workphaseName);
				data.put("title", 					workphaseData.get("commonWorkphaseCateName"));
				data.put("start", 					workphaseData.get("planWorkphaseCateBegin"));
				data.put("end", 					workphaseData.get("planWorkphaseCateEnd"));
				data.put("planWorkphaseBegin", 		workphaseData.get("planWorkphaseBegin"));
				data.put("planWorkphaseEnd", 		workphaseData.get("planWorkphaseEnd"));
				data.put("planWorkphaseCode", 		workphaseData.get("planWorkphaseCode"));
				data.put("color",					workphaseData.get("planWorkphaseColor"));
				data.put("textColor", 				workphaseData.get("planWorkphaseTextColor"));
				data.put("planWorkphaseCateCode", 	workphaseData.get("planWorkphaseCateCode"));
				data.put("projectPlanCode", 	projectPlanCode);
				
				calList.add(data);
			}
		}
		return calList;
	}
	
	@PostMapping("/plan/ajax/getStockItemInfo")
	@ResponseBody
	public Map<String, Object> getStockItemInfo(@RequestParam(value = "resourceStockItemCode", required = false) String resourceStockItemCode) {
		
		Map<String, Object> stockItemInfo = null;
		if(resourceStockItemCode != null && !"".equals(resourceStockItemCode.trim())) {
			stockItemInfo = planService.getStockItemInfo(resourceStockItemCode);
		}
		return stockItemInfo;
	}
	
	//계획서간편보기 전체 리스트 조회 ajax
	@PostMapping("/plan/ajax/getAllPlanSchedule")
	@ResponseBody
	public Map<String,List<Map<String, Object>>> getAllPlanSchedule(@RequestParam(value = "planWorkphaseCateCode",required = false) String planWorkphaseCateCode
																   ,@RequestParam(value = "planWorkphaseCode",required = false) String planWorkphaseCode) {
		
		Map<String,List<Map<String, Object>>> result = null;
		
		if(planWorkphaseCode != null && !"".equals(planWorkphaseCode.trim())) {
			
			//작업단계별, 상세항목별 지출계획 전체 조회
			result = planService.getAllPlanSchedule(planWorkphaseCode, planWorkphaseCateCode);
		}
		return result;
	}
	
}
