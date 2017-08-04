
window.onload = function(){
	
	var edit_info_btn = document.getElementById("edit_info_btn");
	var user_info_lists = document.getElementsByClassName("user_info_lists");
	var info_inputs = getInputs(user_info_lists);  //获取input
	var edit_info_save=document.getElementById("edit_info_save");
	var edit_info_reset=document.getElementById("edit_info_reset");
	//为edit_info_btn绑定点击编辑事件
	edit_info_btn.onclick = function(){
		
		for(var i = 0; i < info_inputs.length; i++){
			
			info_inputs[i].removeAttribute("disabled");
		}
		edit_info_btn.style.display='none';
		edit_info_save.style.display='inline';
		edit_info_reset.style.display='inline';
	};
	
	 edit_info_save.onclick = function(){
		
		for(var i = 0; i < info_inputs.length; i++){
			
			info_inputs[i].setAttribute("disabled");
		}
		edit_info_btn.style.display='inline';
		edit_info_save.style.display='none';
		edit_info_reset.style.display='none';
	};
	
	function getInputs(user_info_lists){
		var info_inputs = new Array();
		for(var i = 0; i < user_info_lists.length; i++){
			info_inputs.push(user_info_lists[i].getElementsByTagName('input')[0]);
		}
		return info_inputs;
	}
};
