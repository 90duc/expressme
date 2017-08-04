/**
 * Created by IRVING on 2017/3/7.
 */
$(document).ready(function(){
	$("#pop_box").click(
			function(e) {
				var x = event.pageX;
				var menu_x = $("#menu_box").position().left
						+ $("#menu_box").outerWidth();
				if (x > menu_x)
					$("#pop_box").fadeOut(300);
			});
	
	$("#pop_box").click(
			function(e) {
				var x = event.pageX;
				var menu_x = $("#menu_box").position().left
						+ $("#menu_box").outerWidth();
				if (x > menu_x)
					$("#pop_box").fadeOut(300);
			});

	$("#login_button").click(function() {

		window.location.href = "login.xhtm";
	});

	$("#home_button").click(function() {
		$("#pop_box").fadeIn(300);
	});

	$("#person_button").click(function() {
		window.location.href = "personpage.xhtm";

	});
	$("#orders_button").click(function() {
		window.location.href = "showorders.xhtm";
	});
});