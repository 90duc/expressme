<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta charset="UTF-8">
<title>个人信息</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="css/normalize.css" />
<link type="text/css" rel="stylesheet" href="css/personal_info.css" />
<link rel="stylesheet" href="css/m_certification.css" />
<link rel="stylesheet" href="css/base.css" />
</head>

<body>
	<div class="warp">
		<div class="warp_form">

			<div class="user_portrait_box">
				<a id="user_portrait" href="###"> <img src="${person.photo}" />
				</a>
			</div>
			<div class="table_info">

				<table rules=rows class="table_field">
					<tr class="table_row">
						<td class="table_cell_title">真实姓名</td>
						<td>${verify.realName}</td>
					</tr>
					<tr class="table_row">
						<td class="table_cell_title">身份证号码</td>
						<td>${verify.cardID}</td>
					</tr>
					<tr class="table_row">
						<td class="table_cell_title">户口所在地</td>
						<td>${verify.location}</td>
					</tr>
				</table>
				<div class="table_info" style="padding-top:10vh;">
					<div>验证状态：${verify.verifyState.value}</div>
				</div>
			</div>

		</div>
	</div>

</body>

</html>