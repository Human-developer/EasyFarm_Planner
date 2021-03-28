
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
			$('#inputFormLeaseHowlong, #inputFormLeasePayDay').children().remove();
			$('#inputFormLeaseHowlong').html('<div class="input-group bootstrap-touchspin"><span class="input-group-btn"><button class="btn btn-default bootstrap-touchspin-down" type="button">-</button></span><span class="input-group-addon bootstrap-touchspin-prefix" style="display: none;"></span><input id="machineLeaseHowlong" type="text" name="machineLeaseHowlong" class="form-control" placeholder="0" style="display: block;"><span class="input-group-addon bootstrap-touchspin-postfix">일</span><span class="input-group-btn"><button class="btn btn-default bootstrap-touchspin-up" type="button">+</button></span></div>');
			$('#inputFormLeasePayDay').html('<div class="input-group bootstrap-touchspin"><span class="input-group-btn"><button class="btn btn-default bootstrap-touchspin-down" type="button">-</button></span><span class="input-group-addon bootstrap-touchspin-prefix" style="display: none;"></span><input id="machineLeasePayDay" type="text" name="machineLeasePayDay" class="form-control" placeholder="1000" style="display: block;"><span class="input-group-addon bootstrap-touchspin-postfix">원</span><span class="input-group-btn"><button class="btn btn-default bootstrap-touchspin-up" type="button">+</button></span></div>');
		}else{
			$('#machineClientCode, #commonMachineCode, #machineLeaseHowlong, #machineLeasePayDay, #farmRetainMachineCode').attr('readonly', 'readonly');
			$('#machineClientCode option, #commonMachineCode option, #farmRetainMachineCode option').attr('disabled', 'disabled');
			$('#inputFormLeaseHowlong, #inputFormLeasePayDay').children().remove();
			$('#inputFormLeaseHowlong').html('<input id="machineLeaseHowlong" type="text" name="machineLeaseHowlong" class="form-control" readonly="readonly" placeholder="0">');
			$('#inputFormLeasePayDay').html('<input id="machineLeasePayDay" type="text" name="machineLeasePayDay" class="form-control" readonly="readonly" placeholder="1000">');
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
	$('#resourceRetainQuantity').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		maxboostedstep: 10000000,
		postfix: $('#resourceRetainQuantityUnit').val()
	});
	$('#resourceRetainQuantityCapacityExtra').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		maxboostedstep: 10000000,
		postfix: $('#resourceRetainQuantityCapacityExtraUnit').val()
	});
	$('#stockItemUseQuantity').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		maxboostedstep: 10000000,
		postfix: $('#stockItemUseQuantityUnit').val()
	});
	$('#stockItemUseQuantityTotal').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		maxboostedstep: 10000000,
		postfix: $('#stockItemUseQuantityTotalUnit').val()
	});
	$('#stockItemUseQuantityConversionPay').TouchSpin({
		min: 0,
		max: 1000000000,
		stepinterval: 50,
		maxboostedstep: 10000000,
		postfix: $('#stockItemUseQuantityConversionPayUnit').val()
	});
	$('#stockItemCode').change(function(){
		var stockItemCode = $(this).val();
		
		$.ajax({
			url: "/ajax/getStockItemInfo",
			method: "POST",
			data: { stockItemCode : stockItemCode },
			success : function(data) {
				if(data.stockItemCode != null){
					$('#resourceRetainQuantity').val(data.stockItemQuantity);
					
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