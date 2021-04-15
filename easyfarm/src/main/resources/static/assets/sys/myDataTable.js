
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
	
	 	/*,
	  	ordering: true,
	  	serverSide: false*/
	return $(this);
}


$.fn.sysTable = function(obj){
	$(this).css('width','100%')
	$(this).addClass('table table-striped table-bordered display dataTable');	
	var vTarget = {};
	var columnDef = [];
	if(obj.vTarget != null){
		vTarget.targets = obj.vTarget;
		vTarget.visible = false;
		vTarget.searchable = false;
	} 
	columnDef.push(vTarget);
	
	var lang_kor = {
	        "decimal" : "",
	        "emptyTable" : "데이터가 없습니다",
	        "info" : "_START_ - _END_ (총 _TOTAL_ 명)",
	        "infoEmpty" : "0명",
	        "infoFiltered" : "(전체 _MAX_ 데이터 중 검색결과)",
	        "infoPostFix" : "",
	        "thousands" : ",",
	        "lengthMenu" : "_MENU_ 개씩 보기",
	        "loadingRecords" : "로딩중...",
	        "processing" : "처리중...",
	        "search" : "검색 : ",
	        "zeroRecords" : "검색된 데이터가 없습니다.",
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
	
	$(this).dataTable({
		"paginate": false,
		"sort": false,
		//기본 표시건수
		displayLength: 10,
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
		language :lang_kor,
		"columnDefs": columnDef
		});
	
	
	
	
	return $(this);
}
			        
			