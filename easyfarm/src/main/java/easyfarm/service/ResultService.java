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

import easyfarm.dao.ResultMapper;
import easyfarm.domain.Farm;
import easyfarm.domain.plan.PlanWorkphaseCate;
import easyfarm.domain.result.EtcPayResult;
import easyfarm.domain.result.InsurancePayResult;
import easyfarm.domain.result.MachineLeasePayResult;
import easyfarm.domain.result.MachineUsePayResult;
import easyfarm.domain.result.ProductGainResult;
import easyfarm.domain.result.ResourcePayResult;
import easyfarm.domain.result.ResourceUsePlanResult;
import easyfarm.domain.result.TaxPayResult;
import easyfarm.domain.result.WorkForcePayResult;

@Service
@Transactional
public class ResultService {
	
	
	
	// DI 생성자 메서드 주입방식 (spring권장)
	private final ResultMapper resultmapper;
	
	//3-1 spring framework 4.3 이후부터는 @Autowired 쓰지 않아도 주입 가능
	@Autowired
	public ResultService(ResultMapper resultmapper) {
		this.resultmapper = resultmapper;
	}
	@PostConstruct
	public void initialize() {
		System.out.println("======================================");
		System.out.println("ResultService bean 등록");
		System.out.println("======================================");
	}	
	
	//기타비용 실행결과리스트 
	public List<EtcPayResult> getEtcPayResult(String workPhaseCode){
		List<EtcPayResult> etcPayList = null;
		etcPayList =resultmapper.getEtcPayResult(workPhaseCode);
		
		return etcPayList;
	}
	//농자재매입 실행결과리스트 
	public List<ResourcePayResult> getResourcePayResult(){
		List<ResourcePayResult> resourcePayList = null;
		resourcePayList =resultmapper.getResourcePayResult();
		
		
		return resourcePayList;
	}
	//보험비 실행결과리스트 
	public List<InsurancePayResult> getInsurancePayResult(){
		List<InsurancePayResult> insurancePayList = null;
		insurancePayList =resultmapper.getInsurancePayResult();
		
		return insurancePayList;
	}
	//농기계 대여료 실행결과리스트 
	public List<MachineLeasePayResult> getMachineLeasePayResult(){
		List<MachineLeasePayResult> machineLeasePay = null;
		machineLeasePay =resultmapper.getMachineLeasePayResult();
		
		return machineLeasePay;
	}
	//보유농기계 사용 실행결과리스트 
	public List<MachineUsePayResult> getMachineUsePayResult(){
		List<MachineUsePayResult> machineUsePay = null;
		machineUsePay =resultmapper.getMachineUsePayResult();
		
		return machineUsePay;
	}
	//생산물 수확 실행결과리스트 
	public List<ProductGainResult> getProductGainResult(){
		List<ProductGainResult> productGainResult = null;
		productGainResult =resultmapper.getProductGainResult();
		
		return productGainResult;
	}
	//농자재 사용 실행결과리스트
	public List<ResourceUsePlanResult> getResourceUsePlanResult(){
		List<ResourceUsePlanResult> resourceUsePlanResult = null;
		resourceUsePlanResult =resultmapper.getResourceUsePlanResult();
		
		return resourceUsePlanResult;
	}
	//공과금 실행결과리스트 
	public List<TaxPayResult> getTaxPayResult(){
		List<TaxPayResult> taxPayResult = null;
		taxPayResult =resultmapper.getTaxPayResult();
		
		return taxPayResult;
	}
	//인건비 실행결과리스트
	public List<WorkForcePayResult> getWorkForcePayResult(){
		List<WorkForcePayResult> workForcePayResult = null;
		workForcePayResult =resultmapper.getWorkForcePayResult();
		
		return workForcePayResult;
	}
	
	//작업단계코드 조회하기
	public List<PlanWorkphaseCate> getPlanWorkphaseCate(String selectedDate, String projectCode) {
		Map<String,Object> result =  null;
		
		List<PlanWorkphaseCate> workPhaseCates = resultmapper.getPlanWorkphaseCate(selectedDate,projectCode);
		
		if(workPhaseCates != null) {
			for(PlanWorkphaseCate workPhaseCate : workPhaseCates) {
				workPhaseCate.getPlanWorkphaseCateCode();
			}
		}
		return resultmapper.getPlanWorkphaseCate(selectedDate,projectCode);
	}
	
	//아이디가 소속된 농가명 가져오기
	public List<Map<String,Object>> getFarmName(String memberId) {
		return resultmapper.getFarmName(memberId);
	}
	
	//선택한 농가의 프로젝트 가져오기
	public List<Map<String,Object>> getProjectNameByFarmName(String farmCode) {
		return resultmapper.getProjectNameByFarmName(farmCode);
	}
	
	//선택한 프로젝트의 작업단계 가져오기
	public List<Map<String,Object>> getWorkPhaseByProjectCode(String projectCode) {
		return resultmapper.getWorkPhaseByProjectCode(projectCode);
	}
	
	
	
	
}
