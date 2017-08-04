<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单查看</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link type="text/css" rel="stylesheet" href="css/order_frame.css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
	<div class="warp">
		<div class="warp_form">

			<div class="order_status">
				<%@include file="orderState.jsp"%>

				<div class="order_place_time">
					<p id="orderPlaceTime">发起退款时间：${order.finishTime}</p>
				</div>

				<div class="order_waited_pay_time">
					<input type="hidden" id="placeTime" value="${time.time}" />
					<p id="orderWaitedPayTime">
						已经等待：<span id="hours" style="color: red">${time.hours}</span>时<span
							style="color: red" id="minutes">${time.minutes}</span>分<span
							id="seconds" style="color: red">${time.seconds}</span>秒
					</p>
				</div>
			</div>

			<%@include file="orderDetail.jsp"%>
			<div class="cancel_order">
				<button id="cancelBtn" class="button_w_h">撤销取消</button>
			</div>
		</div>

		<div class="cancel_sure_box">
			<div class="cancel_sure">
				<div class="icon_btn">
					<i class="iconfont fa-warning">&#xe698;</i>
				</div>
				<p>确定撤销取消？</p>
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
<script src="js/cancel.js"></script>
<script type="text/javascript" src="js/time.js"></script>
</html>
