<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>订单查看</title>
<meta http-equiv="X-UA-Compatible" content="IE=9">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/font.css" />
<link type="text/css" rel="stylesheet" href="css/orders_detail.css" />
<link type="text/css" rel="stylesheet" href="css/order_detail.css" />
<link type="text/css" rel="stylesheet" href="css/info_bottom.css" />
</head>
<body style="background: none;">
	<div class="warp" style="background: none;">
		<div class="warp_form" style="max-width: 700px;">

			<c:forEach var="order" items="${orders}">
				<%@include file="orderInfo.jsp"%>
			</c:forEach>
			<div style="height: 25px;"></div>
		</div>

	</div>
	<div class="bottom" style="font-size: 14px; color: black;">
		<div id="person_button" class="middle_bt inline_block "
			style="border-right: 1px solid #BBB;">
			<div class="icon_button">
				<i class="iconfont_bottom">&#xe61e;</i>
			</div>
			主页
		</div>
		<div id="orders_button" class="middle_bt inline_block">
			<div class="icon_button">
				<i class="iconfont_bottom" id="order_direction_icon">&#xe6e8;</i>
			</div>
			<span id="order_direction">订单底部</span>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {

		$("#person_button").click(function() {
			window.location.href = "home.xhtm";

		});
		$("#orders_button").click(function() {
			//window.location.href = "showorders.xhtm";
			if ($("#order_direction").text() == "订单底部") {

				$("#order_direction").text("订单顶部");
				$("#order_direction_icon").html("&#xe6e9;");
				var _windowHeight;
				if ($(document).height() == $(window).height()) {
					_windowHeight = document.body.clientHeight;
				} else {
					_windowHeight = $(window).height();
				}
              
				var height = $(document).height() - _windowHeight;

				$('html,body').animate({
					scrollTop : height + "px"
				}, 800);

			} else {
				$("#order_direction").text("订单底部");
				$("#order_direction_icon").html("&#xe6e8;");
				$('html,body').animate({
					scrollTop : "0px"
				}, 800);
			}

		});
	});
//-->
</script>
</html>

