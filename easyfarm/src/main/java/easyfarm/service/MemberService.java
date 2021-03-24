package easyfarm.service;

import java.util.List;

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
	 
	 public int addMember(Member member) {
		 int result;
		 result = memberMapper.addMember(member);
		 return result;
	 }
	 
	 public List<Member> getMemberList(String searchKey, String searchValue){
		 List<Member> memberList = null;
		/* memberMapper.countMember(); */
		 
		 if(searchValue != null & !"".equals(searchValue)) {
			 
			 memberList = memberMapper.getMemberList(searchKey, searchValue);
		 }
		 else{
			 memberList = memberMapper.getMemberList();
		 }
		 
		 return memberList;
	 }
	 public int updateLogin(String memberId) {
		 int result = memberMapper.updateLogin(memberId);
		 return result;
	 }
	 public int updateLogout(String memberId) {
		 int result = memberMapper.updateLogout(memberId);
		 return result;
	 }

}
