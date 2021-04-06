package easyfarm.dao;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Farm;
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
	
	public List<EtcPayResult> getEtcPayResult();
	
	public List<ResourcePayResult> getResourcePayResult();
	
	public List<InsurancePayResult> getInsurancePayResult();
	
	public List<MachineLeasePayResult> getMachineLeasePayResult();
	
	public List<MachineUsePayResult> getMachineUsePayResult();
	
	public List<ProductGainResult> getProductGainResult();
	
	public List<ResourceUsePlanResult> getResourceUsePlanResult();
	
	public List<TaxPayResult> getTaxPayResult();
	
	public List<WorkForcePayResult> getWorkForcePayResult();
	
	public List<Map<String,Object>> getFarmName(String memberId);
	
	public List<Map<String,Object>> getProjectNameByFarmName(String farmCode);
}
