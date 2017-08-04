<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="good_detail">
	
		<div class="order_state_title border_tb">
			<span>${order.orderState}</span> <span class="right_float"><a
				href='orderinfo.xhtm?id=<c:out value="${order.ID}"/>'>订单详情</a></span>
		</div>

		<table class="padding_table normal_font">
			<tr>
				<td style="width: 120px"><span
					class="point_img inline_block start_color" style="margin-left: 10px"></span> 发货地址：</td>
				<td><span class="shipper_address">${order.startAddress}</span></td>
			</tr>
			
			<tr class="hr_border" >
				<td class="hr_border" style="width: 120px"><span
					class="point_img inline_block end_color"  style="margin-left: 10px"></span> 收货地址：</td>
				<td class="hr_border"><span class="shipper_address">${order.endAddress}</span></td>

			</tr>
		</table>


		<div class="padding_tb">
			<div class="inline_block mini_photo">
				<img src="${order.goodsPhoto}" />&nbsp;
			</div>
			<span class="inline_block text_word">物品：&nbsp;${order.goodsName}</span>
			<span class="inline_block right_float text_word">￥${order.totalMoney}</span>
		</div>

</div>


