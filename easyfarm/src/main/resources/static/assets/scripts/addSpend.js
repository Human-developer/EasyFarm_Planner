
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
			url: "/ajax/getStockItemInfo",
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
	
	/*간편등록*/
	$('#planAddClient').modal({
		keyboard: true,
		backdrop: 'static',
		show: false
	})
	
});