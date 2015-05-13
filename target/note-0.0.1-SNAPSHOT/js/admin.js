$(function() {
	admin.init();
	admin.loadEvent();
	admin.add();
	$("#adminInfo").click();
});
var admin = {

	init : function() {
		var user = $("#user").val();
		$("#username").html(user + " <b class='caret'></b>");
		$("#addAdminUsername").hide();
		$("#addAdminPassword").hide();
		$("#loginout").click(function(){
			console.log("0010");
			 location.href = "loginout";
		});
		$("#adminInfo").click(function(){
			$("#li_noteInfo").removeAttr("class");
			$("#li_userInfo").removeAttr("class");
			$("#li_noteEvery").removeAttr("class");
			$("#li_adminInfo").attr("class", "active");
			$("#headadminInfo").show();
			$("#headuserInfo").hide();
			$("#headnoteInfo").hide();
			$("#headnoteEvery").hide();
			admin.eachAdmin(1);
		});
		$("#userInfo").click(function() {
			$("#li_noteInfo").removeAttr("class");
			$("#li_adminInfo").removeAttr("class");
			$("#li_noteEvery").removeAttr("class");
			$("#li_userInfo").attr("class", "active");
			$("#headadminInfo").hide();
			$("#headuserInfo").show();
			$("#headnoteInfo").hide();
			$("#headnoteEvery").hide();
			var id = 1;
			admin.eachUser(id);
		});
		$("#noteInfo").click(function() {
			$("#li_userInfo").removeAttr("class");
			$("#li_adminInfo").removeAttr("class");
			$("#li_noteEvery").removeAttr("class");
			$("#li_noteInfo").attr("class", "active");
			$("#headadminInfo").hide();
			$("#headuserInfo").hide();
			$("#headnoteInfo").show();
			$("#headnoteEvery").hide();
			admin.eachNote(1);
		});
		$("#noteEvery").click(function() {
			$("#li_userInfo").removeAttr("class");
			$("#li_adminInfo").removeAttr("class");
			$("#li_noteInfo").removeAttr("class");
			$("#li_noteEvery").attr("class", "active");
			$("#headadminInfo").hide();
			$("#headuserInfo").hide();
			$("#headnoteInfo").hide();
			$("#headnoteEvery").show();
			admin.eachQuote(1);
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
				admin.eachQuote(1);
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
				admin.event($(".active").attr("id") ,parseInt(s[0])-1);
			}
		});
		$("#pageNext").click(function() {
			var str = $("#currentPage").html();
			var s = str.split("/");
			if( parseInt(s[0]) < parseInt(s[1]) ){
				console.log($(".active").attr("id"));
				admin.event($(".active").attr("id") ,parseInt(s[0])+1);
			}
		});
		
	},
	add : function() {
		$("#addAdmin_submit").click(function() {
			var realname = $("#addAdmin_realname").val();
			if (realname == "" || realname == null) {
				alert("请输入真实姓名");
				return;
			}
			$("#addAdmin_realname").attr("readonly", "readonly");
			$.post("ajax/addAdmin", {
				"realName" : realname
			}, function(result) {
				var json = eval("(" + result + ")");
				if (json.result == true) {
					$("#addAdmin_username").val(json.username);
					$("#addAdmin_username").attr("readonly", "readonly");
					$("#addAdminUsername").show();
					$("#addAdmin_password").val(json.password);
					$("#addAdmin_password").attr("readonly", "readonly");
					$("#addAdminPassword").show();
					$("#addAdmin_submit").hide();
				} else {
					alert("添加失败^_^");
					return;
				}
			});
		});
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
	},
	eachAdmin : function(id){
		$("#pageDiv").show();
		$.post("ajax/getAllAdmin",{"id":id,r : Math.random()},
							function(result) {
								var json = eval("(" + result+ ")");
								$("thead").html("");
								$("tbody").html("");
								$("thead").html("<tr><th>#</th><th>用户名</th><th>姓名</th><th>操作</th></tr>");
								$("#currentPage").html(id+"/"+parseInt(json.length/15+1));
								$.each(json,function(idx,obj) {
													$("tbody").append(
																	"<tr><th>"
																			+ ((idx + 1) + 15 * (id - 1))
																			+ "</th><th>"
																			+ obj.username
																			+ "</th><th>"
																			+ obj.realName
																			+ "</th><th><button type='button' id='"
																			+ obj.id
																			+ "' onclick='admin.del(this)' title='eachAdmin' class='btn btn-default'>删除</button></th></tr>");
												});
							});
	},
	eachUser : function(id){
		$("#pageDiv").show();
		$.post("ajax/getAllUser",{"id" : id,"r" : Math.random()},
				function(result) {
					var json = eval("(" + result
							+ ")");
					$.post("ajax/getUserLength",{"r":Math.random()},function(result){
						var json = eval("(" + result+ ")");
						$("#currentPage").html(id+"/"+json.response);
					});
					$("thead").html("");
					$("tbody").html("");
					$("thead").html("<tr><th>#</th><th>用户名</th><th>邮箱</th><th>笔记数量</th><th>上一次登录</th><th>操作</th></tr>");
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
														+ new Date(parseInt(obj.lasttime) ).toLocaleString()
														+ "</th><th><button type='button' id='"
														+ obj.id
														+ "' onclick='admin.del(this)'  title='eachUser' class='btn btn-default'>删除</button></th></tr>");
							});
				});
	},
	eachNote : function(id){
		$("#pageDiv").show();
		$.post("ajax/getAllAdvi", {"r" : Math.random(),"id":id}, function(result) {
			var json = eval("(" + result+ ")");
			$("#currentPage").html(id+"/"+parseInt(json.length/15+1));
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
				$("tbody").append("<tr><th>"+ ((idx + 1) + 15 * (id - 1))+"</th><th>"+obj.type+"</th><th>"+obj.content+"</th><th>"+obj.author+"</th><th><button type='button' id='"+ obj.id+ "' onclick='admin.del(this)' title='eachQuote' class='btn btn-default'>删除</button></tr>");
			});
		});
	},
	event : function(propertis,id){
		if( propertis=="li_adminInfo"){
			admin.eachAdmin(id);
		} else if( propertis=="li_userInfo"){
			admin.eachUser(id);
		} else if( propertis=="li_noteInfo"){
			admin.eachNote(id);
		} else if( propertis=="li_noteEvery"){
			admin.eachQuote(id);
		}
	},
	del : function(obj) {
		var that = obj;
		var id = $(that).attr("id");
		console.log(id);
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
		if( method=="eachAdmin" ){
			url = "ajax/delAdmin";
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
				admin.eachQuote(parseInt(s[0]));
			} 
			if( method=="eachUser" ){
				admin.eachUser(parseInt(s[0]));
			}
			if( method=="eachAdmin" ){
				admin.eachAdmin(parseInt(s[0]));
			}
		});
	},
	loadEvent : function() {
		$("#alterPwd").click(function() {
		});
		$('#register')
				.click(
						function() {
							var username = $('#username').val();
							var email = $('#email').val();
							var pwd = $('#pwd').val();
							var flag = 1;
							if (username == '' || username == null) {
								flag = 0;
								if ($('#errorUsershow').length <= 0) {
									$('#errorUser')
											.append(
													"<a id='errorUsershow' style='color: rgb(255, 0, 0);'>*</a>");
								}
							}
							var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
							if (email == '' || email == null
									|| !reg.test(email)) {
								flag = 0;
								if (!reg.test(email)) {
									alert('邮箱格式不正确，请重新填写!');
								}
								if ($('#errorEmailshow').length <= 0) {
									$('#errorEmail')
											.append(
													"<a id='errorEmailshow' style='color: rgb(255, 0, 0);'>*</a>");
								}
							}
							if (pwd == '' || pwd == null) {
								flag = 0;
								if ($('#errorPwdshow').length <= 0) {
									$('#errorPwd')
											.append(
													"<a id='errorPwdshow' style='color: rgb(255, 0, 0);'>*</a>");
								}
							}
							if (flag == 0) {
								return false;
							}
							// 显示动画旋转效果
							$('#shclDefault').show();

							var sendData = {
								username : username,
								password : pwd,
								email : email
							};
							// debugger;
							// var sendData =
							// "username="+username+"&password="+pwd+"&email="+email;
							$.ajax({
								url : 'ajax/register',
								type : "post",
								data : sendData,
								// dataType:"json",
								// accepts:'json',
								// contentType: "application/json",
								success : function(result) {
									console.log(result);
									var json = eval("(" + result + ")");
									if (json.result == true) {
										alert("注册成功");
										$("#login_username").val(username);
										$("#login_passwd").val(pwd);
										alert(result);
										// $("#login_btn").click();
									} else {
										alert(json.message);
									}
								},
								error : function(XMLHttpRequest, textStatus,
										errorThrown) {
									alert(XMLHttpRequest.readyState
											+ XMLHttpRequest.status
											+ XMLHttpRequest.responseText);
								}
							});
						});
	}

}