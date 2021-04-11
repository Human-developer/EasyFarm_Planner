
$(function(){
	
	$('#workPhaseCateDate-range, #workPhaseDate-range').datepicker({
		language: 'kr',
        autoClose: true,
	});
	
	$('#workforcePayDay').TouchSpin({
		min: 0,
		max: 1000000000,
		step: 1000,
		stepinterval: 50,
		maxboostedstep: 10000000,
		firstclickvalueifempty: 1000,
		postfix: '원'
	});
	
	$('#gainQuantity').TouchSpin({
		min: 0,
		max: 1000000000,
		step: 1,
		stepinterval: 50,
		firstclickvalueifempty: 1,
		maxboostedstep: 10000000,
	});

	$('#workforceCount').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		firstclickvalueifempty: 1,
		maxboostedstep: 10000000,
		postfix: '명'
	});
	
	$('#workforceHowlong').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		firstclickvalueifempty: 1,
		maxboostedstep: 10000000,
		postfix: '일'
	});
	
	$('#leaseSwitch, #useSwitch').prop('checked', false);
	
	$('#leaseSwitch, #useSwitch').on('click', function(){
		if($('#leaseSwitch').prop('checked')){
			$('#machineClientCode, #commonMachineCode, #machineLeaseHowlong, #machineLeasePayDay, #farmRetainMachineCode').removeAttr('readonly');
			$('#machineClientCode option, #commonMachineCode option, #farmRetainMachineCode option').removeAttr('disabled');
			
			$('#machineLeaseHowlong').TouchSpin({
				min: 0,
				max: 1000000000,
				stepinterval: 50,
				maxboostedstep: 10000000,
				firstclickvalueifempty: 1,
				postfix: '일'
			});
			$('#machineLeasePayDay').TouchSpin({
				min: 0,
				max: 1000000000,
				firstclickvalueifempty: 1000,
				step: 1000,
				stepinterval: 50,
				maxboostedstep: 10000000,
				postfix: '원'
			});
			
		}else{
			$('#machineClientCode, #commonMachineCode, #machineLeaseHowlong, #machineLeasePayDay, #farmRetainMachineCode').attr('readonly', 'readonly');
			$('#machineClientCode option, #commonMachineCode option, #farmRetainMachineCode option').attr('disabled', 'disabled');
			
			$("#machineLeaseHowlong").trigger('touchspin.destroy');
			$("#machineLeasePayDay").trigger('touchspin.destroy');
			$('#machineLeaseHowlong, #machineLeasePayDay, #machineLeasePayTotal').val('');
			
		}
		
		if($('#useSwitch').prop('checked')){
			$('#farmRetainMachineCode').removeAttr('readonly');
			$('#farmRetainMachineCode option').removeAttr('disabled');
		}else{
			$('#farmRetainMachineCode').attr('readonly', 'readonly');
			$('#farmRetainMachineCode option').attr('disabled', 'disabled');
		}
	});
	
	$('#workforceCount, #workforceHowlong, #workforcePayDay').on('propertychange change keyup', function(){
		var workforceCountValue = $('#workforceCount').val();
		var workforceHowlongValue = $('#workforceHowlong').val();
		var workforcePayDayValue = $('#workforcePayDay').val();
		var totalSum = (workforceCountValue * workforceHowlongValue * workforcePayDayValue);
		$('#workforcePayTotal').val(totalSum);
	});

	$('#machineLeaseHowlong, #machineLeasePayDay').on('propertychange change keyup', function(){
		var machineLeaseHowlongValue = $('#machineLeaseHowlong').val();
		var machineLeasePayDayValue = $('#machineLeasePayDay').val();
		var totalSum = (machineLeaseHowlongValue * machineLeasePayDayValue);
		$('#machineLeasePayTotal').val(totalSum);
	});
	
	/*농자재탭*/
	$('#stockItemUseQuantity').TouchSpin({
		min: 0,
		max: 1000000000,
		maxboostedstep: 10000000,
		buttondown_class: 'hidden',
		buttonup_class: 'hidden',
		postfix: '단위'
	});
	
	$('#stockItemUseQuantityTotal').TouchSpin({
		min: 0,
		max: 1000000000,
		maxboostedstep: 10000000,
		buttondown_class: 'hidden',
		buttonup_class: 'hidden',
		postfix: '단위'
	});
	
	$('#stockItemUseQuantityConversionPay').TouchSpin({
		min: 0,
		max: 1000000000,
		maxboostedstep: 10000000,
		decimals: 0,
		buttondown_class: 'hidden',
		buttonup_class: 'hidden',
		postfix: '원'
	});
	
	$('#resourceRetainQuantity').TouchSpin({
		min: 0,
		max: 1000000000,
		maxboostedstep: 10000000,
		buttondown_class: 'hidden',
	    buttonup_class: 'hidden',
	    postfix: '단위'
	});
	
	$('#resourceRetainQuantityCapacityExtra').TouchSpin({
		min: 0,
		max: 1000000000,
		maxboostedstep: 10000000,
		buttondown_class: 'hidden',
	    buttonup_class: 'hidden',
	    postfix: '단위'
	});

	$('#resourceStockItemCode').change(function(){
		var resourceStockItemCode = $(this).val();
		
		$.ajax({
			url: "/plan/ajax/getStockItemInfo",
			method: "POST",
			data: { resourceStockItemCode : resourceStockItemCode },
			success : function(data) {
				if(data.stockItemCode != null){
					
					var dataQuantity 					 = data.stockItemQuantity; // 품목수량
					var dataQuantityCapacity 			 = data.stockItemQuantityCapacity; // 품목용량 
					var dataQuantityUnit 				 = data.stockItemQuantityUnit; // 수량단위 
					var dataQuantityCapacityUnit		 = data.stockItemQuantityCapacityUnit; // 용량단위
					var dataStockItemIncomePerPay 		 = data.stockItemIncomePerPay; // 입고단가 
					var dataStockItemIncomeQuantityTotal = data.stockItemIncomeQuantityTotal; // 입고용량소계 
					var dataIncomeQuantityPerPay 		 = (dataStockItemIncomePerPay/dataStockItemIncomeQuantityTotal); // 입고단가/입고용량소계
					var dataResourceRetainQuantity		 = data.resourceRetainQuantity; // 잔여수량 
					var dataResourceCapacityExtra 		 = data.resourceRetainQuantityCapacityExtra; // 잔여용량소계 
					
					var stockItemUseQuantity 				= $('#stockItemUseQuantity');
					var stockItemUseQuantityTotal 			= $('#stockItemUseQuantityTotal');
					var resourceRetainQuantity 				= $('#resourceRetainQuantity');
					var resourceRetainQuantityCapacityExtra = $('#resourceRetainQuantityCapacityExtra');
					var stockItemUseQuantityConversionPay   = $('#stockItemUseQuantityConversionPay');
					
					stockItemUseQuantityConversionPay
						.val('');
					
					stockItemUseQuantity
						.removeAttr('readonly')
						.val('');
					
					stockItemUseQuantityTotal
						.val('')
						.attr('readonly', 'readonly')
						.trigger('touchspin.destroy')
						.TouchSpin({
							min: 0,
							max: 1000000000,
							maxboostedstep: 10000000,
							buttondown_class: 'hidden',
							buttonup_class: 'hidden',
							postfix: '단위'
						}
					);
					
					resourceRetainQuantity
						.val(dataResourceRetainQuantity)
						.trigger(
							"touchspin.updatesettings"
							,{postfix: dataQuantityUnit}
						);
					
					resourceRetainQuantityCapacityExtra
						.val(dataResourceCapacityExtra)
						.trigger(
							"touchspin.updatesettings"
							,{postfix: dataQuantityCapacityUnit}
						);
					
					stockItemUseQuantity
						.trigger('touchspin.destroy')
						.TouchSpin({
							min: 0,
							max: dataResourceRetainQuantity,
							stepinterval: 50,
							step: 0.01,
							decimals: 2,
							firstclickvalueifempty: 0.01,
							maxboostedstep: 10000000,
							buttondown_class: 'btn btn-default',
						    buttonup_class: 'btn btn-default',
						    postfix: dataQuantityUnit
						}
					).on('touchspin.on.startspin keyup', function () {
						var total =  $('#stockItemUseQuantity').val() * (dataQuantityCapacity / dataQuantity);
						$('#stockItemUseQuantityTotal').val(total);
						
						$('#stockItemUseQuantityConversionPay').val($('#stockItemUseQuantityTotal').val() * dataIncomeQuantityPerPay);
						
						if($('#stockItemUseQuantity').val() == '' || $('#stockItemUseQuantityTotal').val() == ''){
							$('#stockItemUseQuantityTotal, #stockItemUseQuantityConversionPay').val('').attr('readonly', 'readonly');
						}
						
						if($('#stockItemUseQuantity').val() > 0){
							
							var stepNum = 0;
							if(100 <= dataQuantityCapacity){
								stepNum = 10;
							}else if(100 > dataQuantityCapacity && dataQuantityCapacity >= 10){
								stepNum = 1;
							}else if(10 > dataQuantityCapacity && dataQuantityCapacity > 0){
								stepNum = 0.1;
							}
							
							$("#stockItemUseQuantityTotal")
								.removeAttr('readonly')
								.trigger('touchspin.destroy')
								.TouchSpin({
								min: 0,
								max: dataResourceCapacityExtra,
								stepinterval: 50,
								step: stepNum,
								decimals: 2,
								firstclickvalueifempty: stepNum,
								maxboostedstep: 10000000,
								buttondown_class: 'btn btn-default',
							    buttonup_class: 'btn btn-default',
								postfix: dataQuantityCapacityUnit
							}).on('touchspin.on.startspin keyup', function(){
								var inputStockItemUseQuantity = (dataQuantity * $('#stockItemUseQuantityTotal').val())/dataQuantityCapacity;
								var conversionPay =  Math.round($('#stockItemUseQuantityTotal').val()*dataIncomeQuantityPerPay);
								console.log(conversionPay);
								$('#stockItemUseQuantity').val(inputStockItemUseQuantity);
								$('#stockItemUseQuantityConversionPay').val(conversionPay);
							})
							
						}else{
							$("#stockItemUseQuantityTotal")
								.attr('readonly', 'readonly')
								.trigger('touchspin.destroy')
								.TouchSpin({
								min: 0,
								max: 1000000000,
								maxboostedstep: 10000000,
								buttondown_class: 'hidden',
								buttonup_class: 'hidden',
								postfix: '단위'
							});
						}
			        })
			        
			        /*$('#stockItemUseQuantityTotal').on('change keyup', function(){
			        	var resourceExtra = $('#resourceRetainQuantityCapacityExtra');
			        	if($("#stockItemUseQuantityTotal").val() > resourceExtra.val()){
			        		console.log($("#stockItemUseQuantityTotal").val());
			        		alert('예상 사용가능한 용량을 초과했습니다. 추가로 소모하실 품목을 선택해주세요');
			        	}
			        	$('#stockItemUseQuantityConversionPay').val($('#stockItemUseQuantityTotal').val()*dataIncomeQuantityPerPay);
			        })*/
			        
				}

			},
			error : function(xhr, status, error) {
				console.log('xhr : ' + xhr);
				console.log('status : ' + status);
				console.log('error : ' + error);
			}
			
		});
	});
	
	/*보험료탭*/
	$('#insurePayTotal').TouchSpin({
		min: 0,
		max: 1000000000,
		maxboostedstep: 10000000,
		buttondown_class: 'hidden',
		buttonup_class: 'hidden',
		postfix: '원'
	});
	
	/*공과금탭*/
	$('#taxPayWhatmonth').TouchSpin({
		min: 0,
		max: 1000000000,
		step: 1,
		firstclickvalueifempty: 1,
		maxboostedstep: 10000000,
		buttondown_class: 'btn btn-default',
		buttonup_class: 'btn btn-default',
		postfix: '월'
	});
	
	$("#taxPayWhatmonth").datepicker({
		dateFormat: 'yy.mm', changeMonth: true, changeYear: true, 
		showButtonPanel: true, 
		onClose: function(dateText, inst) { 
			var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
			var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
			$(this).datepicker('setDate', new Date(year, month, 1)); 
			$(".ui-datepicker-calendar").css("display","none"); 
		} 
	});
	
	$("#taxPayWhatmonth").focus(function () {
		$(".ui-datepicker-calendar").css("display","none"); 
		$("#ui-datepicker-div").position({
			my: "center top", 
			at: "center bottom", 
			of: $(this) 
		}); 
	});

	$('#taxPay').TouchSpin({
		min: 0,
		max: 1000000000,
		step: 100,
		firstclickvalueifempty: 100,
		maxboostedstep: 10000000,
		buttondown_class: 'btn btn-default',
		buttonup_class: 'btn btn-default',
		postfix: '원'
	});
	
	/*기타비용탭*/
	$('#etcPay').TouchSpin({
		min: 0,
		max: 1000000000,
		step: 100,
		firstclickvalueifempty: 100,
		maxboostedstep: 10000000,
		buttondown_class: 'btn btn-default',
		buttonup_class: 'btn btn-default',
		postfix: '원'
	});
	
	/*모달 설정*/
	$('.modal').modal({
		keyboard: true,
		backdrop: 'static',
		show: false
	})
	
	var lang_kor = {
	        "decimal" : "",
	        "emptyTable" : "등록된 정보가 없습니다",
	        "info" : "_START_ - _END_",
	        "infoEmpty" : "0",
	        "infoFiltered" : "(전체 _MAX_검색결과)",
	        "infoPostFix" : "",
	        "thousands" : ",",
	        "lengthMenu" : "_MENU_ 개씩 보기",
	        "loadingRecords" : "로딩중...",
	        "processing" : "처리중...",
	        "search" : "검색 : ",
	        "zeroRecords" : "검색된 정보가 없습니다.",
	        "paginate" : {
	            "first" : "첫 페이지",
	            "last" : "마지막 페이지",
	            "next" : "다음",
	            "previous" : "이전"
	        },
	        "aria" : {
	            "sortAscending" : " :  오름차순 정렬",
	            "sortDescending" : " :  내림차순 정렬"
	        }
	}
	
	$('#commonMachineListTable').DataTable();
	$('#farmBookmarkMachineListTable').DataTable();
	/*거래처 데이터테이블 셋팅*/
	var clientListTable = $('#clientListTable').DataTable({
		"columnDefs": [
			 {className: "hidden clientCode", 	"targets": [ 0 ]}
			,{className: "hidden farmCode", 	"targets": [ 1 ]}
			,{className: "clientName", 			"targets": [ 2 ]}
			,{className: "clientPhone", 		"targets": [ 3 ]}
			,{className: "clientAddress",		"targets": [ 4 ]}
			,{className: "clientAccountBank", 	"targets": [ 5 ]}
			,{className: "clientAccount", 		"targets": [ 6 ]}
			,{className: "clientMemo", 			"targets": [ 7 ]}
		]
		,language : lang_kor
	});
	
	$('#resourceUsecapacityListTable').DataTable();
	
	
	/* Modal 등록버튼, 테이블 수정,삭제 버튼 클릭시 ajax로 처리 */
	
	//거래처리스트를 테이블과 셀렉트 박스로 만든 후 input
	function doClientList(data){
		
		var machineClientSelect = $('#machineClientCode');
		var taxPayClientSelect  = $('#taxPayClientCode');
		var etcPayClientSelect  = $('#etcPayClientCode');
		var resourcePaySelect	= $('#resourcePayClientCode');
		
		var option  = '<option value=""> :: 거래처선택 :: </option>';
		
		clientListTable.clear().draw();
		
		machineClientSelect.children().remove();
		taxPayClientSelect.children().remove();
		etcPayClientSelect.children().remove();
		
		$.each(data, function(index){
			clientListTable.row.add([
				 data[index].clientCode
				,data[index].farmCode
				,data[index].clientName
				,data[index].clientPhone
				,data[index].clientAddress
				,data[index].clientAccountBank
				,data[index].clientAccount
				,data[index].clientMemo
				,'<a class="modifyClientInfo">수정</a>'
				,'<a class="removeClientInfo">삭제</a>'
			]).draw()
			
			option += '<option value=';
			option += data[index].clientCode + '>';
			option += data[index].clientName;
			option += '</option>';
			
		})
		
		machineClientSelect.html(option);
		taxPayClientSelect.html(option);
		etcPayClientSelect.html(option);
		resourcePaySelect.html(option);
	}
	
	//모달 거래처등록버튼 클릭시
	$('#addClientBtn').click(function(){
	
		var queryString = $("form[name=addClient]").serialize();
		
		$.ajax({
			url : "/plan/ajax/addClient",
			method: "POST",
			data: queryString,
			datatype: 'json',
			success : function(data) {
				doClientList(data);
			},
			error : function(xhr, status, error) {
				console.log('xhr : ' + xhr);
				console.log('status : ' + status);
				console.log('error : ' + error);
			}
		});
		
		$('#planAddClient').modal("hide");
		
	});
	
	/*거래처등록모달 초기화*/
	$('#client-justified button').click(function(){
		$("form[name=addClient] .inputReset").val('');
	});
	
	/*거래처 테이블 수정 클릭시*/
	var modalClientCode 		= $('#modifyClientCode');
	var modalClientName 		= $('#modifyClientName');
	var modalClientPhone 		= $('#modifyClientPhone');
	var modalClientAddress 		= $('#modifyClientAddress');
	var modalClientAccountBank  = $('#modifyClientAccountBank');
	var modalClientAccount 		= $('#modifyClientAccount');
	var modalClientMemo			= $('#modifyClientMemo');
	var modalModifyClient 		= $('#planModifyClient');
	
	$(document).on('click', '.modifyClientInfo', function(){
		var clientCode 			= $(this).parent().parent().children('.clientCode').text();
		var clientName 			= $(this).parent().parent().children('.clientName').text();
		var clientPhone 		= $(this).parent().parent().children('.clientPhone').text();
		var clientAddress 		= $(this).parent().parent().children('.clientAddress').text();
		var clientAccountBank 	= $(this).parent().parent().children('.clientAccountBank').text();
		var clientAccount 		= $(this).parent().parent().children('.clientAccount').text();
		var clientMemo 			= $(this).parent().parent().children('.clientMemo').text();
		
		$("form[name=modifyClient] .inputReset").val('');
		
		modalModifyClient.modal('show');
		
		modalClientCode.val(clientCode);
		modalClientName.val(clientName);
		modalClientPhone.val(clientPhone);
		modalClientAddress.val(clientAddress);
		modalClientAccountBank.val(clientAccountBank);
		modalClientAccount.val(clientAccount);
		modalClientMemo.val(clientMemo);
		
	});
	
	/*모달 거래처 정보수정 버튼 클릭시*/
	$('#modifyClientBtn').click(function(){
		
		var queryString = $("form[name=modifyClient]").serialize();
		var commonAjax = new ksmartAjax();
		commonAjax.setAjaxUrl('/plan/ajax/modifyClient');
		commonAjax.setAjaxType('post');
		commonAjax.putObj(queryString);
		commonAjax.setResultType('json');
		commonAjax.action(function(data){
			doClientList(data);
			modalModifyClient.modal("hide");
		});
	});
	
	/*거래처 테이블 삭제 클릭시*/
	$(document).on('click', '.removeClientInfo', function(){
		var clientCode 	= $(this).parent().parent().children('.clientCode').text();
		var farmCode 	= $(this).parent().parent().children('.farmCode').text();
		
		var commonAjax = new ksmartAjax();
		commonAjax.setAjaxUrl('/plan/ajax/removeClient');
		commonAjax.setAjaxType('post');
		commonAjax.put("clientCode", clientCode);
		commonAjax.put("farmCode", farmCode);
		commonAjax.setResultType('json');
		commonAjax.action(function(data){
			doClientList(data);
		});
	});
	
	/*품목 데이터테이블 셋팅*/
	var stockItemTable = $('#stockItemTable').DataTable({
		"columnDefs": [
			 {className: "hidden stockItemCode", 			"targets": [ 0 ]}
			,{className: "hidden farmCode", 				"targets": [ 1 ]}
			,{className: "stockCateName", 					"targets": [ 2 ]}
			,{className: "stockItemName", 					"targets": [ 3 ]}
			,{className: "stockItemQuantity",				"targets": [ 4 ]}
			,{className: "stockItemQuantityUnit", 			"targets": [ 5 ]}
			,{className: "stockItemQuantityCapacity", 		"targets": [ 6 ]}
			,{className: "stockItemQuantityCapacityUnit", 	"targets": [ 7 ]}
		]
		,language : lang_kor
	});
	
	/*품목등록모달 초기화*/
	$('#stockItem-justified button').click(function(){
		$("form[name=addStockItem]")[0].reset();
	});
	
	/*모달 품목등록버튼 클릭시*/
	var modalPlanAddStock = $('#planAddStock');
	
	$('#addStockItemBtn').click(function(){
		var queryString = $("form[name=addStockItem]").serialize();
		
		var commonAjax = new ksmartAjax();
		commonAjax.setAjaxUrl('/plan/ajax/addStockItem');
		commonAjax.setAjaxType('post');
		commonAjax.putObj(queryString);
		commonAjax.setResultType('json');
		commonAjax.action(function(data){
			doStockItemList(data);
			modalPlanAddStock.modal("hide");
		});
	});
	
	//품목리스트를 테이블과 셀렉트 박스로 만든 후 input
	function doStockItemList(data){
		
		var resourcePayStockItemSelect = $('#resourcePayStockItemCode');
		
		var option  = '<option value=""> :: 품목선택 :: </option>';
		
		stockItemTable.clear().draw();
		
		resourcePayStockItemSelect.children().remove();
		
		$.each(data, function(index){
			
			stockItemTable.row.add([
				 data[index].stockItemCode
				,data[index].farmCode
				,data[index].stockCate.stockCateName
				,data[index].stockItemName
				,data[index].stockItemQuantity
				,data[index].stockItemQuantityUnit
				,data[index].stockItemQuantityCapacity
				,data[index].stockItemQuantityCapacityUnit
				,'<a class="modifyStockItemInfo">수정</a>'
				,'<a class="removeStockItemInfo">삭제</a>'
			]).draw()
			
			option += '<option value=';
			option += data[index].stockItemCode + '>';
			option += data[index].stockCate.stockCateName;
			option += " : ";
			option += data[index].stockItemName;
			option += '</option>';
			
		})
		resourcePayStockItemSelect.html(option);
	}
	
});