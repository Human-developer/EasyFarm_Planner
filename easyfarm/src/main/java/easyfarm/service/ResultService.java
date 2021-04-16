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
	
	
	//연관 맺어주기 =  DI 필드 주입방식
	/*@Autowired
	private MemberMapper memberMapper;
	
	private MemberMapper memberMapper2;
	// 2. DI SETTER 메서드 주입방식
	@Autowired
	public void setMemberMapper(MemberMapper memberMapper2) {
		this.memberMapper2 = memberMapper;
	}
	*/
	// 3. DI 생성자 메서드 주입방식 (spring권장)
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
	
	
	public List<EtcPayResult> getEtcPayResult(String workPhaseCode){
		/* MemberMapper member = new MemberMapper(); */
		List<EtcPayResult> etcPayList = null;
		etcPayList =resultmapper.getEtcPayResult(workPhaseCode);
		
		return etcPayList;
	}
	public List<ResourcePayResult> getResourcePayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<ResourcePayResult> resourcePayList = null;
		resourcePayList =resultmapper.getResourcePayResult();
		
		
		return resourcePayList;
	}
	public List<InsurancePayResult> getInsurancePayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<InsurancePayResult> insurancePayList = null;
		insurancePayList =resultmapper.getInsurancePayResult();
		
		return insurancePayList;
	}
	public List<MachineLeasePayResult> getMachineLeasePayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<MachineLeasePayResult> machineLeasePay = null;
		machineLeasePay =resultmapper.getMachineLeasePayResult();
		
		return machineLeasePay;
	}
	public List<MachineUsePayResult> getMachineUsePayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<MachineUsePayResult> machineUsePay = null;
		machineUsePay =resultmapper.getMachineUsePayResult();
		
		return machineUsePay;
	}
	public List<ProductGainResult> getProductGainResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<ProductGainResult> productGainResult = null;
		productGainResult =resultmapper.getProductGainResult();
		
		return productGainResult;
	}
	public List<ResourceUsePlanResult> getResourceUsePlanResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<ResourceUsePlanResult> resourceUsePlanResult = null;
		resourceUsePlanResult =resultmapper.getResourceUsePlanResult();
		
		return resourceUsePlanResult;
	}
	public List<TaxPayResult> getTaxPayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<TaxPayResult> taxPayResult = null;
		taxPayResult =resultmapper.getTaxPayResult();
		
		return taxPayResult;
	}
	public List<WorkForcePayResult> getWorkForcePayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<WorkForcePayResult> workForcePayResult = null;
		workForcePayResult =resultmapper.getWorkForcePayResult();
		
		return workForcePayResult;
	}
	
	//아이디가 소속된 농가명 가져오기
	public List<PlanWorkphaseCate> getPlanWorkphaseCate(String selectedDate, String projectCode) {
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
