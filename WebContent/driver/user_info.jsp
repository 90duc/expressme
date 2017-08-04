<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>订单查看</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=yes">
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/font.css" />
<link type="text/css" rel="stylesheet" href="css/orders_detail.css" />
<link type="text/css" rel="stylesheet" href="css/user_info.css" />
<link type="text/css" rel="stylesheet" href="css/order_frame.css" />
<script type="text/javascript" src="js/jquery.js"></script>

</head>

<body>

	<div class="warp">
		<div class="warp_form" style="max-width:700px;">
			<div class="back_img">
				<img src="${person.photo}" />
			</div>
			<div class="iframe">
				<div class="float_img">
					<img class="image_photo" src="${person.photo}" />
				</div>
				<div class="center_text">${person.userName}</div>

				<div class="table_info">
					<table style="">
						<tr>
							<td class="table_title_cs">用户ID号</td>
							<td>${person.ID}</td>
						</tr>
						<tr>
							<td class="table_title_cs">真实姓名</td>
							<td>${person.userName}<span> ${person.verifyState}</span></td>
						</tr>
						<tr>
							<td class="table_title_cs">联系方式</td>
							<td><a href="tel:${person.phone}">${person.phone}</a></td>
						</tr>
						<tr>
							<td class="table_title_cs">信用等级</td>
							<td>${person.avgRang}分</td>
						</tr>
						<tr>
							<td class="table_title_cs">地区街道</td>
							<td>${person.city}&nbsp;${person.street}</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="iframe" style="padding-left: 5%">
				<img class="image_icon" src="${person.photo}" />&nbsp;&nbsp;&nbsp;&nbsp;个人动态

			</div>
			
		</div>


	</div>


</body>

</html>