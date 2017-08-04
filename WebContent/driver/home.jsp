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
<link type="text/css" rel="stylesheet" href="css/info_bottom.css" />
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
			<form action="saveaddr.do" method="post">
				<div class="iframe_input">
					<div class="address_field border_bottom">
						<div class="left_move_50 inline_block border_bottom">
							<span class="point_img inline_block start_color border_bottom"></span>
						</div>
						<input type="text" id="startAddress" name="startAddress"
							class="input_text" placeholder="正在为您定位" title="起始地址" value="广州" />
						<input type="hidden" name="startPX" value="120" /> <input
							type="hidden" name="startPY" value="20" />
					</div>

					<div class="address_field">
						<span class="left_move_50 point_img inline_block end_color"></span>
						<input type="text" id="endAddress" name="endAddress"
							class="input_text" style="top: 55px;" placeholder="目的地址"
							title="目的地址" value="深圳" /> <input type="hidden" name="endPX"
							value="120" /> <input type="hidden" name="endPY" value="20.5" />
					</div>
				</div>
				<div class="next_button">
					<input type="submit" id="next_button" value="下一步" />
				</div>
			</form>

			<div class="bottom">
				<div id="grab_button" class="middle_bt inline_block "
					style="border-right: 1px solid #BBB;width:33%;">
					<div class="icon_button">
						<i class="iconfont_bottom" >&#xe6fd;</i>
					</div>
					抢单
				</div>
				<div id="orders_button" class="middle_bt inline_block" style="width:33%;">
					<div class="icon_button">
						<i class="iconfont_bottom">&#xe614;</i>
					</div>
					订单
				</div>
				<div id="person_button" class="middle_bt inline_block "
					style="border-left: 1px solid #BBB;width:33%;">
					<div class="icon_button">
						<i class="iconfont_bottom">&#xe60d;</i>
					</div>
					个人
				</div>
			</div>
		</div>
	</div>

	<div class="pop_box" id="pop_box">
		<div class="menu_box" id="menu_box">
			<%@include file="menu.jsp"%>
		</div>
	</div>
	<div id="address_tip" class="tip_popup"></div>

</body>
<script type="text/javascript" src="js/home.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
			
			});
</script>
</html>