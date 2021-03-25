package easyfarm.dao;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.result.EtcPayResult;
import easyfarm.domain.result.InsurancePayResult;
import easyfarm.domain.result.MachineLeasePayResult;
import easyfarm.domain.result.MachineUsePayResult;
import easyfarm.domain.result.ProductGainRunResult;
import easyfarm.domain.result.ResourcePayResult;
import easyfarm.domain.result.ResourceUsePlanRunResult;
import easyfarm.domain.result.TaxPayRunResult;
import easyfarm.domain.result.WorkForcePayRunResult;

@Mapper
public interface ResultMapper {
	
	public List<EtcPayResult> getEtcPayResult();
	public List<ResourcePayResult> getResourcePayResult();
	public List<InsurancePayResult> getInsurancePayResult();
	public List<MachineLeasePayResult> getMachineLeasePayResult();
	public List<MachineUsePayResult> getMachineUsePayResult();
	public List<ProductGainRunResult> getProductGainRunResult();
	public List<ResourceUsePlanRunResult> getResourceUsePlanRunResult();
	public List<TaxPayRunResult> getTaxPayRunResult();
	public List<WorkForcePayRunResult> getWorkForcePayRunResult();
	

}
