<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

	<head>
		<meta charset="UTF-8">
		<title>个人信息</title>
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link rel="stylesheet" href="css/normalize.css" />
		<link type="text/css" rel="stylesheet" href="css/personal_info.css" />
		<link rel="stylesheet" href="css/m_certification.css" />
		<link rel="stylesheet" href="css/base.css" />
	</head>

	<body>
		<div class="warp">
			<div class="warp_form">
				
				<div class="user_portrait_box">
					<a id="user_portrait" href="###">
						<img src="${person.photo}" />
					</a>
				</div>
				<form action="verify.do" method="post">
				<div class="user_info_box">
					
					<div class="user_info_lists certification_lists">
						<label>真实姓名</label>
						<input id="user_address" name="realName" type="text" placeholder="请输入真实姓名" autocomplete="off"/>
					</div>
					<div class="user_info_lists certification_lists">
						<label>身份证号码</label>
						<input id="user_phone" name="idCard" type="phone" placeholder="请输入身份证号码" autocomplete="off"/>
					</div>
					<div class="user_info_lists certification_lists">
						<label>户口所在地</label>
						<input id="user_city" name="city" type="text" placeholder="请输所入处城市" autocomplete="off"/>
					</div>
				</div>
				<div class="table_info">
				    <input id="certification_btn" type="submit" value="确定"/>
				</div>
				</form>
			</div>
		</div>
				
	</body>

</html>