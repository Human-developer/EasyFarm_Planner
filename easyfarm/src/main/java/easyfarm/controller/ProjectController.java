package easyfarm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import easyfarm.domain.Crop;
import easyfarm.domain.CropPhaseInfo;
import easyfarm.domain.Project;
import easyfarm.domain.ProjectWorkphase;
import easyfarm.service.CropService;
import easyfarm.service.ProjectService;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private CropService cropService;
	
	// 프로젝트 메인
	@GetMapping("/project")
	public String projectMain(Model model,
								@RequestParam(value = "farmCode", required = false) String farmCode,
								HttpSession session) {
		if(farmCode == null) {
			farmCode = "farm_1";		
		}
		model.addAttribute("farmCode",farmCode);
		return "views/project/projectMain";
	}
	
	// 프로젝트 등록
	// 등록 1 - 프로젝트 등록 뷰 연결
	@GetMapping("/projectAdd")
	public String projectAdd(Model model,
							@RequestParam String farmCode) {
		model.addAttribute("farmCode",farmCode);
		return "views/project/projectAdd";
	}
	// 등록 2 - 작물 리스트 조회 (ajax)
	@ResponseBody
	@PostMapping("/getCropListByCropCategory")
	public List<Crop> getCropListByCropCategory(String cropCategory) {
		return cropService.getCropListByCropCategory(cropCategory);
	}
	// 등록 3 - 작물별 작업단계 리스트 조회 (ajax)
	@ResponseBody
	@PostMapping("/getCropPhaseByCropCode")
	public List<CropPhaseInfo> getCropPhaseByCropCode(String cropCode){
		return projectService.getCropPhaseByCropCode(cropCode);
	}
	// 등록 4 - 프로젝트 테이블, 작업단계 테이블 인서트
	@PostMapping("/projectAddByProjectObject")
	public String projectAddByProjectObject(Project project,
											@RequestParam String farmCode,
											HttpSession session,
											@RequestParam List<String> checkWorkphase) {
		// 세션에서 멤버아이디 받아옴
		String memberId = (String) session.getAttribute("SID");
		
		project.setFarmCode(farmCode);
		project.setRegMemberId(memberId);
		projectService.projectAddService(project, checkWorkphase);
		String uri = "projectListByFarmCode?farmCode="+farmCode;
		return "redirect:"+uri;
	}
	
	// 프로젝트 리스트 조회 - 농가별 프로젝트 리스트 조회 (농가 테이블 셀렉트)
	@GetMapping("/projectListByFarmCode")
	public String projectListByFarmCode(Model model,
										@RequestParam String farmCode) {
		List<Project> projectList = projectService.getProjectListByFarmCode(farmCode);
		model.addAttribute("projectList",projectList);
		model.addAttribute("farmCode",farmCode);
		return "views/project/projectList";
	}
	
	// 프로젝트 수정
	// 수정 1 - 프로젝트 뷰 연결
	@GetMapping("/projectModify")
	public String projectModify(Model model,
								@RequestParam String farmCode,
								@RequestParam String projectCode) {
		
		Project project = projectService.getProjectByProjectCode(projectCode);
		//List<ProjectWorkphase> projectWorkphaseList=projectService.getProjectWorkphaseListByProjectCode(projectCode);
		//model.addAttribute("projectWorkphaseList",projectWorkphaseList);
		model.addAttribute("project",project);
		model.addAttribute("farmCode", farmCode);
		return "views/project/projectModify";
	}
	// 수정 2 - 프로젝트별 작업단계 조회
	@ResponseBody
	@PostMapping("/getProjectWorkphaseListByProjectCode")
	public List<ProjectWorkphase> getProjectWorkphaseListByProjectCode(String projectCode){
		return projectService.getProjectWorkphaseListByProjectCode(projectCode);
	}
	// 수정 3 - 프로젝트, 프로젝트별 작업단계 update
	@PostMapping("/projectModifyByProjectCode")
	public String projectModifyByProjectCode(Project project,
											@RequestParam String farmCode,
											ProjectWorkphase projectWorkphase) {
		projectService.projectWorkphaseModify(projectWorkphase, project);
		String uri = "projectListByFarmCode?farmCode="+farmCode;
		return "redirect:"+uri;
	}
	
	// 프로젝트 삭제
	@GetMapping("/projectRemoveByProjectCode")
	public String projectRemoveByProjectCode(@RequestParam String projectCode,
											@RequestParam String farmCode,
											HttpServletRequest request) {
		
		int result = projectService.projectRemoveByProjectCode(projectCode);
		if(result == 1) System.out.println(projectCode + " 프로젝트 삭제 성공");
		
		String uri = "projectListByFarmCode?farmCode="+farmCode;
		return "redirect:"+uri;
	}
}