<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta charset="UTF-8">
<title>订单查看</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=yes">
<script type="text/javascript"
	src="js/jquery.js"></script>
<link type="text/css" rel="stylesheet" href="css/order_frame.css" />
</head>
<body>
	<div class="warp">
		<div class="warp_form">

			<div class="order_status">
				<%@include file="orderState.jsp"%>

				<div class="order_waited_pay_time">

					<p id="orderWaitedPayTime">
						关闭时间：<span style="color: red">${order.finishTime}</span>
					</p>

				</div>
			</div>

			<%@include file="orderDetail.jsp"%>


			<!-- 	<div class="evaluate_order">
				<span>去评价</span>
			</div>
			 -->
		</div>
	</div>
	<%@include file="bottom.jsp"%>
</body>

</html>