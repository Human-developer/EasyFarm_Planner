package easyfarm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import easyfarm.dao.FarmMapper;
import easyfarm.domain.Farm;
import easyfarm.domain.FarmCancelRequest;
import easyfarm.domain.FarmMember;
import easyfarm.domain.FarmMemberJoin;


@Transactional
@Service
public class FarmService {

	@Autowired
	private FarmMapper farmMapper;
	
	public Farm farmByName(String farmName) {
		Farm resultFarm = null;
		
		if(farmName != null) {			
			resultFarm = farmMapper.farmByName(farmName);
		}
		
		
		return resultFarm;
	}
	
	/* 농가등록 */
	public int addFarm(Farm farm) {
		int result = 0;
		if(farm != null) {
			FarmMember farmMember= new FarmMember();
			result += farmMapper.addFarm(farm);
			
			farmMember.setFarmCode(farm.getFarmName());
			farmMember.setFarmMemberId(farm.getCeoId());
			farmMember.setFarmLevelName("대표");
			farmMember.setFarmLevelCode("farm_level_1");
			farmMember.setFarmMemberStatus("정상");
			
			result += farmMapper.addFarmMember(farmMember);
		}
		
		return result;
	}
	/* 농가등록 */
	
	
	/* 내 농가 */
	public List<Farm> myFarm(String memberId){
		List<Farm> resultFarmList = null;
		if(memberId != null) {
			resultFarmList = farmMapper.myFamr(memberId);
		}
		return resultFarmList;
	}
	/* 내 농가 */
	
	/* 내 소속 농가 */
	public List<Farm> belongFarm(String memberId){
		List<Farm> resultFarmList = null;
		if(memberId != null) {
			resultFarmList = farmMapper.belongFarm(memberId);
		}
		return resultFarmList;
	}
	/* 내 소속 농가 */
	
	/* 농가상세보기 */
	public Farm detailFarm(Farm farm){
		Farm resultFarm = null;
		if(farm != null) {
			System.out.println(farm +"testesttetstetste");
			resultFarm = farmMapper.detailFarm(farm);
		}
		
		return resultFarm;
	}
	/* 농가상세보기 */
	
	
	/* 농가수정 */
	public Farm updateByFarm(String farmCode, String memberId) {
		Farm resultFarm = null;
		
		if(farmCode != null && memberId != null) {
			
			resultFarm = farmMapper.updateByFarm(farmCode, memberId);
		}
		
		
		return resultFarm;
	}
	
	//처리
	public int updateFarm(Farm farm) {
		int result =0;
		if(farm != null) {
			result += farmMapper.updateFarm(farm);
		}
		return result;
	}
	/* 농가수정 */
	
	/* 농가검색 */
	public List<Farm> searchFarm(String memberId){
		List<Farm> resultFarm = null;
		if(memberId != null) {
			resultFarm = farmMapper.searchFarm(memberId);
		}
		return resultFarm;
	}
	/* 농가검색 */
	
	/* 농가회원조회 */
	public List<FarmMember> getMemberFarm(String farmCode){
		List<FarmMember> resultFarmMemberList = null;
		if(farmCode != null) {
			resultFarmMemberList = farmMapper.getMemberFarm(farmCode);
		}
		return resultFarmMemberList;
	}
	/* 농가회원조회 */
	
	
	/* 농가가입신청 */
	public String addFarmMemberJoin(String farmName,String farmJoinPurpose, String memberId) {
		String result = "신청실패";
		
		if(farmName != null && farmJoinPurpose != null) {
			
			FarmMemberJoin resultJoinList = farmMapper.addFarmMemberJoinCheck(farmName, memberId); 
			if(resultJoinList == null) {
				int insertResult = farmMapper.addFarmMemberJoin(farmName,farmJoinPurpose,memberId);
				/* 실패체크 */
				if(insertResult <=0) {
					
				}
				result ="신청완료";
			}
			else {
				result ="이미 신청하셨습니다";
			}
		}
		
		return result;
	}
	/* 농가가입신청 */
	
	public List<FarmMemberJoin> getJoinFarm(String farmCode){
		List<FarmMemberJoin> resultFarmMemberJoin =null;
		if(farmCode != null) {
			resultFarmMemberJoin = farmMapper.getJoinFarm(farmCode);
		}
		
		return resultFarmMemberJoin; 
	}
	
	public int farmJoinMember(String farmMemberJoinCode,String approval,String memberId) {
		int result = 0;
		if(farmMemberJoinCode != null && approval != null && memberId != null) {
			
			if("승인".equals(approval)) {
				FarmMemberJoin resultFarmMemberJoin = farmMapper.getJoinMember(farmMemberJoinCode);
				
				FarmMember addFarmMember = new FarmMember();
				addFarmMember.setFarmCode(resultFarmMemberJoin.getFarmCode());
				addFarmMember.setFarmMemberId(resultFarmMemberJoin.getFarmJoinRequestMemberId());
				addFarmMember.setFarmLevelName("대표");
				addFarmMember.setFarmLevelCode("farm_level_3");
				addFarmMember.setFarmMemberStatus("정상");
				
				result += farmMapper.joinAddFarmMember(addFarmMember);
				
				result += farmMapper.farmJoinMember(farmMemberJoinCode,approval,memberId);
				
				
			}
			else if("거부".equals(approval)) {
				result += farmMapper.farmJoinMember(farmMemberJoinCode,approval,memberId);
			}
			
		}
		
		return result;
	}
	
	
	public List<FarmMemberJoin> myGetJoinFarm(String memberId){
		List<FarmMemberJoin> resultMemberJoinList = null;
		
		if(memberId != null) {
			resultMemberJoinList = farmMapper.myGetJoinFarm(memberId);
		}
		return resultMemberJoinList;
	}
	
	public int removeJoinFarm(String farmJoinCode) {
		int result =0;
		
		if(farmJoinCode != null) {
			result += farmMapper.removeJoinFarm(farmJoinCode);
		}
		
		return result;
	}
	
	public String getFarmMemberLevel(String farmCode, String memberId) {
		return farmMapper.getFarmMemberLevel(farmCode, memberId);
	}
	
	
	//탈퇴신청
	public String addCancelMember(String memberId, String farmName,String cancelRequestReason) {
		String result = "신청실패";
		if(memberId != null && farmName != null) {
			FarmCancelRequest cancelRequestResult = farmMapper.addCancelMemberCheck(memberId, farmName);
			if(cancelRequestResult == null) {
				int insertResult = 0;
				insertResult += farmMapper.addCancelMember(memberId, farmName,cancelRequestReason);
				
				if(insertResult > 0) {
					result ="신청완료";
				}
			}
			else {
				result ="이미 신청 하셨습니다";
			}
				
		}
		
		
		return result;
	}
	
	
	//농가별 탈퇴신청목록
	public List<FarmCancelRequest> getLeaverFarm(String farmCode,String memberId){
		List<FarmCancelRequest> result =null;
		if(farmCode != null) {			
			result = farmMapper.getLeaverFarm(farmCode,memberId);
		}
		
		return result;
	}
	
	//탈퇴 처리
	public int isLeaverFarm(FarmCancelRequest cancelRequest) {
		int result = 0;
		if(cancelRequest!= null) {
			String approval = cancelRequest.getCancelApproval();
			
			if("탈퇴승인".equals(approval)) {
					
				result += farmMapper.leaverFarmMember(cancelRequest.getCancelRequestCode());
				result += farmMapper.isLeaverFarm(cancelRequest);
			}
			else if("탈퇴거부".equals(approval)) {
				result += farmMapper.isLeaverFarm(cancelRequest);
			}
			
		}
		
		return result;
	}
	
	//내 탈퇴신청목록
	public List<FarmCancelRequest> myGetLeaverFarm(String memberId){
		List<FarmCancelRequest> resultLeaverFarmList = null;
		
		if(memberId != null) {
			resultLeaverFarmList = farmMapper.myGetLeaverFarm(memberId);
		}
		return resultLeaverFarmList;
	}
	
	//농가탈퇴 취소
	public String cancelLeaverFarm(String cancelLeaverFarm) {
		String result ="삭제실패";
		
		if(cancelLeaverFarm != null) {
			int deleteResult = 0;
			deleteResult += farmMapper.cancelLeaverFarm(cancelLeaverFarm);
			if(deleteResult > 0) {
				result = "삭제성공";
			}
		}
		
		return result;
	}
}
