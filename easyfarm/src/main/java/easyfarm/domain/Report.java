package easyfarm.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class Report {

	   
	    private String reportHistoryCode; 			// 회원신고기록코드    
	    private String reportedMemberId;			// 신고 대상자 아이디	 
	    private String reportMemberId;   			// 신고자 아이디	    
	    private String reportCode;					// 신고사유코드
	    private String reportDetailReason;	    	// 신고세부내용
	    private String reportDate;	    			// 신고시간 
	    private String reportApproval;	    		// 신고승인반려 여부
	    private String reportApprovalReason;	    // 신고승인반려 사유
	    private String reportApprovalDate;  	    // 신고승인반려 일
	    private String reportReason;	    		// 신고사유 명
	    private int reportBanDays;	   				// 정지일수
	    private String reportRegDate;	    		// 신고사유 작성일
	    private String statusCriteriaCode;	   		// 휴면탈퇴코드
	    private String loginMemberId;	    		// 로그인 아이디
	    private String statusCriteriaName;	  		// 휴면탈퇴명
	    private int statusCriteriaDays;	    		// 기준일수
	    private String memberId;	  				// 작성자아이디
	    private String useStatus;	    			// 사용유무
	    private String autoRestNum;	    			// 휴면탈퇴 예정코드
	    private Date autoRestDate;	   				// 자동휴면예정일
	    private Date autoWithdrawalDate;	    	// 자동탈퇴예정일
	    private String banCurrentCode;	    		// 정지회원 관리코드
	    private String banMemberId;	    			// 정지대상자 아이디
	    private String banBeginDate;	    		// 정지시작일
	    private Date banEndDate;	    			// 최종정지종료일
	    private String regDate;	    				// 작성일
	    private String regDay;	    				// 화면 보여주기용
	    

	    
		
}
