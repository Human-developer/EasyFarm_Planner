package easyfarm.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyfarm.dao.CropMapper;
import easyfarm.domain.Crop;
import easyfarm.domain.CropDetailCategory;
import easyfarm.domain.CropWorkStage;
import easyfarm.domain.Machine;

@Service
@Transactional
public class CropService {
	private final CropMapper cropMapper;
	
	public CropService(CropMapper cropMapper) {
		this.cropMapper = cropMapper;
	}
/**
 * @param searchValue 
 * @param searchKey **********************************************/
	
	//작물 리스트
	public List<Crop> getCropCode(Map<String, Object> map){
		List<Crop> cropList = cropMapper.getCropCode(map);
		return cropList;
		
	}
	
	//작업단계 리스트
	public List<CropWorkStage> getCropWorkStage(){
		List<CropWorkStage> cropWorkStage = cropMapper.getCropWorkStage();
		return cropWorkStage;
		
	}
	
	//작업상세항목 리스트
	public List<CropDetailCategory> getCropDetailCategory(){
		List<CropDetailCategory> cropDetailCategory = cropMapper.getCropDetailCategory();
		return cropDetailCategory;
		
	}
	
	//작물 추가등록
	public int addCrop(Crop crop, String memberId) {
		Map<String, Object> test = new HashMap<>();
		test.put("crop", crop);
		test.put("memberId", memberId);
		int result = cropMapper.addCrop(test);
		return result;
	}
	//작업단계 추가등록
	public int addCropWorkStage(CropWorkStage crop, String memberId) {
		Map<String, Object> test = new HashMap<>();
		test.put("crop", crop);
		test.put("memberId", memberId);
		int result = cropMapper.addCropWorkStage(test);
		return result;
		
	}
	//상세작업항목 등록
	 public int addCropDetailCategory(CropDetailCategory crop, String memberId) { 
		 Map<String, Object> test = new HashMap<>();
		 test.put("crop", crop); 
		 test.put("memberId", memberId); 
		 int result = cropMapper.addCropDetailCategory(test); 
		 return result; 
	 }
	 
	//작물 수정
	public int modifyCrop(Crop crop, String memberId) {
		Map<String, Object> test = new HashMap<>();
		test.put("crop", crop);
		test.put("memberId", memberId);
		int result = cropMapper.modifyCrop(test);
		return result;
	}
	//작물정보조회 - 카테고리 목록 가져오기
	public Crop getcropCateInfo(String cropCode) {
		Crop crop = cropMapper.getcropCodeInfo(cropCode);
		String cropCate = "";
		if(crop != null && crop.getCropCate() != "") {
			cropCate += crop.getCropCate();
			switch (cropCate) {
			case "기타":
				crop.setCropCate("기타");
				break;
			case "과수":
				crop.setCropCate("과수");
				break;
			case "채소":
				crop.setCropCate("채소");
				break;
			case "축산":
				crop.setCropCate("축산");
				break;
			case "특용":
				crop.setCropCate("특용");
				break;
			case "화훼":
				crop.setCropCate("화훼");
				break;
			case "과채":
				crop.setCropCate("과채");
				break;
			case "버섯":
				crop.setCropCate("버섯");
				break;
			case "식량":
				crop.setCropCate("식량");
				break;
			case "약용":
				crop.setCropCate("약용");
				break;
			default:
				crop.setCropCate("기타");
				break;
			}
		}
		return crop;
	}
	
	//작업단계 정보조회
	public CropWorkStage getcropPhaseInfo(String cropPhaseInfoCode) {
		CropWorkStage cropWorkStage = cropMapper.getcropPhaseInfo(cropPhaseInfoCode);
		return cropWorkStage;
	}
	//작업단계 정보조회- 작업단계와 작물코드 동시에 조회
	public CropWorkStage getcropPhaseInfo2(String workphase, String cropCode) {
		CropWorkStage cropWorkStage = cropMapper.getcropPhaseInfo2(workphase, cropCode);
		return cropWorkStage;
		
	}
	//작물 정보조회-작물명 조회
	public Crop getCropNameinfo(String cropName) {
		return cropMapper.getcropNameInfo(cropName);
	}
	//작업단계 정보수정
	public int modifyCropWorkStage(CropWorkStage cropPhaseInfoCode) {
		int result = cropMapper.modifyCropWorkStage(cropPhaseInfoCode);
		return result;
	}
	
	//상세작업항목 조회
	public CropDetailCategory getCropDetailCategory(String commonWorkphaseCateCode) {
		return cropMapper.getCropDetailCategory(commonWorkphaseCateCode);
	}
	//상세작업 정보수정
	public int modifyCropDetailCategory(CropDetailCategory commonWorkphaseCateCode) {
		return cropMapper.modifyCropDetailCategory(commonWorkphaseCateCode);
		
	}
	
	
	//프로젝트 등록 - 작물 리스트 조회
	public List<Crop> getCropListByCropCategory(String cropCategory){
		List<Crop> cropList = cropMapper.getCropListByCropCategory(cropCategory);
		return cropList;
	}
	
	
	
	
	
	
}