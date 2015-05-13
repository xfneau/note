$(function() {
	normal.init();
	normal.add();
	$("#userInfo").click();
});
var normal = {

	init : function() {
		var user = $("#user").val();
		$("#username").html(user + " <b class='caret'></b>");
		$("#loginout").click(function(){
			console.log("0010");
			 location.href = "loginout";
		});
		$("#userInfo").click(function() {
			$("#li_noteInfo").removeAttr("class");
			$("#li_noteEvery").removeAttr("class");
			$("#li_userInfo").attr("class", "active");
			$("#headadminInfo").hide();
			$("#headuserInfo").show();
			$("#headnoteInfo").hide();
			$("#headnoteEvery").hide();
			var id = 1;
			normal.eachUser(id);
		});
		$("#noteInfo").click(function() {
			$("#li_userInfo").removeAttr("class");
			$("#li_noteEvery").removeAttr("class");
			$("#li_noteInfo").attr("class", "active");
			$("#headadminInfo").hide();
			$("#headuserInfo").hide();
			$("#headnoteInfo").show();
			$("#headnoteEvery").hide();
			normal.eachNote(1);
		});
		$("#noteEvery").click(function(){
			$("#li_userInfo").removeAttr("class");
			$("#li_noteInfo").removeAttr("class");
			$("#li_noteEvery").attr("class", "active");
			$("#headadminInfo").hide();
			$("#headuserInfo").hide();
			$("#headnoteInfo").hide();
			$("#headnoteEvery").show();
			normal.eachQuote(1);
		});
		$("#addQuote").click(function(){
			var type = $("#type").val();
			var context = $("#context").val();
			var author = $("#author").val();
			$.post("ajax/addQuote",{"type":type,"context":context,"author":author},function(result){
				var json = eval("("+result+")");
				if( json.result==true ){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				normal.eachQuote(1);
				$("#type").val("");
				$("#context").val("");
				$("#author").val("");
			});
		});
		$("#search").click(function(){
			var searchUser = $("#searchUser").val();
			$.post("ajax/searchUser",{"context":searchUser},function(result){
				var json = eval("("+result+")");
					$("tbody").html("");
					$("#pageDiv").hide();
					$.each(json,function(idx,obj) {
						$("tbody").append(
										"<tr><th>"
												+ (idx + 1)
												+ "</th><th>"
												+ obj.username
												+ "</th><th>"
												+ obj.email
												+ "</th><th>"
												+ obj.total
												+ "</th><th>"
												+ new Date(parseInt(obj.lasttime) ).toLocaleString()
												+ "</th><th><button type='button' id='"
												+ obj.id
												+ "' onclick='admin.del(this)'  title='eachUser' class='btn btn-default'>删除</button></th></tr>");
					});
					alert("查找成功");
			});
		});
		$("#pagePre").click(function() {
			var str = $("#currentPage").html();
			var s = str.split("/");
			if( parseInt(s[0]) > 1 ){
				normal.event($(".active").attr("id"),parseInt(s[0])-1);
			}
		});
		$("#pageNext").click(function() {
			var str = $("#currentPage").html();
			var s = str.split("/");
			if( parseInt(s[0]) < parseInt(s[1]) ){
				normal.event($(".active").attr("id"),parseInt(s[0])+1);
			}
		});
		
	},
	eachUser : function(id){
		$("#pageDiv").show();
		$.post("ajax/getAllUser",{"id" : id,"r" : Math.random()},
				function(result) {
					var json = eval("(" + result+ ")");
					$.post("ajax/getUserLength",{"r":Math.random()},function(result){
						var json = eval("(" + result+ ")");
						$("#currentPage").html(id+"/"+json.response);
					});
					$("thead").html("");
					$("tbody").html("");
					$("thead").html("<tr><th>#</th><th>用户名</th><th>邮箱</th><th>笔记数量</th><th>上一次登录</th><th>操作</th></tr>");
					$.each(json,function(idx,obj) {
								$("tbody").append(
												"<tr><th>"
														+ ((idx + 1) + 15 * (id - 1))
														+ "</th><th>"
														+ obj.username
														+ "</th><th>"
														+ obj.email
														+ "</th><th>"
														+ obj.total
														+ "</th><th>"
														+ new Date(parseInt(obj.lasttime) ).toLocaleString()
														+ "</th><th><button type='button' id='"
														+ obj.id
														+ "' onclick='normal.del(this)' title='eachUser' class='btn btn-default'>删除</button></th></tr>");
							});
				});
	},
	eachNote : function(id){
		$("#pageDiv").show();
		$.post("ajax/getAllAdvi", {"r" : Math.random(),"id":id}, function(result) {
			var json = eval("(" + result+ ")");
			$.post("ajax/getNoteLength",{"r":Math.random()},function(result){
				var json = eval("(" + result+ ")");
				$("#currentPage").html(id+"/"+json.response);
			});
			$("thead").html("");
			$("tbody").html("");
			$("thead").html("<tr><th>#</th><th>用户名</th><th>内容</th><th>时间</th></tr>");
			$.each(json,function(idx,obj) {
				$("tbody").append("<tr><th>"+ ((idx + 1) + 15 * (id - 1))+"</th><th>"+obj.username+"</th><th><textarea class='form-control' rows='3'>"+obj.context+"</textarea></th><th>"+new Date(parseInt(obj.time) ).toLocaleString()+"</th></tr>");
			});
		});
	},
	eachQuote : function(id){
		$("#pageDiv").show();
		$.post("ajax/getAllQuotes", {"r" : Math.random(),"id":id}, function(result) {
			var json = eval("(" + result+ ")");
			$.post("ajax/getQuoteLength",{"r":Math.random()},function(result){
				var json = eval("(" + result+ ")");
				$("#currentPage").html(id+"/"+json.response);
			});
			$("thead").html("");
			$("tbody").html("");
			$("thead").html("<tr><th>#</th><th>主题</th><th>内容</th><th>作者</th><th>操作</th></tr>");
			$.each(json,function(idx,obj) {
				$("tbody").append("<tr><th>"+ ((idx + 1) + 15 * (id - 1))+"</th><th>"+obj.type+"</th><th>"+obj.content+"</th><th>"+obj.author+"</th><th><button type='button' id='"+ obj.id+ "' onclick='normal.del(this)' title='eachQuote' class='btn btn-default'>删除</button></tr>");
			});
		});
	},
	event : function(propertis,id){
		if( propertis=="li_userInfo"){
			normal.eachUser(id);
		} else if( propertis=="li_noteInfo"){
			normal.eachNote(id);
		} else if( propertis=="li_noteEvery"){
			normal.eachQuote(id);
		}
	},
	del : function(obj) {
		var that = obj;
		var id = $(that).attr("id");
		if (!confirm("确认要删除么？")) {
			return false;
		}
		var method = $(that).attr("title");
		if( method=="eachQuote" ){
			url = "ajax/deleteQuotes";
		} 
		if( method=="eachUser" ){
			url = "ajax/delUser";
		}
		console.log(url);
		$.post(url, {
			"id" : id
		}, function(result) {
			var json = eval("(" + result + ")");
			if (json.result == true) {
				alert("删除成功");
			} else {
				alert("删除失败");
			}
			var str = $("#currentPage").html();
			var s = str.split("/");
			if( method=="eachQuote" ){
				normal.eachQuote(parseInt(s[0]));
			} 
			if( method=="eachUser" ){
				normal.eachUser(parseInt(s[0]));
			}
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