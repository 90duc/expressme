<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/font.css" />
<link type="text/css" rel="stylesheet" href="css/orders_detail.css" />
<link type="text/css" rel="stylesheet" href="css/order_detail.css" />
<div class="order_courier_id">
接单司机：<a title="点击查看司机" href="driverinfo.xhtm?id=${order.driverBean.ID}">${order.driverBean.ID}</a>
</div>

<div class="order_courier">
	<div class="order_courier_info">
		姓名&nbsp;:&nbsp;<span id="orderCourierName">${order.driverBean.userName}</span>
		联系方式&nbsp;:&nbsp;<a id="orderCourierPhone"
			href="tel:${order.driverBean.phone}">${order.driverBean.phone}</a>
	</div>
</div>