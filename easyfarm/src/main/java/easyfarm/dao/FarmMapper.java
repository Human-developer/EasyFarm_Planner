package easyfarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Farm;
import easyfarm.domain.FarmMember;

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
}
