package easyfarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping("/result")
	public String resultMain(Model model) {
		
		return "views/result/resultMain";
	}
	
	@GetMapping("/resultCalendar")
	public String resultTable(Model model) {
		
		return "views/result/resultCalendar";
	}
	
	@GetMapping("/resultData")
	public String resultData(Model model) {
		
		List<EtcPayResult> etcpayResult = resultService.getEtcPayResult();
		List<ResourcePayResult> resourcePayResult = resultService.getResourcePayResult();
		List<InsurancePayResult> insurancePayResult = resultService.getInsurancePayResult();
		List<MachineLeasePayResult> machineLeasePayResult = resultService.getMachineLeasePayResult();
		List<MachineUsePayResult> machineUsePayResult = resultService.getMachineUsePayResult();
		List<ProductGainResult> productGainResult = resultService.getProductGainResult();
		List<ResourceUsePlanResult> resourceUsePlanResult = resultService.getResourceUsePlanResult();
		List<TaxPayResult> taxPayResult = resultService.getTaxPayResult();
		List<WorkForcePayResult> workForcePayResult = resultService.getWorkForcePayResult();
		
		
		List<EtcPay> etcPayPlan = planService.getEtcPayPlan();
		List<ResourcePay> resourcePayPlan = planService.getResourcePayPlan();
		List<InsurancePay> insurancePayPlan = planService.getInsurancePayPlan();
		List<MachineLeasePay> machineLeasePayPlan = planService.getMachineLeasePayPlan();
		List<MachineUsePay> machineUsePlan = planService.getMachineUsePayPlan();
		List<ProductGain> productGainPlan = planService.getProductGainPlan();
		List<ResourceUsePlan> resourceUsePlan = planService.getResourceUsePlanPlan();
		List<TaxPay> taxPayPlan = planService.getTaxPayPlan();
		List<WorkForcePay> workForcePayPlan = planService.getWorkForcePayPlan();
		

		model.addAttribute("etcpayResult", etcpayResult);
		model.addAttribute("resourcePayResult", resourcePayResult);
		model.addAttribute("insurancePayResult", insurancePayResult);
		model.addAttribute("machineLeasePayResult", machineLeasePayResult);
		model.addAttribute("machineUsePayResult", machineUsePayResult);
		model.addAttribute("productGainResult", productGainResult);
		model.addAttribute("resourceUsePlanResult", resourceUsePlanResult);
		model.addAttribute("taxPayResult", taxPayResult);
		model.addAttribute("workForcePayResult", workForcePayResult);
		
		
		model.addAttribute("etcPayPlan", etcPayPlan);
		model.addAttribute("resourcePayPlan", resourcePayPlan);
		model.addAttribute("insurancePayPlan", insurancePayPlan);
		model.addAttribute("machineLeasePayPlan", machineLeasePayPlan);
		model.addAttribute("machineUsePlan", machineUsePlan);
		model.addAttribute("productGainPlan", productGainPlan);
		model.addAttribute("resourceUsePlan", resourceUsePlan);
		model.addAttribute("taxPayPlan", taxPayPlan);
		model.addAttribute("workForcePayPlan", workForcePayPlan);
		
		return "views/result/resultMain";
	}
}
