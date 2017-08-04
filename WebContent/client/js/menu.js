/**
 * Created by IRVING on 2017/3/7.
 */
$(document).ready(function(){
	var lists = document.getElementsByClassName("menu_rows");

	function visit() {
		window.location.href=this.getAttribute("data-url");
	}
	
	for ( var i = 0; i < lists.length; i++) {
		lists[i].onclick = visit;
	}
});