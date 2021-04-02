package easyfarm.controller;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	 
	// 매일 00시에 자동실행
	@Scheduled(cron="0 0 00 * * ?")
	public void scheduler(){
	    
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		 
		List<Report> reportMember = memberService.getSuspend();
		 
		//정지해제
		for(Report report : reportMember) {
				 
		    Date banEndDate = report.getBanEndDate();
		    String banCode = report.getBanCurrentCode();
		    String banId = report.getBanMemberId();

		    String toDay= simpleDate.format(today);
		    String banEndDay = simpleDate.format(banEndDate);
		    
		    int compare = toDay.compareTo(banEndDay);

		    if(compare < 0) {
		      memberService.removeBan(banCode,banId);
		    }
		}
		    
	     
	   
		int restDate = memberService.getStatusDays("휴면");
		int withdrawalDate = memberService.getStatusDays("탈퇴");
		List<Member> loginMaxDateList = memberService.getLoginMaxDate();
		List<Report> expectedList = memberService.getExpectedDate();
		List<String> idList = new ArrayList<String>();
		   
		for(Report expected : expectedList ) {
			   
			idList.add(expected.getLoginMemberId());
		}
		   
	  
	   
	   for(Member login : loginMaxDateList) {
		   
		   Date logoutDate = login.getLogoutDate();
		   String loginId = login.getLoginMemberId();
		   
		   cal.setTime(logoutDate);
		   cal.add(cal.DATE, restDate); // 셋팅된 날에 기준일을 더해준다
		   Date restDay = cal.getTime();
		   String autoRestDate = format.format(restDay);//자동휴면일
		   
		   cal.setTime(logoutDate);
		   cal.add(cal.DATE, withdrawalDate); // 셋팅된 날에 기준일을 더해준다 
		   Date withdrawalDay = cal.getTime();
		   String autoWithdrawalDate = format.format(withdrawalDay); //자동탈퇴일
		   
		  //자동휴면|탈퇴 예정일조회 테이블에 아이디없을시 등록
		  if(!idList.contains(loginId)) {
			  memberService.addStatusSchedule(loginId,autoRestDate,autoWithdrawalDate);
			  
		  //아이디가 있을시 예쩡일 업데이트 
		  }else if(idList.contains(loginId)) {
				  
				  memberService.updateStatusSchedule(loginId,autoRestDate,autoWithdrawalDate);
		  }
			 
		  
	   }
	   
	   for(Report expected : expectedList) {
		 Date RestDay = expected.getAutoRestDate();
		 Date WithdrawalDay = expected.getAutoWithdrawalDate();
		 String memberId = expected.getLoginMemberId();
		   
		   int compare = today.compareTo(RestDay);
		   int Comparison = today.compareTo(WithdrawalDay);
		   if(compare < 0) {
			   Member member = memberService.getMemberInfoById(memberId);
			   member.setUseStatus("휴면");
			   //상태 휴면으로 변경
			   memberService.removeUpdateMember(member);
		   }
		   
		   if(Comparison < 0) {
			   Member member = memberService.getMemberInfoById(memberId);
			   member.setUseStatus("탈퇴");
			   //회원목록에서는 상태탈퇴로 변경
			   memberService.removeUpdateMember(member);
			   //탈퇴회원등록
			   memberService.addCancelMember(member);
		   }
		   
	   }
	   
	   
	   
	 }
	
	 @GetMapping("/member")
	 public String member() {
		 return "views/member/member";
	 }

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
					    member.getMemberStatus() != "정지" &&	 !"정지".equals(member.getMemberStatus().trim())) {
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
					 }else if(member.getMemberStatus() == "휴면" || "휴면".equals(member.getMemberStatus().trim())) {
						 
						 msg = "휴면회원입니다";
						 session.setAttribute("Error", msg);
						 
						 return "";				 
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
		  
		  for(int i=0; i < cancelList.size(); i++) {
			  
			  
			  String address = cancelList.get(i).getMemberAddress().substring(0,4);
			  String date = cancelList.get(i).getCancelDate().substring(0,8);
			  String phone= cancelList.get(i).getMemberPhone().substring(0,5);
			  String email = cancelList.get(i).getMemberEmail().substring(0,4);
			  String reason = cancelList.get(i).getCancelMemberReason().substring(0,6);
			  
			  
			  cancelList.get(i).setMemberAddress(address+"...");  
			  cancelList.get(i).setCancelDate(date+"...");  
			  cancelList.get(i).setMemberPhone(phone+"...");  
			  cancelList.get(i).setMemberEmail(email+"...");  
			  cancelList.get(i).setCancelMemberReason(reason+"...");  
		  }
		  
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
		  
		  return "views/member/baseDate/addBaseDate";
	  }
	  
	  @PostMapping("/member/addBaseDate")
	  public String addBaseDate(Report report) {
		  
		  
			  if("휴면".equals(report.getStatusCriteriaName())) {
				  List<Report> reportList = memberService.getBaseDate();
				  for(int i=0; i < reportList.size(); i++) {
					  String status = reportList.get(i).getUseStatus();
					  String statusCode = reportList.get(i).getStatusCriteriaCode();
					  if("Y".equals(status)) {
						  String statusName = "휴면"; 
						  memberService.modifyBaseDateStatus(statusCode,statusName);
					  }
				  }
				  
			  }
			  else if("탈퇴".equals(report.getStatusCriteriaName())) {
				  List<Report> reportList = memberService.getBaseDate();
				  
				  for(int i=0; i < reportList.size(); i++) {
					  String status = reportList.get(i).getUseStatus();
					  String statusCode = reportList.get(i).getStatusCriteriaCode();
					  if("Y".equals(status)) {
						  String statusName = "탈퇴"; 
						  memberService.modifyBaseDateStatus(statusCode,statusName);
					  }
				  }
			  
			  }
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
		 
		  if("Y".equals(report.getUseStatus())) {
			  
			  List<Report> reportList = memberService.getBaseDate();
			  
			  if("휴면".equals(report.getStatusCriteriaName())) {
				  for(int i=0; i < reportList.size(); i++) {
					  String status = reportList.get(i).getUseStatus();
					  String statusCode = reportList.get(i).getStatusCriteriaCode();
					  if("Y".equals(status)) {
						  String statusName = "휴면"; 
						  memberService.modifyBaseDateStatus(statusCode,statusName);
					  }
				  }
				  
			  }
			  else if("탈퇴".equals(report.getStatusCriteriaName())) {
				  
				  
				  for(int i=0; i < reportList.size(); i++) {
					  String status = reportList.get(i).getUseStatus();
					  String statusCode = reportList.get(i).getStatusCriteriaCode();
					  if("Y".equals(status)) {
						  String statusName = "탈퇴"; 
						  memberService.modifyBaseDateStatus(statusCode,statusName);
					  }
				  }
			  
			  }
		  }
		  memberService.modifyBaseDate(report);
		  
		  return "redirect:/member/getBaseDate";
	  }
	  //기준일 삭제
	  @GetMapping("/member/removeBaseDate")
	  public String removeBaseDate(@RequestParam(value = "statusCriteriaCode",required = false, defaultValue = "" )String statusCriteriaCode) {
		  
		  Report baseDate = memberService.getBaseDate(statusCriteriaCode);
		  
		  //비교를위해 휴면&탈퇴 이름을 변수가 닮아준다
		  String statusName = baseDate.getStatusCriteriaName();
		  //삭제
		  memberService.removeBaseDate(statusCriteriaCode);
			  
		  //삭제후 가장최근에 등록한 기준일상태를 Y로 변경  
		  statusCriteriaCode = memberService.getStatus(statusName);
		  if(statusCriteriaCode != null && !"".equals(statusCriteriaCode.trim())) {
			  memberService.cancelBaseDateStatus(statusCriteriaCode,statusName);		  
		  }
		  
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
	  public String modifyReasonReport(Model model,
			   @RequestParam(value = "reportCode",required = false,defaultValue = "")String reportCode) {
		  
		  Report reasonReport = memberService.getReasonReport(reportCode);
		  model.addAttribute("reasonReport",reasonReport);
		  
		  return "views/member/report/modifyReasonReport";
	  }
	  @PostMapping("/member/modifyReasonReport")
	  public String modifyReasonReport(Report report,
			   @RequestParam(value = "reportCode",required = false,defaultValue = "")String reportCode) {
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
	  public String addReport(Model model, 
			  	@RequestParam(value = "memberId", required = false) String memberId) {
		  List<Report> reportReason = memberService.getReasonReport();
		  
		  model.addAttribute("reportReason",reportReason);
		  model.addAttribute("memberId",memberId);
		  return "views/member/report/addReport";
	  }
	  @PostMapping("/member/addReport")
	  public String addReport(Report report) {
		 
		 memberService.addReport(report);
		  
		  return "redirect:/member/getReport";
	  }
	  //신고 승인|반려
	  @GetMapping("/member/resultReport")
	  public String resultReport(
			  	@RequestParam(value = "reportHistoryCode",required = false,defaultValue = "")String reportHistoryCode,
			  	@RequestParam(value = "reportApprovalReason",required = false,defaultValue = "")String reportApprovalReason,
			  	@RequestParam(value = "reportCode",required = false,defaultValue = "")String reportCode,
			  	@RequestParam(value = "reportedId",required = false,defaultValue = "")String reportedId,
			  	@RequestParam(value = "reportApproval",required = false,defaultValue = "")String reportApproval) {
		  if("승인".equals(reportApproval)) {
			  System.out.println("실행");
			  // 신고상태변경
			  memberService.resultReport(reportHistoryCode,reportApproval,reportApprovalReason);
			  // 정지일수 측정
			  Report report = memberService.getReasonReport(reportCode);
			  int banDay = report.getReportBanDays();		  
			  // 측정한일수를 데이터값으로 변경
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  Calendar cal = Calendar.getInstance();
			  Date today = new Date();
			  cal.setTime(today);
			  cal.add(cal.DATE, banDay);
			  Date date = cal.getTime();
			  //정지 풀리는 날 측정
			  String banEndDay = format.format(date);
			  //이미 정지되있을땐 x 
			  Report member = memberService.getSuspend(reportedId);
			  String banId = member.getBanMemberId();
			  if(banId != null) {
				  memberService.addBanCurrentSituation(reportedId,reportCode,banEndDay);			  
			  }			  
		  
		  }else {			  
			  memberService.resultReport(reportHistoryCode,reportApproval,reportApprovalReason);			  
		  }
		  return "redirect:/member/getReport";
	  }
	  //신고목록
	  @GetMapping("/member/getReport")
	  public String getReport(HttpSession session,Model model) {
		  if(session.getAttribute("SLEVEL") != null) {			  
			  if("관리자".equals(session.getAttribute("SLEVEL"))) {
				  
				  List<Report> reportList = memberService.getReport(null);
				
				  for(Report report : reportList) { 
					  
				   String date = report.getReportDate().substring(2,10); 
				   report.setReportDate(date);
				  }
				 
				  model.addAttribute("reportList",reportList);
				  return "views/member/report/getReport";
			  }
			  if(!"관리자".equals(session.getAttribute("SLEVEL"))) {
				  String memberId = (String) session.getAttribute("SID");
				  
				  List<Report> reportList = memberService.getReport(memberId);
				  
				  for(Report report : reportList) { 
					  
					   String date = report.getReportDate().substring(2,10); 
					   report.setReportDate(date);
					  }
				  
				  model.addAttribute("reportList",reportList);
				  return "views/member/report/getReportMember";
			  }
			 
		  }
		  
		  return "views/member/report/getReportMember";
	  }
	  //신고목록 안보이게하기
	  @GetMapping("/member/removeReportHistoryCode")
	  public String removeReportHistory(
			  	@RequestParam(value = "reportHistoryCode",required = false, defaultValue = "")String reportHistoryCode) {
		  if(reportHistoryCode != null && !"".equals(reportHistoryCode.trim())){
			  memberService.removeReportHistory(reportHistoryCode);	  
		  }
		  return "redirect:/member/getReport";
	  }
	  //신고기록삭제
	  @GetMapping("/member/removeReport")
	  public String removeReport(
			  	@RequestParam(value = "reportHistoryCode",required = false, defaultValue = "")String reportHistoryCode) {
		  if(reportHistoryCode != null) {
			  memberService.removeReport(reportHistoryCode);
		  }
		 return "redirect:/member/getReport";
	  }
	  //==========정지회원관리==========
	  @GetMapping("/member/getSuspend")
	  public String getSuspend(Model model) {
		  
		  List<Report> banList = memberService.getSuspend();
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  for(Report ban : banList) {
			  String banEndDate= format.format(ban.getBanEndDate());
			  ban.setBanBeginDate(ban.getBanBeginDate().substring(2,10));
			  ban.setRegDate(banEndDate.substring(2,10));
		  }
		  model.addAttribute("banList",banList);
		  
		  return "views/member/memberList/getSuspend";
	  }
	
		  
}
