<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="/js/common.js"></script>
<script src="/js/formToJson.js"></script>

<link rel="stylesheet" href="/css/apsStyle.css">

<script>
		$(function(){
			$('#menu li').hover(function(){
				var _this = $(this),
					_subnav = _this.children('ul');
				_this.css('backgroundColor', '#06c');
				_subnav.css('display', 'block');
			} , function(){
				$(this).css('backgroundColor', '').children('ul').css('display', 'none');
			});
			$('a').focus(function(){
				this.blur();
			});
		});
	</script>
</head>
<body>
	<ul id="menu">
		<li><a href="${pageContext.request.contextPath}/login">首頁</a></li>
		<li><a href="#">帳號管理</a>
			<ul>
				<!--<li><a href="${pageContext.request.contextPath}/addUser">新增使用者</a></li>-->
				<li><a href="${pageContext.request.contextPath}/userList">帳號清單</a></li>
			</ul></li>
		<li><a href="#">工單管理</a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/morderImport">匯入工單</a></li>
				<li><a href="${pageContext.request.contextPath}/morder">工單資訊</a></li>
				<li><a href="${pageContext.request.contextPath}/morderDetail">工單彙整</a></li>
				<li><a href="${pageContext.request.contextPath}/morderTime">工單時間查詢</a></li>
			</ul></li>
		<li><a href="#">流程管理</a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/processList">流程設定</a></li>
			</ul></li>
		<li><a href="#">系統設定</a></li>
	</ul>
	<br>
	<br>
</body>
</html>