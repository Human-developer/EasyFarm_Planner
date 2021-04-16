package easyfarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Crop;
import easyfarm.domain.CropDetailCategory;
import easyfarm.domain.CropWorkStage;
import easyfarm.domain.TaxPayCate;

@Mapper
public interface CropMapper {
	//작물코드 리스트
	public List<Crop> getCropCode();
	public List<Crop> getCropCode(Map<String, Object> map);
	//작물 추가등록
	public int addCrop(Map<String ,Object> map);
	//작물정보 조회 >>추후동적쿼리로변경
	public Crop getcropCodeInfo(String cropCode);
	public Crop getcropNameInfo(String cropName);
	//작물수정
	public int modifyCrop(Map<String, Object> map);

	
	//작업단계 리스트
	public List<CropWorkStage> getCropWorkStage();
	//작업단계 추가등록
	public int addCropWorkStage(Map<String, Object> map);
	//작업단계정보 조회 >>추후동적쿼리로변경
	public CropWorkStage getcropPhaseInfo(String cropPhaseInfoCode);
	//작업단계정보 조회 - 중복검사 >>추후동적쿼리로변경
	public CropWorkStage getcropPhaseInfo2(String workphase, String cropCode);
	//작업단계 수정
	public int modifyCropWorkStage(CropWorkStage cropPhaseInfoCode);

	//작물 조회
	public List<Crop> getCropListByCropCategory(String cropCategory);
	
	//상세작업항목 리스트
	public List<CropDetailCategory> getCropDetailCategory();
	//상세작업항목 추가등록 
	public int addCropDetailCategory(Map<String, Object> map);
	//상세작업항목 조회
	public CropDetailCategory getCropDetailCategory(String commonWorkphaseCateCode);
	//상세작업항목 수정
	public int modifyCropDetailCategory(CropDetailCategory commonWorkphaseCateCode);
	
	//품목카테고리 등록
	public int addStockCate(Map<String, Object> map);
	
	//공과금카테고리 리스트
	public List<TaxPayCate> getTaxPayCateList();

	
	
	

	 
}
