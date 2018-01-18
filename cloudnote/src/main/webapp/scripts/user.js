//页面加载完毕调用次函数
$(function() {
	// 绑定事件
	$('#login').click(loginAction);
	$('#regist_button').click(regisAction);
	$('#logout').click(logOut);
	$('#changePassword').click(changePass);
});
function changePass() {
	$('#warning_1 span').html("");
	$('#warning_2 span').html("");
	$('#warning_3 span').html("");
	var userId = getCookie('userId');
	console.log(userId);
	var canMod = true;
	if (userId == null) {
		alert("你未登录，不能进行此操作");
		canMod = false;
	}
	// 获取参数
	var last_password = $('#last_password').val();
	var new_password = $('#new_password').val();
	var final_password = $('#final_password').val();
	console.log(last_password + ":" + new_password + ":" + final_password);
	if (last_password == "") {
		canMod = false;
		$('#warning_1 span').html("请输入原密码");
		$('#warning_1').show();
	}
	if (new_password == "") {
		canMod = false;
		$('#warning_2 span').html("请输入新密码");
		$('#warning_2').show();
	} else if (new_password.length < 6) {
		canMod = false;
		$('#warning_2 span').html("新密码长度太小");
		$('#warning_2').show();
	} else if (new_password == last_password) {
		canMod = false;
		$('#warning_2 span').html("不能和原密码相同");
		$('#warning_2').show();
	}
	if (final_password == "") {
		canMod = false;
		$('#warning_3 span').html("请输入确认新密码");
		$('#warning_3').show();
	} else if (final_password != new_password) {
		canMod = false;
		$('#warning_3 span').html("密码输入不一致");
		$('#warning_3').show();
	}
	var url = "user/changepass.do";
	var data = {
		"userId" : userId,
		"lpass" : last_password,
		"npass" : new_password,
	};
	if (canMod) {
		$.getJSON(url, data, function(result) {
			if (result.state == 0) {
				console.log(result.data);
				window.location.href = "log_in.html";
			}
			if (result.state == 1) {// 系统异常
				alert(result.message);
			}
			if (result.state == 2) {// 用户名错误
				alert(result.message);
			}
			if (result.state == 3) {// 密码错误
				$('#warning_1 span').html(result.message);
				$('#warning_1').show();
			}
		});
	}
}
/**
 * 用户退出登录
 */
function logOut() {
	delCookie('userId');
	window.location.href = "log_in.html";
}
/**
 * 用户登录请求函数
 */
function loginAction() {
	// 获取参数之前清空提示信息
	$('#count_span').html("");
	$('#password_span').html("");
	// 获取参数
	var name = $('#count').val().trim();
	var password = $('#password').val().trim();
	var ok = true;
	// 检测参数格式
	if (name == "") {
		$('#count_span').html("请输入用户名!");
		ok = false;
	}
	if (password == "") {
		$('#password_span').html("请输入密码!");
		ok = false;
	}
	// 参数格式检测通过，发送请求
	if (ok) {
		$.ajax({
			"url" : path + "/user/login.do",
			"type" : "post",
			"data" : {
				"name" : name,
				"password" : password
			},
			"dataType" : "json",
			"success" : function(result) {
				if (result.state == 0) {// 登录成功
					// 将用户信息写入cookie
					var user = result.data;
					addCookie("userId", user.id, 2);
					window.location.href = "edit.html";
				}
				if (result.state == 1) {// 系统异常
					alert(result.message);
				}
				if (result.state == 2) {// 用户名错误
					$('#count_span').html(result.message);
				}
				if (result.state == 3) {// 密码错误
					$('#password_span').html(result.message);
				}
			},
			"error" : function() {
				alert("登录失败!");
			}
		});
	}

}
/**
 * 用户注册
 */
function regisAction() {
	$('#warning_1 span').html("");
	$('#warning_2 span').html("");
	$('#warning_3 span').html("");
	var name = $('#regist_username').val().trim();
	var password = $('#regist_password').val().trim();
	var fpassword = $('#final_password').val().trim();
	var nick = $('#nickname').val().trim();
	var ok = true;
	if (name == "") {
		ok = false;
		$('#warning_1 span').html("请输入用户名");
		$('#warning_1').show();
	}
	if (password == "") {
		ok = false;
		$('#warning_2 span').html("请输入密码");
		$('#warning_2').show();
	} else if (password.length < 6) {
		ok = false;
		$('#warning_2 span').html("密码不能小于6位");
		$('#warning_2').show();
	}
	if (fpassword == "") {
		ok = false;
		$('#warning_3 span').html("请输入确认密码");
		$('#warning_3').show();
	}
	if (fpassword != password) {
		ok = false;
		$('#warning_3 span').html("密码不一致");
		$('#warning_3').show();
	}
	if (ok) {
		$.ajax({
			"url" : path + "/user/regist.do",
			"type" : "post",
			"data" : {
				"name" : name,
				"nick" : nick,
				"password" : password
			},
			"dataType" : "json",
			"success" : function(result) {
				if (result.state == 0) {
					var user = result.data;
					// 返回登录页面--模拟点击操作
					$('#back').click();
					// 将注册的用户名添加到登录页面
					$('#count').val(user.name);
					// 将光标移到密码框
					$('#password').focus();
				}
				if (result.state == 1) {
					alert(result.message);
				}
				if (result.state == 2) {
					$('#warning_1 span').html(result.message);
					$('#warning_1').show();
				}
				if (result.state == 3) {
					$('#warning_2 span').html(result.message);
					$('#warning_2').show();
				}
			},
			"error" : function() {
				alert("注册失败!");
			}
		});
	}

}