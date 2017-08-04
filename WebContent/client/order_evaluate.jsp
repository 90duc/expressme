<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta charset="UTF-8">
<title>订单查看</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=yes">
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/font.css" />
<link type="text/css" rel="stylesheet" href="css/orders_evaluate.css" />
<link type="text/css" rel="stylesheet" href="css/order_detail.css" />
<link type="text/css" rel="stylesheet" href="css/orders_detail.css" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js"></script>
<link href="css/star-rating.css" media="all" rel="stylesheet"
	type="text/css" />
<script src="js/star-rating.js" type="text/javascript"></script>
</head>
<body>

	<div class="warp">
		<div class="warp_form">
			<div class="order_info">
				<div class="good_info">
					<img src="${order.goodsPhoto}" id="goodsPhotos" /> <span>货物名称：${order.goodsName}</span>
				</div>

				<table style="color: black;">
					<tr>
						<td style="width: 140px"><span
							class="order_logistics_info_icon send_icon">发</span> 发货地址：</td>
						<td><span class="shipper_address">${order.startAddress}</span></td>
					</tr>
					<tr>
						<td style="width: 140px"><span
							class="order_logistics_info_icon receive_icon">收</span> 收货地址：</td>
						<td><span class="shipper_address">${order.endAddress}</span></td>

					</tr>
				</table>

			</div>

			<div class="evaluate_grade">
				<span>服务评分</span> <span class="grade_stars"> <input
					id="input-21a" value="0" type="number" class="rating" min=0 max=10
					step=0.1 data-size="xs">
				</span>
			</div>

			<div class="evaluate_txt">
				<textarea class="evaluate_txt_area" id="evaluateTxt"
					placeholder="物流速度，配送服务是否满足您的期待？请说说您的体验心得，分享给其他客户吧~"></textarea>
			</div>
		</div>

		<div class="evaluate_button">
			<button id="evaluateBtn" value="${order.ID}">评价</button>
		</div>
	</div>
	<script type="text/javascript">
		if (document.body.clientWidth < 485) {
			document.getElementById("input-21a")
					.setAttribute("data-size", "xm");
		}

		$(document).ready(function() {
			
			$(".rating-kv").rating();
			$("#evaluateBtn").click(function() {
				var text = $("#evaluateTxt").val();
				var range = $("#input-21a").val();
				if (text.length >=2) {
					
					window.location.href="evaluate.do?id="+$("#evaluateBtn").val()+"&text="+text+"&range="+range;
				}

			});

		});
	</script>
</body>
</html>
