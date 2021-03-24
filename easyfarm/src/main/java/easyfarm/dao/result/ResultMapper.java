package easyfarm.dao.result;

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
	
	
	/*
	 * public int exaddMember(Map<String s, int i> map)
	 */	
	
	//회원목록 조회하는 추상메서드 만듦
	
	//로그인이력 조회
	public List<Map<String, Object>> getLoginHistory(Map<String, Object> paramMap);
	
	// 로그인 이력 전체 행의 갯수
	public int getLoginHistoryCount();

}
