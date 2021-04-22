package easyfarm.dao;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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

@Mapper
public interface ResultMapper {
	
	//기타비용 실행결과리스트 쿼리
	public List<EtcPayResult> getEtcPayResult(String workPhaseCode);
	
	//농자재매입 실행결과리스트 쿼리
	public List<ResourcePayResult> getResourcePayResult();
	
	//보험비 실행결과리스트 쿼리
	public List<InsurancePayResult> getInsurancePayResult();
	
	//농기계 대여료 실행결과리스트 쿼리
	public List<MachineLeasePayResult> getMachineLeasePayResult();
	
	//보유농기계 사용 실행결과리스트 쿼리
	public List<MachineUsePayResult> getMachineUsePayResult();
	
	//생산물 수확 실행결과리스트 쿼리
	public List<ProductGainResult> getProductGainResult();
	
	//농자재 사용 실행결과리스트 쿼리
	public List<ResourceUsePlanResult> getResourceUsePlanResult();
	
	//공과금 실행결과리스트 쿼리
	public List<TaxPayResult> getTaxPayResult();
	
	//인건비 실행결과리스트 쿼리
	public List<WorkForcePayResult> getWorkForcePayResult();
	
	//아이디가 소속된 농가명 쿼리
	public List<Map<String,Object>> getFarmName(String memberId);
	
	//선택한 농가의 프로젝트 쿼리
	public List<Map<String,Object>> getProjectNameByFarmName(String farmCode);
	
	//선택한 프로젝트의 작업단계 쿼리
	public List<Map<String,Object>> getWorkPhaseByProjectCode(String projectCode);
	
	//작업단계코드 조회하는 쿼리
	public List<PlanWorkphaseCate> getPlanWorkphaseCate(String selectedDate,String projectCode);
	
}
