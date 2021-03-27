package easyfarm.service;

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

import easyfarm.dao.PgsPlanMapper;
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
public class PgsPlanService {
	
	private final PgsPlanMapper pgsPlanMapper;
	
	public PgsPlanService(PgsPlanMapper pgsPlanMapper) {
		this.pgsPlanMapper = pgsPlanMapper;
	}
	
	//프로젝트 계획차수 조회
	public Map<String, Object> getProjectPlanInfo(String projectPlanCode) {
		return pgsPlanMapper.getProjectPlanInfo(projectPlanCode);
	}
	
	//프로젝트별 작업단계
	public List<Map<String, Object>> getWorkphaseName(String projectCode) {
		return pgsPlanMapper.getWorkphaseName(projectCode);
	}
	
	//프로젝트별 상세작업항목
	public List<Map<String, Object>> getWorkphaseCateName(String projectCode) {
		return pgsPlanMapper.getWorkphaseCateName(projectCode);
	}
	
	public List<EtcPay> getEtcPayPlan(){
		List<EtcPay> etcPayPlan = null;
		System.out.println("getEtcPayPlan !@@@@@@@@@@@@@@@@@@@@@@@@");
		etcPayPlan =pgsPlanMapper.getEtcPayPlan();
		return etcPayPlan;
	}
	
	public List<ResourcePay> getResourcePayPlan(){
		List<ResourcePay> resourcePayPlan = null;
		resourcePayPlan =pgsPlanMapper.getResourcePayPlan();
		
		return resourcePayPlan;
	}
	
	public List<InsurancePay> getInsurancePayPlan(){
		List<InsurancePay> insurancePayPlan = null;
		insurancePayPlan =pgsPlanMapper.getInsurancePayPlan();
		
		return insurancePayPlan;
	}
	
	public List<MachineLeasePay> getMachineLeasePayPlan(){
		List<MachineLeasePay> machineLeasePayPlan = null;
		machineLeasePayPlan =pgsPlanMapper.getMachineLeasePayPlan();
		
		return machineLeasePayPlan;
	}
	
	public List<MachineUsePay> getMachineUsePayPlan(){
		List<MachineUsePay> machineUsePayPlan = null;
		machineUsePayPlan =pgsPlanMapper.getMachineUsePayPlan();
		
		return machineUsePayPlan;
	}
	
	public List<ProductGain> getProductGainPlan(){
		List<ProductGain> productGainPlan = null;
		productGainPlan =pgsPlanMapper.getProductGainPlan();
		
		return productGainPlan;
	}
	
	public List<ResourceUsePlan> getResourceUsePlanPlan(){
		List<ResourceUsePlan> resourceUsePlan = null;
		resourceUsePlan =pgsPlanMapper.getResourceUsePlan();
		return resourceUsePlan;
	}
	
	public List<TaxPay> getTaxPayPlan(){
		List<TaxPay> taxPayPlan = null;
		taxPayPlan =pgsPlanMapper.getTaxPayPlan();
		
		return taxPayPlan;
	}
	
	public List<WorkForcePay> getWorkForcePayPlan(){
		List<WorkForcePay> workForcePayPlan = null;
		workForcePayPlan =pgsPlanMapper.getWorkForcePayPlan();
		
		return workForcePayPlan;
	}
	
	
	
}
