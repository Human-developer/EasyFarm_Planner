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
	
	//프로젝트별 상세작업항목
	public List<Map<String, Object>> getWorkphaseCateName();
	
	
	

}
