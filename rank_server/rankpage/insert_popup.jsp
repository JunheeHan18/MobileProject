<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 추가</title>
</head>
<body>
<form action="/ProductServlet?key=insert_product" method="post">
<table border=1 width=350px align="center">
<tr>
<th width=auto>우선순위&nbsp:&nbsp </th>
<td width=200px>&nbsp<input type="text" name="priority"></td>
</tr>
<tr>
<th width=auto>상품코드&nbsp:&nbsp </th>
<td width=200px>&nbsp<input type="text"  name="code"></td>
</tr>
<tr>
<th width=auto>상품링크&nbsp:&nbsp </th>
<td width=200px>&nbsp<input type="text" name="link"></td>
</tr>
<tr>
<th width=auto>판매자코드&nbsp:&nbsp </th>
<td width=200px>&nbsp<input type="text" name="code_seller"></td>
</tr>
<tr>
<th width=auto>상품명&nbsp:&nbsp </th>
<td width=200px>&nbsp<input type="text"  name="product_name"></td>
</tr>
<tr>
<th width=auto>키워드1&nbsp:&nbsp </th>
<td width=200px>&nbsp<input type="text"  name="key_1"></td>
</tr>
<tr>
<th width=auto>키워드2&nbsp:&nbsp </th>
<td width=200px>&nbsp<input type="text"  name="key_2"></td>
</tr>
<tr>
<th width=auto>키워드3&nbsp:&nbsp </th>
<td width=200px>&nbsp<input type="text"  name="key_3"></td>
</tr>
<tr>
<th width=auto>키워드4&nbsp:&nbsp </th>
<td width=200px>&nbsp<input type="text"  name="key_4"></td>
</tr>
<tr><td colspan=2 align="right"><input type="submit" value="보내기"><input type="reset" value="취소" onclick="finish_pop()"></td></tr>
</table>
</form>
<script>
function finish_pop(){
	window.close();
}
</script>
</body>
</html>