		var key = "";
		var isCertification = "";
		var msg = "";
			  //매일 유효성검사
			  $(".sendMail").click(function() {// 메일 입력 유효성 검사
					var mail = $("#memberEmail").val(); //사용자의 이메일 입력값. 
					
					if (mail == "") {
						alert("메일 주소가 입력되지 않았습니다.");
					} else {
					
						$.ajax({
							type : 'post',
							url : '/checkMail',
							data : {mail:mail},
							dataType :'json',
							success: function(data){
														
								key = data.mailAuthKey;
								msg = data.msg
								console.log(key);
								if(key != null){								
									alert("인증번호가 전송되었습니다.");
									$("#certified").attr('style','display: block');
									$("#certified2").attr('style','display: block');
									$(".compare").attr('style','display: block');
								}else{
									alert(msg);
								}
							}
		
						});
						
						isCertification=false; //추후 인증 여부를 알기위한 값
						
					}
					
					
				});
			  
			 
			  //인증 여부확인
			  $(".compare").on("click", function() {
				 
				
				 if($("#certified").val() != ""){
					 					
					if ($("#certified").val() == key) {   //인증 키 값을 비교를 위해 텍스트인풋과 벨류를 비교
						$(".compare-text").text("인증 성공!").css("color", "black");
						isCertification = true;  //인증 성공여부 check
					}else{
						$(".compare-text").text("불일치!").css("color", "red");
						isCertification = false; //인증 실패
					}
				 }else{
					 $(".compare-text").text("불일치!").css("color", "red");
						isCertification = false; //인증 실패
				 }
				
				});
			 	

				//아이디 중복검사
				$('#checkBtn').click(function(){
					
					var memberId = $('#memberId').val();
					
					if(memberId != ""){
						
						$.ajax({
							url: "/ajax/idCheck",
							method: "POST",
							data: { memberId : memberId },
							success : function(data) {
								if(data == "사용가능") {
	
									swal({
										title: "사용 가능한 아이디 입니다.",
										confirmButtonColor: '#304ffe'
									});
									$('#checkBtn').attr('value','yes');
								}else {
									 swal({
											title: "사용 불가능한 아이디 입니다.",
											confirmButtonColor: '#304ffe'
										});
																		//중복된 아이디면 중복체크가 다시 필요하기 때문에 check의 값을 no로 다시 지정
									$('#checkBtn').attr('value','no');
										return;
								}							
							},
							error : function(xhr, status, error) {
								console.log('xhr : ' + xhr);
								console.log('status : ' + status);
								console.log('error : ' + error);
							}
							
						});
					}else{
						
						swal({
							title: "아이디를 입력해주세요.",
							confirmButtonColor: '#304ffe'
						});
						$('#memberId').focus();
						return false; 
					}
				}); 
			

		
			//중복검사후 아이디변경시 중복체크 초기화
			$('#memberId').keyup(function(){
				$('#checkBtn').attr('value','no');
			});


			// 유효성 검사
			$('#addCheck').click(function(){
											
				var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
				var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);
				var getName= RegExp(/^[가-힣]+$/);
				var phoneNumberRegex = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
				if(!getCheck.test($('#memberId').val())){
					 swal({
							title: "아이디를 형식에 맞게 써주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberId').val("");
					$('#memberId').focus();
					return false;
				}
				if($('#checkBtn').val() == "no"){
					 swal({
							title: "아이디 중복검사를 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					return false;
				}
				
				if($('#memberPw').val() == ""){
					 swal({
							title: "비밀번호를 입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberPw').focus();
					
					return false;
				}
				if(!getCheck.test($('#memberPw').val())){
					
					 swal({
							title: "비밀번호를 형식에 맞게 입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberPw').val("");
					$('#memberPw').focus();
					return false;
				}
				if($('#memberPw').val() == $('#memberId').val()){
					 swal({
							title: "아이디와 비밀번호가 같습니다.",
							confirmButtonColor: '#304ffe'
						});
					$("#password").val("");
					$('#memberPw').focus();
					return false;
				}
				if($('#memberPw').val() != $('#memberPwCheck').val()){
					 swal({
							title: "비밀번호가 다릅니다.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberPwCheck').focus();
					return false;
				}
				if($('#memberName').val() == ""){
					 swal({
							title: "이름을 입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberName').focus();
					return false;
				}
				if(!getName.test($('#memberName').val())){
					 swal({
							title: "이름을 형식에 맞게 입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					
					$('#memberName').val("");
					$('#memberName').focus();
					return false;
				}
				if($('#memberPhone').val() == ""){
					 swal({
							title: "휴대폰번호를입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberPhone').focus();
					return false;
				}
				if(!phoneNumberRegex.test($('#memberPhone').val())){
					 swal({
							title: "휴대폰번호를 형식에 맞게 입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberPhone').focus();
					return false;
				}
				if($('#memberIdenNum').val() == "" || $('#memberIdenNum2').val() == ""){
					 swal({
							title: "주민번호를 입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberIdenNum').focus();
					return false;
				}
				
				if(check_jumin() == false){
					return false;
				}
				
				if($('#memberEmail').val() == ""){
					 swal({
							title: "이메일을 입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberEmail').focus();
					return false;
				}
				
				if(!isCertification){			
					 swal({
							title: "메일 인증이 완료되지않았습니다.",
							confirmButtonColor: '#304ffe'
						});
					return false;
				}
				
				if(!getMail.test($('#memberEmail').val())){
					 swal({
							title: "이메일을 형식에 맞게 입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberEmail').val("");
					$('#memberEmail').focus();
					return false;
				}
				if($('#memberAddress').val() == ""){
					 swal({
							title: "주소를 입력 해주세요.",
							confirmButtonColor: '#304ffe'
						});
					$('#memberAddress').focus();
					$(".addressBtn").click();
					
					return false;
				}
				
				return true;
			})
			
			$("#memberEmail").on("keyup",function(){
				isCertification = false;
				key ="";
				$("#certified").attr('style','display: none');
				$("#certified2").attr('style','display: none');
				$(".compare").attr('style','display: none');
			});
			
			

		//주민등록번호 유효성검사 
		function check_jumin()
		{
			var jumins3 = $("#memberIdenNum").val() + $("#memberIdenNum2").val();
			//주민등록번호 생년월일 전달 
			
			var fmt = RegExp(/^\d{6}[1234]\d{6}$/) 			
			//포멧 설정
			
			var buf = new Array(13);
			//주민번호 유효성 검사 
			
			if (!fmt.test(jumins3)) {
				 swal({
						title: "주민번호를 형식에 맞게 입력 해주세요.",
						confirmButtonColor: '#304ffe'
					});
				$("#memberIdenNum").focus();
				return false;
			}
			//주민번호 존재 검사
				
			for (var i = 0; i < buf.length; i++){
				buf[i] = parseInt(jumins3.charAt(i)); //int형으로 변환
			}
				
				var multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
				// 밑에 더해주는 12자리 숫자들 
				var sum = 0;
				
			for (var i = 0; i < 12; i++){
				sum += (buf[i] *= multipliers[i]);
				// 배열끼리12번 돌면서 
			} 
			
			if ((11 - (sum % 11)) % 10 != buf[12]) {
				 swal({
						title: "잘못된 주민등록번호 입니다.",
						confirmButtonColor: '#304ffe'
				 });
				$("#memberIdenNum").focus();
				return false;
			} 
			return true;
		}

		
		
		
		
	    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	    function sample4_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	 
	                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 도로명 조합형 주소 변수
	 
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
	                if(fullRoadAddr !== ''){
	                    fullRoadAddr += extraRoadAddr;
	                }
	 
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	               
	                document.getElementById('memberAddress').value = fullRoadAddr;
	               
	 
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if(data.autoRoadAddress) {
	                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	 
	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	 
	                } else {
	                    document.getElementById('guide').innerHTML = '';
	                }
	            }
	        }).open();
	    }
