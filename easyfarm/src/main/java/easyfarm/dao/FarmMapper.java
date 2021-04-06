package easyfarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Farm;
import easyfarm.domain.FarmCancelRequest;
import easyfarm.domain.FarmMember;
import easyfarm.domain.FarmMemberJoin;

@Mapper
public interface FarmMapper {


	public Farm farmByName(String farmName);
	public int addFarm(Farm farm);
	public int addFarmMember(FarmMember farmMember);
	public List<Farm> myFamr(String memberId);
	public List<Farm> belongFarm(String memberId);
	public Farm detailFarm(Farm farm);
	public Farm updateByFarm(String farmCode, String memberId);
	public int updateFarm(Farm farm);
	public List<Farm> searchFarm(String memberId);
	public List<FarmMember> getMemberFarm(String farmCode);
	public int addFarmMemberJoin(String farmName,String farmJoinPurpose, String memberId);
	public FarmMemberJoin addFarmMemberJoinCheck(String farmName, String memberId);
	public List<FarmMemberJoin> listJoinFarm(String memberId);
	public List<FarmMemberJoin> getJoinFarm(String farmCode);
	public String getFarmMemberLevel(String farmCode, String memberId);
	
	//가입 승인후
	public int farmJoinMember(String farmMemberJoinCode,String approval,String memberId);
	public FarmMemberJoin getJoinMember(String farmMemberJoinCode);
	public int joinAddFarmMember(FarmMember farmMember);
	
	//가입신청한 목록
	public List<FarmMemberJoin> myGetJoinFarm(String memberId);
	//가입신청 취소
	public int removeJoinFarm(String farmJoinCode);
	
	//탈퇴신청
	public FarmCancelRequest addCancelMemberCheck(String memberId, String farmName);
	public int addCancelMember(String memberId, String farmName,String cancelRequestReason);
	public List<FarmCancelRequest> getLeaverFarm(String farmCode,String memberId);
	
	//탈퇴신청 처리
	public int isLeaverFarm(FarmCancelRequest cancelRequest);
	
	//회원 탈퇴 처리
	public int leaverFarmMember(String cancelRequestCode);
	
	//내 탈퇴신청 목록
	public List<FarmCancelRequest> myGetLeaverFarm(String memberId);
	
	//농가탈퇴 취소
	public int cancelLeaverFarm(String cancelLeaverFarm);
	
}
