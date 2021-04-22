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

	//농가 중복확인
	public Farm farmByName(String farmName); 													
	//농가 등록
	public int addFarm(Farm farm);																
	//농가회원등록
	public int addFarmMember(FarmMember farmMember);											
	//내농가보기
	public List<Farm> myFamr(String memberId);										
	//내소속농가보기
	public List<Farm> belongFarm(String memberId);												
	//농가 상세보기
	public Farm detailFarm(Farm farm);															
	//농가정보조회
	public Farm updateByFarm(String farmCode, String memberId); 								
	//농가 수정
	public int updateFarm(Farm farm);															
	//농가 검색
	public List<Farm> searchFarm(String memberId); 												
	//농가 회원목록 조회
	public List<FarmMember> getMemberFarm(String farmCode);										
	//농가 가입 신청 등록
	public int addFarmMemberJoin(String farmName,String farmJoinPurpose, String memberId);		
	//농가 가입 신청 중복 확인
	public FarmMemberJoin addFarmMemberJoinCheck(String farmName, String memberId);				
	//농가 가입 신청 목록 조회
	public List<FarmMemberJoin> listJoinFarm(String memberId);									
	//농가 가입 신청 목록 조회
	public List<FarmMemberJoin> getJoinFarm(String farmCode);									
	//농가 회원권한 조회
	public String getFarmMemberLevel(String farmCode, String memberId);							
	//농가 가입 승인/거부
	public int farmJoinMember(String farmMemberJoinCode,String approval,String memberId);		
	//농가 가입신청 정보 조회
	public FarmMemberJoin getJoinMember(String farmMemberJoinCode);								
	//농가 회원 등록
	public int joinAddFarmMember(FarmMember farmMember);										
	//가입신청한 목록
	public List<FarmMemberJoin> myGetJoinFarm(String memberId);									
	//가입신청 취소
	public int removeJoinFarm(String farmJoinCode);												
	//농가 탈퇴 신청 중복 확인
	public FarmCancelRequest addCancelMemberCheck(String memberId, String farmName);			
	//농가 탈퇴 신청 등록
	public int addCancelMember(String memberId, String farmName,String cancelRequestReason);	
	//농가 탈퇴 신청 목록 조회
	public List<FarmCancelRequest> getLeaverFarm(String farmCode,String memberId);				
	//탈퇴신청 처리
	public int isLeaverFarm(FarmCancelRequest cancelRequest);									
	//회원 탈퇴 처리
	public int leaverFarmMember(String cancelRequestCode);										
	//내 탈퇴신청 목록
	public List<FarmCancelRequest> myGetLeaverFarm(String memberId);							
	//농가탈퇴 취소
	public int cancelLeaverFarm(String cancelLeaverFarm);										
	//농가 대표제외 회원목록 조회
	public List<FarmMember> farmMemeberList(String farmCode);									
	//농가 대표 수정
	public int modifyCeoFarmProcedure(String farmMemberCode);									
	//농가 대표 회원코드 조회
	public String farmCeoMemberCode(String farmCode);											
	//회원 권한 수정
	public int modifyFarmMemberLevel(String farmMemberCode, String memberLevel);				
	//농가 정보 대표 수정
	public int modifyFarmCeo(String farmCode,String memberId);									
	//농가 대표 수정
	public boolean modifyFarmCeoCheck(String ceoMemberCode, String memberCode, String memberId, String farmCode);
	//농가회원 추방
	public int deportationFarmMember(String farmMemberCode);									
	//농가회원 탈퇴/추방 등록
	public int deportationCancelRequest(String farmMemberCode,String farmCode, String memberId);
	
}
