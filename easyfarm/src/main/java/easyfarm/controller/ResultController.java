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

import easyfarm.domain.Farm;
import easyfarm.domain.Member;
import easyfarm.domain.plan.EtcPay;
import easyfarm.domain.plan.InsurancePay;
import easyfarm.domain.plan.MachineLeasePay;
import easyfarm.domain.plan.MachineUsePay;
import easyfarm.domain.plan.ProductGain;
import easyfarm.domain.plan.ResourcePay;
import easyfarm.domain.plan.ResourceUsePlan;
import easyfarm.domain.plan.TaxPay;
import easyfarm.domain.plan.WorkForcePay;
import easyfarm.domain.result.EtcPayResult;
import easyfarm.domain.result.InsurancePayResult;
import easyfarm.domain.result.MachineLeasePayResult;
import easyfarm.domain.result.MachineUsePayResult;
import easyfarm.domain.result.ProductGainResult;
import easyfarm.domain.result.ResourcePayResult;
import easyfarm.domain.result.ResourceUsePlanResult;
import easyfarm.domain.result.TaxPayResult;
import easyfarm.domain.result.WorkForcePayResult;
import easyfarm.service.PlanService;
import easyfarm.service.ResultService;

@Controller
public class ResultController {

	@Autowired
	ResultService resultService;
	
	@Autowired
	PlanService planService;
	
	
	@PostMapping(value = "/result/getProjectNameByFarmCode", produces = "application/json")
	public @ResponseBody List<Map<String,Object>> test( @RequestParam(value = "farmCode",required = false) String farmCode,Model model) {
		List<Map<String,Object>> project = null;
		
		project = resultService.getProjectNameByFarmName(farmCode);
		System.out.println(project.toString() + "muyahooooo");
		
		return project;
	}
	
	@PostMapping(value = "/result/getWorkPhaseByProjectCode", produces = "application/json")
	public @ResponseBody List<Map<String,Object>> getWorkPhaseByProjectCode( @RequestParam(value = "projectCode",required = false) String projectCode) {
		List<Map<String,Object>> workPhase = null;
		
		workPhase = resultService.getWorkPhaseByProjectCode(projectCode);
		System.out.println(workPhase.toString() + "muyahuuu");
		
		return workPhase;
	}
	
	
	@GetMapping(value = "/result/resultData")
	public String resultMain(Model model,
			@RequestParam(value = "farmCode",required = false) String farmCode,
			@RequestParam(value = "projectCode",required = false) String projectCode,
			@RequestParam(value = "workPhaseCode",required = false) String workPhaseCode,
			@RequestParam(value = "projectPlanCode",required = false) String projectPlanCode) {
		
		System.out.println("\n\n\n\n\n\n\n\n"+farmCode+"<<farmCode \n\n\n\n\n\n\n\n\n");
		System.out.println("\n\n\n\n\n\n\n\n"+workPhaseCode+"<<workPhaseCode \n\n\n\n\n\n\n\n\n");
		List<EtcPayResult> etcpayResult = resultService.getEtcPayResult(workPhaseCode);
		System.out.println("\n\n\n\n\n\n\n\n"+etcpayResult+"<<etcpayResult \n\n\n\n\n\n\n\n\n");
		
		List<ResourcePayResult> resourcePayResult = resultService.getResourcePayResult();
		List<InsurancePayResult> insurancePayResult = resultService.getInsurancePayResult();
		List<MachineLeasePayResult> machineLeasePayResult = resultService.getMachineLeasePayResult();
		List<MachineUsePayResult> machineUsePayResult = resultService.getMachineUsePayResult();
		List<ProductGainResult> productGainResult = resultService.getProductGainResult();
		List<ResourceUsePlanResult> resourceUsePlanResult = resultService.getResourceUsePlanResult();
		List<TaxPayResult> taxPayResult = resultService.getTaxPayResult();
		List<WorkForcePayResult> workForcePayResult = resultService.getWorkForcePayResult();
		

		model.addAttribute("etcpayResult", etcpayResult);
		
		 model.addAttribute("resourcePayResult", resourcePayResult);
		 model.addAttribute("insurancePayResult", insurancePayResult);
		 model.addAttribute("machineLeasePayResult", machineLeasePayResult);
		 model.addAttribute("machineUsePayResult", machineUsePayResult);
		 model.addAttribute("productGainResult", productGainResult);
		 model.addAttribute("resourceUsePlanResult", resourceUsePlanResult);
		 model.addAttribute("taxPayResult", taxPayResult);
		 model.addAttribute("workForcePayResult", workForcePayResult);
		 
		
		
		return "views/result/resultData";
	}
	
	
	
	
	@GetMapping("/result")
	public String result(Model model,HttpSession session) {
		
		List<Map<String,Object>> farm = null;
		
		 if(session.getAttribute("SID") == null) {
			 return "views/member/login";
		 }
		
		String memberId = (String) session.getAttribute("SID");
		
		if(memberId != null) {
			farm = resultService.getFarmName(memberId);
			model.addAttribute("farm", farm);
		 }
		return "views/result/resultMain";
	}
	
	//캘린더 들어가기전 선택화면
	@GetMapping("/result/beforeCalendar")
	public String resultCalendar(Model model,HttpSession session) {
		
		List<Map<String,Object>> farm = null;
		
		 if(session.getAttribute("SID") == null) {
			return "views/member/login";
		 }
		 String memberId = (String) session.getAttribute("SID");
			
			if(memberId != null) {
				farm = resultService.getFarmName(memberId);
				model.addAttribute("farm", farm);
			 }
			return "views/result/dummyCalendar";
	}
	
	//실행캘린더 화면
	@GetMapping("/result/resultCalendar")
	public String loadCalendar(Model model,
			@RequestParam(value = "projectPlanCode",required = false) String projectPlanCode) {
		
		
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
		return "views/result/resultCalendar";
	}
	
	
	//캘린더 들어가기전 선택화면
		@GetMapping("/result/addWorkphaseResult")
		public String addWorkphaseResult(Model model,
				@RequestParam(value = "projectPlanCode",required = false) String projectPlanCode) {
			
			
			
			 
				return "views/result/addWorkphaseResult";
		}
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/result/select")
	public String selectOptions(Model model,HttpSession session) {
		
		List<Map<String,Object>> farm = null;
		
		 if(session.getAttribute("SID") == null) {
			return "views/member/login";
		 }else {
			 String memberId = (String) session.getAttribute("SID");
				
				if(memberId != null) {
					farm = resultService.getFarmName(memberId);
					model.addAttribute("farm", farm);
				 }
			return "views/result/resultSelect";
		 }
		
		/*
		List<EtcPay> etcPayPlan = planService.getEtcPayPlan();
		List<ResourcePay> resourcePayPlan = planService.getResourcePayPlan();
		List<InsurancePay> insurancePayPlan = planService.getInsurancePayPlan();
		List<MachineLeasePay> machineLeasePayPlan = planService.getMachineLeasePayPlan();
		List<MachineUsePay> machineUsePlan = planService.getMachineUsePayPlan();
		List<ProductGain> productGainPlan = planService.getProductGainPlan();
		List<ResourceUsePlan> resourceUsePlan = planService.getResourceUsePlanPlan();
		List<TaxPay> taxPayPlan = planService.getTaxPayPlan();
		List<WorkForcePay> workForcePayPlan = planService.getWorkForcePayPlan();
		
		model.addAttribute("etcPayPlan", etcPayPlan);
		model.addAttribute("resourcePayPlan", resourcePayPlan);
		model.addAttribute("insurancePayPlan", insurancePayPlan);
		model.addAttribute("machineLeasePayPlan", machineLeasePayPlan);
		model.addAttribute("machineUsePlan", machineUsePlan);
		model.addAttribute("productGainPlan", productGainPlan);
		model.addAttribute("resourceUsePlan", resourceUsePlan);
		model.addAttribute("taxPayPlan", taxPayPlan);
		model.addAttribute("workForcePayPlan", workForcePayPlan);
		*/
		
	}
	
	
	
	
	
	
}
