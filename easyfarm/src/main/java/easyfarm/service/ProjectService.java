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
	public void projectAddService(Project project) {
		// 프로젝트 insert
		String max = projectMapper.getMaxProjectCode();
		
		if(max == null) {
			project.setProjectCode("project_1");
		}
		else {
			max="project_"+(Integer.parseInt(max.substring(8))+1);
			project.setProjectCode(max);
		}
		int result1 = projectMapper.projectAddByProjectObject(project);
		if(result1!=0) System.out.println("프로젝트 등록 1단계 성공 --------- 프로젝트 서비스");
		
		// projectWorkphase 객체에 데이터 세팅
		ProjectWorkphase projectWorkphase = new ProjectWorkphase();
		projectWorkphase.setCropCode(project.getCropCode());
		projectWorkphase.setRegMemberId(project.getRegMemberId());
		projectWorkphase.setProjectCode(max);
		
		for(int i = 0; i<project.getCheckWorkphase().size(); i++) {
			String max2 = projectMapper.getMaxProjectWorkphaseCode();
			if(max2 == null) {
				projectWorkphase.setProjectWorkphaseCode("project_workphase_1");
			}
			else {
				max2="project_workphase_"+(Integer.parseInt(max2.substring(18))+1);
				projectWorkphase.setProjectWorkphaseCode(max2);
			}
			projectWorkphase.setCropPhaseInfoCode(project.getCheckWorkphase().get(i));
			// 프로젝트별 작업단계 insert
			int result2 = projectMapper.projectWorkphaseAddByProjectWorkphase(projectWorkphase);
			if(result2!=0) System.out.println("프로젝트 등록 2단계 성공 "+i+"번째 --------------  프로젝트 서비스");
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
	public void projectWorkphaseModify(ProjectWorkphase projectWorkphase, Project project) {
		// 프로젝트 - update
		projectMapper.projectModifyByProjectObject(project);
		
		// 프로젝트별 작업단계 - 전체 N으로 초기화
		String projectCode = project.getProjectCode();
		int result1 = projectMapper.projectWorkphaseModifyN(projectCode);
		if(result1!=0) System.out.println("프로젝트별 작업단계 수정 1단계 성공 --------------  프로젝트 서비스");
		
		// 프로젝트별 작업단계 - 체크된 프로젝트별 작업단계 Y
		String[] projectWorkphaseCode = projectWorkphase.getProjectWorkphaseCode().split(",");
		for(int i = 0; i<projectWorkphaseCode.length; i++) {
			// 프로젝트별 작업단계 update
			int result2 = projectMapper.projectWorkphaseModify(projectWorkphaseCode[i], projectCode);
			if(result2!=0) System.out.println("프로젝트별 작업단계 수정 2단계 성공 "+i+"번째 --------------  프로젝트 서비스");
		}
	}
	
	// 프로젝트 삭제
	public int projectRemoveByProjectCode(String projectCode) {
		return projectMapper.projectRemoveByProjectCode(projectCode);
	}
	
}
