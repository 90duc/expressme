<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>物品信息</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=yes">
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/default.css" />

<link rel="stylesheet" type=text/css href="css/font.css" />
<link type="text/css" rel="stylesheet"
	href="css/order_taking_goods_info.css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
	<div class="warp">
		<div class="warp_form">
			<form action="savegoods.do" method="post"
				enctype="multipart/form-data" name="goodsInfo">
				<div class="goods_basic_info">
					<div class="goods_name_box">
						<label class="col-md-2">物品类型</label> <input type="text"
							class="goods_name input_box col-md-8" id="goodsName"
							name="goodsName" placeholder="鲜花/水果/海鲜/其他"
							value="${order.goodsName}">
					</div>

					<div class="row2 clearfix">
						<div class="goods_value_box">
							<label>物品价值</label> <input type="number"
								class="goods_value input_box" name="goodsValue"
								value="${order.goodsValue}" /> 元
						</div>
						<div class="goods_weight_box">
							<label>物品重量</label> <input onkeyup="value=inputNumber(value)"
								class="goods_weight input_box" name="goodsWeight"
								value="${order.goodsWeight}" /> 千克
						</div>
					</div>

					<div class="row3 goods_volumn_box">
						<label>体积</label> <input onkeyup="value=inputNumber(value)"
							class="goods_length goods_volumn input_box" name="goodsLength"
							placeholder="长度" value="${order.goodsLength}" />* <input
							onkeyup="value=inputNumber(value)" class="goods_ goods_volumn input_box"
							name="goodsWidth" placeholder="宽度" value="${order.goodsWidth}" />*
						<input onkeyup="value=inputNumber(value)" class="goods_weight goods_volumn input_box"
							name="goodsHeight" placeholder="高度" value="${order.goodsHeight}" />
						<span class="unit">(厘米)</span>
					</div>
				</div>

				<div class="goods_photo_box" id="goods_photo_detail"
					data-photo="${order.hasPhoto()}">
					<label>上传照片：</label>
					<div class="upload_box">

						<div class="icon_button">
							<img id="preview_image" src="${order.goodsPhoto}"
								data-url="${order.goodsPhoto}" class="icon_button" />
						</div>
						<input class="goods_photo" type="file" name="goodsPhotoFile"
							id="goodsPhoto" />
					</div>

				</div>

				<div class="deliver_type_box">
					<p class="deliver_type_title">选择配送方式</p>
					<div class="radio_select_box">
						<label class="deliver_type"><input type="radio"
							checked="checked" name="deliverType">不限 </label>
						<div class="radio_skin"></div>
					</div>
					<div class="radio_select_box">
						<label class="deliver_type"><input type="radio"
							name="deliverType">轿车</label>
						<div class="radio_skin"></div>
					</div>
					<div class="radio_select_box">
						<label class="deliver_type"><input type="radio"
							name="deliverType">非机动车</label>
						<div class="radio_skin"></div>
					</div>
				</div>
				<div class="btn_box">
					<input type="submit" name="upload" id="upload" value="确定" />

				</div>

			</form>
		</div>
	</div>
	<div id="address_tip" class="tip_popup"></div>

</body>
<script>
	function inputNumber(value) {
		if (value.search(/^(\d*|\d+\.\d*)$/) == -1) {

			value = value.substring(0, value.length - 1);
		}
		return value;
	}
	$(document)
			.ready(
					function() {

						//添加默认radio选中效果
						$('input:radio[name="deliverType"]:checked').parent()
								.siblings('.radio_skin').addClass("active");
						$(".radio_select_box")
								.find('input')
								.click(
										function() {
											$(
													'input:radio[name="deliverType"]:checked')
													.parent().siblings(
															'.radio_skin')
													.addClass("active");
											$("input").not("input:checked")
													.parent().siblings(
															'.radio_skin')
													.removeClass("active");
										});
						$('#goodsPhoto')
								.change(
										function() {
											var fil = this.files[0];
											if (fil) {
												var reader = new FileReader();
												reader.readAsDataURL(fil);
												reader.onload = function() {
													document
															.getElementById("preview_image").src = reader.result;
													$('#goods_photo_detail')
															.attr("data-photo",
																	"true");
												};
											} else {
												document
														.getElementById("preview_image").src = $(
														'#preview_image').attr(
														'data-url');
											}
										});

						$('#upload').click(
								function() {
									if ($('#goodsName').val().length < 1
											|| $('#goods_photo_detail').attr(
													"data-photo") != "true") {
										var text = "照片不能为空";
										if ($('#goodsName').val().length < 1)
											text = "物品类型不能为空";
										$("#address_tip").text(text);
										$("#address_tip").fadeIn(200);
										$("#address_tip").delay(1000).fadeOut(
												100);
										return false;
									}
								});
					});
</script>
</html>