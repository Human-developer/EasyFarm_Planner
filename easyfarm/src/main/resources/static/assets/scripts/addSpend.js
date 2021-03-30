
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

	$('#stockItemCode').change(function(){
		var stockItemCode = $(this).val();
		
		$.ajax({
			url: "/ajax/getStockItemInfo",
			method: "POST",
			data: { stockItemCode : stockItemCode },
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
							firstclickvalueifempty: 1,
							maxboostedstep: 10000000,
							buttondown_class: 'btn btn-default',
						    buttonup_class: 'btn btn-default',
						    postfix: dataQuantityUnit
						}
					).on('touchspin.on.startspin keyup change', function () {
						var total =  $('#stockItemUseQuantity').val() * (dataQuantityCapacity / dataQuantity);
						$('#stockItemUseQuantityTotal').val(total);
						
						$('#stockItemUseQuantityConversionPay').val($('#stockItemUseQuantityTotal').val() * dataIncomeQuantityPerPay);
						
						if($('#stockItemUseQuantity').val() == '' || $('#stockItemUseQuantityTotal').val() == ''){
							$('#stockItemUseQuantityTotal, #stockItemUseQuantityConversionPay').val('').attr('readonly', 'readonly');
						}
						if($('#stockItemUseQuantity').val() > 0){
							$("#stockItemUseQuantityTotal")
								.removeAttr('readonly')
								.trigger('touchspin.destroy')
								.TouchSpin({
								min: 0,
								max: dataResourceCapacityExtra,
								stepinterval: 50,
								firstclickvalueifempty: 1,
								maxboostedstep: 10000000,
								buttondown_class: 'btn btn-default',
							    buttonup_class: 'btn btn-default',
								postfix: dataQuantityCapacityUnit
							});
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
			        
			        $('#stockItemUseQuantityTotal, #resourceRetainQuantity').on('change keyup', function(){
			        	var resourceExtra = $('#resourceRetainQuantityCapacityExtra');
			        	var stockItemUseQuantity = $('#stockItemUseQuantity');
			        	if($("#stockItemUseQuantityTotal").val() >= resourceExtra.val() || $("#resourceRetainQuantity").val() >= stockItemUseQuantity.val()){
			        		console.log($("#stockItemUseQuantityTotal").val());
			        		alert('예상 사용가능한 용량을 초과했습니다. 추가로 소모하실 품목을 선택해주세요');
			        	}
			        	$('#stockItemUseQuantityConversionPay').val($('#stockItemUseQuantityTotal').val()*dataIncomeQuantityPerPay);
			        })
			        
		
				}

			},
			error : function(xhr, status, error) {
				console.log('xhr : ' + xhr);
				console.log('status : ' + status);
				console.log('error : ' + error);
			}
			
		});
	});
	
	
	
});