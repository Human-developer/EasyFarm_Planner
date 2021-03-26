package easyfarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Farm;
import easyfarm.domain.FarmMember;

@Mapper
public interface FarmMapper {

	public List<Farm> myFarm(String memberId);
	public List<Farm> belongFarm(String memberId);
	public Farm detailFarm(String fCode);
	public List<FarmMember> farmMemberList(String memberStatus, String fCode);
	public int addFarm(Farm farm);
	public int addFarmMember(Map<String,String> member);
}
