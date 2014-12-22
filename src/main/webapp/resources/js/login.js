// 页面加载时触发的事件
window.onload = function() {
	var mes = $("#hiddenMessage").val();
	if (mes != "" && mes == "wrongName") {
		$("#username").val("无此登录用户！");
	} else if (mes != "" && mes == "wrongPass") {
		alert("用户密码错误！");
	} else if(mes == "registerSuccess"){
		layer.alert("请重新登录！",1,'注册成功！');
	}
};

$(function() {
	// 首页图片自动切换
	var len = $("#idNum > li").length;
	var index = 1;
	setInterval(function() {
		showImg(index);
		index++;
		if (index == len) {
			index = 0;
		}
	}, 5000);
	// 注册时提示用户名规则
	$("#registerName").on('focus', function() {
		layer.tips('建议用邮箱作为用户名', this, {
			guide : 1,
			time : 5,
			style : [ 'background-color:#F26C4F; color:#fff', '#F26C4F' ],
			maxWidth : 120
		});
	});
	// 注册时提示密码规则
	$("#registerPass").on('focus', function() {
		layer.tips('6-16位大小写字母和数字混合密码', this, {
			guide : 1,
			time : 5,
			style : [ 'background-color:#F26C4F; color:#fff', '#F26C4F' ],
			maxWidth : 120
		});
	});
	$("#confirmPass").on('focus', function() {
		layer.tips('再次输入密码', this, {
			guide : 1,
			time : 5,
			style : [ 'background-color:#F26C4F; color:#fff', '#F26C4F' ],
			maxWidth : 120
		});
	});
});
// 切换图片
function showImg(i) {
	$("#idSlider").stop(true, false).animate({
		top : -340 * i
	}, 1000);
	$("#idNum li").eq(i).addClass("on").siblings().removeClass("on");
}
// 清空用户名文本框
function nameClear() {
	$("#username").val("");
}
// 清空密码文本框
function passClear() {
	$("#password").val("");
}
// 登录表单提交时验证
function checkAll() {
	var check = false;
	if ($("#username").val() == "") {
		$("#username").val("用户名不能为空！");
		return check;
	} else if ($("#password").val() == "") {
		alert("密码不能为空！");
	} else {
		check = true;
	}
	return check;
}
// 弹出注册对话框
function register() {
	$.layer({
		shade : [ 0.7, '#000', true ],
		border : [ 7, 1, '#FFF', true ],
		closeBtn : [ 1, true ],
		type : 1,
		offset : ['120px' , '50%'],
		area : [ 'auto', 'auto' ],
		title : false,
		page : {
			dom : '#registerDiv'
		}
	});
}
// 检查用户名
function checkName() {
	var check = false;
	var name = $('#registerName').val();
	if (name == "") {
		$('#checkName').html("用户名不能为空");
	} else {
		var url = "ajax_checkUserName";
		var params = {
			"userName" : name
		};
		$.getJSON(url, params, function callback(data) {
			var user = eval("(" + data + ")");
			// result=Y:已存在,result=N:不存在
			if (user.result == "Y") {
				$('#checkName').html("用户名已存在！");
			} else {
				$('#checkName').html("该用户名可用！");
			}
		});
	}
	return check;
}
// 检查密码
function checkPass() {
	var check = false;
	var checkStr = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
	var name = $('#registerPass').val();
	if (name == "") {
		$('#checkPass').html("密码不能为空！");
	} else if (!name.match(checkStr)) {
		$('#checkPass').html("此密码不符合要求！");
	} else {
		$('#checkPass').html("");
		check = true;
	}
	return check;
}
// 检查确认密码
function checkConfirmPass() {
	var check = false;
	if ($('#registerPass').val() != $('#confirmPass').val()) {
		$('#checkConfirmPass').html("两次输入密码不一致！");
	} else {
		$('#checkConfirmPass').html("");
		check = true;
	}
	return check;
}
// 提交注册表单
function registerFormSubmit() {
	if ("该用户名可用！" == $('#checkName').html() && checkPass() && checkConfirmPass()) {
		$('#registerForm').submit();
	}
}