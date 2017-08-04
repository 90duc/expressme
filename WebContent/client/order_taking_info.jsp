<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>订单填写</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=yes">
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/default.css" />
<link type="text/css" rel="stylesheet"
	href="css/order_taking_orders_info.css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
	<div class="warp">
		<div class="warp_form" style="max-width: 700px;">
			<form action="finishorder.do" method="post">

				<div class="row1 client_info_box">
					<div class="row1-1 shipper_info_box">
						<div class="col1 tips_icon shipper_icon">
							<span>发</span>
						</div>
						<div class="col2 shipper_info">
							<input class="input_box checked_input" type="text" name="shipper"
								placeholder="发货人姓名" /> <span class="input_bar"></span> <input
								class="input_box checked_input" type="tel" name="shipperPhone"
								placeholder="发货人联系方式" title="请输入正确的手机号码" />
						</div>
					</div>
					<div class="row1-2 receiver_info_box">
						<div class="col1 tips_icon  receiver_icon">
							<span>收</span>
						</div>
						<div class="col2 receiver_info">
							<input class="input_box checked_input" type="text"
								name="receiver" placeholder="收货人姓名"> <span
								class="input_bar"></span> <input class="input_box checked_input"
								type="tel" name="receiverPhone" placeholder="收货人联系方式">
						</div>
					</div>
				</div>

				<div class="row2 budget_box">
					<p class="budget">
						价格：￥<span id="price">${price}</span>&nbsp;&nbsp;(&nbsp;${distance}公里/预计${time}分钟送达&nbsp;)
					</p>
				</div>

				<div class="row3 fee_box">
					<label class="label_title">加价：</label>
					<div class="col2">
						<input id="fee" name="fee" placeholder="给消费物品送的更快"
							onkeyup="value=inputNumber(value) "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
							ID="Text2" NAME="Text2"><span>元</span>
					</div>

				</div>

				<div class="row4 remark_box">
					<div class="label_title">备注：</div>
					<textarea name="remark" id="remark" cols="29" rows="7"
						placeholder="添加物品描述或其他要求"></textarea>
				</div>

				<div class="row5 amount_box">
					<span class="amount"> 合计：<span>￥</span><span
						id="total_money">${price}</span>
					</span> <input type="submit" id="placeOrder" name="placeOrder"
						value="提交订单" />
				</div>

			</form>
		</div>
	</div>
	<div id="address_tip" class="tip_popup"></div>

</body>
<script>
	function inputNumber(value) {
		if (value.search(/^(\d*|\d+\.\d*)$/) == -1) {

			value = value.substring(0, value.length - 1);
		}
		return value;
	}
	$(document).ready(function() {

		$('#fee').bind('input propertychange', function() {
			var price = $('#price').html();
			price = parseFloat(price);
			var add = $('#fee').val();
			if (add.length == 0)
				add = 0;
			else
				add = parseFloat(add);
			price = price + add;
			$('#total_money').html(price);
		});

		var lists = document.getElementsByClassName("checked_input");
		$("#placeOrder").click(function() {

			for ( var i = 0; i < lists.length; i++) {
				var value = lists[i].value;
				if (lists[i].type == "tel") {
					if (!checkPhoneFormat(value)) {
						showTip(lists[i].placeholder + "不合法");
						return false;
					}

				} else if (value.length < 1) {
					showTip(lists[i].placeholder + "不能为空");
					return false;
				}
			}

		});
		function showTip(text) {

			$("#address_tip").text(text);
			$("#address_tip").fadeIn(200);
			$("#address_tip").delay(1000).fadeOut(100);
		}
		function checkPhoneFormat(phoneStr) {

			var reg = /^1[34578]\d{9}$/;

			return reg.test(phoneStr);
		}
	});
</script>
</html>