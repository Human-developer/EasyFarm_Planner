package easyfarm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import easyfarm.domain.plan.PlanWorkphaseCate;
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
	
	//프로젝트명 리스트 가져오기
	@PostMapping(value = "/result/getProjectNameByFarmCode", produces = "application/json")
	public @ResponseBody List<Map<String,Object>> test( @RequestParam(value = "farmCode",required = false) String farmCode,Model model) {
		List<Map<String,Object>> project = null;
		
		project = resultService.getProjectNameByFarmName(farmCode);
		System.out.println(project.toString() + "muyahooooo");
		
		return project;
	}
	
	//작업단계 가져오기
	@PostMapping(value = "/result/getWorkPhaseByProjectCode", produces = "application/json")
	public @ResponseBody List<Map<String,Object>> getWorkPhaseByProjectCode( @RequestParam(value = "projectCode",required = false) String projectCode) {
		List<Map<String,Object>> workPhase = null;
		
		workPhase = resultService.getWorkPhaseByProjectCode(projectCode);
		System.out.println(workPhase.toString() + "muyahuuu");
		
		return workPhase;
	}
	
	//선택한 대상의 결과데이터 출력
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
	
	
	
	//기본 매핑
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
	
	
	//날짜 변경시 데이터 다시 
		@PostMapping(value = "/result/getPlanDataforResult", produces = "application/json")
		public @ResponseBody String getPlanDataforResult(Model model,
				@RequestParam(value = "selectedDate",required = false) String selectedDate,
				@RequestParam(value = "planWorkphaseCate",required = false) String planWorkphaseCate
				) {
			System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+ planWorkphaseCate + selectedDate +"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			List<Map<String,Object>> planData = null;
			String defaultDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			
			return null;
		}
	
	
	
	
	//계획캘린더에서 실행버튼 클릭시
	@GetMapping("/result/addResult")
	public String resultCalendar(Model model,HttpSession session,
			@RequestParam(value = "projectPlanCode",required = false) String projectPlanCode) {
		
		//default 선택된 날짜는 오늘
		String selectedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		if(session.getAttribute("SID") == null) {
			return "views/member/login";
		}
		if(projectPlanCode == null) {
			projectPlanCode = "project_plan_1";
		}
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			/* projectPlanCode로 농가코드, 프로젝트코드, 작업단계코드 조회하기 */
			Map<String, Object> projectPlanInfo = planService.getProjectPlanInfo(projectPlanCode);
			String projectPlanN = (String)projectPlanInfo.get("projectPlanN");
			String projectCode =  (String)projectPlanInfo.get("projectCode");
			String farmCode =  (String)projectPlanInfo.get("farmCode");
			System.out.println(projectPlanN + "<<<< projectPlanN    "+projectCode + "<<<< projectCode   "+farmCode + "<<<< farmCode   ");
			
			//작업단계코드 조회하기
			List<PlanWorkphaseCate> planWorkphaseCate = resultService.getPlanWorkphaseCate(selectedDate, projectPlanCode);
			System.out.println(planWorkphaseCate.get(0).getPlanWorkphaseCode() + "<<<  작업코드@@@");
			
			
			for(int i=0; i < planWorkphaseCate.size();i++) {
				
				List<EtcPayResult> etcpayResult = resultService.getEtcPayResult(planWorkphaseCate.get(i).getPlanWorkphaseCode());
				if(etcpayResult!=null) model.addAttribute("etcpayResult"+i, etcpayResult);
				
				//데이터가 없어서 디폴트값으로 함@@@@
				List<ResourcePayResult> resourcePayResult = resultService.getResourcePayResult();
				List<InsurancePayResult> insurancePayResult = resultService.getInsurancePayResult();
				List<MachineLeasePayResult> machineLeasePayResult = resultService.getMachineLeasePayResult();
				List<MachineUsePayResult> machineUsePayResult = resultService.getMachineUsePayResult();
				List<ProductGainResult> productGainResult = resultService.getProductGainResult();
				List<ResourceUsePlanResult> resourceUsePlanResult = resultService.getResourceUsePlanResult();
				List<TaxPayResult> taxPayResult = resultService.getTaxPayResult();
				List<WorkForcePayResult> workForcePayResult = resultService.getWorkForcePayResult();
				
				model.addAttribute("resourcePayResult", resourcePayResult);
				model.addAttribute("insurancePayResult", insurancePayResult);
				model.addAttribute("machineLeasePayResult", machineLeasePayResult);
				model.addAttribute("machineUsePayResult", machineUsePayResult);
				model.addAttribute("productGainResult", productGainResult);
				model.addAttribute("resourceUsePlanResult", resourceUsePlanResult);
				model.addAttribute("taxPayResult", taxPayResult);
				model.addAttribute("workForcePayResult", workForcePayResult);
			}
			
			
			model.addAttribute("projectPlanCode",projectPlanCode);
			model.addAttribute("selectedDate",selectedDate);
			model.addAttribute("planWorkphaseCate",planWorkphaseCate);
		}
			return "views/result/addResult";
	}
	
	
	
	
	
	
	
	
	
	
	
	//쓸데없는거 처리장소
	@GetMapping("/result/dino")
	public String selectOptions(Model model,HttpSession session) {
		
			return "views/result/dino";
		
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
