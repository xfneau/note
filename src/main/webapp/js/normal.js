$(function() {
	normal.init();
	normal.add();
	$("#userInfo").click();
});
var normal = {

	init : function() {
		var user = $("#user").val();
		$("#username").html(user + " <b class='caret'></b>");
		$("#userInfo")
				.click(
						function() {
							$("li").removeAttr("class");
							$("#li_userInfo").attr("class", "active");
							var id = 1;
							normal.eachJson(id);
						});
		$("#noteInfo").click(function() {
			$("li").removeAttr("class");
			$("#li_noteInfo").attr("class", "active");
			$.post("ajax/getAllNote", {
				"r" : Math.random()
			}, function(result) {
				
			});
		});
		$("#pagePre").click(function() {
			var str = $("#currentPage").html();
			var s = str.split("/");
			if( s[0] > 1 ){
				normal.eachJson(parseInt(s[0])-1);
			}
		});
		$("#pageNext").click(function() {
			var str = $("#currentPage").html();
			var s = str.split("/");
			if( s[0] < s[1] ){
				normal.eachJson(parseInt(s[0])+1);
			}
		});
	},
	eachJson : function(id){
		$.post("ajax/getAllUser",{"id" : id,"r" : Math.random()},
				function(result) {
					var json = eval("(" + result
							+ ")");
					$.post("ajax/getUserLength",{"r":Math.random()},function(result){
						var json = eval("(" + result+ ")");
						$("#currentPage").html(id+"/"+json.response);
					});
					$("tbody").html("");
					$.each(
							json,
							function(idx,
									obj) {
								$("tbody")
										.append(
												"<tr><th>"
														+ ((idx + 1) + 15 * (id - 1))
														+ "</th><th>"
														+ obj.username
														+ "</th><th>"
														+ obj.email
														+ "</th><th>"
														+ obj.total
														+ "</th><th>"
														+ obj.lasttime
														+ "</th><th><button type='button' id='"
														+ obj.id
														+ "' onclick='normal.del(this)' class='btn btn-default'>删除</button></th></tr>");
							});
				});
	},
	del : function(obj) {
		var that = obj;
		var id = $(that).attr("id");
		if (!confirm("确认要删除么？")) {
			return false;
		}
		$.post("ajax/delUser", {
			"id" : id
		}, function(result) {
			var json = eval("(" + result + ")");
			if (json.result == true) {
				alert("删除成功");
			} else {
				alert("删除失败");
			}
			window.location.reload();
		});
	},
	add : function() {
		$(".close").click(function() {
			window.location.reload();
		});
		$("#alterAdmin_submit").click(
				function() {
					var oldpwd = $("#alterAdmin_oldpwd").val();
					var newpwd = $("#alterAdmin_newpwd").val();
					if (oldpwd == "" || oldpwd == null || newpwd == ""
							|| newpwd == null)
						return;
					var user = $("#user").val();
					$.post("ajax/alterPasswd", {
						"oldpwd" : oldpwd,
						"newpwd" : newpwd,
						"username" : user
					}, function(result) {
						var json = eval("(" + result + ")");
						$("#alterAdmin_submit").hide();
						alert(json.response);
					});
				});
	}
}