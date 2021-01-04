var dataset;
var dataset2;
				

function LoadingWithMask(gif) {
    //화면의 높이와 너비를 구합니다.
    var maskHeight = $(document).height();
    var maskWidth  = window.document.body.clientWidth;
     
    //화면에 출력할 마스크를 설정해줍니다.
    var mask       ="<div id='mask' style='position:absolute; z-index:9000; background-color:#ffffff; display:none; left:0; top:0;'></div>";
    var loadingImg ='';
      
    loadingImg +=" <img src='"+ gif +"' id='loadingImg' style='position: fixed; display: block; left: 45%; top: 35%; z-index:9999 ;'/>";
 
    //화면에 레이어 추가
    $('body')
        .append(mask)
        $('body')
        .append(loadingImg)
 
    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
    $('#mask').css({
            'width' : maskWidth,
            'height': maskHeight,
            'opacity' :'0.3'
    });
  
    //마스크 표시
    $('#mask').show();
  
    //로딩중 이미지 표시
    $('#loadingImg').append(loadingImg);
    $('#loadingImg').show();
}
 
function closeLoadingWithMask() {
    $('#mask, #loadingImg').hide();
    $('#mask, #loadingImg').empty(); 
}

function check_value(){
	var value_keyword;
	var value_store;
	var value_url;
	var xMLHttpRequest = new XMLHttpRequest();
	value_keyword = document.getElementById('keyword_value').value;
	value_store = document.getElementById('store_value').value;
	value_url = document.getElementById('url_value').value;
	
	if(value_keyword==""&&value_store==""&&value_url==""){
		alert("값을 입력해주세요.");
	}else if(value_keyword!=""&&value_store!=""&&value_url==""){
		alert("상점명, 키워드 값이 입력되었습니다. 페이지를 이동합니다.");
		dataset = '{ \"store\" :\"' +value_store + '\", \"keyword\" : \"' + value_keyword + '\", \"url\" : \"\" , \"add\" : \"no\" , \"idx\" : \"\" , \"order\" : \"\"}'; 
		//dataset = '{ store :\"' +value_store + '\", keyword : \"' + value_keyword + '\", url : "" , add : \"no\"}'; 
		//dataset2 = eval("("+JSON.stringify(dataset)+")");
		// 웹 서버를 접속한다.
		var webSocket = new WebSocket("ws://3.34.183.246:8889");
		// 소켓 접속이 되면 호출되는 함수
		webSocket.onopen = function(message) {
			//alert("Server connect...");
			webSocket.send(dataset);
			LoadingWithMask('lodingImg.gif');
		};
		// 소켓 접속이 끝나면 호출되는 함수
		webSocket.onclose = function(message) {
			
		};
		// 소켓 통신 중에 에러가 발생되면 호출되는 함수
		webSocket.onerror = function(message) {
			alert("error...");
		};
		// 소켓 서버로 부터 메시지가 오면 호출되는 함수.
		webSocket.onmessage = function(message) {
			if(message.data=="OK"){
				closeLoadingWithMask();
				alert("크롤링 완료");
				webSocket.close();
				location.href="/RankServlet?key=crawling_result&value="+value_keyword+"&seval="+value_store;
			} else if(message.data=="FAIL"){
				closeLoadingWithMask();
				alert("크롤링 실패");
				webSocket.close();
			}else if(message.data=="NO"){
				closeLoadingWithMask();
				alert("결과없음");
				webSocket.close();
			}
		};
	}else if(value_keyword!=""&&value_store==""&&value_url!=""){
		alert("키워드, url 값이 입력되었습니다. 페이지를 이동합니다.");
		dataset = '{ \"store\" : \"\", \"keyword\" :\"'+value_keyword + '\" , \"url\" :\"' +value_url+'\" , \"add\" : \"no\" , \"idx\" : \"\" , \"order\" : \"\"}'; 
		// 웹 서버를 접속한다.
		var webSocket = new WebSocket("ws://3.34.183.246:8889");
		// 소켓 접속이 되면 호출되는 함수
		webSocket.onopen = function(message) {
			alert("Server connect...");
			webSocket.send(dataset);
			LoadingWithMask('lodingImg.gif');
		};
		// 소켓 접속이 끝나면 호출되는 함수
		webSocket.onclose = function(message) {
			
		};
		// 소켓 통신 중에 에러가 발생되면 호출되는 함수
		webSocket.onerror = function(message) {
			alert("error...");
		};
		// 소켓 서버로 부터 메시지가 오면 호출되는 함수.
		webSocket.onmessage = function(message) {
			if(message.data=="OK"){
				closeLoadingWithMask();
				alert("크롤링 완료");
				webSocket.close();
				location.href="/RankServlet?key=crawling_result_product&value="+value_keyword+"&thirval="+value_url.slice(8,value_url.lenght);
			}else if(message.data=="FAIL"){
				closeLoadingWithMask();
				alert("크롤링 실패");
				webSocket.close();
				location.reload();
			}else if(message.data=="NO"){
				closeLoadingWithMask();
				alert("결과없음");
				webSocket.close();
				location.reload();
			}
		};

		//location.href="/RankServlet?key=search_multipu&value="+value_store+"&seval="+value_url;
		
	}else{
		alert("올바른 입력형태가 필요합니다. ex)keyword,url or keyword,store");
	}
	
}