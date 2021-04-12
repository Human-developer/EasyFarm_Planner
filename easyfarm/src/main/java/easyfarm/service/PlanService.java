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
import easyfarm.domain.plan.InsurancePay;
import easyfarm.domain.plan.MachineLeasePay;
import easyfarm.domain.plan.MachineUsePay;
import easyfarm.domain.plan.PlanWorkphase;
import easyfarm.domain.plan.PlanWorkphaseCate;
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
	public List<Map<String, Object>> addClient(Client client) {
		List<Map<String, Object>> result 		= null;
		Map<String, Object> paramMap 			= null;
		int addResult 				 			= 0;
		
		if(client != null) {
			client.setUseStatus("Y");
			addResult = planMapper.addClient(client);
			
			if(addResult > 0) {
				paramMap = new HashMap<String, Object>();
				paramMap.put("farmCode", client.getFarmCode());
				
				result = planMapper.getClientName(paramMap);
			}
		}
		
		return result;
	}
	
	//농가별 거래처 수정
	public List<Map<String, Object>> modifyClient(Client client) {
		List<Map<String, Object>> result 		= null;
		Map<String, Object> paramMap 			= null;
		int modifyResult 				 			= 0;
		
		if(client != null) {
			modifyResult = planMapper.modifyClient(client);
			
			if(modifyResult > 0) {
				paramMap = new HashMap<String, Object>();
				paramMap.put("farmCode", client.getFarmCode());
				
				result = planMapper.getClientName(paramMap);
			}
		}
		return result;
	}
	
	//농가별 거래처 삭제
	public List<Map<String, Object>> removeClient(Client client) {
		List<Map<String, Object>> result 		= null;
		Map<String, Object> paramMap 			= null;
		int removeResult 				 		= 0;
		
		if(client != null) {
			client.setUseStatus("N");
			removeResult = planMapper.removeClient(client);
			
			if(removeResult > 0) {
				paramMap = new HashMap<String, Object>();
				paramMap.put("farmCode", client.getFarmCode());
				
				result = planMapper.getClientName(paramMap);
			}
		}
		return result;
	}
	
	//농가별 품목등록
	public List<StockItem> addStockItem(StockItem stockItem) {
		List<StockItem> result 		= null;
		int addResult 				= 0;
		
		if(stockItem != null) {
			stockItem.setUseStatus("Y");
			addResult = planMapper.addStockItem(stockItem);
			
			if(addResult > 0) {
				result = planMapper.getStockItemList(stockItem.getFarmCode());
			}
		}
		return result;
	}
	
	//농가별 품목수정
	public List<StockItem> modifyStockItem(StockItem stockItem) {
		List<StockItem> result 		= null;
		int modifyResult 			= 0;
		
		if(stockItem != null) {
			modifyResult = planMapper.modifyStockItem(stockItem);
			
			if(modifyResult > 0) {
				result = planMapper.getStockItemList(stockItem.getFarmCode());
			}
		}
		return result;
	}
	
	//농가별 품목삭제
	public List<StockItem> removeStockItem(StockItem stockItem) {
		List<StockItem> result 		= null;
		int removeResult 			= 0;
		
		if(stockItem != null) {
			stockItem.setUseStatus("N");
			removeResult = planMapper.removeStockItem(stockItem);
			
			if(removeResult > 0) {
				result = planMapper.getStockItemList(stockItem.getFarmCode());
			}
		}
		return result;
	}
	
	//농가별 농자재매입등록
	public List<Map<String, Object>> addResourcePay(ResourcePay resourcePay) {
		List<Map<String, Object>> result 	= null;
		int addResult 						= 0;
		
		if(resourcePay != null) {
			
			addResult = planMapper.addResourcePay(resourcePay);
			System.out.println(resourcePay.getResourcePayCode() + " <---- getResourcePayCode");
			if(addResult > 0) {
				result = planMapper.getResourcePayList(resourcePay.getFarmCode());
			}
		}
		return result;
	}

	//계획서 간편보기 전체 리스트 조회
	public Map<String,List<Object>> getAllPlanSchedule(String planWorkphaseCode) {
		Map<String,List<Object>> result = null;
		
		if(planWorkphaseCode != null) {
			result = new HashMap<>();
			
			
			
		}
		
		
		
		return result;
	}
}
