/**
 * 向后台发送手机号码并获取验证码
 * 
 */

window.onload = function() {

	var interValObj; //timer变量，控制时间
	var count = 60; //间隔函数，以秒执行
	var curCount; //当前剩余秒数
	curCount = count - 1;
	var clicked = false; //点击标志

	var btnGetCode = document.getElementById("btnGetCode");
	var userPhone = document.getElementById('userID');
    
	//为btnGetCode绑定点击事件
	btnGetCode.onclick = function() {

		if(clicked) return;

		clicked = true;

		if(!checkPhoneFormat(userPhone)) {
			return;
		}

		this.setAttribute("disabled", true); //设置按钮为禁用状态
		this.value = "请在" + curCount + "秒后再次获取"; //更改按钮文字
		interValObj = window.setInterval(setRemainTime, 1000); //启动计时器
		// $.ajaxSetup({ cache:false });
		//向后台请求处理
		$.ajax({
			type:"GET",
			url: "checkphone.do?type=LOGIN&phone=" + $("#userID").val(),
			dateType: "jsonp",
			jsonp: "callback",
			success: function(data){
				var data = data.parseJSON();
				if(data.success){
					alert("success");
				}else{
					alert("false!1");
				}
			},
			error: function(jqXHR){
				alert("发生错误" + jqXHR.status);
			}
		});
	};
	$.ajaxSetup({ cache:false });
	
	/**
	 * timer处理倒数获取验证码
	 */
	function setRemainTime() {
		if(curCount == 1) {
			curCount = 59;
			window.clearInterval(interValObj); //停止计时器
			btnGetCode.removeAttribute("disabled"); //移除禁用状态
			btnGetCode.value = "重新获取验证码";
		} else {
			curCount--;
			btnGetCode.value = "请在" + curCount + "秒后再次获取";
		}
	}

	/**
	 * 检测手机号码输入是否符合正则表达式
	 * 传入：获取验证码按钮元素
	 * return: true / false
	 */
	function checkPhoneFormat(userPhone) {
		var invalidPopup = document.getElementsByClassName('invalid_popup')[0];
		var phoneStr = userPhone.value;
		var reg = /^1[34578]\d{9}$/;

		if(reg.test(phoneStr)) {
			clicked = false;
			return true;
		} else {
			$("#invalid_popup").fadeIn(200);
			$("#invalid_popup").delay(1000).fadeOut(100);
			clicked = false;
			return false;
		}
	}
	
}
