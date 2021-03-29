
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
		postfix: '원'
	});
	
	$('#gainQuantity').TouchSpin({
		min: 0,
		max: 1000000000,
		step: 1,
		stepinterval: 50,
		maxboostedstep: 10000000,
	});

	$('#workforceCount').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		maxboostedstep: 10000000,
		postfix: '명'
	});
	
	$('#workforceHowlong').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		maxboostedstep: 10000000,
		postfix: '일'
	});
	
	$('#leaseSwitch, #useSwitch').prop('checked', false);
	
	$('#leaseSwitch, #useSwitch').on('click', function(){
		if($('#leaseSwitch').prop('checked')){
			$('#machineClientCode, #commonMachineCode, #machineLeaseHowlong, #machineLeasePayDay, #farmRetainMachineCode').removeAttr('readonly');
			$('#machineClientCode option, #commonMachineCode option, #farmRetainMachineCode option').removeAttr('disabled');
			
			var touchSpinActive = "touchSpinActive";
			$.ajax({
				url: "/ajax/touchSpinActive",
				method: "POST",
				data: { touchSpinActive : touchSpinActive },
				success : function(data) {
					$('#machineLeaseHowlong').TouchSpin({
						min: 0,
						max: 1000000000,
						stepinterval: 50,
						maxboostedstep: 10000000,
						postfix: '일'
					});
					$('#machineLeasePayDay').TouchSpin({
						min: 0,
						max: 1000000000,
						step: 1000,
						stepinterval: 50,
						maxboostedstep: 10000000,
						postfix: '원'
					});
				},
				error : function(xhr, status, error) {
					console.log('xhr : ' + xhr);
					console.log('status : ' + status);
					console.log('error : ' + error);
				}
				
			});
		}else{
			$('#machineClientCode, #commonMachineCode, #machineLeaseHowlong, #machineLeasePayDay, #farmRetainMachineCode').attr('readonly', 'readonly');
			$('#machineClientCode option, #commonMachineCode option, #farmRetainMachineCode option').attr('disabled', 'disabled');
			var touchSpinActive = "touchSpinActive";
			$.ajax({
				url: "/ajax/touchSpinActive",
				method: "POST",
				data: { touchSpinActive : touchSpinActive },
				success : function(data) {
					$('#inputFormLeaseHowlong, #inputFormLeasePayDay').children().detach();
					$('#inputFormLeaseHowlong').html('<input id="machineLeaseHowlong" type="text" name="machineLeaseHowlong" class="form-control" readonly="readonly" placeholder="0">');
					$('#inputFormLeasePayDay').html('<input id="machineLeasePayDay" type="text" name="machineLeasePayDay" class="form-control" readonly="readonly" placeholder="1000">');
					$('#machineLeasePayTotal').val('');
				},
				error : function(xhr, status, error) {
					console.log('xhr : ' + xhr);
					console.log('status : ' + status);
					console.log('error : ' + error);
				}
				
			});
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

	$(document).on('propertychange change keyup', '#machineLeaseHowlong, #machineLeasePayDay', function(){
		var machineLeaseHowlongValue = $('#machineLeaseHowlong').val();
		var machineLeasePayDayValue = $('#machineLeasePayDay').val();
		var totalSum = (machineLeaseHowlongValue * machineLeasePayDayValue);
		$('#machineLeasePayTotal').val(totalSum);
	});
	
	/*농자재탭*/
	$('#stockItemUseQuantity').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		maxboostedstep: 10000000,
	});
	$('#stockItemUseQuantityTotal').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		maxboostedstep: 10000000,
	});

	$('#stockItemCode').change(function(){
		var stockItemCode = $(this).val();
		
		$.ajax({
			url: "/ajax/getStockItemInfo",
			method: "POST",
			data: { stockItemCode : stockItemCode },
			success : function(data) {
				if(data.stockItemCode != null){
					$('#resourceRetainQuantity').val(data.resourceRetainQuantity);
					
					$('#quantityAddClass').addClass('input-group bootstrap-touchspin');
					$('#resourceRetainQuantityUnit').removeAttr('style');
					$('#resourceRetainQuantityUnit').text(data.stockItemQuantityUnit);
					
					$('#resourceRetainQuantityCapacityExtra').val(data.resourceRetainQuantityCapacityExtra);
					
					$('#quantityCapacityAddClass').addClass('input-group bootstrap-touchspin');
					$('#resourceRetainQuantityCapacityExtraUnit').removeAttr('style');
					$('#resourceRetainQuantityCapacityExtraUnit').text(data.stockItemQuantityCapacityUnit);
					
					var quantityUnit = data.stockItemQuantityUnit;
					var quantityCapacityUnit = data.stockItemQuantityCapacityUnit;
					$('#inputFormQuantity, #inputFormQuantityTotal').children().detach();
					$('#inputFormQuantity').html('<input id="stockItemUseQuantity" type="text" name="stockItemUseQuantity" class="form-control">');
					$('#inputFormQuantityTotal').html('<input id="stockItemUseQuantityTotal" type="text" name="stockItemUseQuantityTotal" class="form-control">');
					
					$('#stockItemUseQuantity').TouchSpin({
						min: 0,
						max: 1000000000,
						stepinterval: 50,
						maxboostedstep: 10000000,
						postfix: quantityUnit
					});
					$('#stockItemUseQuantityTotal').TouchSpin({
						min: 0,
						max: 1000000000,
						stepinterval: 50,
						maxboostedstep: 10000000,
						postfix: quantityCapacityUnit
					});
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