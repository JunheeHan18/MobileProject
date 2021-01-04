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

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.5/examples/dashboard/">

<!-- CSS -->
<link href="/rank/assets/dist/css/bootstrap.css" rel="stylesheet">

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
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link type="text/css" rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
<link type="text/css" rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid-theme.min.css" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3"
			href="/rankpage/index.html">조금특별한생각</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed"
			type="button" data-toggle="collapse" data-target="#sidebarMenu"
			aria-controls="sidebarMenu" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<input class="form-control form-control-white w-100" type="text"
			placeholder="keyword" aria-label="Search" id="keyword_value" required
			onkeyPress="if (event.keyCode==13){check_value();}"> <input
			class="form-control form-control-white w-100" type="text"
			placeholder="store" aria-label="Search" id="store_value" required
			onkeyPress="if (event.keyCode==13){check_value();}"> <input
			class="form-control form-control-white w-100" type="text"
			placeholder="URL" aria-label="Search" id="url_value" required
			onkeyPress="if (event.keyCode==13){check_value();}">
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
								<span data-feather="home"></span>광고 추적내역 조회 
						</a></li>
						<li class="nav-item"><a class="nav-link" href="/rankpage/add_rank.html"> <span
								data-feather="shopping-cart"></span> 광고상품 순위 추적
						</a></li>
						<li class="nav-item"><a class="nav-link active" href="/ProductServlet?key=product_db"> <span
								data-feather="users"></span> 상품목록 조회 <span class="sr-only">(current)</span>
						</a></li>
						
					</ul>

				</div>
			</nav>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

				<div class="btn-toolbar mb-2 mb-md-0">
					<div class="btn-group mr-2">
						<button type="button" class="btn btn-sm btn-outline-secondary">ClearAll</button>
						<button type="button" class="btn btn-sm btn-outline-secondary"
							onclick="location.href='/ProductServlet?key=product_db_doexcel'">Export</button>
							<button type="button" class="btn btn-sm btn-outline-secondary" onclick="getInsertProduct()">Add Product</button>
					</div>
					<button type="button"
						class="btn btn-sm btn-outline-secondary dropdown-toggle">
						<span data-feather="calendar"></span> This week
					</button>
				</div>
			</div>


			<h2>상품목록 관리 - 목록 개수 ${count }개</h2>
			<div class="table-responsive">
				<table class="table table-striped table-sm">
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
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${product }">
							<tr>
								<td id="${i.priority }">${i.priority }</td>
								<td id="${i.product_code }">${i.product_code }</td>
								<td id="${i.product_link }"><a href="${i.product_link }" target="_blank">이동</</a></td>
								<td id="${i.product_code_seller }">${i.product_code_seller }</td>
								<td id="${i.product_name }"><a onclick="getCrawlingAll('${i.keyword_first }','${i.keyword_second }','${i.keyword_third }','${i.keyword_four }','${i.product_link }','${i.idx }')">${i.product_name }</a></td>
								<td id="${i.product_code }${i.keyword_first }"><a class="shop_link" onclick="getCrawling('${i.keyword_first }','${i.product_link }','first','${i.idx }')">${i.keyword_first }</a>(${i.keyword_first_rank }위)</td>
 								<td id="${i.product_code }${i.keyword_second }"><a class="shop_link" onclick="getCrawling('${i.keyword_second }','${i.product_link }','second','${i.idx }')">${i.keyword_second }</a>(${i.keyword_second_rank }위)</td>
								<td id="${i.product_code }${i.keyword_third }"><a class="shop_link" onclick="getCrawling('${i.keyword_third }','${i.product_link }','third','${i.idx }')">${i.keyword_third }</a>(${i.keyword_third_rank }위)</td>
								<td id="${i.product_code }${i.keyword_four }"><a class="shop_link" onclick="getCrawling('${i.keyword_four }','${i.product_link }','four','${i.idx }')">${i.keyword_four }</a>(${i.keyword_four_rank }위)</td>
								<td><a class="shop_link" onclick='getUpdateProduct("${i.product_link }","${i.product_code }","${i.product_code_seller }","${i.product_name }","${i.keyword_first }","${i.keyword_second }","${i.keyword_third }","${i.keyword_four }","${i.priority}")'>수정</a></td>
								<td><a class="shop_link" onclick='getDeleteProduct("${i.idx }")'>삭제</a></td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			</main>
		</div>
	</div>

	<script>
		window.jQuery
				|| document
						.write('<script src="/rank/assets/js/vendor/jquery.slim.min.js"><\/script>')
	</script>

	<script src="../assets/dist/js/bootstrap.bundle.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
	<script src="/rank/resource/socket_product_list.js"></script>
</body>
</html>
