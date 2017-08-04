<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="UTF-8">
<title>订单查看</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=yes">
<link type="text/css" rel="stylesheet" href="css/order_frame.css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>
	<div class="warp">
		<div class="warp_form">

			<div class="order_status">
				<%@include file="orderState.jsp"%>
				<hr />
				<%@include file="orderDriver.jsp"%>
			</div>
			<%@include file="orderDetail.jsp"%>

			<c:if test="${!order.hasEvaluated() }">
				<div class="evaluate_order">
					<button id="evaluateBtn" class="button_w_h" value="${order.ID}">去评价</button>
				</div>
			</c:if>
		</div>
	</div>
	<%@include file="bottom.jsp"%>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {

				$("#evaluateBtn").click(
						function() {

							window.location.href = "evaluate.xhtm?id="
									+ $("#evaluateBtn").val();
						});

			});
</script>
</html>