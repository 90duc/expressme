<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title>个人信息</title>
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="stylesheet" href="css/normalize.css" />
		<link type="text/css" rel="stylesheet" href="css/personal_info.css" />
	</head>

	<body>
		<div class="warp">
			<div class="warp_form">
				
				<div class="user_portrait_box">
					<a id="user_portrait" href="###">
						<img src="images/portrait_example2.jpg" />
					</a>
				</div>
				
				<div class="user_info_box">
					<div class="user_info_lists">
						<label>姓名</label>
						<input id="user_name" name="user_name" type="text" value="梁海杰"/>
					</div>
					<div class="user_info_lists">
						<label>城市</label>
						<input id="user_city" name="user_name" type="text" value="广州"/>
					</div>
					<div class="user_info_lists">
						<label>地址</label>
						<input id="user_address" name="user_name" type="text" value="天河区五山街道华南农业大学"/>
					</div>
					<div class="user_info_lists">
						<label>手机号码</label>
						<input id="user_phone" name="user_name" type="phone" value="1234567890"/>
					</div>
				</div>
				
				<button id="edit_info_reset">重置</button> <button id="edit_info_save">保存</button>
				
			</div>
		</div>
		
		<script type="text/javascript" src="js/personal_info_edit.js" ></script>
	</body>

</html>