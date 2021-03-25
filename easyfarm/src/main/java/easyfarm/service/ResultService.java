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
import easyfarm.domain.result.EtcPayResult;
import easyfarm.domain.result.InsurancePayResult;
import easyfarm.domain.result.MachineLeasePayResult;
import easyfarm.domain.result.MachineUsePayResult;
import easyfarm.domain.result.ProductGainRunResult;
import easyfarm.domain.result.ResourcePayResult;
import easyfarm.domain.result.ResourceUsePlanRunResult;
import easyfarm.domain.result.TaxPayRunResult;
import easyfarm.domain.result.WorkForcePayRunResult;

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
	
	
	public List<EtcPayResult> getEtcPayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<EtcPayResult> etcPayList = null;
		etcPayList =resultmapper.getEtcPayResult();
		
		//실습. memberList 안에 객체 회원레벨1 : 관리자, 2: 판매자, 3: 구매자 로 세팅
		//memberLevelName : 관리자 or 판매자 or 구매자 세팅
		
		return etcPayList;
	}
	public List<ResourcePayResult> getResourcePayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<ResourcePayResult> resourcePayList = null;
		resourcePayList =resultmapper.getResourcePayResult();
		
		//실습. memberList 안에 객체 회원레벨1 : 관리자, 2: 판매자, 3: 구매자 로 세팅
		//memberLevelName : 관리자 or 판매자 or 구매자 세팅
		
		return resourcePayList;
	}
	public List<InsurancePayResult> getInsurancePayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<InsurancePayResult> insurancePayList = null;
		insurancePayList =resultmapper.getInsurancePayResult();
		
		//실습. memberList 안에 객체 회원레벨1 : 관리자, 2: 판매자, 3: 구매자 로 세팅
		//memberLevelName : 관리자 or 판매자 or 구매자 세팅
		
		return insurancePayList;
	}
	public List<MachineLeasePayResult> getMachineLeasePayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<MachineLeasePayResult> machineLeasePay = null;
		machineLeasePay =resultmapper.getMachineLeasePayResult();
		
		//실습. memberList 안에 객체 회원레벨1 : 관리자, 2: 판매자, 3: 구매자 로 세팅
		//memberLevelName : 관리자 or 판매자 or 구매자 세팅
		
		return machineLeasePay;
	}
	public List<MachineUsePayResult> getMachineUsePayResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<MachineUsePayResult> machineUsePay = null;
		machineUsePay =resultmapper.getMachineUsePayResult();
		
		//실습. memberList 안에 객체 회원레벨1 : 관리자, 2: 판매자, 3: 구매자 로 세팅
		//memberLevelName : 관리자 or 판매자 or 구매자 세팅
		
		return machineUsePay;
	}
	public List<ProductGainRunResult> getProductGainRunResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<ProductGainRunResult> productGainRun = null;
		productGainRun =resultmapper.getProductGainRunResult();
		
		//실습. memberList 안에 객체 회원레벨1 : 관리자, 2: 판매자, 3: 구매자 로 세팅
		//memberLevelName : 관리자 or 판매자 or 구매자 세팅
		
		return productGainRun;
	}
	public List<ResourceUsePlanRunResult> getResourceUsePlanRunResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<ResourceUsePlanRunResult> resourceUsePlanRun = null;
		resourceUsePlanRun =resultmapper.getResourceUsePlanRunResult();
		
		//실습. memberList 안에 객체 회원레벨1 : 관리자, 2: 판매자, 3: 구매자 로 세팅
		//memberLevelName : 관리자 or 판매자 or 구매자 세팅
		
		return resourceUsePlanRun;
	}
	public List<TaxPayRunResult> getTaxPayRunResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<TaxPayRunResult> taxPayRun = null;
		taxPayRun =resultmapper.getTaxPayRunResult();
		
		//실습. memberList 안에 객체 회원레벨1 : 관리자, 2: 판매자, 3: 구매자 로 세팅
		//memberLevelName : 관리자 or 판매자 or 구매자 세팅
		
		return taxPayRun;
	}
	public List<WorkForcePayRunResult> getWorkForcePayRunResult(){
		/* MemberMapper member = new MemberMapper(); */
		List<WorkForcePayRunResult> workForcePayRun = null;
		workForcePayRun =resultmapper.getWorkForcePayRunResult();
		
		//실습. memberList 안에 객체 회원레벨1 : 관리자, 2: 판매자, 3: 구매자 로 세팅
		//memberLevelName : 관리자 or 판매자 or 구매자 세팅
		
		return workForcePayRun;
	}
	
	
	
}
