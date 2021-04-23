/**
 * Theme: Ninja Admin Template
 * Author: NinjaTeam
 * Module/App: Full Calendar
 */
(function($) {
	"use strict";

$(document).ready(function() {


	/* initialize the calendar
	-----------------------------------------------------------------*/

	if ($('#calendar').length){
		var todayDate = moment().startOf('day');
		var YM = todayDate.format('YYYY-MM');
		var YESTERDAY = todayDate.clone().subtract(1, 'day').format('YYYY-MM-DD');
		var TODAY = todayDate.format('YYYY-MM-DD');
		var TOMORROW = todayDate.clone().add(1, 'day').format('YYYY-MM-DD');
		$('#calendar').fullCalendar({
			timezone: 'local',
			header: {
				left: 'prevYear,prev,next,nextYear today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay,list'
			},
			buttonIcons: {
				prev: 'none fa fa-angle-left',
				next: 'none fa fa-angle-right',
				prevYear: 'none fa  fa-angle-double-left',
				nextYear: 'none fa  fa-angle-double-right'
			},
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			navLinks: true,
			droppable: true, // this allows things to be dropped onto the calendar
			displayEventTime: false,
			drop: function(date, allDay) {
				var originalEventObject = $(this).data('eventObject');
				var valueArray = ($(this).attr('class')).split(" ");
				var thisClass;
				for(var i=0; i<valueArray.length; i++){
					if (valueArray[i].search("bg") > -1){
						thisClass = valueArray[i];
					}
				}
				var copiedEventObject = $.extend({}, originalEventObject);
				copiedEventObject.start = date;
				copiedEventObject.className = thisClass;
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
				console.log(originalEventObject);
			},
			eventDragStop : function(info){
				console.log(info);
			},
			eventClick: function(data){
				//var yy = date.end._i.format("YYYY");
	        	//var mm = date.end._i.format("MM");
	        	//var dd = date.end._i.format("DD");
	        	//var ss = date.end._i.format("dd");
		        //onChangeDate(yy,mm,dd);
				
				
				console.log(data);
				if(data.planWorkphaseCateCode != null && data.planWorkphaseCateCode.trim()){
					$('#workPhaseCate').parent().parent().removeClass('hidden');
					
					$('#workPhaseCate').val(data.title);
					$('#workPhaseCateDate-range [name="start"]').val(data.start._i);
					$('#workPhaseCateDate-range [name="end"]').val(data.end._i);
					
					$('#workPhase').val(data.workphaseName);
					$('#workPhaseDate-range [name="start"]').val(data.planWorkphaseBegin);
					$('#workPhaseDate-range [name="end"]').val(data.planWorkphaseEnd);
				}
				else{
					$('#workPhaseCate').parent().parent().addClass('hidden');
					$('#workPhase').val(data.title);
					$('#workPhaseDate-range [name="start"]').val(data.start._i);
					$('#workPhaseDate-range [name="end"]').val(data.end._i);
				}
				
				$.ajax({
					url : "/plan/ajax/getAllPlanSchedule",
					method: "POST",
					data: {	'planWorkphaseCateCode' : data.planWorkphaseCateCode,
							'planWorkphaseCode'		: data.planWorkphaseCode
						  },
					datatype: 'json',
					success : function(data) {
						var tr = '';
						var expWorkforcePayListTableTbody 	 = $('#expWorkforcePayListTable tbody'); 	//인건비
						var expMachineLeaseListTableTbody 	 = $('#expMachineLeaseListTable tbody');	//농기계대여
						var expMachineUseListTableTbody   	 = $('#expMachineUseListTable tbody');   	//보유농기계
						var expResourceUseplanListTableTbody = $('#expResourceUseplanListTable tbody'); //농자재사용
						var expTaxPayListTableTbody 		 = $('#expTaxPayListTable tbody'); 			//공과금
						var productGainListTableTbody 		 = $('#productGainListTable tbody'); 		//생산물
						var expEtcPayListTableTbody 		 = $('#expEtcPayListTable tbody'); 			//기타비용
						
						//인건비
						if(data.expWorkforcePayList.length > 0){
							tr = '';
							$.each(data.expWorkforcePayList, function(index){
								tr += '<tr>';
								tr += '<td>' + data.expWorkforcePayList[index].workforceCount + ' 명</td>';
								tr += '<td>' + data.expWorkforcePayList[index].workforceHowlong + ' 일</td>';
								tr += '<td>' + data.expWorkforcePayList[index].workforceRequireDay + '</td>';
								tr += '<td>' + data.expWorkforcePayList[index].workforcePayDay + ' 원</td>';
								tr += '<td>' + data.expWorkforcePayList[index].workforcePayTotal + ' 원</td>';
								tr += '</tr>';
							})
							expWorkforcePayListTableTbody.children().remove();
							expWorkforcePayListTableTbody.html(tr);
						}else{
							tr = '';
							tr += '<tr>';
							tr += '<td colspan="5" class="t-center">등록된 정보가 없습니다.</td>';
							tr += '</tr>';
							expWorkforcePayListTableTbody.html(tr);
						}
						
						//농기계대여
						if(data.expMachineLeaseList.length > 0){
							tr = '';
							$.each(data.expMachineLeaseList, function(index){
								tr += '<tr>';
								tr += '<td>' + data.expMachineLeaseList[index].clientName + '</td>';
								tr += '<td>' + data.expMachineLeaseList[index].commonMachineName + '</td>';
								tr += '<td>' + data.expMachineLeaseList[index].machineLeaseHowlong + ' 일</td>';
								tr += '<td>' + data.expMachineLeaseList[index].machineLeasePayDay + ' 원</td>';
								tr += '<td>' + data.expMachineLeaseList[index].machineLeasePayTotal + ' 원</td>';
								tr += '</tr>';
							})
							expMachineLeaseListTableTbody.children().remove();
							expMachineLeaseListTableTbody.html(tr);
						}else{
							tr = '';
							tr += '<tr>';
							tr += '<td colspan="5" class="t-center">등록된 정보가 없습니다.</td>';
							tr += '</tr>';
							expMachineLeaseListTableTbody.html(tr);
						}
						
						//보유농기계
						if(data.expMachineUseList.length > 0){
							tr = '';
							$.each(data.expMachineUseList, function(index){
								tr += '<tr>';
								tr += '<td>' + data.expMachineUseList[index].commonMachineName + '</td>';
								tr += '</tr>';
							})
							expMachineUseListTableTbody.children().remove();
							expMachineUseListTableTbody.html(tr);
						}else{
							tr = '';
							tr += '<tr>';
							tr += '<td colspan="1" class="t-center">등록된 정보가 없습니다.</td>';
							tr += '</tr>';
							expMachineUseListTableTbody.html(tr);
						}
						
						//농자재
						if(data.expResourceUseplanList.length > 0){
							tr = '';
							$.each(data.expResourceUseplanList, function(index){
								tr += '<tr>';
								tr += '<td>' + data.expResourceUseplanList[index].stockCateName + '</td>';
								tr += '<td>' + data.expResourceUseplanList[index].stockItemName + '</td>';
								tr += '<td>' + data.expResourceUseplanList[index].stockItemUseQuantity + '</td>';
								tr += '<td>' + data.expResourceUseplanList[index].stockItemQuantityUnit + '</td>';
								tr += '<td>' + data.expResourceUseplanList[index].stockItemUseQuantityTotal + '</td>';
								tr += '<td>' + data.expResourceUseplanList[index].stockItemQuantityCapacityUnit + '</td>';
								tr += '<td>' + data.expResourceUseplanList[index].stockItemUseQuantityConversionPay + ' 원</td>';
								tr += '</tr>';
							})
							expResourceUseplanListTableTbody.children().remove();
							expResourceUseplanListTableTbody.html(tr);
						}else{
							tr = '';
							tr += '<tr>';
							tr += '<td colspan="7" class="t-center">등록된 정보가 없습니다.</td>';
							tr += '</tr>';
							expResourceUseplanListTableTbody.html(tr);
						}
						
						//공과금
						if(data.expTaxPayList.length > 0){
							tr = '';
							$.each(data.expTaxPayList, function(index){
								tr += '<tr>';
								tr += '<td>' + data.expTaxPayList[index].clientName + '</td>';
								tr += '<td>' + data.expTaxPayList[index].taxPayCateName + '</td>';
								tr += '<td>' + data.expTaxPayList[index].taxPayWhatmonth + '</td>';
								tr += '<td>' + data.expTaxPayList[index].taxPay + ' 원</td>';
								tr += '<td>' + data.expTaxPayList[index].taxPayDetail + '</td>';
								tr += '</tr>';
							})
							expTaxPayListTableTbody.children().remove();
							expTaxPayListTableTbody.html(tr);
						}else{
							tr = '';
							tr += '<tr>';
							tr += '<td colspan="5" class="t-center">등록된 정보가 없습니다.</td>';
							tr += '</tr>';
							expTaxPayListTableTbody.html(tr);
						}
						
						//생산물
						if(data.productGainList.length > 0){
							tr = '';
							$.each(data.productGainList, function(index){
								tr += '<tr>';
								tr += '<td>' + data.productGainList[index].stockCateName + '</td>';
								tr += '<td>' + data.productGainList[index].stockItemName + '</td>';
								tr += '<td>' + data.productGainList[index].gainQuantity + '</td>';
								tr += '<td>' + data.productGainList[index].stockItemQuantityUnit + '</td>';
								tr += '<td>' + data.productGainList[index].gainQuantityCapacity + '</td>';
								tr += '<td>' + data.productGainList[index].stockItemQuantityCapacityUnit + '</td>';
								tr += '<td>' + data.productGainList[index].gainDetail + '</td>';
								tr += '</tr>';
							})
							productGainListTableTbody.children().remove();
							productGainListTableTbody.html(tr);
						}else{
							tr = '';
							tr += '<tr>';
							tr += '<td colspan="7" class="t-center">등록된 정보가 없습니다.</td>';
							tr += '</tr>';
							productGainListTableTbody.html(tr);
						}
						
						//기타비용
						if(data.expEtcPayList.length > 0){
							tr = '';
							$.each(data.expEtcPayList, function(index){
								tr += '<tr>';
								tr += '<td>' + data.expEtcPayList[index].clientName + '</td>';
								tr += '<td>' + data.expEtcPayList[index].etcPay + ' 원</td>';
								tr += '<td>' + data.expEtcPayList[index].etcPayDetail + '</td>';
								tr += '</tr>';
							})
							expEtcPayListTableTbody.children().remove();
							expEtcPayListTableTbody.html(tr);
						}else{
							tr = '';
							tr += '<tr>';
							tr += '<td colspan="3" class="t-center">등록된 정보가 없습니다.</td>';
							tr += '</tr>';
							expEtcPayListTableTbody.html(tr);
						}
						
						$('#planModal').modal('show');
					},
					error : function(xhr, status, error) {
						console.log('xhr : ' + xhr);
						console.log('status : ' + status);
						console.log('error : ' + error);
					}
				});
				
			},
			customButtons: {
                changeDateButton: {
                    text: '날짜선택',
                    click: function() {
                        $('#sdate').monthpicker('show');
                    }
                }
            },
            
            dayClick: function(date, allDay, jsEvent, view) {
        	   
        	   
            },
            
			events : function(info, successCallback, failureCallback, callback) {				
				
				var projectPlanCode = $('#projectPlanNList').val();
				
				var request = $.ajax({
				  url: "/plan/calendarDataList",
				  data: { projectPlanCode : projectPlanCode },
				  method: "Post",
				  dataType: "json"
				});
				 
				request.done(function( data ) {
					var fixedDate = data.map(function (array) {
				          if (array.start !== array.end) {
				            array.end = moment(array.end).add(1, 'days'); // 이틀 이상 AllDay 일정인 경우 달력에 표기시 하루를 더해야 정상출력
				            array.start = moment(array.start);
				          }
				          return array;
			        });
					callback(fixedDate);
				});
				 
				request.fail(function( jqXHR, textStatus ) {
				  alert( "Request failed: " + textStatus );
				});
				
			},
			
		});
		$('#external-events .fc-event').each(function () {
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var mydata = $(this).attr('data-mydata');
			console.log(mydata)
			var eventObject = {
				title: $.trim($(this).text()), // use the element's text as the event title	
				mydata : mydata
			};
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
		});
	}

	if ($("#calendar-widget").length){
		$('#calendar-widget').fullCalendar({
			height: 470,
			header: {
				left: 'prev,next',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			buttonIcons: {
				prev: 'none fa fa-angle-left',
				next: 'none fa fa-angle-right',
				prevYear: 'none fa fa-angle-left',
				nextYear: 'none fa fa-angle-right'
			},
			editable: true,
			defaultDate: '2016-10-11',
			events: [
				{
					title: 'Birthday',
					start: '2016-10-11',
					className: 'bg-success'
				},
				{
					title: 'NinjaTeam Events',
					start: '2016-10-16',
					end: '2016-10-18',
					className: 'bg-primary'
				},
				{
					title: 'Meeting',
					start: '2016-10-13',
					end: '2016-10-14',
					className: 'bg-danger'
				},
				{
					title: 'Events Group',
					start: '2016-10-03',
					end: '2016-10-04',
					className: 'bg-warning'
				},
				{
					title: 'Group',
					start: '2016-10-28',
					end: '2016-10-29',
					className: 'bg-violet'
				},
			]
		});
	}


});

})(jQuery);