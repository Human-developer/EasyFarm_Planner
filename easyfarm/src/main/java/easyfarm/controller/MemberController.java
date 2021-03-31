package easyfarm.controller;


import java.util.List;
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
		 if(session.getAttribute("SID") == null) {
					
			 return "views/member/login";
		 }
		
		 return "main";
	 }

	 @PostMapping("/member/login")
	 public String login(
			 @RequestParam(value = "memberId", required = false) String memberId,
			 @RequestParam(value = "memberPw", required = false) String memberPw, HttpSession session) 	
	 {
		
		 String msg = "로그인실패";
		
		 if (memberId != null && !"".equals(memberId.trim()) && memberPw != null && !"".equals(memberPw.trim())) 
		 {
			
			 Member member = memberService.getMemberInfoById(memberId);
			
			 if(member != null)
			 {
				 if (member.getMemberPw().equals(memberPw))
				 {
					 if(member.getMemberStatus() != "탈퇴" && !"탈퇴".equals(member.getMemberStatus().trim()) && 
					    member.getMemberStatus() != "정지" &&	!"정지".equals(member.getMemberStatus().trim())) {
						 if(session.getAttribute("SID") == null) {
							 //memberService.updateLogin(memberId);							
						 }
					
					 // 아이디
					 session.setAttribute("SID", memberId);
					 // 권한
					 session.setAttribute("SNAME", member.getMemberName());
					 // 이름
					 session.setAttribute("SLEVEL", member.getLevelName());
					 
									
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
	 //로그인기록 조회
	 @GetMapping("/member/getLoginHistory")
	 public String getloginHistory(Model model) {
		
		 List<Member> loginList = memberService.getLogin();
		 model.addAttribute("loginList",loginList);
		
		 return "views/member/getLoginHistory";
	 }
	
	
	 //로그아웃
	 @GetMapping("/member/logout")
	 public String logout(HttpSession session) {
		 String memberId = (String) session.getAttribute("SID");
		 if(memberId != null) {
			
			 Member member = memberService.getLoginCode(memberId);
			
			 if(member != null){
				
				 String loginCode = member.getLoginCode();
				 memberService.updateLogout(loginCode);
			 }
		 }
		
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
		
		 if(member != null) {
			
			 String memberIdenNum = member.getMemberIdenNum() + "-" + member.getMemberIdenNum2();

			 member.setMemberIdenNum(memberIdenNum);
			 member.setLevelCode("level_2");
			 member.setMemberStatus("정상");
			
	
			 memberService.addMember(member);
		 }

		 return "main";
	 }
	//아이디 중복체크
	 @PostMapping("/ajax/idCheck")
	 public @ResponseBody String idChdck(@RequestParam(value = "memberId", required = false) String memberId) {
	 	 String result = "사용불가능";

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
						) {
		  
		List<Member> memberList = memberService.getMemberList();
		model.addAttribute("memberList",memberList);
		  return "views/member/memberList/getMember";
	  }
	  //회원수정
	  @GetMapping("/member/modifyMember")
	  public String modifyMember(Model model, 
								@RequestParam(name = "memberId", required = false, defaultValue = "") String memberId) {
		  
			if(memberId != null && !"".equals(memberId.trim())) {
				Member member = memberService.getMemberInfoById(memberId);
				List<Member> levelList = memberService.getAuthority();
				model.addAttribute("levelList", levelList);
				model.addAttribute("member", member);
			}
			return "views/member/memberList/modifyMember";
	  }
	  
	  @PostMapping("/member/modifyMember")
	  public String modifyMember(Member member) {
	
		  if(member != null && !"".equals(member.getMemberId())) {
			
			  memberService.modifyMember(member);
		  }
		
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
		  	
		
		  List<Member> levelList = memberService.getAuthority();	  		  
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
		  if(member != null) {		  
			  memberService.addAuthority(member);
		  }
		  return "redirect:/member/getAuthority"; 
	  }
	  
	  //권한수정
	  @GetMapping("/member/modifyAuthority")
	  public String modifyAuthority(Model model,
			  				@RequestParam(name = "levelCode" ,required = false,defaultValue = "") String levelCode) {
		  
			  Member authorityList = memberService.getAuthorityByCode(levelCode);
			  model.addAttribute("authorityList", authorityList);
			  
			  
		  
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
		 
		  List<Member> fkCode =  memberService.getCode(levelCode);
							  
		  if(fkCode.size() == 0 ) {
			  
			  memberService.removeAuthority(levelCode);
		  }
		
		  return "redirect:/member/getAuthority"; 
	  }
	  
	  //=======================탈퇴회원관리==============
	  //조회
	  @GetMapping("/member/getCancelMember")
	  public String getCancelMember(Model model) {
		  List<Member> cancelList = memberService.getCancelMember();
		  model.addAttribute("cancelList",cancelList);
		  
		  return "views/member/memberList/getCancelMember";
	  }
	  //=======================탈퇴예정일관리==============
	  //조회
	  @GetMapping("/member/getExpectedDate")
	  public String getExpectedDate(Model model) {
		  List<Report> expectedList = memberService.getExpectedDate(); 
		  model.addAttribute("expectedList",expectedList);
		  
		  return "views/member/baseDate/getExpectedDate";
	  }
	  
	  //=======================기준일관리===============  
	  //기준일 조회
	  @GetMapping("/member/getBaseDate")
	  public String getBaseDate(Model model) {
		  List<Report> baseDateList = memberService.getBaseDate();
		  model.addAttribute("baseDateList",baseDateList);
		  
		  return "views/member/baseDate/getBaseDate";
	  }
	  
	  //기준일 등록
	  @GetMapping("/member/addBaseDate")
	  public String addBaseDate(Model model) {
		
		  List<Report> nameList = memberService.getBaseDate();			
		  model.addAttribute("nameList",nameList);
		 
		  return "views/member/baseDate/addBaseDate";
	  }
	  
	  @PostMapping("/member/addBaseDate")
	  public String addBaseDate(Report report) {
		 // System.out.prStringln(report.getUseStatus()+ "11111111111111111111111");
		  
		  memberService.addBaseDate(report);
		  return "redirect:/member/getBaseDate";
	  }
	  
	  //기준일 수정
	  @GetMapping("/member/modifyBaseDate")
	  public String modifyBaseDate(Model model,
			  				@RequestParam(value = "statusCriteriaCode",required = false, defaultValue = "" )String statusCriteriaCode) {
		  	
		  	Report baseDate = memberService.getBaseDate(statusCriteriaCode);
		  	
		  	
		 
		  	model.addAttribute("baseDate",baseDate);
		  	
		  return "views/member/baseDate/modifyBaseDate";
	  }
	  
	  @PostMapping("/member/modifyBaseDate")
	  public String modifyBaseDate(Report report) {
		  memberService.modifyBaseDate(report);
		  return "redirect:/member/getBaseDate";
	  }
	  //기준일 삭제
	  @GetMapping("/member/removeBaseDate")
	  public String removeBaseDate(@RequestParam(value = "statusCriteriaCode",required = false, defaultValue = "" )String statusCriteriaCode) {
		  
		  
		  memberService.removeBaseDate(statusCriteriaCode);
		  
		  return "redirect:/member/getBaseDate";

	  }
	  
	  //=================신고관리=================
	  //신고사유조회
	  @GetMapping("/member/getReasonReport")
	  public String getReasonReport(Model model) {
		  List<Report> reportReasonList = memberService.getReasonReport();
		  
		  model.addAttribute("reportReasonList",reportReasonList);
		  
		  return "views/member/report/getReasonReport";
	  }
	  
	  //신고사유등록
	  @GetMapping("/member/addReasonReport")
	  public String addReasonReport() {
		  return "views/member/report/addReasonReport";
	  }
	  
	  @PostMapping("/member/addReasonReport")
	  public String addReasonReport(Report report) {
		  memberService.addReasonReport(report);
		  return "redirect:/member/getReasonReport";
	  }
	  
	  //신고사유수정
	  
	  @GetMapping("/member/modifyReasonReport")
	  public String modifyReasonReport(Model model
			  ,@RequestParam(value = "reportCode",required = false,defaultValue = "")String reportCode) {
		  
		  Report reasonReport = memberService.getReasonReport(reportCode);
		  model.addAttribute("reasonReport",reasonReport);
		  
		  return "views/member/report/modifyReasonReport";
	  }
	  @PostMapping("/member/modifyReasonReport")
	  public String modifyReasonReport(Report report
			  ,@RequestParam(value = "reportCode",required = false,defaultValue = "")String reportCode) {
		  report.setReportCode(reportCode);
		  memberService.modifyReasonReport(report);
		  return "redirect:/member/getReasonReport";
	  }
	  
	  //신고사유삭제
	  @GetMapping("/member/removeReasonReport")
	  public String removeReasonReport(
			  @RequestParam(value = "reportCode",required = false,defaultValue = "")String reportCode) {
		  
		  memberService.removeReasonReport(reportCode);
		  
		  return "redirect:/member/getReasonReport";
	  }
	 
	  //신고
	  @GetMapping("/member/addReport")
	  public String addReport(Model model) {
		  List<Report> reportReason = memberService.getReasonReport();
		  
		  model.addAttribute("reportReason",reportReason);
		  return "views/member/report/addReport";
	  }
	  @PostMapping("/member/addReport")
	  public String addReport(Report report) {
		  return "redirect:/member/getReport";
	  }
	  //신고 승인|반려
	  @GetMapping("/member/resultReport")
	  public String resultReport(
			  @RequestParam(value = "reportHistoryCode",required = false,defaultValue = "")String reportHistoryCode,
			  @RequestParam(value = "reportApproval",required = false,defaultValue = "")String reportApproval) {
		  
		  memberService.resultReport(reportHistoryCode,reportApproval);
		  return "redirect:/member/getReport";
	  }
	  //신고목록
	  @GetMapping("/member/getReport")
	  public String getReport(HttpSession session,Model model) {
		  if(session.getAttribute("SLEVEL") != null) {			  
			  if("관리자".equals(session.getAttribute("SLEVEL"))) {
				 
				  List<Report> reportList = memberService.getReport(null);
				  model.addAttribute("reportList",reportList);
				  return "views/member/report/getReport";
			  }
			  if(!"관리자".equals(session.getAttribute("SLEVEL"))) {
				  String memberId = (String) session.getAttribute("SID");
				  List<Report> reportList = memberService.getReport(memberId);
				  model.addAttribute("reportList",reportList);
				  return "views/member/report/getReportMember";
			  }
			 
		  }
		  
		  return "views/member/report/getReportMember";
	  }
	  @GetMapping("/member/removeReport")
	  public String removeReport(@RequestParam(value = "reportHistoryCode",required = false,defaultValue = "")String reportHistoryCode) {
		  if(reportHistoryCode != null) {
			  memberService.removeReport(reportHistoryCode);
		  }
		  return "rediract:/member/getReport";
	  }
	  //==========정지회원관리==========
	  @GetMapping("/member/getSuspend")
	  public String getSuspend() {
		  
		  return "views/member/memberList/getSuspend";
	  }
	
		  
}
