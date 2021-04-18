<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/menu.jsp"%>
<html>
<head>
<title>匯入工單資訊</title>
<script>
		$( function() {
			$("#tabs").tabs();
		});
	</script>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<div id="tabs" style="width: 50%; margin: 0px auto;">
		<ul>
			<li><a href="#tabs-1">匯入工單資訊</a></li>
		</ul>
		<div id="tabs-1">
			<fieldset class="fieldset_container">
				<legend>匯入工單資訊</legend>
				<div style="text-align: center;">
					<form method="POST" enctype="multipart/form-data"
						action="importMorder">
						<div>
							<label>選擇上傳檔案:<input type="file" name="file" /></label>
						</div>
						<div>
							<label><input type="submit" value="上傳" /></label>
						</div>
					</form>
				</div>
			</fieldset>
		</div>
	</div>
</body>
</html>