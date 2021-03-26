package easyfarm.dao;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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
	public List<ProductGainResult> getProductGainRunResult();
	public List<ResourceUsePlanResult> getResourceUsePlanRunResult();
	public List<TaxPayResult> getTaxPayRunResult();
	public List<WorkForcePayResult> getWorkForcePayRunResult();
	

}
