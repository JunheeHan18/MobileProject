function check_value(){
	var value_keyword;
	var value_store;
	var calnd = document.getElementById("cld").value;
	var temp = new Date(document.getElementById("cld").value);
	var calnd2 = temp.getFullYear()+"-"+(temp.getMonth()+1)+"-"+(temp.getDate()+1);
	value_keyword = document.getElementById('keyword_value').value;
	value_store = document.getElementById('store_value').value;

	if(value_keyword==""&&value_store==""){
		alert("값을 입력해주세요.");
	}else if(value_keyword!=""&&value_store!=""){
		if(calnd!=""){
		location.href="/RankServlet?key=search_add_date&value="+value_keyword+"&seval="+value_store+"&date="+calnd+"&date2="+calnd2;
		}else{
		location.href="/RankServlet?key=search_add&value="+value_keyword+"&seval="+value_store;
		}
	}else if(value_keyword=="ALL"&&value_store==""){
		if(calnd!=""){
			alert("모든 내역을 조회합니다. 페이지를 이동합니다.");
			location.href="/RankServlet?key=search_add_all_date&date="+calnd+"&date2="+calnd2;
		}else{
		alert("모든 내역을 조회합니다. 페이지를 이동합니다.");
		location.href="/RankServlet?key=search_add_all";
		}
	}else{
		alert("올바른 입력 형태가 필요합니다.");
	}
	
}

