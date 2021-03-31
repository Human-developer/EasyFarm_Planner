package easyfarm.service;


import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyfarm.dao.MemberMapper;
import easyfarm.domain.Member;
import easyfarm.domain.Report;

@Service
@Transactional
public class MemberService {
	
	private MemberMapper memberMapper;
	
	 MemberService(MemberMapper memberMapper) { 
		 this.memberMapper = memberMapper;
	 }
	 
	 public Member getMemberInfoById(String memberId) {
		 
		 Member member = memberMapper.getMemberInfoById(memberId);
			return member;
		 
		
	 }
	 //회원등록
	 public void addMember(Member member) {
		
		memberMapper.addMember(member);
		 
	 }
	 
	 public List<Member> getMemberList(){
		 List<Member> memberList = memberMapper.getMemberList();
		return memberList;
	 }
	 //로그인기록
	 public void updateLogin(String memberId) {		 
		 if(memberId != null) memberMapper.updateLogin(memberId);		 
		
	 }
	 //최근로그인기록
	 public Member getLoginCode(String memberId) {
		 
		 Member member = memberMapper.getLoginCode(memberId);
		 return member;
	 }
	 //로그인기록 조회
	 public  List<Member> getLogin(){
		 List<Member> loginList = memberMapper.getLogin();
				
			return loginList;
	 }
	 //로그아웃기록
	 public void updateLogout(String levelCode) {
		  memberMapper.updateLogout(levelCode);
		 
	 }
	 
	 //관리자가 회원수정
	 public void modifyMember(Member member) {
		 if(member != null)  memberMapper.modifyMember(member);
		 
	 }
	 //회원탈퇴시 상태변경
	 public void removeUpdateMember(Member member) {
		 if(member != null)  memberMapper.removeUpdateMember(member);
	 }
	 //회원탈퇴시 탈퇴회원등록
	 public void addCancelMember(Member member) {
		 if(member != null)  memberMapper.addCancelMember(member);
	 }
	 //관리자가 level 권한등록
	 public void addAuthority(Member member) {
		 if(member != null) memberMapper.addAuthority(member);
		
	 }
	 //권한 목록조회
	 public List<Member> getAuthority(){
		 
		 List<Member> authority = memberMapper.getAuthority();
		 return authority;
	 }
	
	  //권한 조회 
	 public Member getAuthorityByCode(String levelCode) { 
		 Member authorityList = memberMapper.getAuthorityByCode(levelCode); 
	  return authorityList; 
	  }
	 //권한수정
	 public void modifyAuthority(Member member) {
		 if(member != null) memberMapper.modifyAuthority(member);
	 }
	 //권한 삭제전 fk 확인
	 public List<Member> getCode(String levelCode) {
		 List<Member> member = memberMapper.getCode(levelCode);
		 return member;
	 }
	 //권한삭제
	 public void removeAuthority(String levelCode) {
		
		 memberMapper.removeAuthority(levelCode);
		
	 }
	 
	 //탈퇴회원 조회
	
	 public List<Member> getCancelMember(){
		 List<Member> cancelList = memberMapper.getCancelMember();
	  return cancelList;
	 }
	 //휴면|탈퇴 예정일조회
	 public List<Report> getExpectedDate(){
		 List<Report> expectedList = memberMapper.getExpectedDate();
			 return expectedList;
	 }
	 //휴면|탈퇴 기준일 조회
	 public List<Report> getBaseDate() {
		 List<Report> baseDateList = null;
		 
		 baseDateList = memberMapper.getBaseDate();
		 
		 return baseDateList;
	 }
	 //휴면|탈퇴 기준일 수정용 조회
	 public Report getBaseDate(String statusCriteriaCode) {
		 Report baseDate = null;
		 
		 baseDate = memberMapper.getBaseDate(statusCriteriaCode);
		 
		 return baseDate;
	 }
	 // 휴면|탈퇴 기준일 수정
	 public void modifyBaseDate(Report report) {
		 memberMapper.modifyBaseDate(report);
	 }
	 // 휴면|탈퇴 기준일 리스트조회
	 public List<Report> getstatusCriteriaName(){
		 List<Report> nameList = memberMapper.getstatusCriteriaName();
		 
		 return nameList;
	 }
	 // 휴면|탈퇴 기준일 수정
	 public void removeBaseDate(String statusCriteriaCode) {
		 memberMapper.removeBaseDate(statusCriteriaCode);
	 }
	 
	 // 휴면|탈퇴 등록
	 public void addBaseDate(Report report) {
		 memberMapper.addBaseDate(report);
	 }
	 
	 // 신고사유 조회
	 public List<Report> getReasonReport(){
		 List<Report> reportReasonList = memberMapper.getReasonReport();
		 return reportReasonList;
	 }
	 // 신고사유 수정을위한 조회
	 public Report getReasonReport(String reportCode){
		 Report reportReasonList = memberMapper.getReasonReport(reportCode);
		 
		 return reportReasonList;
	 }
	 // 신고사유 등록
	 public void addReasonReport(Report report) {
		 memberMapper.addReasonReport(report);	 
	 }
	 // 신고사유 수정
	 public void modifyReasonReport(Report report) {
		 memberMapper.modifyReasonReport(report);
	 }
	 // 신고사유 삭제
	 public void removeReasonReport(String report) {
		 memberMapper.removeReasonReport(report);
	 }
	 // 신고목록 조회
	 public List<Report> getReport(String memberId){
		 List<Report> reportList = memberMapper.getReport(memberId);
		 return reportList;
	 }
	 // 신고목록 삭제
	 public void removeReport(String reportHistoryCode) {
		 memberMapper.removeReport(reportHistoryCode);
	 }
	 
	 // 신고승인|반려
	 public void resultReport(String reportHistoryCode,String reportApproval) {
		 memberMapper.resultReport(reportHistoryCode,reportApproval);
	 }
	
}
