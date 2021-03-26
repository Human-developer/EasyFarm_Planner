package easyfarm.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import easyfarm.domain.Member;
import easyfarm.domain.Report;
import easyfarm.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	//로그인
	@GetMapping("/member/login")
	public String login(Model model,HttpSession session) {
		
		
		return "views/member/login";
	}

	@PostMapping("/member/login")
	public String login(
			@RequestParam(value = "memberId", required = false) String memberId,
			@RequestParam(value = "memberPw", required = false) String memberPw, HttpSession session) 	
	{
		
		String msg = "로그인실패";
		
		if (memberId != null && !"".equals(memberId.trim()) && memberPw != null && !"".equals(memberPw.trim())) 
		{
			System.out.println("실행");
			Member member = memberService.getMemberInfoById(memberId);
			System.out.println(member.getMemberStatus());
			if(member != null)
			{
				if (member.getMemberPw().equals(memberPw))
				{
					if(member.getMemberStatus() != "탈퇴" && !"탈퇴".equals(member.getMemberStatus().trim()) && 
					   member.getMemberStatus() != "정지" &&	!"정지".equals(member.getMemberStatus().trim())) {
						
					System.out.println("============login============");
					// 아이디
					session.setAttribute("SID", memberId);
					// 권한
					session.setAttribute("SNAME", member.getMemberName());
					// 이름
					session.setAttribute("SLEVEL", member.getLevelName());
					
					memberService.updateLogin(memberId);
					
					return "main";
					}else if(member.getMemberStatus() == "탈퇴" || "탈퇴".equals(member.getMemberStatus().trim())){
						msg = "탈퇴회원입니다";
						
						
						session.setAttribute("Error", msg);
						return "redirect:/member/login";
					
					}else if(member.getMemberStatus() == "정지" || "정지".equals(member.getMemberStatus().trim())){
						
						msg = "정지회원입니다";
						session.setAttribute("Error", msg);
						
						return "redirect:/member/login";
					}
					
				}
							
			}
					
		}
		session.setAttribute("Error", msg);
		return "redirect:/member/login";
	}
	
	

	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		String memberId = (String) session.getAttribute("SID");
		memberService.updateLogout(memberId);
		session.invalidate();
		return "main";
	}
	//회원 등록
	@RequestMapping(value = "/member/addMember", method = RequestMethod.GET)
	public String addMember() {
		return "views/member/memberList/addMember";
	}
	
	@PostMapping("/member/addMember")
	public String addMember(Member member) {
		

		String memberIdenNum = member.getMemberIdenNum() + "-" + member.getMemberIdenNum2();

		member.setMemberIdenNum(memberIdenNum);
		member.setLevelCode("level_2");
		member.setMemberStatus("정상");
		

		memberService.addMember(member);

		return "main";
	}
	//아이디 중복체크
	@PostMapping("/ajax/idCheck")
	public @ResponseBody String idChdck(@RequestParam(value = "memberId", required = false) String memberId) {
		String result = "사용불가능";
		System.out.println(memberId);
		if (memberId != null && !"".equals(memberId) && !"".equals(memberId.trim())) {
			Member member = memberService.getMemberInfoById(memberId);

			if (member != null && member.getMemberId().equals(memberId)) {
				result = "사용불가능";
			} else {
				result = "사용가능";
			}
		}

		return result;
	}

	//회원검색&조회
	@GetMapping("/member/getMember") 
	public String getMember(Model model
						  ,@RequestParam(value="searchKey", required = false) String searchKey
						  ,@RequestParam(value="searchValue", required = false) String searchValue
						  ,@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {
		  
		Map<String, Object> resultMap = null;
		  
		if(searchKey != null && searchValue != null && !"".equals(searchKey) && !"".equals(searchValue)) { 
			  
		 resultMap = memberService.getMemberList(searchKey, searchValue,currentPage);
		  
		 System.out.println(resultMap); 
		}
		else {
			
		 resultMap = memberService.getMemberList(searchKey, searchValue,currentPage);
			 
		}
		 
			  	model.addAttribute("currentPage"		, currentPage);
			  	model.addAttribute("searchKey"			, searchKey);
			  	model.addAttribute("searchValue"		, searchValue);
				model.addAttribute("lastPage"			, resultMap.get("lastPage"));
				model.addAttribute("memberList"			, resultMap.get("memberList"));
				model.addAttribute("startPageNum"		, resultMap.get("startPageNum"));
				model.addAttribute("endPageNum"			, resultMap.get("endPageNum"));
				
		  return "views/member/memberList/getMember";
	  }
	  //회원수정
	  @GetMapping("/member/modifyMember")
	  public String modifyMember(Model model, 
								@RequestParam(name = "memberId", required = false, defaultValue = "") String memberId) {
		  
			if(memberId != null && !"".equals(memberId.trim())) {
				Member member = memberService.getMemberInfoById(memberId);
				model.addAttribute("member", member);
			}
			return "views/member/memberList/modifyMember";
		}
	  
	  @PostMapping("/member/modifyMember")
	  public String modifyMember(Member member) {
	
		  if(member != null && !"".equals(member.getMemberId())) {
			  System.out.println("실행");
			  memberService.modifyMember(member);
		  }
		  System.out.println("실행실패");
		  return "redirect:/member/getMember";
	  }
		
	  //회원 탈퇴
	  @GetMapping("/member/removeMember")
	  public String removeMember(Model model,
			  					@RequestParam(name = "memberId", required = false, defaultValue = "") String memberId
			  					) {
		  
		  if(memberId != null && !"".equals(memberId.trim()) ) {
			  Member member = memberService.getMemberInfoById(memberId);
			  model.addAttribute("member", member);
		  }
		  
		  return "views/member/memberList/removeUpdate";
	  }
	  
	  @PostMapping("/member/removeMember") 
	  public String removeMember(Member member) { 
		  
		  
		  if(member != null && !"".equals(member.getMemberId())) 
		  {
 
			 
			 System.out.println(member.getCancelMemberReason()+"ttttttttttttttttttttttt");
			  memberService.removeUpdateMember(member);
			  Member memberUpdate = memberService.getMemberInfoById(member.getMemberId());
			  memberUpdate.setCancelMemberReason(member.getCancelMemberReason());
			  
			  if(memberUpdate != null) {
				
				  memberService.addCancelMember(memberUpdate);
			  }
		
		  } 
		  
		  
		  return "redirect:/member/getMember"; 
	  }
	 
	
	  //회원 상세보기
	  @GetMapping("/member/detailMember")	  
	  public String detailMember(Model model,
				@RequestParam(name = "memberId", required = false, defaultValue = "") String memberId) {
		  System.out.println(memberId +"ddddddddddddddddddddddd");
		  
		  if(memberId != null && !"".equals(memberId.trim()) ) {
			  Member member = memberService.getMemberInfoById(memberId);
			  model.addAttribute("member", member);
		  }
		  
		  return"views/member/memberList/detailMember";
	  }
	  
	 
	
	 //==============================권한======================
	
	  //조회	  
	  @GetMapping("/member/getAuthority") 
	  public String getAuthority(Model model) {
		  	
		 List<Member> levelList = null;
		 levelList = memberService.getAuthority();
		  
		  
		  model.addAttribute("levelList",levelList);
		  return "views/member/authority/getAuthority"; 
	  }	 
	  
	  //권한등록
	  @GetMapping("/member/addAuthority")
	  public String addAuthority(){
		  
		  return "views/member/authority/addAuthority"; 
	  }
		  
	  @PostMapping("/member/addAuthority") 
	  public String addAuthority(Member member){
		  memberService.addAuthority(member);
		  return "redirect:/member/getAuthority"; 
	  }
	  
	  //권한수정
	  @GetMapping("/member/modifyAuthority")
	  public String modifyAuthority(Model model,
			  				@RequestParam(name = "levelCode" ,required = false,defaultValue = "") String levelCode) {
		  if(levelCode != null) {
			  
			  Member authorityList = memberService.getAuthorityByCode(levelCode);
			  model.addAttribute("authorityList", authorityList);
		  }
		  
		  return "views/member/authority/modifyAuthority"; 
	  }
	  
	  @PostMapping("/member/modifyAuthority")
	  public String modifyAuthority(Member member) {
		  if(member != null) {
			  memberService.modifyAuthority(member);
		  }
		  return "redirect:/member/getAuthority"; 
	  }
	  //권한삭제
	  @GetMapping("/member/removeAuthority")
	  public String removeAuthority(
			  @RequestParam(name = "levelCode" ,required = false,defaultValue = "") String levelCode) {
		  	memberService.removeAuthority(levelCode);
		  return "redirect:/member/getAuthority"; 
	  }
	  
	 
	  //=======================탈퇴회원관리==============
	  //조회
	  @GetMapping("/member/getExpectedDate")
	  public String getExpectedDate() {
		  return "views/member/baseDate/getExpectedDate";
	  }
	  //=======================기준일관리===============  
	  //기준일 조회
	  @GetMapping("/member/getBaseDate")
	  public String getBaseDate(Model model) {
		  return "views/member/baseDate/getBaseDate";
	  }
	  
	  //기준일 등록
	  @GetMapping("/member/addBaseDate")
	  public String addBaseDate() {
		  return "views/member/baseDate/addBaseDate";
	  }
	  @PostMapping("/member/addBaseDate")
	  public String addBaseDate(Model model, Report report) {
		  
		  return "views/member/baseDate/getBaseDate";
	  }
	  //기준일 수정
	  @GetMapping("/member/modifyBaseDate")
	  public String modifyBaseDate(Model model,Report report) {
		  return "views/member/baseDate/modifyBaseDate";
	  }
	  @PostMapping("/member/modifyBaseDate")
	  public String modifyBaseDate() {
		  
		  return "views/member/baseDate/getBaseDate";
	  }
	  //기준일 삭제
	  @GetMapping("/member/removeBaseDate")
	  public String removeBaseDate(Model model) {
		  return "views/member/baseDate/removeBaseDate";
	  }
	  @PostMapping("/member/removeBaseDate")
	  public String removeBaseDate() {
		  
		  return "views/member/baseDate/getBaseDate";
	  }
	  //=================신고관리=================
	  //신고사유조회
	  @GetMapping("/member/getReasonReport")
	  public String getReasonReport(Model model) {
		  
		  
		  return "views/member/report/getReasonReport";
	  }
	  //신고사유등록
	  @GetMapping("/member/addReasonReport")
	  public String addReasonReport() {
		  return "views/member/report/addReasonReport";
	  }
	  @PostMapping("/member/addReasonReport")
	  public String addReasonReport(Report report) {
		  return "views/member/report/addReasonReport";
	  }
	  //신고사유수정
	  @GetMapping("/member/modifyReasonReport")
	  public String modifyReasonReport() {
		  return "views/member/report/modifyReasonReport";
	  }
	  @PostMapping("/member/modifyReasonReport")
	  public String modifyReasonReport(Report report) {
		  return "views/member/report/getReasonReport";
	  }
	  //신고사유삭제
	  @GetMapping("/member/removeReasonReport")
	  public String removeReasonReport() {
		  return "views/member/report/removeReasonReport";
	  }
	  @PostMapping("/member/removeReasonReport")
	  public String removeReasonReport(Report report) {
		  return "views/member/report/getReasonReport";
	  }
	  //신고
	  @GetMapping("/member/addReport")
	  public String addReport() {
		  return "views/member/report/addReport";
	  }
	  @PostMapping("/member/addReport")
	  public String addReport(Report report) {
		  return "redirect:/member/getReport";
	  }
	  //신고목록
	  @GetMapping("/member/getReport")
	  public String getReport(HttpSession session) {
		  if(session.getAttribute("SLEVEL") != null) {			  
			  if(session.getAttribute("SLEVEL") == "관리자") {
				  return "views/member/report/getReport";
			  }
			  if(session.getAttribute("SLEVEL") == "회원") {
				  return "views/member/report/getReportMember";
			  }
		  }
		  return "views/member/report/getReportMember";
	  }
	  //==========정지회원관리==========
	  @GetMapping("/member/getSuspend")
	  public String getSuspend() {
		  
		  return "views/member/memberList/getSuspend";
	  }
	
		  
}
