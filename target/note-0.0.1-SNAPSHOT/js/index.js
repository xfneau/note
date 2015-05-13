$(function() {
			index.init();
		});
var index = {

	init : function() {
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

			$.post("ajax/register", {
						"username" : username,
						"password" : pwd,
						"emai" : email,
						r : Math.random()
					}, function(result) {
						var json = eval("(" + result + ")");
						alert(result);
					});
				// var sendData = {
				// username : username,
				// password : pwd,
				// email : email
				// };
				// $.ajax({
				// url : 'ajax/register',
				// type : "post",
				// data : sendData,
				// // dataType:"json",
				// // accepts:'json',
				// // contentType: "application/json",
				// success : function(result) {
				// console.log(result);
				// var json = eval("(" + result + ")");
				// if (json.result == true) {
				// alert("注册成功");
				// $("#login_username").val(username);
				// $("#login_passwd").val(pwd);
				// alert(result);
				// // $("#login_btn").click();
				// } else {
				// alert(json.message);
				// }
				// },
				// error : function(XMLHttpRequest, textStatus,
				// errorThrown) {
				// alert(XMLHttpRequest.readyState
				// + XMLHttpRequest.status
				// + XMLHttpRequest.responseText);
				// }
				// });
		});

	}

}