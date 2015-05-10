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
							$("#addAdmin_username")
									.attr("readonly", "readonly");
							$("#addAdminUsername").show();
							$("#addAdmin_password").val(json.password);
							$("#addAdmin_password")
									.attr("readonly", "readonly");
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
		$("#alterAdmin_submit").click(function() {
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
	del : function(obj) {
		var that = obj;
		var id = $(that).attr("id");
		 if (!confirm("确认要删除么？")) {
             return false;
         }
		$.post("ajax/delAdmin", {
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
	loadEvent : function() {
		$("#alterPwd").click(function() {
				});
		$("#adminInfo").click(function() {
			$.post("ajax/getAllAdmin", {
						r : Math.random()
					}, function(result) {
						var json = eval("(" + result + ")");
						$("tbody").html("");
						$.each(json, function(idx, obj) {
							$("tbody")
									.append("<tr><th>"
											+ (idx + 1)
											+ "</th><th>"
											+ obj.username
											+ "</th><th>"
											+ obj.realName
											+ "</th><th><button type='button' id='"
											+ obj.id
											+ "' onclick='admin.del(this)' class='btn btn-default'>删除</button></th></tr>");
						});
					});
		});
		$('#register').click(function() {
			var username = $('#username').val();
			var email = $('#email').val();
			var pwd = $('#pwd').val();
			var flag = 1;
			if (username == '' || username == null) {
				flag = 0;
				if ($('#errorUsershow').length <= 0) {
					$('#errorUser')
							.append("<a id='errorUsershow' style='color: rgb(255, 0, 0);'>*</a>");
				}
			}
			var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
			if (email == '' || email == null || !reg.test(email)) {
				flag = 0;
				if (!reg.test(email)) {
					alert('邮箱格式不正确，请重新填写!');
				}
				if ($('#errorEmailshow').length <= 0) {
					$('#errorEmail')
							.append("<a id='errorEmailshow' style='color: rgb(255, 0, 0);'>*</a>");
				}
			}
			if (pwd == '' || pwd == null) {
				flag = 0;
				if ($('#errorPwdshow').length <= 0) {
					$('#errorPwd')
							.append("<a id='errorPwdshow' style='color: rgb(255, 0, 0);'>*</a>");
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