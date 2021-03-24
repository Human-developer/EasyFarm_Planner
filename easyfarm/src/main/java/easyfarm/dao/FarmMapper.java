package easyfarm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Farm;
import easyfarm.domain.FarmMember;

@Mapper
public interface FarmMapper {

	public List<Farm> searchFarm(String searchKey, String searchValue);
	public Farm myFarm(String fCode);
	public List<FarmMember> myFarmMemberList(String memberStatus, String fCode);
}
