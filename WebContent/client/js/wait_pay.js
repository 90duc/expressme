/**
 * Created by IRVING on 2017/3/7.
 */
$(document).ready(function() {
	
	$("#cancelBtn").click(function() {
		$("#info_title").text('取消');
		$(".cancel_sure_box").fadeIn(300);
		
	});
	$("#cancel_no").click(function() {
		$(".cancel_sure_box").fadeOut(300);
	});

	$("#cancel_yes").click(function() {
		if($("#info_title").text()=='支付')
			payment();
		else
		 window.location.href = $("#cancel_yes").val();
	});
	
	$("#payBtn").click(function() {
		$("#info_title").text('支付');
		$(".cancel_sure_box").fadeIn(300);
		
	});
	
	
	$.ajaxSetup({
		cache : false
	});
	
	function payment() {		
		$.ajax({
			type : "POST",
			url : "payment.do?id=" + $("#order_id").val() + "&value=pay",
			dataType : "text",
			async:true,
			success : function(data) {
				
				if (data == 'yes') {
					//alert('yes');
					window.location.href = $("#cancel_yes").val();
				}
			},
			error:function(data) {
				alert(data);				
			}
		});

	};
});