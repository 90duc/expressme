$(document).ready(function() {
		var today = new Date(parseInt($("#placeTime").val()));
		var time = setInterval(remaindTime, 1000);
		function remaindTime() {
			today = new Date(today.getTime() + 1000);
			//alert(today);
			$("#hours").html(today.getUTCHours());
			$("#minutes").html(today.getUTCMinutes());
			$("#seconds").html(today.getUTCSeconds());
			//$("#cssa").html(today.getTime());
			//if (today.getTime() < 1000) {
			//	clearInterval(time);
			//	location.reload();
			//}
		}
	});