package easyfarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.FreeBoard;
import easyfarm.domain.Member;
import easyfarm.domain.Report;

@Mapper
public interface MemberMapper {
	
	// 개인회원조회&로그인아이디 검사
	public Member getMemberInfoById(String memberId);
		
	// 로그인 기록
	public int updateLogin(String memberId);
	public int updateLogout(String levelCode);
	
	// 최근로그인조회
	public Member getLoginCode(String memberId);
	
	// 로그인 기록 전체 조회 & 검색
	public List<Member> getLogin();
	// 최근 로그인 기록 조회
	public List<Member> getLoginMaxDate();
	// 로그인기록삭제
	public void removeLoginDate(String loginCode);
	
	// 회원가입
	public int addMember(Member member);
	// 아이디 찾기|비밀번호찾기
	public Member getMemberInfoByEmail(String email);
	// 회원조회 & 검색
	
	public List<Member> getMemberList();
	
	
	
	
	//회원 수정
	public int modifyMember(Member member);
	//회원 이메일 수정
	public void modifyEmail(Member member);
	//회원 탈퇴시 상태변경
	public int removeUpdateMember(Member member);
	public int removeUpdateMember(String useStatus ,String memberId);
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
	//휴면|탈퇴 예정일 등록
	public void addStatusSchedule(String loginId,String autoRestDate,String autoWithdrawalDate);
	//휴면|탈퇴 예정일 업데이트
	public void updateStatusSchedule(String loginId,String autoRestDate,String autoWithdrawalDate);
	
	//휴면|탈퇴 기준일 조회
	public List<Report> getBaseDate();
	//휴면|탈퇴 기준이름 리스트
	public List<Report> getstatusCriteriaName(String statusName);
	public List<Report> getstatusCriteriaName();
	//휴면|탈퇴 기준일 등록
	public int addBaseDate(Report report);
	//휴면|탈퇴 기준일 수정을위한 데이터가져오기
	public Report getBaseDate(String statusCriteriaCode);
	//휴면|탈퇴 기준일 수정
	public int modifyBaseDate(Report report);
	//상태 N으로
	public void modifyBaseDateStatus(String statusCode,String statusName);
	//상태 Y로
	public void cancelBaseDateStatus(String statusCode,String statusName);
	//가장최근에 생성한 기준일 조회
	public Report getStatus(String statusName);
	//기준일 조회
	public Report getStatusDays(String statusName);
	//휴면|탈퇴 기준일 삭제
	public int removeBaseDate(String statusCriteriaCode);
	
	//신고사유 조회
	public List<Report> getReasonReport();
	//신고사유 수정을위한 조회
	public Report getReasonReport(String reportCode);
	//신고사유 등록
	public int addReasonReport(Report report);
	//신고사유 수정
	public int modifyReasonReport(Report report);
	//신고사유 삭제
	public int removeReasonReport(String report);
	//신고
	public int addReport(Report report);
	//신고수정용 조회
	public Report getModifyReport(String reportHistoryCode);
	//신고수정
	public void modifyReport(Report report);
	//신고 목록 조회
	public List<Report> getReport(String memberId);
	//신고신청 목록 삭제(회원);
	public int removeReport(String reportHistoryCode);
	//신고신청 목록 삭제(관리자);
	public int removeReportHistory(String reportHistoryCode);
	//신고 승인|반려
	public int resultReport(String reportHistoryCode, String reportApproval,String reportApprovalReason);
	
	//정지회원조회
	public List<Report> getSuspend();
	public Report getSuspend(String reportedId);
	//정지등록
	public void addBanCurrentSituation(String reportedId,String reportCode ,String banEndDate);
	public void updateMember(String reportedId);
	//정지해제
	public void removeBan(String banCode);
	public void updateMemberCancel(String banId);

	//자유게시판 조회
	public List<FreeBoard> getFreeBoard(String boardId);
	//자유게시판 등록
	public void addFreeBoard(FreeBoard board);
	//개인 게시판 조회
	public FreeBoard getBoard(int boardNum);
	//개인 게시판 댓글리스트
	public List<FreeBoard> getCommentList(int boardNum);
	//게시판 댓글등록
	public void addComment(String comment,int boardNum,String memberId);
	//게시판 댓글삭제
	public void removeComment(int commentsNum);
	//게시판 조회수증가
	public void updateBoardGetNum(int boardNum,int boardGetNum);
	//게시판 수정
	public void modifyFreeBoard(FreeBoard board);
	//게시판 삭제
	public void removeFreeBoard(int boardNum);
	
	//프로필 사진변경
	public void modifyProfile(String memberId,String pathName);
}
