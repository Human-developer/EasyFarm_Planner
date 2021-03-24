package easyfarm.service.plan;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import easyfarm.dao.plan.PlanMapper;
import easyfarm.domain.plan.EtcPay;
import easyfarm.domain.plan.InsurancePay;
import easyfarm.domain.plan.MachineLeasePay;
import easyfarm.domain.plan.MachineUsePay;
import easyfarm.domain.plan.ProductGain;
import easyfarm.domain.plan.ResourcePay;
import easyfarm.domain.plan.ResourceUsePlan;
import easyfarm.domain.plan.TaxPay;
import easyfarm.domain.plan.WorkForcePay;

@Service
@Transactional
public class PlanService {
	
	
	//연관 맺어주기 =  DI 필드 주입방식
	/*@Autowired
	private MemberMapper memberMapper;
	
	private MemberMapper memberMapper2;
	// 2. DI SETTER 메서드 주입방식
	@Autowired
	public void setMemberMapper(MemberMapper memberMapper2) {
		this.memberMapper2 = memberMapper;
	}
	*/
	// 3. DI 생성자 메서드 주입방식 (spring권장)
	private final PlanMapper planMapper;
	
	//3-1 spring framework 4.3 이후부터는 @Autowired 쓰지 않아도 주입 가능
	@Autowired
	public PlanService(PlanMapper planMapper) {
		this.planMapper = planMapper;
	}
	@PostConstruct
	public void initialize() {
		System.out.println("======================================");
		System.out.println("PlanService bean 등록");
		System.out.println("======================================");
	}	
	
	
	public List<EtcPay> getEtcPayPlan(){
		List<EtcPay> etcPayPlan = null;
		System.out.println("getEtcPayPlan !@@@@@@@@@@@@@@@@@@@@@@@@");
		etcPayPlan =planMapper.getEtcPayPlan();
		return etcPayPlan;
	}
	public List<ResourcePay> getResourcePayPlan(){
		List<ResourcePay> resourcePayPlan = null;
		resourcePayPlan =planMapper.getResourcePayPlan();
		
		return resourcePayPlan;
	}
	public List<InsurancePay> getInsurancePayPlan(){
		List<InsurancePay> insurancePayPlan = null;
		insurancePayPlan =planMapper.getInsurancePayPlan();
		
		return insurancePayPlan;
	}
	public List<MachineLeasePay> getMachineLeasePayPlan(){
		List<MachineLeasePay> machineLeasePayPlan = null;
		machineLeasePayPlan =planMapper.getMachineLeasePayPlan();
		
		return machineLeasePayPlan;
	}
	public List<MachineUsePay> getMachineUsePayPlan(){
		List<MachineUsePay> machineUsePayPlan = null;
		machineUsePayPlan =planMapper.getMachineUsePayPlan();
		
		return machineUsePayPlan;
	}
	public List<ProductGain> getProductGainPlan(){
		List<ProductGain> productGainPlan = null;
		productGainPlan =planMapper.getProductGainPlan();
		
		return productGainPlan;
	}
	public List<ResourceUsePlan> getResourceUsePlanPlan(){
		List<ResourceUsePlan> resourceUsePlan = null;
		resourceUsePlan =planMapper.getResourceUsePlan();
		return resourceUsePlan;
	}
	public List<TaxPay> getTaxPayPlan(){
		List<TaxPay> taxPayPlan = null;
		taxPayPlan =planMapper.getTaxPayPlan();
		
		return taxPayPlan;
	}
	public List<WorkForcePay> getWorkForcePayPlan(){
		List<WorkForcePay> workForcePayPlan = null;
		workForcePayPlan =planMapper.getWorkForcePayPlan();
		
		return workForcePayPlan;
	}
	
	
	
}
