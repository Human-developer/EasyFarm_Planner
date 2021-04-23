package easyfarm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.CropPhaseInfo;
import easyfarm.domain.Project;
import easyfarm.domain.ProjectWorkphase;

@Mapper
public interface ProjectMapper {
	
	// 프로젝트 등록
	public List<CropPhaseInfo> getCropPhaseByCropCode(String cropCode); 					// 등록뷰 - 작물 리스트 조회
	public int addProjectByProject(Project project); 									// 등록 - 프로젝트 테이블 insert
	public int addProjectWorkphaseByProjectWorkphase(ProjectWorkphase projectWorkphase); 	// 등록 - 프로젝트작업단계 테이블 insert
	public String getMaxProjectCode();
	public String getMaxProjectWorkphaseCode();
	public List<String> getCropPhaseCodeByCropCode(String cropCode);
	// 프로젝트 리스트 조회
	public List<Project> getProjectListByFarmCode(String farmCode); // 조회 - 프로젝트 리스트 조회
	
	// 프로젝트 수정
	public Project getProjectByProjectCode(String projectCode); // 수정뷰 - 프로젝트 정보 조회
	public int modifyProjectByProject(Project project); 	// 수정 - 프로젝트 테이블 update
	public List<ProjectWorkphase> getProjectWorkphaseListByProjectCode(String projectCode);
	public int modifyProjectWorkphase(String projectWorkphaseCode, String projectCode);
	public int modifyProjectWorkphaseUseStatus(String projectCode);
	
	// 프로젝트 삭제
	public int removeProjectByProjectCode(String projectCode); // 삭제 - 프로젝트 테이블 delete
}
