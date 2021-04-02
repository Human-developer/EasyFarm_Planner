
$.fn.sysDataTable = function(langKor, tagets,displayLen){
	
	
	if(displayLen == null) displayLen =10;
	$(this).css('width','100%')
	$(this).addClass('table table-striped table-bordered display dataTable');
	$(this).dataTable({
		"paginate": false,
		"sort": false,
		//기본 표시건수
		displayLength: displayLen,
		//표시건수바
		lengthChange: true,
		// 검색 기능 숨기기
		searching: true,
		// 정렬 기능 숨기기
		ordering: true,
		// 정보 표시 숨기기
		info: false,
		// 페이징 기능 숨기기
		paging: true,
		//언어설정
		language :langKor,
		"columnDefs": [
            {
                "targets": tagets,
                "visible": false,
                "searchable": false
            }
        ]
		});
}

			        
			