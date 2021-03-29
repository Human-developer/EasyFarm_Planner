package easyfarm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyfarm.dao.MemberMapper;
import easyfarm.domain.Member;

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
	 
	 public Map<String, Object> getMemberList(String searchKey, String searchValue, int currentPage){
		// 페이지에 보여줄 행의 갯수
		int rowPerPage = 5;
		
		// table 행의 시작점
		int startNum = 0;
		
		// 마지막 페이지
		int lastPage = 0;
		
		// 페이지 시작
		int startPageNum = 1;
		
		// 페이지 끝
		int endPageNum = 10;	
		
		// 페이지 알고리즘 -> (현재페이지-1) * 페이지에 보여줄 행의 갯수 => Limit ? , 5 (?부분을 구하는 과정)
		startNum = (currentPage - 1) * rowPerPage;
		if(startNum < 0) {
			startNum = 0;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startNum", startNum);
		paramMap.put("rowPerPage", rowPerPage);
		
		List<Map<String, Object>> memberList = null;
				
		
		 double countMember = memberMapper.countMember();
		 double countSearchMember = memberMapper.countSearchMember(searchKey, searchValue);
		 
		 System.out.println(countMember +"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		 if(searchValue != null & !"".equals(searchValue)) {
			 paramMap.put("searchKey", searchKey);
			 paramMap.put("searchValue", searchValue);
			 
			 memberList = memberMapper.getMemberList(paramMap);
			 lastPage = (int) Math.ceil(countSearchMember/rowPerPage);
		 }
		 else{
			 memberList = memberMapper.getMemberList(paramMap);
			 lastPage = (int) Math.ceil(countMember/rowPerPage);
		 }
		 
		 // 마지막 페이지
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("memberList", memberList);
		resultMap.put("lastPage", lastPage);
		
		if(lastPage <= endPageNum) {
			endPageNum = lastPage;
		}
		
		// 7페이지인 경우 동적 페이지 번호 구성
		if(currentPage > 6) {
			startPageNum	= currentPage - 5;
			endPageNum		= currentPage + 4;
			
						
		  if(endPageNum <= lastPage){ 
			  startPageNum = lastPage - 9;
			  endPageNum = lastPage; 
			  
			  }
			 			 
		}
				
		
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("endPageNum", endPageNum);
			
		return resultMap;
	 }
	 //로그인기록
	 public void updateLogin(String memberId) {		 
		 if(memberId != null) memberMapper.updateLogin(memberId);		 
		
	 }
	 //로그아웃기록
	 public void updateLogout(String memberId) {
		 if(memberId != null)  memberMapper.updateLogout(memberId);
		 
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
	 //권한삭제
	 public int removeAuthority(String levelCode) {
		 int a = 0;
		 if(levelCode != null)  a = memberMapper.removeAuthority(levelCode);
		 System.out.println(a +"===1111111111111111111111111111111");
		 return a;
	 }
	 
	
	 

}
