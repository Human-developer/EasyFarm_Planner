package easyfarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyfarm.dao.ProjectMapper;
import easyfarm.domain.CropPhaseInfo;
import easyfarm.domain.Project;
import easyfarm.domain.ProjectWorkphase;

@Transactional
@Service
public class ProjectService {
	
	// projectMapper 연결
	private final ProjectMapper projectMapper;
	@Autowired
	public ProjectService(ProjectMapper projectMapper) {
		this.projectMapper = projectMapper;
	}
	
	// 프로젝트 등록
	// 등록 1 - 작물 리스트 조회
	public List<CropPhaseInfo> getCropPhaseByCropCode(String cropCode){
		return projectMapper.getCropPhaseByCropCode(cropCode);
	}
	// 등록 2 - 프로젝트, 프로젝트별 작업단계 insert
	public void addProjectService(Project project, List<String> checkWorkphase) {
		
		// 프로젝트 insert
		String maxProjectCode = projectMapper.getMaxProjectCode();
		if(maxProjectCode == null) {
			project.setProjectCode("project_1");
		}
		else {
			maxProjectCode="project_"+(Integer.parseInt(maxProjectCode)+1);
			project.setProjectCode(maxProjectCode);
		}
		int result1 = projectMapper.addProjectByProject(project);
		if(result1!=0) System.out.println("프로젝트 등록 1단계 성공 --------- 프로젝트 서비스");
		
		// projectWorkphase 객체에 데이터 세팅
		ProjectWorkphase projectWorkphase = new ProjectWorkphase();
		projectWorkphase.setCropCode(project.getCropCode());
		projectWorkphase.setRegMemberId(project.getRegMemberId());
		projectWorkphase.setProjectCode(maxProjectCode);
		
		
		List<String> cropWorkphaseList = projectMapper.getCropPhaseCodeByCropCode(project.getCropCode());
		for(int i = 0; i<cropWorkphaseList.size(); i++) {
			String maxProjectWorkphaseCode = projectMapper.getMaxProjectWorkphaseCode();
			if(maxProjectWorkphaseCode == null) {
				projectWorkphase.setProjectWorkphaseCode("project_workphase_1");
			}
			else {
				maxProjectWorkphaseCode="project_workphase_"+(Integer.parseInt(maxProjectWorkphaseCode)+1);
				projectWorkphase.setProjectWorkphaseCode(maxProjectWorkphaseCode);
			}
			
			String cropPhaseInfoCode = cropWorkphaseList.get(i);
			projectWorkphase.setCropPhaseInfoCode(cropPhaseInfoCode);
			projectWorkphase.setUseStatus("N");
			for(int j = 0; j<checkWorkphase.size(); j++) {
				if(cropPhaseInfoCode.equals(checkWorkphase.get(j))) {
					projectWorkphase.setUseStatus("Y");
				}
			}
			int result2 = projectMapper.addProjectWorkphaseByProjectWorkphase(projectWorkphase);
			if(result2 !=0) System.out.println("프로젝트 등록 2단계 성공"+i+"번째 --------- 프로젝트 서비스");
		}
	}
	
	// 프로젝트 리스트 조회
	public List<Project> getProjectListByFarmCode(String farmCode){
		return projectMapper.getProjectListByFarmCode(farmCode);
	}

	// 프로젝트 수정
	// 수정 1 - 프로젝트 조회
	public Project getProjectByProjectCode(String projectCode) {
		return projectMapper.getProjectByProjectCode(projectCode);
	}
	// 수정 2 - 프로젝트별 작업단계 조회
	public List<ProjectWorkphase> getProjectWorkphaseListByProjectCode(String projectCode){
		return projectMapper.getProjectWorkphaseListByProjectCode(projectCode);
	}
	// 수정 3 - 프로젝트, 프로젝트별 작업단계 update
	public void modifyProjectWorkphase(ProjectWorkphase projectWorkphase, Project project) {
		// 프로젝트 - update
		projectMapper.modifyProjectByProject(project);
		
		// 프로젝트별 작업단계 - 전체 N으로 초기화
		String projectCode = project.getProjectCode();
		int result1 = projectMapper.modifyProjectWorkphaseUseStatus(projectCode);
		if(result1!=0) System.out.println("프로젝트별 작업단계 수정 1단계 성공 --------------  프로젝트 서비스");
		
		// 프로젝트별 작업단계 - 체크된 프로젝트별 작업단계 Y
		String[] projectWorkphaseCode = projectWorkphase.getProjectWorkphaseCode().split(",");
		for(int i = 0; i<projectWorkphaseCode.length; i++) {
			// 프로젝트별 작업단계 update
			int result2 = projectMapper.modifyProjectWorkphase(projectWorkphaseCode[i], projectCode);
			if(result2!=0) System.out.println("프로젝트별 작업단계 수정 2단계 성공 "+i+"번째 --------------  프로젝트 서비스");
		}
	}
	
	// 프로젝트 삭제
	public int removeProjectByProjectCode(String projectCode) {
		return projectMapper.removeProjectByProjectCode(projectCode);
	}
	
}
