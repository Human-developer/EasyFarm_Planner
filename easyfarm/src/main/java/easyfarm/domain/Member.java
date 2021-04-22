package easyfarm.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Member {

	private String memberId;			//회원 아이디
	private String memberPw;			//회원 비밀번호
	private String memberName;			//회원 이름
	private String levelCode;			//회원 등급
	private String memberGender;		//회원 성별 
	private String memberAddress;		//회원 주소
	private String memberEmail;			//회원 이메일
	private String memberPhone;			//회원 휴대폰번호
	private String memberIdenNum;		//회원 주민등록번호
	private String memberIdenNum2;		//회원 주민등록번호
	private String memberRegDate;		//회원 등록 일자
	private String memberStatus;		//회원 상태
	private String levelName;			//권한 이름
	private String useStatus;			//권한 사용 상태
	private String cancelMemberCode;	//탈퇴코드
	private String cancelMemberReason;	//탈퇴사유
	private String cancelDate;			//탈퇴날짜
	private String loginMemberId;		//로그인아이디
	private String loginCode;			//로그인코드
	private Date loginDate;				//로그인 날짜
	private Date logoutDate;			//로그아웃 날짜
	private String regDate;				// 날짜값을 셋팅
	private String regDay;				//날짜값을 셋팅
	private String memberImg;			//프로필 사진경로

	
	
}
