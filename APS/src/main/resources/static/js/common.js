function queryForModify(divId,id,actionName){
	$("#"+divId).load(actionName + " #"+divId,{"id":id},function(responseText,textStatus,XMLHttpRequest){
		if(textStatus != "success"){return false;}
		$("#"+divId).html($(responseText).find("#"+divId).html());
	});
}

