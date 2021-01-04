<%@ page language="java"
	contentType="application/vnd.ms-excel; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7;IE=8;IE=9" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="imagetoolbar" content="no" />
<meta name="robots" content="index,follow" />
<%
	response.setHeader("Content-Type", "application/vnd.ms-xls");
	response.setHeader("Content-Disposition", "inline; filename=product_list.xls");
%>
<title>엑셀다운</title>
</head>

<body>
	<table border="1">
					<thead>
						<tr>
							<th>우선순위</th>
							<th>상품코드</th>
							<th>상품링크</th>
							<th>판매자코드</th>
							<th>상품명</th>
							<th>키워드1</th>
							<th>키워드2</th>
							<th>키워드3</th>
							<th>키워드4</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${product }">
							<tr>
								<td>${i.priority }</td>
								<td>${i.product_code }</td>
								<td><a href="${i.product_link }" target="_blank">이동</</a></td>
								<td>${i.product_code_seller }</td>
								<td>${i.product_name }</td>
								<td>${i.keyword_first }(${i.keyword_first_rank }위)</td>
 								<td>${i.keyword_second }(${i.keyword_second_rank }위)</td>
								<td>${i.keyword_third }(${i.keyword_third_rank }위)</td>
								<td>${i.keyword_four }(${i.keyword_four_rank }위)</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
</body>
</html>