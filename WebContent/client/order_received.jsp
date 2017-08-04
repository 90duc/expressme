<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>订单查看</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=yes">
<link type="text/css" rel="stylesheet"
	href="css/order_taking_wait_pay.css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>
	<div class="warp">
		<div class="warp_form">

			<div class="order_status">
				<div class="order_title">
					<p>${order.OSName}</p>
					<a class="icon_button icon_button_navigation"> <i
						class="iconfont fa-navigation"> &#xe65b; </i>
					</a><span class="order_finished_time" id="orderFinishedTime">验证码:${order.verifyCode}</span>
				</div>
				<div class="order_place_time">
					<p id="orderPlaceTime">接单时间&nbsp;:${order.receiveTime}</p>
				</div>
				<hr />
				<%@include file="orderDriver.jsp"%>
			</div>

			<%@include file="orderDetail.jsp"%>
			<div class="cancel_order">
				<button id="cancelBtn" class="button_w_h">取消订单</button>
			</div>
		</div>

		<div class="cancel_sure_box">
			<div class="cancel_sure">
				<div class="icon_btn">
					<i class="iconfont fa-warning">&#xe698;</i>
				</div>
				<p>确定取消？</p>
				<div class="sure_btns">
					<button id="cancel_no">否</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="cancel_yes"
						value="cancelorder.do?id=${order.ID}&state=${order.orderStateE}">是</button>
				</div>
			</div>
		</div>
	</div>

	<script src="js/cancel.js">
		
	</script>
</body>

</html>