package easyfarm.dao;

import java.util.List;



import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.plan.EtcPay;
import easyfarm.domain.plan.InsurancePay;
import easyfarm.domain.plan.MachineLeasePay;
import easyfarm.domain.plan.MachineUsePay;
import easyfarm.domain.plan.ProductGain;
import easyfarm.domain.plan.ResourcePay;
import easyfarm.domain.plan.ResourceUsePlan;
import easyfarm.domain.plan.TaxPay;
import easyfarm.domain.plan.WorkForcePay;

@Mapper
public interface PgsPlanMapper {
	
	public List<EtcPay> getEtcPayPlan();
	public List<ResourcePay> getResourcePayPlan();
	public List<InsurancePay> getInsurancePayPlan();
	public List<MachineLeasePay> getMachineLeasePayPlan();
	public List<MachineUsePay> getMachineUsePayPlan();
	public List<ProductGain> getProductGainPlan();
	public List<ResourceUsePlan> getResourceUsePlan();
	public List<TaxPay> getTaxPayPlan();
	public List<WorkForcePay> getWorkForcePayPlan();
	
	//농가별 프로젝트 정보조회
	public Map<String, Object> getFarmProjectInfo(String projectCode);
	
	//프로젝트 계획정보조회
	public Map<String, Object> getProjectPlanInfo(String projectPlanCode);
	
	//프로젝트별 계획차수 리스트 조회
	public List<Map<String, Object>> getProjectPlanNList(String projectCode);
	
	//프로젝트별 작업단계
	public List<Map<String, Object>> getWorkphaseName(Map<String, Object> projectData);
	
	//프로젝트별 상세작업항목
	public List<Map<String, Object>> getWorkphaseCateName(Map<String, Object> projectData);
	
	//농가별 거래처
	public List<Map<String, Object>> getClientName(Map<String, Object> projectData);
	
	//농가별 농기계즐겨찾기
	public List<Map<String, Object>> getFarmBookmarkMachine(Map<String, Object> projectData);
	
	//농가별 보유농기계
	public List<Map<String, Object>> getFarmRetainMachine(Map<String, Object> projectData);
	
	//농가별 품목조회
	public List<Map<String, Object>> getStockItem(Map<String, Object> projectData);
	
	//품목정보조회
	public Map<String, Object> getStockItemInfo(String stockItemCode);
	
	//공과금항목조회
	public List<Map<String, Object>> getTaxPayCateCode();
	

}