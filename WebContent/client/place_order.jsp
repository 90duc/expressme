<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="UTF-8">
<title>主页</title>
<meta http-equiv="X-UA-Compatible" content="IE=11">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=yes">
<script type="text/javascript" src="js/jquery.js"></script>
<link type="text/css" rel="stylesheet" href="css/default.css" />
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/home.css" />
<link type="text/css" rel="stylesheet" href="css/place_order.css" />
<link type="text/css" rel="stylesheet" href="css/info_bottom.css" />

<script src="timepicker/mobiscroll_002.js" type="text/javascript"></script>
<script src="timepicker/mobiscroll_004.js" type="text/javascript"></script>
<link href="timepicker/mobiscroll_002.css" rel="stylesheet"
	type="text/css">
<link href="timepicker/mobiscroll.css" rel="stylesheet" type="text/css">
<script src="timepicker/mobiscroll.js" type="text/javascript"></script>
<script src="timepicker/mobiscroll_003.js" type="text/javascript"></script>
<script src="timepicker/mobiscroll_005.js" type="text/javascript"></script>
<link href="timepicker/mobiscroll_003.css" rel="stylesheet"
	type="text/css">
</head>

<body style="height: auto;">
	<div class="back_img">
		<img src="images/static_map.png" />
	</div>
	<div class="iframe_title">
		<div class="user_icon">
			<img id="home_button" src="images/user_button.png" /> <span
				id="login_button"
				class="right_float vertical_align_float cursor_hand">登录</span>
		</div>
	</div>
	<div class="warp_home">
		<div class="warp_home_form">
			<form action="placeorder.do" method="post">
				<div class="iframe_input">
					<div class="address_field border_bottom">
						<div class="left_move_50 inline_block border_bottom">
							<span class="point_img inline_block start_color border_bottom"></span>
						</div>
						<input type="text" class="input_text" disabled="disabled"
							title="起始地址" value="${order.startAddress}" />
					</div>

					<div class="address_field">
						<span class="left_move_50 point_img inline_block end_color"></span>
						<input type="text" class="input_text" disabled="disabled"
							title="目的地址" style="top: 55px;" value="${order.endAddress}">
					</div>
				</div>

				<div class="iframe_info" style="border-bottom: none;"
					id="good_info_detail" data-goods="${order.hasGoods()}">

					<span class="vertical_align">物品信息</span> <span id="goods_info"
						class="right_img cursor_hand"> <img src="images/right.png" />
					</span>
				</div>

				<div class="iframe_info"
					style="border-bottom: none; vertical-align: middle;">
					<table style="color: #101010;">
						<tr>
							<td style="width: 110px;">取件时间</td>
							<td><input placeholder="期待起始时间" title="期待起始时间"
								class="time_input" readonly="readonly" name="SETime" id="SETime"
								type="text">
								 <input placeholder="期待截止时间" title="期待截止时间"
								class="time_input" readonly="readonly" name="EETime" id="EETime"
								type="text"></td>
						</tr>
					</table>
				</div>

				<div class="iframe_info" style="margin-bottom: 20px;">

					<img width="20px" style="vertical-align: middle;"
						src="images/rmb.png" /> <span class="vertical_align"
						style="font-size: 17px; vertical-align: middle;">${price}&nbsp;&nbsp;(&nbsp;${distance}公里/预计${time}分钟&nbsp;)</span>

				</div>

				<div class="next_button">
					<input type="submit" id="next_button" value="下一步" />
				</div>
			</form>
		</div>
	</div>

	<div class="pop_box" id="pop_box">
		<div class="menu_box" id="menu_box">
			<%@include file="menu.jsp"%>
		</div>
	</div>
	<div id="address_tip" class="tip_popup">物品信息未填写</div>
</body>
<script type="text/javascript" src="js/home.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#goods_info").click(function() {
			window.location.href = "goods.xhtm";
		});

		$("#next_button").click(function() {
			if ($("#good_info_detail").attr("data-goods") != "true") {
				$("#address_tip").fadeIn(200);
				$("#address_tip").delay(1000).fadeOut(100);
				return false;
			}

		});

		function timePicker() {
			var currYear = (new Date()).getFullYear();
			var opt = {};
			opt.datetime = {
				preset : 'datetime'
			};
			opt.defaultValue = {
				theme : 'android-ics', //皮肤样式
				display : 'modal', //显示方式 
				mode : 'scroller', //日期选择模式
				dateFormat : 'yyyy-mm-dd',
				lang : 'zh',
				showNow : true,
				nowText : "今天",
				startYear : currYear - 1, //开始年份
				endYear : currYear + 1
			//结束年份
			};

			var optDateTime = $.extend(opt['datetime'], opt['defaultValue']);
			//var optTime = $.extend(opt['time'], opt['default']);
			$("#SETime").mobiscroll(optDateTime).datetime(optDateTime);
			$("#EETime").mobiscroll(optDateTime).datetime(optDateTime);

		}

		timePicker();
	});
</script>
</html>