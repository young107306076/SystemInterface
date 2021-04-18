<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/menu.jsp"%>
<html>
<head>
<title>工單資訊</title>

<script>
	$(function(){
		$("#dialog0").dialog({
			autoOpen: false,
			width: 800,
			height: 600,
			modal: true,
			resizable: false,
			title: "修改工單資訊",
			buttons: {
				"確定": function(){modifyMorder();},
				"關閉": function(){$("#dialog0").dialog("close");}
			}
		});
		$("#dialog1").dialog({
			autoOpen: false,
			width: 800,
			height: 600,
			modal: true,
			resizable: false,
			title: "新增工單資訊",
			buttons: {
				"確定": function(){createMorder();},
				"關閉": function(){$("#dialog0").dialog("close");}
			}
		});
		$("#preSDateCreate").datepicker({dateFormat: 'yy/mm/dd'});
		$("#preEDateCreate").datepicker({dateFormat: 'yy/mm/dd'});
		$("#preSDateQuery").datepicker({dateFormat: 'yy/mm/dd'});
		$("#preEDateQuery").datepicker({dateFormat: 'yy/mm/dd'});
	});

	function checkSubmit(){
		$("#form_main").submit();
	}
	
	function openModify(id){
		$("#dialog0").load("queryMorder #dialog0",{"id":id},function(responseText,textStatus,XMLHttpRequest){
			if(textStatus != "success"){return false;}
			$("#dialog0").html($(responseText).find("#dialog0").html());
			$("#preEDate").datepicker({dateFormat: 'yy/mm/dd'});
		});
		$("#dialog0").dialog("open");
	}
	
	function modifyMorder(){
		$("#form0").submit();
	}
	
	function removeMorder(){
		$("#form1").submit();
	}
	
	function openCreate(){
		$("#dialog1").dialog("open");
	}
	
	function createMorder(){
		$("#form2").submit();
	}
	
	function queryMorderByCondition(){
		$("#table_result").load("queryMorderByStartAndEnd #table_result",$("#form_query").formToJson(),function(responseText,textStatus,XMLHttpRequest){
			if(textStatus != "success"){return false;}
			$("#table_result").html($(responseText).find("#table_result").html());
		});
	}
	
	function readMorder(){
		$("#table_result").load("readMorder #table_result",$("#form3").formToJson(), function(responseText, textStatus, XMLHttpRequest){
			if(textStatus != "success"){return false;}
			$("#table_result").html($(responseText).find("#table_result").html());
		});
	}
</script>
</head>
<body>
	<div style="text-align: center">
		<h3 style="color: blue;">工單資訊</h3>
		<div id="dialog2">
			<form:form id="form3" name="form3" method="POST" action="/readMorder"
				modelAttribute="morderBean">
				<table border="1" cellpadding="3" cellspacing="0"
					style="width: 60%; margin: auto">
					<tr>
						<th>制令單號ID</th>
						<td><form:input path="id" /></td>
					</tr>
				</table>
				<input type="button" value="查詢" onclick="readMorder();" />
			</form:form>
		</div>
		<div>

			<div style="text-align: right; width: 60%; margin: auto">
				<input type="button" value="新增" onclick="openCreate();" />&nbsp;&nbsp;
				<input type="button" value="刪除" onclick="removeMorder();" />&nbsp;&nbsp;<br>
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
	<form id="form_main" name="form_main" method="POST"
		action="/exportMorder"></form>

	<div id="dialog0">
		<form:form id="form0" name="form0" method="POST"
			action="/modifyMorder" modelAttribute="morderBean">
			<form:input path="id" type="hidden" />
			<table border="1" cellpadding="3" cellspacing="0"
				style="width: 60%; margin: auto">
				<tr>
					<th>預完工日</th>
					<td><form:input path="preEDateString" id="preEDate"
							readonly="true" /></td>
				</tr>
				<tr>
					<th>製造數量</th>
					<td><form:input path="quan" /></td>
				</tr>
			</table>
		</form:form>
	</div>

	<div id="dialog1">
		<form:form id="form2" name="form2" method="POST"
			action="/createMorder" modelAttribute="morderBean">
			<form:input path="id" type="hidden" />
			<table border="1" cellpadding="3" cellspacing="0"
				style="width: 60%; margin: auto">
				<tr>
					<th>制令單號ID</th>
					<td><form:input path="id" /></td>
				</tr>
				<tr>
					<th>制令单号</th>
					<td><form:input path="planNo" /></td>
				</tr>
				<tr>
					<th>预开工日</th>
					<td><form:input path="preSDateString" id="preSDateCreate"
							readonly="true" /></td>
				</tr>
				<tr>
					<th>預完工日</th>
					<td><form:input path="preEDateString" id="preEDateCreate"
							readonly="true" /></td>
				</tr>
				<tr>
					<th>製造數量</th>
					<td><form:input path="quan" /></td>
				</tr>
				<tr>
					<th>状态ID</th>
					<td><form:input path="stateId" /></td>
				</tr>
				<tr>
					<th>状态</th>
					<td><form:input path="state" /></td>
				</tr>
				<tr>
					<th>加工类型ID</th>
					<td><form:input path="mkTypeId" /></td>
				</tr>
				<tr>
					<th>加工类型</th>
					<td><form:input path="mkType" /></td>
				</tr>
				<tr>
					<th>单别ID</th>
					<td><form:input path="categoryId" /></td>
				</tr>
				<tr>
					<th>单别</th>
					<td><form:input path="category" /></td>
				</tr>
				<tr>
					<th>货品ID</th>
					<td><form:input path="gdId" /></td>
				</tr>
				<tr>
					<th>货品编号</th>
					<td><form:input path="gdNo" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>