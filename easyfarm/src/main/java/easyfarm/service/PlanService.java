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
import easyfarm.domain.plan.Client;
import easyfarm.domain.plan.CommonMachine;
import easyfarm.domain.plan.EtcPay;
import easyfarm.domain.plan.FarmBookmarkMachine;
import easyfarm.domain.plan.FarmRetainMachine;
import easyfarm.domain.plan.InsurancePay;
import easyfarm.domain.plan.MachineLeasePay;
import easyfarm.domain.plan.MachineUsePay;
import easyfarm.domain.plan.PlanWorkphase;
import easyfarm.domain.plan.PlanWorkphaseCate;
import easyfarm.domain.plan.ProductGain;
import easyfarm.domain.plan.ResourcePay;
import easyfarm.domain.plan.ResourceUsePlan;
import easyfarm.domain.plan.ResourceUsecapacity;
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
	
	//농자재매입지출 조회
	public List<Map<String, Object>> getResourcePayList(String farmCode) {
		List<Map<String, Object>> result = null;
		if(farmCode != null && !"".equals(farmCode.trim())) {
			result = planMapper.getResourcePayList(farmCode);
		}
		return result;
	}
	
	//단계별작업계획 월켈린더 리스트
	public List<Map<String, Object>> getPlanWorkphaseSchedule(String projectPlanCode) {
		List<Map<String, Object>> result = null;
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			result = planMapper.getPlanWorkphaseSchedule(projectPlanCode);
		}
		return result;
	}
	
	//단계별 상세작업계획 월켈린더 리스트
	public List<Map<String, Object>> getPlanWorkphaseCateSchedule(String projectPlanCode) {
		List<Map<String, Object>> result = null;
		if(projectPlanCode != null && !"".equals(projectPlanCode.trim())) {
			result = planMapper.getPlanWorkphaseCateSchedule(projectPlanCode);
		}
		return result;
	}
	
	//단계별작업계획 등록
	public int addPlanWorkphase(PlanWorkphase planWorkphase, PlanWorkphaseCate planWorkphaseCate) {
		int result = 0;
		String maxCode = null;
		
		if(planWorkphase.getProjectWorkphaseCode() != null && !"".equals(planWorkphase.getProjectWorkphaseCode().trim())) {
			
			maxCode = planMapper.getPlanWorkphaseMaxCode();
			
			planWorkphase.setPlanWorkphaseCode(maxCode);
			planWorkphase.setRunStatus("실행전");
			
			result += planMapper.addPlanWorkphase(planWorkphase);
		}
		if(planWorkphaseCate.getFarmWorkphaseCateCode() != null && !"".equals(planWorkphaseCate.getFarmWorkphaseCateCode().trim())) {
			
			planWorkphaseCate.setPlanWorkphaseCode(maxCode);
			planWorkphaseCate.setRunStatus("실행전");
			
			result += planMapper.addPlanWorkphaseCate(planWorkphaseCate);
		}
		
		return result;
	}

	//농가별 거래처 등록
	public int addClient(Client client) {
		int addResult = 0;
		
		if(client != null) {
			client.setUseStatus("Y");
			addResult = planMapper.addClient(client);
		}
		
		return addResult;
	}
	
	//거래처 코드별 정보 조회
	public Client getClientInfo(String clientCode) {
		
		Client result = null;
		
		if(clientCode != null && !"".equals(clientCode.trim())) {
			result = planMapper.getClientInfo(clientCode);
		}
		
		return result;
	}
	
	//농가별 거래처 수정
	public int modifyClient(Client client) {
		int modifyResult = 0;
		
		if(client != null) {
			modifyResult = planMapper.modifyClient(client);
		}
		return modifyResult;
	}
	
	//농가별 거래처 삭제
	public int removeClient(String clientCode) {
		int removeResult = 0;
		
		if(clientCode != null) {
			removeResult = planMapper.removeClient(clientCode);
		}
		return removeResult;
	}
	
	//농가별 품목등록
	public int addStockItem(StockItem stockItem) {
		int addResult = 0;
		
		if(stockItem != null) {
			stockItem.setUseStatus("Y");
			addResult = planMapper.addStockItem(stockItem);
		}
		return addResult;
	}
	
	//품목 코드별 정보 조회
	public StockItem getStockItemInfoByCode(String stockItemCode) {
		
		StockItem result = null;
		
		if(stockItemCode != null && !"".equals(stockItemCode.trim())) {
			result = planMapper.getStockItemInfoByCode(stockItemCode);
		}
		
		return result;
	}
	
	//농가별 품목수정
	public int modifyStockItem(StockItem stockItem) {
		
		int modifyResult 			= 0;
		
		if(stockItem != null) {
			modifyResult = planMapper.modifyStockItem(stockItem);
		}
		return modifyResult;
	}
	
	//농가별 품목삭제
	public int removeStockItem(String stockItemCode) {
		
		int removeResult = 0;
		
		if(stockItemCode != null) {
			removeResult = planMapper.removeStockItem(stockItemCode);
		}
		return removeResult;
	}
	
	//농가별 농자재매입등록
	public int addResourcePay(ResourcePay resourcePay) {
		ResourceUsecapacity resourceUsecapacity = null;
		int addResult = 0;
		
		if(resourcePay != null) {
			resourceUsecapacity = new ResourceUsecapacity();
			
			addResult += planMapper.addResourcePay(resourcePay);
			addResult += planMapper.addResourceUsecapacity(resourcePay);
		}
		return addResult;
	}
	
	//농자재매입 코드별 정보 조회
	public ResourcePay getResourcePayInfo(String resourcePayCode) {
		
		ResourcePay result = null;
		
		if(resourcePayCode != null && !"".equals(resourcePayCode.trim())) {
			result = planMapper.getResourcePayInfo(resourcePayCode);
		}
		
		return result;
	}
	
	//농가별 농자재매입수정
	public int modifyPlanResourcePay(ResourcePay resourcePay) {
		
		int modifyResult = 0;
		
		if(resourcePay != null) {
			modifyResult = planMapper.modifyPlanResourcePay(resourcePay);
		}
		return modifyResult;
	}
	
	//농기계 즐겨찾기에 등록한 항목 제외한 공통농기계 리스트
	public List<Map<String, Object>> getFarmCommonMachineList(String farmCode) {
		
		List<Map<String, Object>> result = null;
		
		if(farmCode != null && !"".equals(farmCode.trim())) {
			result = planMapper.getFarmCommonMachineList(farmCode);
		}
		
		return result;
	}
	
	//농기계 즐겨찾기 등록
	public int addFarmBookmarkMachine(FarmBookmarkMachine farmBookmarkMachine) {
		
		int addResult = 0;
		
		if(farmBookmarkMachine != null) {
			addResult = planMapper.addFarmBookmarkMachine(farmBookmarkMachine);
		}
		return addResult;
	}
	
	//농기계 즐겨찾기 삭제
	public int removeFarmBookmarkMachine(String farmBookmarkMachineCode) {
		
		int removeResult = 0;
		
		if(farmBookmarkMachineCode != null) {
			removeResult = planMapper.removeFarmBookmarkMachine(farmBookmarkMachineCode);
		}
		return removeResult;
	}
	
	//보유농기계에 등록한 항목 제외한 농기계 즐겨찾기 리스트
	public List<Map<String, Object>> getFarmBookmarkMachineList(String farmCode) {
		
		List<Map<String, Object>> result = null;
		
		if(farmCode != null && !"".equals(farmCode.trim())) {
			result = planMapper.getFarmBookmarkMachineList(farmCode);
		}
		return result;
	}
	
	//보유농기계 등록
	public int addPlanFarmRetainMachine(FarmRetainMachine farmRetainMachine) {
		
		int addResult = 0;
		
		if(farmRetainMachine != null) {
			addResult = planMapper.addPlanFarmRetainMachine(farmRetainMachine);
		}
		return addResult;
	}
	
	//보유농기계 삭제
	public int removePlanFarmRetainMachine(String farmRetainMachineCode) {
		
		int removeResult = 0;
		
		if(farmRetainMachineCode != null) {
			removeResult = planMapper.removePlanFarmRetainMachine(farmRetainMachineCode);
		}
		return removeResult;
		
	}
	
	//기간별 작업단계 계획 정보조회
	public PlanWorkphase getPlanWorkphaseInfo(String planWorkphaseCode) {
		
		PlanWorkphase result = null;
		
		if(planWorkphaseCode != null && !"".equals(planWorkphaseCode.trim())) {
			result = planMapper.getPlanWorkphaseInfo(planWorkphaseCode);
		}
		return result;
		
	}
	
	//기간별 작업단계별 상세항목 계획 정보조회
	public Map<String, Object> getPlanWorkphaseCateInfo(String planWorkphaseCateCode) {
		
		Map<String, Object> result = null;
		
		if(planWorkphaseCateCode != null && !"".equals(planWorkphaseCateCode.trim())) {
			result = planMapper.getPlanWorkphaseCateInfo(planWorkphaseCateCode);
		}
		return result;
	}
	
	//작업단계별, 상세항목별 지출계획 전체 조회
	public Map<String,List<Map<String, Object>>> getAllPlanSchedule(String planWorkphaseCode, String planWorkphaseCateCode) {
		
		Map<String,List<Map<String, Object>>> result = null;
		
		if(planWorkphaseCode != null) {
			result = new HashMap<>();
			result.put("expWorkforcePayList", planMapper.getExpWorkforcePayList(planWorkphaseCode, planWorkphaseCateCode));
			result.put("expMachineLeaseList", planMapper.getExpMachineLeaseList(planWorkphaseCode, planWorkphaseCateCode));
			result.put("expMachineUseList", planMapper.getExpMachineUseList(planWorkphaseCode, planWorkphaseCateCode));
			result.put("expResourceUseplanList", planMapper.getExpResourceUseplanList(planWorkphaseCode, planWorkphaseCateCode));
			result.put("expTaxPayList", planMapper.getExpTaxPayList(planWorkphaseCode, planWorkphaseCateCode));
			result.put("productGainList", planMapper.getProductGainList(planWorkphaseCode, planWorkphaseCateCode));
			result.put("expEtcPayList", planMapper.getExpEtcPayList(planWorkphaseCode, planWorkphaseCateCode));
		}
		return result;
	}
	
	//인건비 지출계획 등록
	public int addWorkforcePay(WorkForcePay workForcePay) {
		
		int addResult = 0;
		
		if(workForcePay != null) {
			String workforceRequireDay = Integer.toString(Integer.parseInt(workForcePay.getWorkforceCount()) * Integer.parseInt(workForcePay.getWorkforceHowlong()));
			workForcePay.setWorkforceRequireDay(workforceRequireDay);
			addResult = planMapper.addWorkforcePay(workForcePay);
		}
		return addResult;
	}
	
	//농기계 대여 지출계획 등록
	public int addMachineLeasePay(MachineLeasePay machineLeasePay) {
		
		int addResult = 0;
		
		if(machineLeasePay != null) {
			addResult = planMapper.addMachineLeasePay(machineLeasePay);
		}
		return addResult;
	}
	
	//보유농기계 지출계획 등록
	public int addMachineUsePay(MachineUsePay machineUsePay) {
		
		int addResult = 0;
		
		if(machineUsePay != null) {
			addResult = planMapper.addMachineUsePay(machineUsePay);
		}
		return addResult;
	}
	
	//농자재사용 지출계획 등록
	public int addResourceUsePlan(ResourceUsePlan resourceUsePlan) {
		
		ResourceUsecapacity resourceUsecapacity = null; 
		String resourceUsecapacityCode = null;
		int result = 0;
		
		double useQuantity = 0.00;
		double useQuantityTotal = 0.00;
		
		double retainQuantity = 0.00;
		double retainQuantityExtra = 0.00;
		
		String changeQuantity = null;
		String changeQuantityExtra = null;
		
		if(resourceUsePlan != null) {
			
			
			result += planMapper.addResourceUsePlan(resourceUsePlan); //농자재사용지출계획 등록
			
			resourceUsecapacityCode = resourceUsePlan.getResourceUsecapacityCode();
			resourceUsecapacity 	= planMapper.getResourceUsecapacityInfo(resourceUsecapacityCode); //농자재사용현황 업데이트를 위한 조회
			
			useQuantity 		= Double.parseDouble(resourceUsePlan.getStockItemUseQuantity());		//화면에서 입력한 사용수량
			useQuantityTotal 	= Double.parseDouble(resourceUsePlan.getStockItemUseQuantityTotal());	//화면에서 입력한 사용용량
			retainQuantity 		= Double.parseDouble(resourceUsecapacity.getResourceRetainQuantity());	//농자재사용현황 현재잔여수량
			retainQuantityExtra = Double.parseDouble(resourceUsecapacity.getResourceRetainQuantityCapacityExtra());//농자재사용현황 현재잔여용량
			
			changeQuantity 		= Double.toString(retainQuantity - useQuantity); //잔여수량 - 사용수량
			changeQuantityExtra = Double.toString(retainQuantityExtra - useQuantityTotal); //잔여용량 - 사용용량
			
			resourceUsecapacity.setResourceRetainQuantity(changeQuantity);
			resourceUsecapacity.setResourceRetainQuantityCapacityExtra(changeQuantityExtra);
			
			if("0.0".equals(changeQuantity) || "0.0".equals(changeQuantityExtra)) {
				resourceUsecapacity.setAvailableStatus("N");
			}
			
			result += planMapper.modifyResourceUsecapacityInfo(resourceUsecapacity);
			
		}
		return result;
	}
	
	
	
	
	
	
}
