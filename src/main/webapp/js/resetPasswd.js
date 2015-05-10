$(function() {
			$("#no").hide();
			$("#ok").hide();
			reset.init();
		});
var reset = {

	init : function() {
		var dat = $("#json").val();
		var data = eval("(" + dat + ")");
		var result = data.result;
		if (result == true) {
			var response = data.response;
			$("#ok").show();
			$("#passwd").val(response);
		} else {
			$("#no").show();
		}
	}
	,

}