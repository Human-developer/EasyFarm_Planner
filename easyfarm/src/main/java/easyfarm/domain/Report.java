package easyfarm.domain;

public class Report {

	    // 회원신고기록코드
	    private String reportHistoryCode;

	    // 신고 대상자 아이디
	    private String reportedMemberId;

	    // 신고자 아이디
	    private String reportMemberId;

	    // 신고사유코드
	    private String reportCode;

	    // 신고세부내용
	    private String reportDetailReason;

	    // 신고시간
	    private String reportDate;

	    // 신고승인반려 여부
	    private String reportApproval;

	    // 신고승인반려 사유
	    private String reportApprovalReason;

	    // 신고승인반려 일
	    private String reportApprovalDate;  

	    // 신고사유 명
	    private String reportReason;

	    // 정지일수
	    private String reportBanDays;

	    // 신고사유 작성일
	    private String reportRegDate;

	    //휴면
	    // 휴면탈퇴코드
	    private String statusCriteriaCode;
	    // 로그인 아이디
	    private String loginMemberId;
	    // 휴면탈퇴명
	    private String statusCriteriaName;

	    // 기준일수
	    private String statusCriteriaDays;

	    // 작성자아이디
	    private String memberId;

	    // 작성일
	    private String regDate;

	    // 사용유무
	    private String useStatus;

	    // 휴면탈퇴 예정코드
	    private String autoRestNum;

	    // 자동휴면예정일
	    private String autoRestDate;

	    // 자동탈퇴예정일
	    private String autoWithdrawalDate;
	    
	    // 정지회원 관리코드
	    private String banCurrentCode;

	    // 정지대상자 아이디
	    private String banMemberId;

	    // 정지시작일
	    private String banBeginDate;

	    // 최종정지종료일
	    private String banEndDate;

	    
	    
		public String getLoginMemberId() {
			return loginMemberId;
		}

		public void setLoginMemberId(String loginMemberId) {
			this.loginMemberId = loginMemberId;
		}

		public String getReportHistoryCode() {
			return reportHistoryCode;
		}

		public void setReportHistoryCode(String reportHistoryCode) {
			this.reportHistoryCode = reportHistoryCode;
		}

		public String getReportedMemberId() {
			return reportedMemberId;
		}

		public void setReportedMemberId(String reportedMemberId) {
			this.reportedMemberId = reportedMemberId;
		}

		public String getReportMemberId() {
			return reportMemberId;
		}

		public void setReportMemberId(String reportMemberId) {
			this.reportMemberId = reportMemberId;
		}

		public String getReportCode() {
			return reportCode;
		}

		public void setReportCode(String reportCode) {
			this.reportCode = reportCode;
		}

		public String getReportDetailReason() {
			return reportDetailReason;
		}

		public void setReportDetailReason(String reportDetailReason) {
			this.reportDetailReason = reportDetailReason;
		}

		public String getReportDate() {
			return reportDate;
		}

		public void setReportDate(String reportDate) {
			this.reportDate = reportDate;
		}

		public String getReportApproval() {
			return reportApproval;
		}

		public void setReportApproval(String reportApproval) {
			this.reportApproval = reportApproval;
		}

		public String getReportApprovalReason() {
			return reportApprovalReason;
		}

		public void setReportApprovalReason(String reportApprovalReason) {
			this.reportApprovalReason = reportApprovalReason;
		}

		public String getReportApprovalDate() {
			return reportApprovalDate;
		}

		public void setReportApprovalDate(String reportApprovalDate) {
			this.reportApprovalDate = reportApprovalDate;
		}

		public String getReportReason() {
			return reportReason;
		}

		public void setReportReason(String reportReason) {
			this.reportReason = reportReason;
		}

		public String getReportBanDays() {
			return reportBanDays;
		}

		public void setReportBanDays(String reportBanDays) {
			this.reportBanDays = reportBanDays;
		}

		public String getReportRegDate() {
			return reportRegDate;
		}

		public void setReportRegDate(String reportRegDate) {
			this.reportRegDate = reportRegDate;
		}

		public String getStatusCriteriaCode() {
			return statusCriteriaCode;
		}

		public void setStatusCriteriaCode(String statusCriteriaCode) {
			this.statusCriteriaCode = statusCriteriaCode;
		}

		public String getStatusCriteriaName() {
			return statusCriteriaName;
		}

		public void setStatusCriteriaName(String statusCriteriaName) {
			this.statusCriteriaName = statusCriteriaName;
		}

		public String getStatusCriteriaDays() {
			return statusCriteriaDays;
		}

		public void setStatusCriteriaDays(String statusCriteriaDays) {
			this.statusCriteriaDays = statusCriteriaDays;
		}

		public String getMemberId() {
			return memberId;
		}

		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}

		public String getRegDate() {
			return regDate;
		}

		public void setRegDate(String regDate) {
			this.regDate = regDate;
		}

		public String getUseStatus() {
			return useStatus;
		}

		public void setUseStatus(String useStatus) {
			this.useStatus = useStatus;
		}

		public String getAutoRestNum() {
			return autoRestNum;
		}

		public void setAutoRestNum(String autoRestNum) {
			this.autoRestNum = autoRestNum;
		}

		public String getAutoRestDate() {
			return autoRestDate;
		}

		public void setAutoRestDate(String autoRestDate) {
			this.autoRestDate = autoRestDate;
		}

		public String getAutoWithdrawalDate() {
			return autoWithdrawalDate;
		}

		public void setAutoWithdrawalDate(String autoWithdrawalDate) {
			this.autoWithdrawalDate = autoWithdrawalDate;
		}

		public String getBanCurrentCode() {
			return banCurrentCode;
		}

		public void setBanCurrentCode(String banCurrentCode) {
			this.banCurrentCode = banCurrentCode;
		}

		public String getBanMemberId() {
			return banMemberId;
		}

		public void setBanMemberId(String banMemberId) {
			this.banMemberId = banMemberId;
		}

		public String getBanBeginDate() {
			return banBeginDate;
		}

		public void setBanBeginDate(String banBeginDate) {
			this.banBeginDate = banBeginDate;
		}

		public String getBanEndDate() {
			return banEndDate;
		}

		public void setBanEndDate(String banEndDate) {
			this.banEndDate = banEndDate;
		}
		

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Report [reportHistoryCode=");
			builder.append(reportHistoryCode);
			builder.append(", reportedMemberId=");
			builder.append(reportedMemberId);
			builder.append(", reportMemberId=");
			builder.append(reportMemberId);
			builder.append(", reportCode=");
			builder.append(reportCode);
			builder.append(", reportDetailReason=");
			builder.append(reportDetailReason);
			builder.append(", reportDate=");
			builder.append(reportDate);
			builder.append(", reportApproval=");
			builder.append(reportApproval);
			builder.append(", reportApprovalReason=");
			builder.append(reportApprovalReason);
			builder.append(", reportApprovalDate=");
			builder.append(reportApprovalDate);
			builder.append(", reportReason=");
			builder.append(reportReason);
			builder.append(", reportBanDays=");
			builder.append(reportBanDays);
			builder.append(", reportRegDate=");
			builder.append(reportRegDate);
			builder.append(", statusCriteriaCode=");
			builder.append(statusCriteriaCode);
			builder.append(", statusCriteriaName=");
			builder.append(statusCriteriaName);
			builder.append(", statusCriteriaDays=");
			builder.append(statusCriteriaDays);
			builder.append(", memberId=");
			builder.append(memberId);
			builder.append(", regDate=");
			builder.append(regDate);
			builder.append(", useStatus=");
			builder.append(useStatus);
			builder.append(", autoRestNum=");
			builder.append(autoRestNum);
			builder.append(", autoRestDate=");
			builder.append(autoRestDate);
			builder.append(", autoWithdrawalDate=");
			builder.append(autoWithdrawalDate);
			builder.append(", banCurrentCode=");
			builder.append(banCurrentCode);
			builder.append(", banMemberId=");
			builder.append(banMemberId);
			builder.append(", banBeginDate=");
			builder.append(banBeginDate);
			builder.append(", banEndDate=");
			builder.append(banEndDate);
			builder.append(", getReportHistoryCode()=");
			builder.append(getReportHistoryCode());
			builder.append(", getReportedMemberId()=");
			builder.append(getReportedMemberId());
			builder.append(", getReportMemberId()=");
			builder.append(getReportMemberId());
			builder.append(", getReportCode()=");
			builder.append(getReportCode());
			builder.append(", getReportDetailReason()=");
			builder.append(getReportDetailReason());
			builder.append(", getReportDate()=");
			builder.append(getReportDate());
			builder.append(", getReportApproval()=");
			builder.append(getReportApproval());
			builder.append(", getReportApprovalReason()=");
			builder.append(getReportApprovalReason());
			builder.append(", getReportApprovalDate()=");
			builder.append(getReportApprovalDate());
			builder.append(", getReportReason()=");
			builder.append(getReportReason());
			builder.append(", getReportBanDays()=");
			builder.append(getReportBanDays());
			builder.append(", getReportRegDate()=");
			builder.append(getReportRegDate());
			builder.append(", getStatusCriteriaCode()=");
			builder.append(getStatusCriteriaCode());
			builder.append(", getStatusCriteriaName()=");
			builder.append(getStatusCriteriaName());
			builder.append(", getStatusCriteriaDays()=");
			builder.append(getStatusCriteriaDays());
			builder.append(", getMemberId()=");
			builder.append(getMemberId());
			builder.append(", getRegDate()=");
			builder.append(getRegDate());
			builder.append(", getUseStatus()=");
			builder.append(getUseStatus());
			builder.append(", getAutoRestNum()=");
			builder.append(getAutoRestNum());
			builder.append(", getAutoRestDate()=");
			builder.append(getAutoRestDate());
			builder.append(", getAutoWithdrawalDate()=");
			builder.append(getAutoWithdrawalDate());
			builder.append(", getBanCurrentCode()=");
			builder.append(getBanCurrentCode());
			builder.append(", getBanMemberId()=");
			builder.append(getBanMemberId());
			builder.append(", getBanBeginDate()=");
			builder.append(getBanBeginDate());
			builder.append(", getBanEndDate()=");
			builder.append(getBanEndDate());
			builder.append(", getClass()=");
			builder.append(getClass());
			builder.append(", hashCode()=");
			builder.append(hashCode());
			builder.append(", toString()=");
			builder.append(super.toString());
			builder.append("]");
			return builder.toString();
		}
		
		
	    
	    
		
}
