<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="finish_pop()">
<script>
function finish_pop(){
	opener.alert("수정되었습니다.");
	opener.location.reload();
	window.close();
}
</script>
</body>
</html>