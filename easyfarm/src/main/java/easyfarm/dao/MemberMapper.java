package easyfarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Member;

@Mapper
public interface MemberMapper {
	
	// 로그인 
	public Member getMemberInfoById(String memberId);
	
	// 회원가입
	public int addMember(Member member);
	
	// 회원조회 & 검색
	
	public List<Map<String, Object>> getMemberList(Map<String, Object> paramMap);
	
	// 회원 count 조회
	public int countMember();
	public int countSearchMember(String searchKey,String searchValue);
	
	// 로그인 기록
	public int updateLogin(String memberId);
	public int updateLogout(String memberId);
	
	//회원 수정
	public int modifyMember(Member member);
	
	//회원 탈퇴시 상태변경
	public int removeUpdateMember(Member member);
	//회원 탈퇴시 탈퇴회원등록
	public int addCancelMember(Member member);
	
	
	//권한 등록
	public int addAuthority(Member member);
	
	//권한 목록조회
	public List<Member> getAuthority();
	//권한 수정용 조회
	public Member getAuthorityByCode(String levelCode);
	//권한 수정
	public int modifyAuthority(Member member);
	//권한 삭제
	public int removeAuthority(String levelCode);
}
