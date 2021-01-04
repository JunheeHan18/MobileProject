var dataset;
var dataset2;
var order;

function LoadingWithMask(gif) {
    //화면의 높이와 너비를 구합니다.
    var maskHeight = $(document).height();
    var maskWidth  = window.document.body.clientWidth;
     
    //화면에 출력할 마스크를 설정해줍니다.
    var mask       ="<div id='mask' style='position:absolute; z-index:9000; background-color:#ffffff; display:none; left:0; top:0;'></div>";
    var loadingImg ='';
      
    loadingImg +=" <img src='"+ gif +"' id='loadingImg' style='position: fixed; display: block; left: 45%; top: 35% ; z-index:9999;'/>";
 
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


function getCrawling(keyword,url,order,idx){
	
		alert("키워드, url 기반 크롤링 시작. 페이지를 이동합니다.");
		dataset = '{ \"store\" :\"\", \"keyword\" : \"'+keyword + '\" , \"url\" :\"' +url+'\" , \"add\" : \"no\" , \"order\" : \"'+order+'\" , \"idx\" : \"'+idx+'\"}'; 

		// 웹 서버를 접속한다.
		var webSocket = new WebSocket("ws://3.34.183.246:8889");
		// 소켓 접속이 되면 호출되는 함수
		webSocket.onopen = function(message) {
			//alert("Server connect...");
			//alert(dataset);
			webSocket.send(dataset);
			LoadingWithMask('resource/lodingImg.gif');
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
				//location.href="/RankServlet?key=crawling_result_product&value="+keyword+"&thirval="+url.slice(8,url.lenght);
				location.reload();
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
	
	
}

function getCrawlingAll(keyword1,keyword2,keyword3,keyword4,url,idx){
		var dataset_1,dataset_2,dataset_3,dataset_4;
		var cnt=1;
		alert("키워드, url 기반 크롤링 시작. 페이지를 이동합니다.");
		dataset_1 = '{ \"store\" :\"\", \"keyword\" : \"'+keyword1 + '\" , \"url\" :\"' +url+'\" , \"add\" : \"no\" , \"order\" : \"first\" , \"idx\" : \"'+idx+'\"}'; 
		dataset_2 = '{ \"store\" :\"\", \"keyword\" : \"'+keyword2 + '\" , \"url\" :\"' +url+'\" , \"add\" : \"no\" , \"order\" : \"second\" , \"idx\" : \"'+idx+'\"}'; 
		dataset_3 = '{ \"store\" :\"\", \"keyword\" : \"'+keyword3 + '\" , \"url\" :\"' +url+'\" , \"add\" : \"no\" , \"order\" : \"third\" , \"idx\" : \"'+idx+'\"}'; 
		dataset_4 = '{ \"store\" :\"\", \"keyword\" : \"'+keyword4 + '\" , \"url\" :\"' +url+'\" , \"add\" : \"no\" , \"order\" : \"four\" , \"idx\" : \"'+idx+'\"}'; 
		// 웹 서버를 접속한다.
		var webSocket = new WebSocket("ws://3.34.183.246:8889");
		// 소켓 접속이 되면 호출되는 함수
		webSocket.onopen = function(message) {
			//alert("Server connect...");
			//alert(dataset);
			webSocket.send(dataset_1);
			LoadingWithMask('resource/lodingImg.gif');
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
			if(message.data=="OK"&&cnt==5){
				closeLoadingWithMask();
				alert("크롤링 완료");
				webSocket.close();
				//location.href="/RankServlet?key=crawling_result_product&value="+keyword+"&thirval="+url.slice(8,url.lenght);
				location.reload();
			}else if(message.data=="FAIL"&&cnt==5){
				closeLoadingWithMask();
				alert("크롤링 완료");
				webSocket.close();
				location.reload();
			}else if(message.data=="NO"&&cnt==5){
				closeLoadingWithMask();
				alert("크롤링 완료");
				webSocket.close();
				location.reload();
			}
			if(cnt==1){
				cnt++;
				webSocket.send(dataset_2);
			}else if(cnt==2){
				cnt++;
				webSocket.send(dataset_3);
			}else if(cnt==3){
				cnt=5;
				webSocket.send(dataset_4);
			}
		};		
	
	
}
function getUpdateProduct(link,code,code_seller,name,key1,key2,key3,key4,priority){

	window.open("/rankpage/update_pop.jsp?link="+link+"&code="+code+"&code_seller="+code_seller+"&product_name="+name+"&key_1="+key1+"&key_2="+key2+"&key_3="+key3+"&key_4="+key4+"&priority="+priority,"상품정보 수정하기","width=380,height=400");
}


function getDeleteProduct(idx){
	if(confirm("해당 상품을 목록에서 삭제하시겠습니까 ?")){
		location.href="/ProductServlet?key=product_delete&idx="+idx;
	}
}

function getInsertProduct(){
	window.open("/rankpage/insert_popup.jsp","상품추가","width=380,height=400");
}
function rest(){
	location.href="/RankServlet?key=product_db";
}

