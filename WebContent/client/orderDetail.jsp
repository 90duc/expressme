<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/font.css" />
<link type="text/css" rel="stylesheet" href="css/orders_detail.css" />
<link type="text/css" rel="stylesheet" href="css/order_detail.css" />
<link type="text/css" rel="stylesheet" href="css/info_bottom.css" />

<div class="good_detail good_preview">
	<table class="normal_font" style="width: 100%">
		<tr>
			<td style="width: 45"><div class="good_preview_photo ">
					<img src="${order.goodsPhoto}" id="goodsPhotos" />
				</div></td>
			<td class="good_preview_price goods_name" style="min-width: 65px">${order.goodsName}</td>
			<td><div class="good_preview_price right_float">
					<span class="goods_price">￥${order.totalMoney}&nbsp;</span>(包含小费${order.fee}元)
				</div></td>
		</tr>
	</table>
	<c:if test="${order.hasGoodsVolume()}">
		<div class="good_preview_volume">
			<div class="icon_button">
				<i class="iconfont fa-volume"> &#xe837; </i>
			</div>
			<span id="goodVolume">体积：${order.goodsLength}x${order.goodsWidth}x${order.goodsHeight}厘米</span>
		</div>
	</c:if>
	<c:if test="${order.hasGoodsWeight()}">
		<div class="good_preview_weight">
			<div class="icon_button">
				<i class="iconfont fa-weight"> &#xe72b; </i>
			</div>
			<span>重量：${order.goodsWeight}KG</span>
		</div>
	</c:if>
	<c:if test="${order.hasGoodsValue()}">
		<div class="good_preview_weight">
			<div class="icon_button">
				<img width="20px" height="20px" style="vertical-align: text-bottom;"
					src="images/rmb.png" />
			</div>
			<span>价值：${order.goodsValue}元</span>
		</div>
	</c:if>
	<c:if test="${order.hasExpectTime()}">
		<table>
			<tr>
				<td style="width: 80px;"><div class="icon_button">
						<i class="iconfont_bottom"> &#xe633; </i>
					</div> <span >取件：</span></td>
				<td><div class="inline_block">${order.SETime}</div>
					<div class="inline_block">至${order.EETime}</div></td>
			</tr>
		</table>

	</c:if>
	<div class="good_preview_weight">
		<div class="icon_button">
			<i class="iconfont_bottom"> &#xe6fd; </i>
		</div>
		<span>运送：${order.deliveryType}</span>
	</div>
	<c:if test="${order.hasRemark()}">
		<div class="good_preview_weight">
			<table>
				<tr>
					<td style="width: 80px;"><div class="icon_button">
							<i class="iconfont_bottom"> &#xe62b; </i>
						</div> <span >备注：</span></td>
					<td>${order.remark}</td>
				</tr>
			</table>
		</div>
	</c:if>
</div>

<div class="order_logistics_info">
	<div class="auot_positon">
		<div class="order_logistics_info_icon send_icon ">
			<span>发</span>
		</div>
		<div class="margin_left_45">
			<div class="auot_positon">
				<p class="address_position">发货地址：</p>
				<span class="margin_left_80 inline_block shipper_address"
					id="startAddress">${order.startAddress}</span>
			</div>
			<p class="shipper_info" id="shipperInfo">发货人姓名：${order.shipper}</p>
			<p class="shipper_info" id="shipperInfo">联系方式：${order.shipperPhone}</p>
		</div>
	</div>
	<hr class="mim_h_border" />
	<div class="auot_positon">
		<div class="order_logistics_info_icon receive_icon">
			<span>收</span>
		</div>
		<div class="margin_left_45">
			<div class="auot_positon">
				<p class="address_position">发货地址：</p>
				<span class="margin_left_80 inline_block shipper_address"
					id="startAddress">${order.endAddress}</span>
			</div>
			<p class="receiver_info" id="receiverInfo">收货人姓名：${order.receiver}</p>
			<p class="receiver_info" id="receiverInfo">
				联系方式：<a id="orderCourierPhone" href="tel:${order.receiverPhone}">${order.receiverPhone}</a>
			</p>
		</div>
	</div>
</div>
