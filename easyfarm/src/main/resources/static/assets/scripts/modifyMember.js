$(function(){

			
			$('#addCheck').click(function(){
							
				
				var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
				var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);
				var phoneNumberRegex = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
				
				
				
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
						title: "비밀번호를 형식에맞게 입력 해주세요.",
						confirmButtonColor: '#304ffe'
					});
					$('#memberPw').val("");
					$('#memberPw').focus();
					return false;
				}
				if($('#memberPw').val() == $('#memberId').val()){
					swal({
						title: "아이디 비밀번호가 같습니다.",
						confirmButtonColor: '#304ffe'
					});
					$("#password").val("");
					$('#memberPw').focus();
					return false;
				}
				if($('#memberPw').val() != $('#memberPwCheck').val()){
					swal({
						title: "비밀번호가 같지않습니다.",
						confirmButtonColor: '#304ffe'
					});
					$('#memberPwCheck').focus();
					return false;
				}
				
				if($('#memberPhone').val() == ""){
					swal({
						title: "휴대폰번호를 입력 해주세요.",
						confirmButtonColor: '#304ffe'
					});
					$('#memberPhone').focus();
					return false;
				}
				
				if(!phoneNumberRegex.test($('#memberPhone').val())){
					swal({
						title: "휴대폰번호를 형식에맞게 입력해주세요.",
						confirmButtonColor: '#304ffe'
					});
					$('#memberPhone').focus();
					return false;
				}
								
				if($('#memberAddress').val() == ""){
					swal({
						title: "주소를 입력 해주세요.",
						confirmButtonColor: '#304ffe'
					});
					$('#memberAddress').focus();
					return false;
				}
				
				return true;
			})
		});
			
		
		$('#img').click(function(){
			$('.file').click();
		})
		
		$('#profileStorage').click(function(){
			
			var formData = new FormData();
			var inputFile = $(".file");
			var files = inputFile[0].files;
			
	
			
			if(files.length > 1) {
			    alert('파일은 1개까지 첨부 가능합니다.');
			    return false;
			}
			
			for(var i = 0; i < files.length; i++) {
			    var fileSize = files[i].size;
			    var maxSize = 100000000; //100MB
			   
			    if(fileSize > maxSize){
			        alert("첨부파일 사이즈는 100MB 이내로 등록 가능합니다. ");
			        return false;
			    }
			    var fileName = files[i].name;
			    var ext = fileName.split('.').pop().toLowerCase();
			    if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
			        alert('gif,png,jpg,jpeg 파일만 업로드 할수 있습니다.');
			        $(".file").val("");
			        return;
			    }
			    formData.append('uploadFiles', files[i]);
			    //console.log( "파일 : " + files[i] );
			}		
				$.ajax({
				    type : "post",
				    url : '/member/profile',
				    data : formData,
				    contentType: false,
				    processData: false,
				    enctype: 'multipart/form-data',
				    dataType : 'json',
				    beforeSend:function(){
				       
				    },
				    success : function(data) {
				 
				    	
				       if(data != null){
				    	   
				      	 var name = "/assets/images/"+data.imgName;
				       	 $('#img').attr("src",name);
				       }
				    },
				    error : function(error) {
				        
				       
				        console.log('error : ' + error);
				    }
			});
		})
		
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
