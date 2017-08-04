<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/font.css" />
<link type="text/css" rel="stylesheet" href="css/menu.css" />
<div class="user_info">
	<div class="user_form">
		<div class="user_info_box">
			<div class="user_portrait">
				<a href="personpage.xhtm"> <img src="${person.photo}" />
				</a>
			</div>
			<div style="text-align: center;">
				<a id="userID" href="personinfo.xhtm"> ${person.userName}</a>
			</div>
		</div>
	</div>

	<!--历史订单-->
	<div class="menu_rows" data-url="showorders.xhtm">
		<div class="menu_lists">
			<div class="icon_button">
				<i class="iconfont"> &#xe659; </i>
			</div>
			<span>历史订单</span>
		</div>
	</div>

	<!--实名认证-->
	<div class="menu_rows" data-url="verify.xhtm">
		<div class="menu_lists">
			<div class="icon_button">
				<i class="iconfont"> &#xe63b; </i>
			</div>
			<span>实名认证</span>
		</div>
	</div>

	<!--帮助-->
	<div class="menu_rows" data-url="help.xhtm">
		<div class=" menu_lists">
			<div class="icon_button">
				<i class="iconfont"> &#xe600; </i>
			</div>
			<span>帮助</span>
		</div>
	</div>

	<!--设置-->
	<div class="menu_rows" data-url="seting.xhtm">
		<div class=" menu_lists">
			<div class="icon_button">
				<i class="iconfont"> &#xe60c; </i>
			</div>
			<span>设置</span>
		</div>
	</div>

	<!--退出登录-->
	<div class="menu_rows" data-url="signOut.xhtm">
		<div class=" menu_lists">
			<div class="icon_button">
				<i class="iconfont"> &#xe6a3; </i>
			</div>
			<span>退出登录</span>
		</div>
	</div>

</div>
<script type="text/javascript" src="js/menu.js"></script>
