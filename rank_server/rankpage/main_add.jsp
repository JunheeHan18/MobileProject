<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="org.json.simple.JSONObject"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v4.0.1">
<title>조금특별한생각</title>
<%
	String rank_date = request.getParameter("data");
%>
<link rel="canonical"
	href="https://getbootstrap.com/docs/4.5/examples/dashboard/">

<!-- CSS -->
<link href="/rank/assets/dist/css/bootstrap.css" rel="stylesheet">
<link href="/rank/resource/font.css" rel="stylesheet">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="/rank/resource/dashboard.css" rel="stylesheet">
</head>
<body>
	<script src="/rank/resource/searchvalue_add.js"></script>
	<script src="/rank/resource/deletecheck.js"></script>
	
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="/rankpage/index.html">조금특별한생각</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed"
			type="button" data-toggle="collapse" data-target="#sidebarMenu"
			aria-controls="sidebarMenu" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<input class="form-control form-control-white w-100" type="text"
			placeholder="store" aria-label="Search" id="store_value" required onkeyPress="if (event.keyCode==13){check_value();}" value="${seval }">
		<input class="form-control form-control-white w-100" type="text"
			placeholder="keyword" aria-label="Search" id="keyword_value" required onkeyPress="if (event.keyCode==13){check_value();}" value="${value }">
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap">
				<!-- <a class="nav-link" href="/RankServlet?key=search">Search</a> -->
				<a class="nav-link" onclick="check_value()">search</a>
			</li>
		</ul>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
				<div class="sidebar-sticky pt-3">
				<h6
						class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
						<span>rank reports</span> <a
							class="d-flex align-items-center text-muted" href="#"
							aria-label="Add a new report"> <span
							data-feather="plus-circle"></span>
						</a>
					</h6>
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link" href="/rankpage/index.html">
								<span data-feather="home"></span>추적내역 조회
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/rankpage/product_rank.html"> <span
								data-feather="file"></span> 상품순위 추적
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/rankpage/index_add.html">
								<span data-feather="home"></span>광고 추적내역 조회 <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link active" href="/rankpage/add_rank.html"> <span
								data-feather="shopping-cart"></span> 광고상품 순위 추적
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/ProductServlet?key=product_db"> <span
								data-feather="users"></span> 상품목록 조회
						</a></li>
						
					</ul>

				</div>
			</nav>
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

				<div class="btn-toolbar mb-2 mb-md-0">
					<div class="btn-group mr-2">
						<button type="button" class="btn btn-sm btn-outline-secondary" onclick="deleteAllToAdd()">Clear All</button>
						<button type="button" class="btn btn-sm btn-outline-secondary"
							onclick="location.href='/RankServlet?key=doexcel'">Export</button>
					</div>
					
						<input type="date" id="cld">
				</div>
			</div>


			<h2>광고상품 순위추적 - 목록 개수 ${count }개</h2>
			<div class="table-responsive">
				<table class="table table-striped table-sm">
					<thead>
						<tr>
							<th>구분</th>
							<th>키워드</th>
							<th>상품명</th>
							<th>페이지번호</th>
							<th>광고순위</th>
							<th>조회일</th>
							<th>쇼핑몰페이지</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${rank }">
							<tr>
								<td>${i.idx }</td>
								<td>${i.keyword }</td>
								<td>${i.title }</td>
								<td>${i.page_num }</td>
								<td>${i.add_rank }</td>
								<td>${i.search_date }</td>
								<td><a class="shop_link"
									href="${i.page_link }" target="_blank">이동</a></td>
									<td><a onclick="deleteCheckAdd('${i.idx}')">삭제</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="/rank/assets/js/vendor/jquery.slim.min.js"><\/script>')
	</script>
	<script src="../assets/dist/js/bootstrap.bundle.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
</body>
</html>
