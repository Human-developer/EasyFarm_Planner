package easyfarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Crop;
import easyfarm.domain.CropDetailCategory;
import easyfarm.domain.CropWorkStage;

@Mapper
public interface CropMapper {
	//작물코드 리스트
	public List<Crop> getCropCode();
	public List<Crop> getCropCode(Map<String, Object> map);
	//작물 추가등록
	public int addCrop(Map<String ,Object> map);
	//작물수정
	public int modifyCrop(Map<String, Object> map);

	//작물정보 조회
	public Crop getcropCodeInfo(String cropCode);
	public Crop getcropNameInfo(String cropName);

	
	//작업단계 리스트
	public List<CropWorkStage> getCropWorkStage();
	//작업단계 추가등록
	public int addCropWorkStage(Map<String, Object> map);
	//작업단계정보 조회
	public CropWorkStage getcropPhaseInfo(String cropPhaseInfoCode);

	
	//상세작업항목 리스트
	public List<CropDetailCategory> getCropDetailCategory();
	//상세작업항목 추가등록 
	public int addCropDetailCategory(Map<String, Object> map);
	
	
	

	 
}
