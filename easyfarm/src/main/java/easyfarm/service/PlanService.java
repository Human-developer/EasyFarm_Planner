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
import easyfarm.domain.plan.CommonMachine;
import easyfarm.domain.plan.EtcPay;
import easyfarm.domain.plan.InsurancePay;
import easyfarm.domain.plan.MachineLeasePay;
import easyfarm.domain.plan.MachineUsePay;
import easyfarm.domain.plan.ProductGain;
import easyfarm.domain.plan.ResourcePay;
import easyfarm.domain.plan.ResourceUsePlan;
import easyfarm.domain.plan.StockCate;
import easyfarm.domain.plan.StockItem;
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
	
	//프로젝트 통합계획 등록
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
	
	//농가별 사용가능한 품목조회
	public List<Map<String, Object>> getStockItem(Map<String, Object> projectData) {
		List<Map<String, Object>> result = null;
		Map<String, Object> stockItemInfo = null;
		
		if(projectData != null && !"".equals(projectData.toString())) {
			result = planMapper.getStockItem(projectData);
			for(int i = 0; i < result.size(); i++) {
				stockItemInfo = result.get(i);
				
				if(stockItemInfo.get("availableStatus") == "Y") {
				}else if(stockItemInfo.get("availableStatus") == "N"){
					stockItemInfo.replace("availableStatus", "불가능");
				}
				System.out.println(stockItemInfo.get("availableStatus"));
			}
		}
		
		return result;
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
	public List<InsurancePay> getInsurePayList(Map<String, Object> paramMap){
		List<InsurancePay> insurancePayList = null;
		if(paramMap != null && !"".equals(paramMap.toString())) {
			insurancePayList = planMapper.getInsurePayList(paramMap);
		}
		return insurancePayList;
	}
	
	//프로젝트 보험지출계획 등록
	public int addInsurePay(Map<String, Object> paramMap) {
		int result = 0;
		if(paramMap != null && !"".equals(paramMap.toString())) {
			result = planMapper.addInsurePay(paramMap);
		}
		return result;
	}
	
	//프로젝트 보험지출계획 수정
	public int modifyInsurePay(InsurancePay insurePay) {
		int result = 0;
		if(insurePay != null && !"".equals(insurePay)) {
			result = planMapper.modifyInsurePay(insurePay);
		}
		return result;
	}
	
	//품목카테고리조회
	public List<StockCate> getStockCateList() {
		return planMapper.getStockCateList();
	}
	
	//공통농기계목록조회
	public List<CommonMachine> getCommonMachineList() {
		return planMapper.getCommonMachineList();
	}
	
	//품목리스트조회
	public List<StockItem> getStockItemList(String farmCode) {
		List<StockItem> result = null;
		if(farmCode != null && !"".equals(farmCode.trim())) {
			result = planMapper.getStockItemList(farmCode);
		}
		return result;
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
