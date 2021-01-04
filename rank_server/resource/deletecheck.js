/**
 * 
 */


function deleteCheck(idx){
	var keyword_value = document.getElementById("keyword_value").value;
	var store_value= document.getElementById("store_value").value;
	var url_value = document.getElementById("url_value").value;
	var bDelete = confirm("데이터를 삭제합니다.");
	if(store_value!=""&&bDelete){
		location.href="/RankServlet?key=delete_rank_selected&value="+keyword_value+"&seval="+store_value+"&idx="+idx;
	}else if(url_value!=""&&bDelete){
		location.href="/RankServlet?key=delete_rank_selected&value="+keyword_value+"&thirval="+url_value+"&idx="+idx;
	}else{
		location.href="/RankServlet?key=delete_rank_selected&idx="+idx;
	}
}

function deleteCheckAdd(idx){
	var keyword_value = document.getElementById("keyword_value").value;
	var store_value= document.getElementById("store_value").value;
	var bDelete = confirm("데이터를 삭제합니다.");
	if(store_value!=""&&bDelete){
		location.href="/RankServlet?key=delete_add_selected&value="+keyword_value+"&seval="+store_value+"&idx="+idx;
	}
}

function deleteAllToRank(){
	
	var bDelete = confirm("해당 테이블의 모든 데이터를 삭제합니다.");
	
	if(bDelete){
		location.href="/RankServlet?key=delete_rank_all";
	}
	
}

function deleteAllToAdd(){
	
	var bDelete = confirm("해당 테이블의 모든 데이터를 삭제합니다.");
	
	if(bDelete){
		location.href="/RankServlet?key=delete_add_all";
	}
	
}