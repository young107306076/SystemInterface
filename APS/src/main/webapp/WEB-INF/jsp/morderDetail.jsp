<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/menu.jsp"%>
<html>
<head>
<title>工單彙整</title>
<script>
	$(function(){
	});
</script>
</head>
<body>
	<div style="text-align: center">
		<h2 style="color: blue;">工單資訊詳細</h2>



		<div>
			<table id="table_result" border="1" cellpadding="3" cellspacing="0"
				style="width: 60%; margin: auto">
				<tr>
					<th>制令單號</th>
					<th>預開工日</th>
					<th>預完工日</th>
					<th>工藝單號</th>
					<th>備註</th>
					<th>工序</th>
				</tr>
				<c:forEach var="morderDetail" items="${morderDetails}">
					<tr>
						<td><c:out value="${morderDetail.id}" /></td>
						<td><c:out value="${morderDetail.preSDate}" /></td>
						<td><c:out value="${morderDetail.preEDate}" /></td>
						<td><c:out value="${morderDetail.billNo}" /></td>
						<td><c:out value="${morderDetail.note}" /></td>
						<td><c:out value="${morderDetail.wstNo}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>