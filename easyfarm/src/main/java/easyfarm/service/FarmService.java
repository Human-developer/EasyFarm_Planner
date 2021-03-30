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
	
	
	public List<Farm> searchFarm(String memberId){
		List<Farm> resultFarm = null;
		if(memberId != null) {
			resultFarm = farmMapper.searchFarm(memberId); 
		}
		return resultFarm;
	}
	
	
	public List<FarmMember> getMemberFarm(String farmCode){
		List<FarmMember> resultFarmMemberList = null;
		if(farmCode != null) {
			resultFarmMemberList = farmMapper.getMemberFarm(farmCode);
		}
		return resultFarmMemberList;
	}
	
}
