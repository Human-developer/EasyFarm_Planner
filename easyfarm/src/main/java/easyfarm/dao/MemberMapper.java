package easyfarm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Member;
import easyfarm.domain.Report;

@Mapper
public interface MemberMapper {
	
	// 로그인 
	public Member getMemberInfoById(String memberId);
		
	// 로그인 기록
	public int updateLogin(String memberId);
	public int updateLogout(String levelCode);
	
	// 최근로그인조회
	public Member getLoginCode(String memberId);
	
	// 로그인 기록 전체 조회 & 검색
	public List<Member> getLogin();
	
	// 회원가입
	public int addMember(Member member);
	
	// 회원조회 & 검색
	
	public List<Member> getMemberList();
	
	
	
	
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
	//권한 fk 확인
	public List<Member> getCode(String levelCode);
	//권한 삭제
	public int removeAuthority(String levelCode);
	
	//탈퇴회원 조회
	public List<Member> getCancelMember();
	
	//휴면|탈퇴 예정일 조회
	public List<Report> getExpectedDate();
	//휴묜|탈퇴 기준일 조회
	public List<Report> getBaseDate();
	//휴묜|탈퇴 기준이름 리스트
	public List<Report> getstatusCriteriaName();
	//휴묜|탈퇴 기준일 등록
	public void addBaseDate(Report report);
	//휴묜|탈퇴 기준일 수정을위한 데이터가져오기
	public Report getBaseDate(String statusCriteriaCode);
	//휴묜|탈퇴 기준일 수정
	public int modifyBaseDate(Report report);
	//휴묜|탈퇴 기준일 삭제
	public void removeBaseDate(String statusCriteriaCode);
	
	//신고사유 조회
	public List<Report> getReasonReport();
	//신고사유 수정을위한 조회
	public Report getReasonReport(String reportCode);
	//신고사유 등록
	public void addReasonReport(Report report);
	//신고사유 수정
	public void modifyReasonReport(Report report);
	//신고사유 삭제
	public void removeReasonReport(String report);
	//신고 목록 조회
	public List<Report> getReport(String memberId);
	//신고 삭제
	public void removeReport(String reportHistoryCode);
	//신고 승인|반려
	public void resultReport(String reportHistoryCode, String reportApproval);
}
