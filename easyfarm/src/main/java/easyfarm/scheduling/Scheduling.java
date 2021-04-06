package easyfarm.scheduling;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import easyfarm.domain.Member;
import easyfarm.domain.Report;
import easyfarm.service.MemberService;

@Component
public class Scheduling {
	
	
	
	@Autowired
	MemberService memberService;
	
	
		// 매일 00시에 자동실행
		@Scheduled(cron="0 0 00 * * ?")
		public void scheduler(){
			
		   
			Date today = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			 
			List<Report> reportMember = memberService.getSuspend();
			 
			//정지해제
			for(Report report : reportMember) {
					 
			    Date banEndDate = report.getBanEndDate();
			    String banCode = report.getBanCurrentCode();
			    String banId = report.getBanMemberId();

			    String toDay= simpleDate.format(today);
			    String banEndDay = simpleDate.format(banEndDate);
			    
			    int compare = toDay.compareTo(banEndDay);
			    	
			    if(compare > 0) {
			      memberService.removeBan(banCode,banId);
			    }
			}
			    
		     
		   
			int restDate = memberService.getStatusDays("휴면");
			int withdrawalDate = memberService.getStatusDays("탈퇴");
			List<Member> loginMaxDateList = memberService.getLoginMaxDate();
			List<Report> expectedList = memberService.getExpectedDate();
			List<String> idList = new ArrayList<String>();
			   
			for(Report expected : expectedList ) {
				   
				idList.add(expected.getLoginMemberId());
			}
			   
		  
		   
			for(Member login : loginMaxDateList) {
			   
			   Date logoutDate = login.getLogoutDate();
			   String loginId = login.getLoginMemberId();
			   
			   cal.setTime(logoutDate);
			   cal.add(cal.DATE, restDate); // 셋팅된 날에 기준일을 더해준다
			   Date restDay = cal.getTime();
			   String autoRestDate = format.format(restDay);//자동휴면일
			   
			   cal.setTime(logoutDate);
			   cal.add(cal.DATE, withdrawalDate); // 셋팅된 날에 기준일을 더해준다 
			   Date withdrawalDay = cal.getTime();
			   String autoWithdrawalDate = format.format(withdrawalDay); //자동탈퇴일
			   
			   //자동휴면|탈퇴 예정일조회 테이블에 아이디없을시 등록
			   if(!idList.contains(loginId)) {
				   memberService.addStatusSchedule(loginId,autoRestDate,autoWithdrawalDate);
				  
			  //아이디가 있을시 예정일 업데이트 
			   }else if(idList.contains(loginId)) {
					  
				   memberService.updateStatusSchedule(loginId,autoRestDate,autoWithdrawalDate);
			   }
				 
			  
			}
		   
			for(Report expected : expectedList) {
			   Date RestDay = expected.getAutoRestDate(); //자동휴면일
			   Date WithdrawalDay = expected.getAutoWithdrawalDate(); //자동탈퇴일
			   String memberId = expected.getLoginMemberId(); //로그인아이디
			   
			   int compare = today.compareTo(RestDay);
			   int Comparison = today.compareTo(WithdrawalDay);
			   
			   if(compare > 0) {
				   Member member = memberService.getMemberInfoById(memberId);
				   
				   String useStatus = member.getUseStatus();
				   //상태 휴면으로 변경
				   memberService.removeUpdateMember(memberId,useStatus);
			   }
			   
			   if(Comparison > 0) {
				
				   Member member = memberService.getMemberInfoById(memberId);
				   member.setUseStatus("탈퇴");
				   //회원목록에서는 상태탈퇴로 변경
				   memberService.removeUpdateMember(member);
				   //탈퇴회원등록
				   member.setCancelMemberReason("자동탈퇴");
				   memberService.addCancelMember(member);
			   }
			   
			}
		   		   
		 }
}
