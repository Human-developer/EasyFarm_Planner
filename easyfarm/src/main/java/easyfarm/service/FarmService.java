package easyfarm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyfarm.dao.FarmMapper;
import easyfarm.domain.Farm;
import easyfarm.domain.FarmMember;


@Transactional
@Service
public class FarmService {

	@Autowired
	private FarmMapper farmMapper;
	
	public Map<String,List<Farm>> myFarmList(String searchValue) {
		
		Map<String,List<Farm>> mapListFarm= null;
		if(searchValue != null && !"".equals(searchValue.trim())) {
			List<Farm> myFarmList = farmMapper.searchFarm("ceoId",searchValue);
			List<Farm> belongFarmList = farmMapper.searchFarm("memberId",searchValue);
			
			if(myFarmList != null) {
				if(mapListFarm == null) mapListFarm =new HashMap<>();
				mapListFarm.put("myFarmList", myFarmList);
			}
			
			if(belongFarmList != null) {
				if(mapListFarm == null) mapListFarm =new HashMap<>();
				
				mapListFarm.put("belongFarmList", belongFarmList);	
			}
			
		}
		
		
		
		return mapListFarm;
	}
	public Map<String,Object> detailFarm(String fCode) {
		Map<String,Object> resultMap =null;
		
		Farm myFarm = null;
		myFarm = farmMapper.myFarm(fCode);
		List<FarmMember> farmMemberList = null;
		farmMemberList = farmMapper.myFarmMemberList("정상",fCode);
		
		if(myFarm != null) {
			if(resultMap == null) resultMap = new HashMap<>();
			
			resultMap.put("myFarm",myFarm);
		}
		if(farmMemberList != null) {
			if(resultMap == null) resultMap = new HashMap<>();
			
			resultMap.put("myFarmMemberList",farmMemberList);
		}
		
		
		
		return resultMap;
	}
	
}
