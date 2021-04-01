package easyfarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Farm;
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
	
	
	//가입 승인후
	public int farmJoinMember(String farmMemberJoinCode,String approval,String memberId);
	public FarmMemberJoin getJoinMember(String farmMemberJoinCode);
	public int joinAddFarmMember(FarmMember farmMember);
	
}
