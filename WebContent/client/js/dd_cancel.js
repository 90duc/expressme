/**
 * Created by IRVING on 2017/3/7.
 */
$(document).ready(function(){
    $("#noBtn").click(function () {
    	$("#cancel_yes").val($("#noBtn").val());
    	$("#cancel_type").html($("#noBtn").html());
        $(".cancel_sure_box").fadeIn(300);
    });
    $("#yesBtn").click(function () {
    	 $("#cancel_yes").val( $("#yesBtn").val());
    	 $("#cancel_type").html($("#yesBtn").html());
        $(".cancel_sure_box").fadeIn(300);
    });
    $("#cancel_no").click(function () {
        $(".cancel_sure_box").fadeOut(300);
    });
    
    $("#cancel_yes").click(function () {
    	window.location.href= $("#cancel_yes").val();
    });
});