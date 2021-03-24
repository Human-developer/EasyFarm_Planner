package easyfarm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Member;

@Mapper
public interface MemberMapper {
	
	// 로그인 
	public Member getMemberInfoById(String memberId);
	
	// 회원가입
	public int addMember(Member member);
	
	// 회원조회 & 검색
	public List<Member> getMemberList(String searchKey,String searchValue);
	public List<Member> getMemberList();
	
	// 회원 count 조회
	public int countMember();
	
	// 로그인 기록
	public int updateLogin(String memberId);
	public int updateLogout(String memberId);

}
