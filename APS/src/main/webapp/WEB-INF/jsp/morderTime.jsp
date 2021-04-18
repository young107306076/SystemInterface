<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>工單時間查詢</title>

<script>
$(function(){
	//讓他選擇日期
	$("#preSDateCreate").datepicker({dateFormat: 'yy/mm/dd'});
	$("#preEDateCreate").datepicker({dateFormat: 'yy/mm/dd'});
	$("#preSDateQuery").datepicker({dateFormat: 'yy/mm/dd'});
	$("#preEDateQuery").datepicker({dateFormat: 'yy/mm/dd'});
});

 function queryMorderByCondition(){
	$("#table_result").load("queryMorderByStartAndEnd #table_result",$("#form_query").formToJson(),function(responseText,textStatus,XMLHttpRequest){
		if(textStatus != "success"){return false;}
		$("#table_result").html($(responseText).find("#table_result").html());
	});
  }

</script>

</head>
<body>
	<div style="text-align: center">
		<h3 style="color: blue;">工單資訊</h3>
		<div style="width: 60%; margin: auto">
			<form:form id="form_query" name="form_query" method="POST"
				modelAttribute="morderBean">
				<fieldset class="fieldset_container">
					<legend>查詢條件</legend>
					<table width="96%" style="border: 3px #003C9D solid; padding: 5px;"
						rules="all" cellpadding='5' align="center">
						<tr class="table_content">
							<td nowrap="nowrap" align="right" class="table_label"
								style="width: 200px;">預開工日、預完工日</td>
							<td align="left" nowrap="nowrap"><form:input
									path="preSDateString" id="preSDateQuery" readonly="true" /> ~ <form:input
									path="preEDateString" id="preEDateQuery" readonly="true" /></td>
						</tr>
					</table>
					<input type="button" value="查詢" onclick="queryMorderByCondition();" />
				</fieldset>
			</form:form>
		</div>

		<div>
			<div style="text-align: right; width: 60%; margin: auto">
				<input type="button" value="新增" onclick="openCreate();" />&nbsp;&nbsp;
				<input type="button" value="刪除" onclick="removeMorder();" />&nbsp;&nbsp;
				<input type="button" value="查詢" onclick="readMorder();" /><br>
				<br>
			</div>
			<form:form id="form1" name="form1" method="POST"
				action="/removeMorder" modelAttribute="morderBean">
				<table id="table_result" border="1" cellpadding="3" cellspacing="0"
					style="width: 60%; margin: auto">
					<tr>
						<th></th>
						<th>制令單號</th>
						<th>預開工日</th>
						<th>預完工日</th>
						<th>製造數量</th>
					</tr>
					<c:forEach var="morder" items="${morders}">
						<tr>
							<td><input type="checkbox" name="removeId"
								value="${morder.id}" /></td>
							<td><a href="javascript:openModify('${morder.id}')"><c:out
										value="${morder.id}" /></a></td>
							<td><c:out value="${morder.preSDateString}" /></td>
							<td><c:out value="${morder.preEDateString}" /></td>
							<td><c:out value="${morder.quan}" /></td>
						</tr>
					</c:forEach>
				</table>
			</form:form>
		</div>
	</div>

</body>
</html>