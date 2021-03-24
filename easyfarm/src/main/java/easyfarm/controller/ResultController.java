package easyfarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import easyfarm.domain.result.EtcPayResult;
import easyfarm.domain.result.InsurancePayResult;
import easyfarm.domain.result.MachineLeasePayResult;
import easyfarm.domain.result.MachineUsePayResult;
import easyfarm.domain.result.ProductGainRunResult;
import easyfarm.domain.result.ResourcePayResult;
import easyfarm.domain.result.ResourceUsePlanRunResult;
import easyfarm.domain.result.TaxPayRunResult;
import easyfarm.domain.result.WorkForcePayRunResult;
import easyfarm.service.result.ResultService;

@Controller
public class ResultController {

	@Autowired
	ResultService resultService;
	
	@GetMapping("/result")
	public String resultMain(Model model) {
		
		return "views/result/resultMain";
	}
	
	@GetMapping("/resultEtc")
	public String resultEtc(Model model) {
		
		List<EtcPayResult> etcpayList = resultService.getEtcPayResult();
		List<ResourcePayResult> resourcePayList = resultService.getResourcePayResult();
		List<InsurancePayResult> insurancePayList = resultService.getInsurancePayResult();
		List<MachineLeasePayResult> machineLeasePay = resultService.getMachineLeasePayResult();
		List<MachineUsePayResult> machineUsePay = resultService.getMachineUsePayResult();
		List<ProductGainRunResult> productGainRun = resultService.getProductGainRunResult();
		List<ResourceUsePlanRunResult> resourceUsePlanRun = resultService.getResourceUsePlanRunResult();
		List<TaxPayRunResult> taxPayRun = resultService.getTaxPayRunResult();
		List<WorkForcePayRunResult> workForcePayRun = resultService.getWorkForcePayRunResult();
		
		model.addAttribute("etcpayList", etcpayList);
		model.addAttribute("resourcePayList", resourcePayList);
		model.addAttribute("insurancePayList", insurancePayList);
		model.addAttribute("machineLeasePay", machineLeasePay);
		model.addAttribute("machineUsePay", machineUsePay);
		model.addAttribute("productGainRun", productGainRun);
		model.addAttribute("resourceUsePlanRun", resourceUsePlanRun);
		model.addAttribute("taxPayRun", taxPayRun);
		model.addAttribute("workForcePayRun", workForcePayRun);
		
		return "views/result/resultMain";
	}
}
