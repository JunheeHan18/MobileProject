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
	response.setHeader("Content-Disposition", "inline; filename=searchlist.xls");
%>
<title>엑셀다운</title>
</head>

<body>
	<table border="1">
		<thead>
			<tr>
				<th>구분</th>
				<th>키워드</th>
				<th>상점명</th>
				<th>상품코드</th>
				<th>상품명</th>
				<th>리뷰건수</th>
				<th>구매건수</th>
				<th>찜수</th>
				<th>등록일</th>
				<th>조회일</th>
				<th>페이지번호</th>
				<th>순위</th>
				<th>최저가여부</th>
				<th>쇼핑몰</th>
			</tr>
		</thead>
		<c:forEach var="i" items="${rank}" varStatus="status">
			<tr>
				<td>${i.idx }</td>
				<td>${i.keyword }</td>
				<td>${i.store }</td>
				<td>${i.product_code }</td>
				<td>>${i.title }</td>
				<td>${i.review }</td>
				<td>${i.purchase }</td>
				<td>${i.zzim }</td>
				<td>${i.regist_date }</td>
				<td>${i.search_date }</td>
				<td>${i.page_num }</td>
				<td>${i.rank }</td>
				<td>${i.cheap_bool }</td>
				<td><a href="${i.page_link }" target="_blank">이동</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>