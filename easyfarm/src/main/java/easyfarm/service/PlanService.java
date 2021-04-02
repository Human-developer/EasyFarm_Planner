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

import easyfarm.dao.PlanMapper;
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
	
	private final PlanMapper planMapper;
	
	public PlanService(PlanMapper pgsPlanMapper) {
		this.planMapper = pgsPlanMapper;
	}
	
	//농가별 프로젝트 정보조회
	public Map<String, Object> getFarmProjectInfo(String projectCode) {
		Map<String, Object> result = null;
		if(projectCode != null && !"".equals(projectCode.trim())) {
			result = planMapper.getFarmProjectInfo(projectCode);
		}
		return result;
	}
	
	//프로젝트별 계획차수 리스트 조회
	public List<Map<String, Object>> getProjectPlanNList(String projectCode) {
		List<Map<String, Object>> result = null;
		if(projectCode != null && !"".equals(projectCode.trim())) {
			result = planMapper.getProjectPlanNList(projectCode);
		}
		return result;
	}
	
	//프로젝트 계획정보조회
	public Map<String, Object> getProjectPlanInfo(String projectPlanCode) {
		Map<String, Object> result = null;
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			result = planMapper.getProjectPlanInfo(projectPlanCode);
		}
		return result;
	}
	
	//프로젝트별 max계획차수 조회
	public Map<String, Object> getMaxProjectPlanNum(String projectCode) {
		Map<String, Object> result = null;
		if(projectCode != null && !"".equals(projectCode.trim())) {
			result = planMapper.getMaxProjectPlanNum(projectCode);
		}
		return result;
	}
	
	public int addProjectPlan(Map<String, Object> projectPlanData) {
		int result = 0;
		if(projectPlanData != null && !"".equals(projectPlanData)) {
			result = planMapper.addProjectPlan(projectPlanData);
		}
		return result;
	}
	
	//프로젝트별 작업단계
	public List<Map<String, Object>> getWorkphaseName(Map<String, Object> projectData) {
		return planMapper.getWorkphaseName(projectData);
	}
	
	//프로젝트별 상세작업항목
	public List<Map<String, Object>> getWorkphaseCateName(Map<String, Object> projectData) {
		return planMapper.getWorkphaseCateName(projectData);
	}
	
	//농가별 거래처
	public List<Map<String, Object>> getClientName(Map<String, Object> projectData) {
		return planMapper.getClientName(projectData);
	}
	
	//농가별 농기계즐겨찾기
	public List<Map<String, Object>> getFarmBookmarkMachine(Map<String, Object> projectData) {
		return planMapper.getFarmBookmarkMachine(projectData);
	}
	
	//농가별 보유농기계
	public List<Map<String, Object>> getFarmRetainMachine(Map<String, Object> projectData) {
		return planMapper.getFarmRetainMachine(projectData);
	}
	
	//농가별 품목조회
	public List<Map<String, Object>> getStockItem(Map<String, Object> projectData) {
		return planMapper.getStockItem(projectData);
	}
	
	//품목정보조회
	public Map<String, Object> getStockItemInfo(String stockItemCode) {
		return planMapper.getStockItemInfo(stockItemCode);
	}
	
	//공과금항목조회
	public List<Map<String, Object>> getTaxPayCateCode() {
		return planMapper.getTaxPayCateCode();
	}
	
	//프로젝트별 보험료지출계획 조회
	public List<InsurancePay> getInsurePayList(String projectCode){
		List<InsurancePay> insurancePayList = null;
		System.out.println(projectCode + "projectCode PlanService 실행");
		if(projectCode != null && !"".equals(projectCode)) {
			insurancePayList = planMapper.getInsurePayList(projectCode);
			System.out.println(insurancePayList + "insurancePayList PlanService 실행 ");
		}
		
		return insurancePayList;
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
