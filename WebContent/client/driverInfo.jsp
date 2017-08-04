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
<link type="text/css" rel="stylesheet"
	href="css/order_taking_wait_pay.css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>

	<div class="warp">
		<div class="warp_form">
			<div class="back_img">
				<img src="${driver.photo}" />
			</div>
			<div class="iframe">
				<div class="float_img">
					<img class="image_photo" src="${driver.photo}" />
				</div>
				<div class="center_text">${driver.userName}</div>

				<div class="table_info">
					<table style="">
						<tr>
							<td class="table_title_cs">司机ID号</td>
							<td>${driver.ID}</td>
						</tr>
						<tr>
							<td class="table_title_cs">真实姓名</td>
							<td>${driver.verify.realName}<span>
									${driver.verifyState}</span></td>
						</tr>
						<tr>
							<td class="table_title_cs">联系方式</td>
							<td><a href="tel:${driver.phone}">${driver.phone}</a></td>
						</tr>
						<tr>
							<td class="table_title_cs">信用等级</td>
							<td>${driver.avgRang}分</td>
						</tr>
						<tr>
							<td class="table_title_cs">地区街道</td>
							<td>${driver.city}&nbsp;${driver.street}</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="iframe" style="padding-left: 5%">
				<img class="image_icon" src="${driver.photo}" />&nbsp;&nbsp;&nbsp;&nbsp;订单评价

			</div>
			<c:forEach var="evaluate" items="${evaluates}">
				<div class="iframe">
					<div class="e_user">
						<img class="min_icon" src="${evaluate.clientPhoto}" />&nbsp;&nbsp;&nbsp;&nbsp;${evaluate.clientName}
					</div>
					<div class="padding_text white_text">${evaluate.time}
						&nbsp;&nbsp;&nbsp;&nbsp;物品类型：${evaluate.goodsName}</div>
					<div class="padding_text">${evaluate.text}</div>
				</div>
			</c:forEach>
		</div>


	</div>


</body>

</html>