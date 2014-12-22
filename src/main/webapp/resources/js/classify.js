$(function() {
	changeClassify($('#callbackText').val());
});
// 改变分类选项卡
function changeClassify(classType) {
	var classifyList = new Array('l-out', 'l-in', 'l-store', 'l-pro',
			'l-member', 'l-template');
	for ( var int = 0; int < classifyList.length; int++) {
		if (classifyList[int] == classType) {
			$("#" + classType).removeClass().addClass("select");
			$("#" + classType + "Classify").css("display", "block");
		} else {
			$("#" + classifyList[int]).removeClass();
			$("#" + classifyList[int] + "Classify").css("display", "none");
		}
	}
}

function showSecondLevel(id) {
	$("#" + id).toggle("fast");
}

function showBox(id) {
	$("#" + id).toggle("fast");
}
// 显示添加模板窗体
function showAddTem() {
	$.layer({
		shade : [ 0.5, '#000', true ],
		border : [ 9, 1, '#157069', true ],
		closeBtn : [ 1, true ],
		fadeIn : 500,
		type : 1,
		offset : [ '150px', '50%' ],
		area : [ '610px', '320px' ],
		title : "添加模板",
		page : {
			dom : "#addTempPanel"
		},
		end : function() {
			refresh('l-template');
		}
	});
}
// 刷新页面
function refresh(type) {
	$('#callbackText').val(type);
	window.location.reload();
}
// 改变添加模板类型
function changeAddTem(type) {
	if (type == 'payout') {
		$('#addBtnOut').removeClass().addClass("addbtn-payout addbtn-payout-s");
		$('#addBtnIn').removeClass().addClass("addbtn-income");
		$('#tempType').val("spending");
		$("#businessLi").show();
		changeLevel();
	} else if (type == 'income') {
		$('#addBtnOut').removeClass().addClass("addbtn-payout");
		$('#addBtnIn').removeClass().addClass("addbtn-income addbtn-income-s");
		$('#tempType').val("income");
		$("#businessLi").hide();
		changeLevel();
	}
}
// 一级分类选择改变时改变二级选择中的选项
function changeFirstLevel() {
	var firstId = $("#firstSpendingLevel").val();
	changeSecondLevel(firstId);
}
// 切换收支类型时
function changeLevel() {
	var url = "ajax_getAllFirstLevelByType";
	var params = {};
	if ($('#tempType').val() == "spending") {
		params = {
			"userName" : "spending"
		};
	} else if ($('#tempType').val() == "income") {
		params = {
			"userName" : "income"
		};
	}
	$.getJSON(url, params, function callback(data) {
		var resultData = eval("(" + data + ")");
		var result = resultData.result;
		$("#firstSpendingLevel").empty();
		var firstId = 1;
		for ( var i = 0; i < result.length; i++) {
			firstId = result[0].firstLevelId;
			$("#firstSpendingLevel").append(
					"<option value=" + result[i].firstLevelId + ">"
							+ result[i].firstLevelName + "</option>");
		}
		changeSecondLevel(firstId);
	});
}
// 改变二级选项中的值
function changeSecondLevel(firstId) {
	$("#secondLevelSelect").empty();
	var url = "ajax_getSecondLevelByFirstId";
	var params = {
		"userName" : firstId + ""
	};
	$.getJSON(url, params, function callback(data) {
		var resultData = eval("(" + data + ")");
		var result = resultData.result;
		for ( var i = 0; i < result.length; i++) {
			$("#secondLevelSelect").append(
					"<option value=" + result[i].secondLevelId + ">"
							+ result[i].secondLevelName + "</option>");
		}
	});
}
// 添加模板
function addTemplate() {
	if ($("#templateName").val() == "") {
		layer.alert("请填写模板名称！", 3, '提示');
		return;
	} else if ($("#templateMoney").val() == "") {
		layer.alert("金额不能为空！", 3, '提示');
		return;
	}
	var url = "ajax_addTemplate";
	var type = "O";
	if ($('#tempType').val() == "income") {
		type = "I";
	}
	var spending = JSON.stringify({
		templateName : $("#templateName").val(),
		ioType : type,
		firstLevelId : $("#firstSpendingLevel").val(),
		secondLevelId : $("#secondLevelSelect").val(),
		accountId : $("#accountSelect").val(),
		projectId : $("#projectSelect").val(),
		businessId : $("#businessSelect").val(),
		memberId : $("#memberSelect").val(),
		money : $("#templateMoney").val(),
		remark : $("#tempMemo").val()
	});
	var params = {
		"userName" : spending
	};
	$.getJSON(url, params, function callback(data) {
		var resultData = eval("(" + data + ")");
		var str = resultData.result;
		if (str == "Y") {
			layer.alert("添加成功！", 1, '提示');
		}
	});
}
// 改变一级支出分类时
function changeTempFirst(id) {
	$("#secondSelect-" + id).empty();
	var firstId = $('#firstSelect-' + id).val();
	var url = "ajax_getSecondLevelByFirstId";
	var params = {
		"userName" : firstId + ""
	};
	$.getJSON(url, params, function callback(data) {
		var resultData = eval("(" + data + ")");
		var result = resultData.result;
		for ( var i = 0; i < result.length; i++) {
			$("#secondSelect-" + id).append(
					"<option value=" + result[i].secondLevelId + ">"
							+ result[i].secondLevelName + "</option>");
		}
	});
}

// 模板删除
function delTemp(templateId) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '您确定要删除该账单模板么？',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delTemplate";
				var params = {
					"userName" : templateId + ""
				};
				$.getJSON(url, params, function callback(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						refresh('l-template');
					}
				});
			}
		}
	});
}

function delBusiness(busId) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '您确定要删除该商家么？若该商家下有账单信息将不能删除！',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delBusiness";
				var params = {
					"userName" : busId + ""
				};
				$.getJSON(url, params, function callback(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						refresh('l-store');
					} else if (result == "N") {
						layer.alert("删除失败！改商家有相关账单！", 3, '提示');
					}
				});
			}
		}
	});
}
/**
 * 删除项目
 * 
 * @param proId
 *            项目编号
 */
function delPro(proId) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '您确定要删除该项目么？若该项目下有账单信息将不能被删除！',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delProject";
				var params = {
					"userName" : proId + ""
				};
				$.getJSON(url, params, function callback(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						refresh('l-pro');
					} else if (result == "N") {
						layer.alert("删除失败！该项目下有相关账单！", 3, '提示');
					}
				});
			}
		}
	});
}
/**
 * 删除家庭成员
 * 
 * @param memberId
 *            家庭成员编号
 */
function delMember(memberId) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '您确定要删除该成员么？若该成员有账单信息将不能被删除！',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delMember";
				var params = {
					"userName" : memberId + ""
				};
				$.getJSON(url, params, function callback(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						refresh('l-member');
					} else if (result == "N") {
						layer.alert("删除失败！该成员有相关账单！", 3, '提示');
					}
				});
			}
		}
	});
}
/**
 * 删除一级支出分类
 */
function delFirstOut(outId) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '您确定要删除一级分类？若该一级分类下有账单信息将不能被删除！',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delFirstOut";
				var params = {
					"userName" : outId + ""
				};
				$.getJSON(url, params, function callback(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						refresh('l-out');
					} else if (result == "N") {
						layer.alert("删除失败！该一级分类有相关账单！", 3, '提示');
					}
				});
			}
		}
	});
}
/**
 * 删除一级收入分类
 */
function delFirstIn(inId) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '您确定要删除一级分类？若该一级分类下有账单信息将不能被删除！',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delFirstIn";
				var params = {
					"userName" : inId + ""
				};
				$.getJSON(url, params, function callback(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						refresh('l-in');
					} else if (result == "N") {
						layer.alert("删除失败！该一级分类有相关账单！", 3, '提示');
					}
				});
			}
		}
	});
}
/**
 * 删除二级支出分类
 */
function delSecondOut(outId) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '您确定要删除二级分类？若该二级分类下有账单信息将不能被删除！',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delSecondOut";
				var params = {
					"userName" : outId + ""
				};
				$.getJSON(url, params, function callback(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						refresh('l-out');
					} else if (result == "N") {
						layer.alert("删除失败！该二级分类有相关账单！", 3, '提示');
					}
				});
			}
		}
	});
}
/**
 * 删除二级收入分类
 */
function delSecondIn(inId) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '您确定要删除二级分类？若该二级分类下有账单信息将不能被删除！',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delSecondIn";
				var params = {
					"userName" : inId + ""
				};
				$.getJSON(url, params, function callback(data) {
					var resultData = eval("(" + data + ")");
					var result = resultData.result;
					if (result == "Y") {
						refresh('l-in');
					} else if (result == "N") {
						layer.alert("删除失败！该二级分类有相关账单！", 3, '提示');
					}
				});
			}
		}
	});
}

/**
 * 展示添加分类面板
 * 
 * @param type
 *            分类类型
 */
function showAddClassifyPanel(type) {
	$('#addClassifyText').val(type);
	$.layer({
		shade : [ 0.5, '#000', true ],
		border : [ 9, 1, '#157069', true ],
		closeBtn : [ 1, true ],
		fadeIn : 500,
		type : 1,
		offset : [ '220px', '50%' ],
		area : [ '360px', '160px' ],
		title : "添加分类",
		page : {
			dom : "#addClassifyPanel"
		}
	});
}

/**
 * 添加分类
 * 
 * @param IOType
 *            收支类型
 */
function addClassify() {
	var name = $("#classifyName").val();
	var backPage = "";
	var typeText = $('#addClassifyText').val();
	var classifyType = [];
	classifyType = typeText.split('-');
	if (classifyType[0] == "first") {
		name = name + "-" + typeText;
		if (classifyType[1] == "O") {
			backPage = "l-out";
		} else if (classifyType[1] == "I") {
			backPage = "l-in";
		}
	} else if (classifyType[0] == "second") {
		name = name + "-" + typeText;
		if (classifyType[1] == "O") {
			backPage = "l-out";
		} else if (classifyType[1] == "I") {
			backPage = "l-in";
		}
	} else if (classifyType[0] == "business") {
		name += "-" + classifyType[0];
		backPage = "l-store";
	} else if (classifyType[0] == "project") {
		name += "-" + classifyType[0];
		backPage = "l-pro";
	} else if (classifyType[0] == "member") {
		name += "-" + classifyType[0];
		backPage = "l-member";
	}
	if (name == "") {
		layer.alert("分类名称不能为空！", 3, '提示');
		return;
	} else {
		var url = "ajax_addClassify";
		var params = {
			"userName" : name
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var result = resultData.result;
			if (result == "Y") {
				refresh(backPage);
			}
		});
	}
}
/**
 * 修改分类名称
 */
function updateClassify() {
	var classifyName = $("#updateClassifyText").val().split('-')[0];
	var classifyId = $("#updateClassifyText").val().split('-')[1];
	var newName = $('#updateClassifyName').val();
	var updateClassifyIOType = $('#updateClassifyIOType').val();
	if (classifyName == "first") {
		if (updateClassifyIOType == "O") {
			backPage = "l-out";
		} else if (updateClassifyIOType == "I") {
			backPage = "l-in";
		}
	} else if (classifyName == "second") {
		if (updateClassifyIOType == "O") {
			backPage = "l-out";
		} else if (updateClassifyIOType == "I") {
			backPage = "l-in";
		}
	} else if (classifyName == "business") {
		backPage = "l-store";
	} else if (classifyName == "project") {
		backPage = "l-pro";
	} else if (classifyName == "member") {
		backPage = "l-member";
	}
	if (newName == "") {
		layer.alert("分类名称不能为空！", 3, '提示');
		return;
	} else {
		var url = "ajax_updateClassify";
		var params = {
			"userName" : classifyName + '-' + classifyId + "-" + newName
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var result = resultData.result;
			if (result == "Y") {
				refresh(backPage);
			}
		});
	}
}
/**
 * 展示修改面板
 */
function showUpdatePanel(str) {
	var type = str.split('-')[0];
	var classifyId = str.split('-')[1];
	var classifyName = str.split('-')[2];
	if (str.split('-')[3] != null) {
		$('#updateClassifyIOType').val(str.split('-')[3]);
	}
	$("#updateClassifyText").val(type + '-' + classifyId);
	$('#updateClassifyName').val(classifyName);
	$.layer({
		shade : [ 0.5, '#000', true ],
		border : [ 9, 1, '#157069', true ],
		closeBtn : [ 1, true ],
		fadeIn : 500,
		type : 1,
		offset : [ '220px', '50%' ],
		area : [ '360px', '160px' ],
		title : "修改分类",
		page : {
			dom : "#updateClassifyPanel"
		}
	});
}
/**
 * 修改模板信息
 * 
 * @param tempStr
 */
function saveTemp(tempStr) {
	var tempId = tempStr.split('-')[0];
	var tempType = tempStr.split('-')[1];
	if ($("#templateName-" + tempId).val() == "") {
		layer.alert("请填写模板名称！", 3, '提示');
		return;
	} else if ($("#templateMoney-" + tempId).val() == "") {
		layer.alert("金额不能为空！", 3, '提示');
		return;
	}
	var url = "ajax_updateTemplate";
	var spending = JSON.stringify({
		templateName : $("#templateName-" + tempId).val(),
		ioType : tempType,
		firstLevelId : $("#firstSelect-" + tempId).val(),
		secondLevelId : $("#secondSelect-" + tempId).val(),
		accountId : $("#accountSelect-" + tempId).val(),
		projectId : $("#projectSelect-" + tempId).val(),
		businessId : $("#businessSelect-" + tempId).val(),
		memberId : $("#memberSelect-" + tempId).val(),
		money : $("#templateMoney-" + tempId).val(),
		remark : $("#tempMemo-" + tempId).val()
	});
	var params = {
		"userName" : spending + '-' + tempId
	};
	$.getJSON(url, params, function callback(data) {
		var resultData = eval("(" + data + ")");
		var str = resultData.result;
		if (str == "Y") {
			refresh('l-template');
		}
	});
}