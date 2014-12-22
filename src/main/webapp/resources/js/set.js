$(function() {
	var imageStr = $('#box-photo-img').attr("src");
	if (imageStr != "") {
		$('#box-photo-panel-btn-1').css('display', 'none');
		$('#box-photo-panel-btn-2').css('display', 'block');
	} else {
		$('#box-photo-panel-btn-1').css('display', 'block');
		$('#box-photo-panel-btn-2').css('display', 'none');
	}
	$('#box-photo').on('mouseover', function() {
		$('#box-photo-panel').css('display', 'block');
	});
	$('#box-photo').on('mouseout', function() {
		$('#box-photo-panel').css('display', 'none');
	});
	if ($('#flag').val() == 'log') {
		changeLi('li-log');
	}
});

/**
 * 点击Li时触发
 * 
 * @param id
 *            li编号
 */
function changeLi(id) {
	var list = new Array('li-user', 'li-log', 'li-safe');
	for ( var i in list) {
		$('#' + list[i]).removeClass();
		$('#' + list[i] + '-div').css('display', 'none');
	}
	$('#' + id).removeClass().addClass('select');
	$('#' + id + '-div').css('display', 'block');
}

/**
 * 弹出上传图片的面板
 */
function upImagePanel() {
	$.layer({
		shade : [ 0.7, '#000', true ],
		border : [ 9, 1, '#157069', true ],
		closeBtn : [ 1, true ],
		fadeIn : 500,
		type : 1,
		offset : [ '220px', '50%' ],
		area : [ '360px', '160px' ],
		title : "上传图片",
		page : {
			dom : "#upImagePanel"
		}
	});
}

/**
 * 检查图片类型
 */
function chackImage() {
	var check = false;
	var fileName = $("#imagefile").val();
	if (!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(fileName)) {
		$("#fileMsg").html("图片类型必须是.gif,jpeg,jpg,png中的一种！");
	} else {
		check = true;
	}
	return check;
}

/**
 * 删除头像
 */
function delHeadImage() {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '确定要删除该头像？',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delHeadImage";
				var params = {
					userName : id
				};
				$.getJSON(url, params, function(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						window.location.reload();
					}
				});
			}
		}
	});
}

/**
 * 显示昵称修改框
 */
function editNickname() {
	$('#nickName').css("display", "none");
	$('#nickNameInput').css("display", "block");
	$('#nickNameInput').focus();
}

/**
 * 修改昵称
 */
function changeNickName() {
	$('#nickName').css("display", "inline-block");
	$('#nickNameInput').css("display", "none");
	var oldName = $('#nickName').html();
	var newName = $('#nickNameInput').val();
	if (oldName != newName) {
		var url = "ajax_changeNickName";
		var params = {
			userName : newName
		};
		$.getJSON(url, params, function(data) {
			var resultData = eval("(" + data + ")");
			var result = resultData.result;
			if (result == "Y") {
				$('#nickName').html(newName);
				$('#nickNameInput').val(newName);
			}
		});
	}
}

/**
 * 根据日志编号删除日志
 * 
 * @param id
 *            日志编号
 */
function delLog(id) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '确定要删除该日志？',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delLog";
				var params = {
					userName : id
				};
				$.getJSON(url, params, function(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						location.href = "set_init?pageNum=1";
					}
				});
			}
		}
	});

}

/**
 * 展示修改密码面板
 */
function showChangePanel() {
	$.layer({
		shade : [ 0.7, '#000', true ],
		border : [ 7, 1, '#FFF', true ],
		closeBtn : [ 1, true ],
		type : 1,
		offset : [ '120px', '50%' ],
		area : [ 'auto', 'auto' ],
		title : false,
		page : {
			dom : '#changePass'
		}
	});
}

/**
 * 检查用户原始密码
 */
function checkOldPass() {
	var oldPass = $('#oldPass').val();
	if (oldPass == "") {
		$('#checkOldPass').html("密码不能为空！");
	} else {
		var url = "ajax_checkOldPass";
		var params = {
			"userName" : oldPass
		};
		$.getJSON(url, params, function callback(data) {
			var user = eval("(" + data + ")");
			if (user.result == "N") {
				$('#checkOldPass').html("密码错误！");
			} else if (user.result == "Y") {
				$('#checkOldPass').html('');
			}
		});
	}
}

/**
 * 检查新密码
 */
function checkNewPass() {
	var check = false;
	var newPass = $('#newPass').val();
	var checkStr = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
	if (newPass == "") {
		$('#checkNewPass').html("密码不能为空！");
	} else if (!newPass.match(checkStr)) {
		$('#checkNewPass').html("密码不合要求！");
	} else {
		$('#checkNewPass').html('');
		check = true;
	}
	return check;
}

/**
 * 检查确认密码
 */
function checkConfirmPass() {
	var check = false;
	if ($('#newPass').val() != $('#confirmPass').val()) {
		$('#checkConfirmPass').html("两次输入密码不一致！");
	} else {
		$('#checkConfirmPass').html("");
		check = true;
	}
	return check;
}

/**
 * 修改密码
 */
function changePass() {
	alert(checkConfirmPass() + ":" + checkNewPass() + $('#checkOldPass').html());
	if (checkConfirmPass() && checkNewPass()
			&& ($('#checkOldPass').html() == "")) {
		var newPass = $('#newPass').val();
		var url = "ajax_changePass";
		var params = {
			"userName" : newPass
		};
		$.getJSON(url, params, function callback(data) {
			var user = eval("(" + data + ")");
			if (user.result == "Y") {
				layer.alert('密码修改成功！\n将跳转到登录页面，请重新登录！', 1, '提示', function() {
					location.href = "user_logout";
				});
			}
		});
	}
}