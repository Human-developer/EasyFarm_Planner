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
		System.out.println(project.toString() + "muyahoooooooooooooooooooooooooooooooooooooo");
		
		return project;
	}
	
	@PostMapping(value = "/result/getWorkPhaseByFarmCode", produces = "application/json")
	public @ResponseBody List<Map<String,Object>> getWorkPhaseByProjectCode( @RequestParam(value = "projectCode",required = false) String projectCode) {
		List<Map<String,Object>> workPhase = null;
		
		workPhase = resultService.getWorkPhaseByProjectCode(projectCode);
		System.out.println(workPhase.toString() + "muyahuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
		
		return workPhase;
	}
	
	
	
	@GetMapping(value = "/result/resultData")
	public String resultMain(Model model,
			@RequestParam(value = "farmCode",required = false) String farmCode,
			@RequestParam(value = "projectCode",required = false) String projectCode,
			@RequestParam(value = "workPhaseCode",required = false) String workPhaseCode) {
		
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
		 
		
		
		return "views/result/resultAlert";
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
	
	@GetMapping("/resultCalendar")
	public String resultTable(Model model) {
		
		return "views/result/resultCalendar";
	}
	
	@GetMapping("/resultData5464654644")
	public String resultData(Model model) {
		
		
		
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
		
		return "views/result/resultMain";
	}
}
