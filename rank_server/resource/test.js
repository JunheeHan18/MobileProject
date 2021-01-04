/**
 * 
 */

var xMLHttpRequest = new XMLHttpRequest();

var test_text;
test_text= document.getElementById('text_value').value;
var text = "{ 'data' : 'a','b'} ";

(function(){
	
	
	
})

function getready (){
		test_text= document.getElementById('text_value').value;
	  xMLHttpRequest.open("Get","http://localhost:8081/rank/RankServlet?key=test&text=ㅁ"+text,true);
	  xMLHttpRequest.onreadystatechange= function(){
		  if(xMLHttpRequest.readyState == 4 && xMLHttpRequest.status == 200){
			  ///var strline = eval('('+xMLHttpRequest.responseText+')');
	
			  alert(xMLHttpRequest.responseText);
			  //alert();
		  }
		  
	  };
	  xMLHttpRequest.send(null);
	
}
