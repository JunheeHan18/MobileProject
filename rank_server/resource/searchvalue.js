/**
 * 
 */

function check_value(){
	var value_keyword;
	var value_store;
	var value_url;
	var xMLHttpRequest = new XMLHttpRequest();
	var calnd = document.getElementById("cld").value;
	var temp = new Date(document.getElementById("cld").value);
	var calnd2 = temp.getFullYear()+"-"+(temp.getMonth()+1)+"-"+(temp.getDate()+1);
	value_keyword = document.getElementById('keyword_value').value;
	value_store = document.getElementById('store_value').value;
	value_url = document.getElementById('url_value').value;
	
	if(value_keyword==""&&value_store==""&&value_url==""){
		alert("값을 입력해주세요.");
	}else if(value_keyword=="ALL"&&value_store==""&&value_url==""){
		if(calnd!=""){
			alert("모든 내역을 조회합니다. 페이지를 이동합니다.");
			location.href="/RankServlet?key=search_all_date&date="+calnd+"&date2="+calnd2;
		}else{
		alert("모든 내역을 조회합니다. 페이지를 이동합니다.");
		location.href="/RankServlet?key=search_all";
		}
	}else if(value_keyword!=""&&value_store==""&&value_url==""){
		if(calnd!=""){
			alert("키워드 값이 입력되었습니다. 페이지를 이동합니다.");
			location.href = "/RankServlet?key=search_keyword_date&value="+value_keyword+"&date="+calnd+"&date2="+calnd2;
		}else{
		alert("키워드 값이 입력되었습니다. 페이지를 이동합니다.");
		location.href = "/RankServlet?key=search_keyword&value="+value_keyword;
		}
	}else if(value_keyword==""&&value_store!=""&&value_url==""){
		if(calnd!=""){
			alert("상점명 값이 입력되었습니다. 페이지를 이동합니다.");
			location.href="/RankServlet?key=search_store_date&seval="+value_store+"&date="+calnd+"&date2="+calnd2;
		}else{
			alert("상점명 값이 입력되었습니다. 페이지를 이동합니다.");
			location.href="/RankServlet?key=search_store&seval="+value_store;
		}
	}else if(value_keyword==""&&value_store==""&&value_url!=""){
		if(calnd!=""){
		alert("url 값이 입력되었습니다. 페이지를 이동합니다.");
		location.href="/RankServlet?key=search_url_date&thirval="+value_url+"&date="+calnd+"&date2="+calnd2;
		}else{
			alert("url 값이 입력되었습니다. 페이지를 이동합니다.");
			location.href="/RankServlet?key=search_url&thirval="+value_url;
		}
	}else if(value_keyword!=""&&value_store!=""&&value_url==""){
		if(calnd!=""){
		alert("키워드, 상점명 값이 입력되었습니다. 페이지를 이동합니다.");
		location.href="/RankServlet?key=search_multikp_date&value="+value_keyword+"&seval="+value_store+"&date="+calnd+"&date2="+calnd2;
		}else{
			alert("키워드, 상점명 값이 입력되었습니다. 페이지를 이동합니다.");
			location.href="/RankServlet?key=search_multikp&value="+value_keyword+"&seval="+value_store;

		}
	}else if(value_keyword==""&&value_store!=""&&value_url!=""){
		if(calnd!=""){
		alert("상점명, url 값이 입력되었습니다. 페이지를 이동합니다.");
		location.href="/RankServlet?key=search_multipu_date&seval="+value_store+"&thirval="+value_url+"&date="+calnd+"&date2="+calnd2;
		}else{
			alert("상점명, url 값이 입력되었습니다. 페이지를 이동합니다.");
			location.href="/RankServlet?key=search_multipu&seval="+value_store+"&thirval="+value_url;
		}
	}else if(value_keyword!=""&&value_store==""&&value_url!=""){
		if(calnd!=""){
		alert("키워드, url 값이 입력되었습니다. 페이지를 이동합니다.");
		location.href="/RankServlet?key=search_multiku_date&value="+value_keyword+"&thirval="+value_url+"&date="+calnd+"&date2="+calnd2;
		}else{
			alert("키워드, url 값이 입력되었습니다. 페이지를 이동합니다.");
			location.href="/RankServlet?key=search_multiku&value="+value_keyword+"&thirval="+value_url;
		}
	}else if(value_keyword!=""&&value_store!=""&&value_url!=""){
		if(calnd!=""){
		alert("모든 값이 입력되었습니다. 페이지를 이동합니다.");
		location.href="/RankServlet?key=search_multikpu_date&value="+value_keyword+"&seval="+value_store+"&thirval="+value_url+"&date="+calnd+"&date2="+calnd2;
		}else{
			alert("모든 값이 입력되었습니다. 페이지를 이동합니다.");
			location.href="/RankServlet?key=search_multikpu&value="+value_keyword+"&seval="+value_store+"&thirval="+value_url;

		}
	}
	
}