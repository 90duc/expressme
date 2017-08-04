<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>订单查看</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/font.css" />
<link type="text/css" rel="stylesheet" href="css/order_frame.css" />
<link type="text/css" rel="stylesheet" href="css/info_bottom.css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body style="background: #f8f8f8;">
	<div class="warp">
		<div class="warp_form">

			<div class="order_status">
				<div class="order_title">
					<span>${order.OSName}</span><span class="right_float">类型：<span
						class="red">${order.OSType}</span></span>
				</div>

				<div class="order_waited_pay_time">
					<input type="hidden" id="placeTime" value="${time.time}" />
					<div id="orderWaitedPayTime">
						剩余时间：<span id="hours" style="color: red">${time.hours}</span>时<span
							style="color: red" id="minutes">${time.minutes}</span>分<span
							id="seconds" style="color: red">${time.seconds}</span>秒后<span
							class="warning_color">自动关闭</span>
					</div>

				</div>
			</div>

			<%@include file="orderDetail.jsp"%>
			<div class="cancel_order">
				<input type="hidden" id="order_id" value="${order.ID}" /> <span
					id="cancelBtn">取消订单</span> <span id="payBtn">付款</span>
			</div>
		</div>
		<div class="cancel_sure_box">
			<div class="cancel_sure">
				<div class="icon_btn">
					<i class="iconfont fa-warning">&#xe698;</i>
				</div>
				<p>
					确定<span id="info_title">取消？</span>
				</p>
				<div class="sure_btns">
					<button id="cancel_no">否</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="cancel_yes"
						value="cancelorder.do?id=${order.ID}&state=${order.orderStateE}">是</button>
				</div>
			</div>
		</div>
	</div>
<%@include file="bottom.jsp"%>
</body>
<script src="js/wait_pay.js"></script>
<script type="text/javascript">
	
	$("#person_button").click(function() {
		window.location.href = "home.xhtm";

	});
	$("#orders_button").click(function() {
		window.location.href = "showorders.xhtm";		
	});

	$(document).ready(function() {
		var today = new Date(parseInt($("#placeTime").val()));
		var time = setInterval(remaindTime, 1000);
		function remaindTime() {
			today = new Date(today.getTime() - 1000);
			//alert(today);
			$("#hours").html(today.getUTCHours());
			$("#minutes").html(today.getUTCMinutes());
			$("#seconds").html(today.getUTCSeconds());
			//$("#cssa").html(today.getTime());
			if (today.getTime() < 1000) {
				clearInterval(time);
				location.reload();
			}
		}

	});
</script>
</html>
