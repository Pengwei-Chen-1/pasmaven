$(function() {
	$("#tb-money").on("blur", function() {
		$(this).formatCurrency();
	});
	$("#nav .l-tally a").on("mouseover", function() {
		if ($("#nav .l-tally a").attr("class") != "a select") {
			$(this).removeClass().addClass("a hover");
		}
	});
	$("#nav .l-tally a").on("mouseout", function() {
		if ($("#nav .l-tally a").attr("class") != "a select") {
			$(this).removeClass().addClass("a");
		}
	});
	$("#nav .l-classify a").on("mouseover", function() {
		if ($("#nav .l-classify a").attr("class") != "a select") {
			$(this).removeClass().addClass("a hover");
		}
	});
	$("#nav .l-classify a").on("mouseout", function() {
		if ($("#nav .l-classify a").attr("class") != "a select") {
			$(this).removeClass().addClass("a");
		}
	});
	$("#nav .l-report a").on("mouseover", function() {
		if ($("#nav .l-report a").attr("class") != "a select") {
			$(this).removeClass().addClass("a hover");
		}
	});
	$("#nav .l-report a").on("mouseout", function() {
		if ($("#nav .l-report a").attr("class") != "a select") {
			$(this).removeClass().addClass("a");
		}
	});
	$("#nav .l-set a").on("mouseover", function() {
		if ($("#nav .l-set a").attr("class") != "a select") {
			$(this).removeClass().addClass("a hover");
		}
	});
	$("#nav .l-set a").on("mouseout", function() {
		if ($("#nav .l-set a").attr("class") != "a select") {
			$(this).removeClass().addClass("a");
		}
	});
	$("#tb-save").on("mouseover", function() {
		$(this).css("cursor", "pointer");
		$(this).removeClass().addClass("hover");
	});
	$("#tb-save").on("mouseout", function() {
		$(this).removeClass().addClass("");
	});
	$("#tb-save").on("mousedown", function() {
		$(this).removeClass().addClass("active");
	});
	$("#tb-save").on("mouseup", function() {
		$(this).removeClass().addClass("");
	});
	$('#' + $('#parText').val()).removeClass().addClass('button fb-btn choose');
	$("#tb-datepicker").val($.formatDate("yyyy-MM-dd HH:mm", new Date()));
});

// 改变收支类型时触发的事件
function changeTallyType(num) {
	if (num == 1) {
		$("#tm-1 a").removeClass().addClass("select");
		$("#tm-5 a").removeClass().addClass("");
		$('#tallyTpe').val("spending");
		$("#businessLi").show();
		changeLevel();
	} else if (num == 5) {
		$("#tm-5 a").removeClass().addClass("select");
		$("#tm-1 a").removeClass().addClass("");
		$('#tallyTpe').val("income");
		$("#businessLi").hide();
		changeLevel();
	}
}
// 切换收支类型时
function changeLevel() {
	// 请求url
	var url = "ajax_getAllFirstLevelByType";
	// 请求参数
	var params = {};
	// 判断收支类型
	if ($('#tallyTpe').val() == "spending") {
		// 收支类型为支出时，参数设为"spending"
		params = {
			"userName" : "spending"
		};
	} else if ($('#tallyTpe').val() == "income") {
		// 参数为收入时，参数设为"income"
		params = {
			"userName" : "income"
		};
	}
	// 进行异步调用
	$.getJSON(url, params, function callback(data) {
		// 获取传回的数据并使用eval函数解析返回的JSON对象
		var resultData = eval("(" + data + ")");
		// 根据result获取数据中的值
		var result = resultData.result;
		// 清空一级分类下拉菜单
		$("#firstSpendingLevel").empty();
		// 初始化二级菜单的所属一级分类编号
		var firstId = 1;
		// 循环为一级分类下拉菜单添加选项
		for ( var i = 0; i < result.length; i++) {
			// 获取第一个一级分类编号
			firstId = result[0].firstLevelId;
			$("#firstSpendingLevel").append(
					"<option value=" + result[i].firstLevelId + ">"
							+ result[i].firstLevelName + "</option>");
		}
		// 根据tempList获取返回值中的模板列表
		var tempList = resultData.tempList;
		// 清空模板下拉菜单
		$('#templateSelect').empty();
		// 给模板下拉菜单赋默认值并选中
		$("#templateSelect").append(
				"<option value='-1' selected='selected'>选择模板</option>");
		// 循环为下拉菜单添加选项
		for ( var int = 0; int < tempList.length; int++) {
			$("#templateSelect").append(
					"<option value=" + tempList[int].templateId + ">"
							+ tempList[int].templateName + "</option>");
		}
		// 根据一级分类编号改变二级分类下拉 
		changeSecondLevel(firstId);
	});
}

// 展示图片面板
function showImagePanel(type) {
	if (type == "over") {
		$("#img-panel-add").css("display", "block");
	} else if (type == "out") {
		$("#img-panel-add").css("display", "none");
	}
}
// 弹出上传图片的面板
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
// 检查图片类型
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
// 鼠标经过列表时改变样式
function listOver(id) {
	if ($("#" + id).attr("class") != "list-tit list-tit-last list-show") {
		$("#" + id).removeClass().addClass("list-tit list-tit-last list-hover");
	}
}
// 鼠标离开列表时改变样式
function listOut(id) {
	if ($("#" + id).attr("class") != "list-tit list-tit-last list-show") {
		$("#" + id).removeClass().addClass("list-tit list-tit-last");
	}
}
// 鼠标点击列表时改变样式
function listClick(id) {
	if ($("#" + id).attr("class") != "list-tit list-tit-last list-show") {
		$("#" + id).removeClass().addClass("list-tit list-tit-last list-show");
	} else {
		$("#" + id).removeClass().addClass("list-tit list-tit-last list-hover");
	}
	var str = id.split("list-tit-")[1];
	$("#list-box-" + str).slideToggle(100);
}
// 一级分类选择改变时改变二级选择中的选项
function changeFirstLevel() {
	var firstId = $("#firstSpendingLevel").val();
	changeSecondLevel(firstId);
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
// 显示对应的添加面板
function showAddPanel(panelName) {
	if (panelName == "firstLevel" && $('#tallyTpe').val() == "spending") {
		$.layer({
			shade : [ 0.7, '#000', true ],
			border : [ 9, 1, '#157069', true ],
			closeBtn : [ 1, true ],
			fadeIn : 500,
			type : 1,
			offset : [ '220px', '50%' ],
			area : [ '360px', '160px' ],
			title : "添加一级支出分类",
			page : {
				dom : "#upFirstSpendingPanel"
			}
		});
	} else if (panelName == "secondLevel" && $('#tallyTpe').val() == "spending") {
		$.layer({
			shade : [ 0.7, '#000', true ],
			border : [ 9, 1, '#157069', true ],
			closeBtn : [ 1, true ],
			fadeIn : 500,
			type : 1,
			offset : [ '220px', '50%' ],
			area : [ '360px', '160px' ],
			title : "添加二级支出分类",
			page : {
				dom : "#upSecondSpendingPanel"
			}
		});
	} else if (panelName == "firstLevel" && $('#tallyTpe').val() == "income") {
		$.layer({
			shade : [ 0.7, '#000', true ],
			border : [ 9, 1, '#157069', true ],
			closeBtn : [ 1, true ],
			fadeIn : 500,
			type : 1,
			offset : [ '220px', '50%' ],
			area : [ '360px', '160px' ],
			title : "添加一级收入分类",
			page : {
				dom : "#upFirstIncomePanel"
			}
		});
	} else if (panelName == "secondLevel" && $('#tallyTpe').val() == "income") {
		$.layer({
			shade : [ 0.7, '#000', true ],
			border : [ 9, 1, '#157069', true ],
			closeBtn : [ 1, true ],
			fadeIn : 500,
			type : 1,
			offset : [ '220px', '50%' ],
			area : [ '360px', '160px' ],
			title : "添加二级收入分类",
			page : {
				dom : "#upSecondIncomePanel"
			}
		});
		var url = "ajax_getAllFirstLevelByType";
		var params = {
			"userName" : "income"
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var result = resultData.result;
			$("#upSecondIncomeLevel").empty();
			for ( var i = 0; i < result.length; i++) {
				$("#upSecondIncomeLevel").append(
						"<option value=" + result[i].firstLevelId + ">"
								+ result[i].firstLevelName + "</option>");
			}
		});
	} else if (panelName == "account") {
		$.layer({
			shade : [ 0.7, '#000', true ],
			border : [ 9, 1, '#157069', true ],
			closeBtn : [ 1, true ],
			fadeIn : 500,
			type : 1,
			offset : [ '220px', '50%' ],
			area : [ '360px', '160px' ],
			title : "添加账户",
			page : {
				dom : "#upAccountPanel"
			}
		});
	} else if (panelName == "project") {
		$.layer({
			shade : [ 0.7, '#000', true ],
			border : [ 9, 1, '#157069', true ],
			closeBtn : [ 1, true ],
			fadeIn : 500,
			type : 1,
			offset : [ '220px', '50%' ],
			area : [ '360px', '160px' ],
			title : "添加项目",
			page : {
				dom : "#upProjectPanel"
			}
		});
	} else if (panelName == "business") {
		$.layer({
			shade : [ 0.7, '#000', true ],
			border : [ 9, 1, '#157069', true ],
			closeBtn : [ 1, true ],
			fadeIn : 500,
			type : 1,
			offset : [ '220px', '50%' ],
			area : [ '360px', '160px' ],
			title : "添加商家",
			page : {
				dom : "#upBusinessPanel"
			}
		});
	} else if (panelName == "member") {
		$.layer({
			shade : [ 0.7, '#000', true ],
			border : [ 9, 1, '#157069', true ],
			closeBtn : [ 1, true ],
			fadeIn : 500,
			type : 1,
			offset : [ '220px', '50%' ],
			area : [ '360px', '160px' ],
			title : "添加家庭成员",
			page : {
				dom : "#upMemberPanel"
			}
		});
	}
}
// 添加一级分类
function addFirstLevel(IOType) {
	var name = "";
	if (IOType == "O") {
		name = $("#firstSpendingLevelName").val();
	} else if (IOType == "I") {
		name = $("#firstIncomeLevelName").val();
	}
	if (name == "") {
		layer.alert("分类名称不能为空！", 3, '提示');
		return;
	} else {
		var url = "ajax_addFirstLevel";
		var params = {
			"userName" : name + "," + IOType
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var firstLevelList = resultData.result;
			$("#firstSpendingLevel").empty();
			for ( var i = 0; i < firstLevelList.length; i++) {
				$("#firstSpendingLevel").append(
						"<option value=" + firstLevelList[i].firstLevelId + ">"
								+ firstLevelList[i].firstLevelName
								+ "</option>");
			}
			layer.alert("添加成功！", 1, '提示');
		});
	}
}
// 添加账户信息
function addAccount() {
	var name = $("#accountName").val();
	if (name == "") {
		layer.alert("名称不能为空！", 3, '提示');
		return;
	} else {
		var url = "ajax_addAccount";
		var params = {
			"userName" : name
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var accountList = resultData.result;
			$("#accountSelect").empty();
			for ( var i = 0; i < accountList.length; i++) {
				$("#accountSelect").append(
						"<option value=" + accountList[i].accountId + ">"
								+ accountList[i].accountName + "</option>");
			}
			layer.alert("添加成功！", 1, '提示');
		});
	}
}
// 添加家庭成员
function addMember() {
	var name = $("#memberName").val();
	if (name == "") {
		layer.alert("名称不能为空！", 3, '提示');
		return;
	} else {
		var url = "ajax_addMember";
		var params = {
			"userName" : name
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var familyList = resultData.result;
			$("#memberSelect").empty();
			for ( var i = 0; i < familyList.length; i++) {
				$("#memberSelect").append(
						"<option value=" + familyList[i].memberId + ">"
								+ familyList[i].memberName + "</option>");
			}
			layer.alert("添加成功！", 1, '提示');
		});
	}
}
// 添加项目信息
function addProject() {
	var name = $("#projectrName").val();
	if (name == "") {
		layer.alert("名称不能为空！", 3, '提示');
		return;
	} else {
		var url = "ajax_addProject";
		var params = {
			"userName" : name
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var projectList = resultData.result;
			$("#projectSelect").empty();
			for ( var i = 0; i < projectList.length; i++) {
				$("#projectSelect").append(
						"<option value=" + projectList[i].projectId + ">"
								+ projectList[i].projectName + "</option>");
			}
			layer.alert("添加成功！", 1, '提示');
		});
	}
}
// 添加商家信息
function addBusiness() {
	var name = $("#businessName").val();
	if (name == "") {
		layer.alert("名称不能为空！", 3, '提示');
		return;
	} else {
		var url = "ajax_addBusiness";
		var params = {
			"userName" : name
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var businessList = resultData.result;
			$("#businessSelect").empty();
			for ( var i = 0; i < businessList.length; i++) {
				$("#businessSelect").append(
						"<option value=" + businessList[i].businessId + ">"
								+ businessList[i].businessName + "</option>");
			}
			layer.alert("添加成功！", 1, '提示');
		});
	}
}
// 添加二级分类列表
function addSecondLevel() {
	var name = "";
	var firstLevelId = "";
	var IOType = "";
	if ($("#tallyTpe").val() == "spending") {
		name = $("#secondSpendingLevelName").val();
		firstLevelId = $("#upLevel").val();
		IOType = "O";
	} else if ($("#tallyTpe").val() == "income") {
		name = $("#secondIncomeLevelName").val();
		firstLevelId = $("#upSecondIncomeLevel").val();
		IOType = "I";
	}
	if (name == "") {
		layer.alert("名称不能为空！", 3, '提示');
		return;
	} else {
		var url = "ajax_addSecondLevel";
		var params = {
			"userName" : name + "," + firstLevelId + "," + IOType
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var secondList = resultData.result;
			$("#secondLevelSelect").empty();
			for ( var i = 0; i < secondList.length; i++) {
				$("#secondLevelSelect").append(
						"<option value=" + secondList[i].secondLevelId + ">"
								+ secondList[i].secondLevelName + "</option>");
			}
			layer.alert("添加成功！", 1, '提示');
		});
	}
}
// 添加收支信息
function addTally() {
	if ($("#tb-money").val() == "") {
		layer.alert("金额不能为空！", 3, '提示');
		return;
	}
	var url = "";
	var params = {};
	if ($("#tallyTpe").val() == "spending") {
		url = "ajax_addSpending";
		var spending = JSON.stringify({
			image : "",
			firstLevelId : $("#firstSpendingLevel").val(),
			secondLevelId : $("#secondLevelSelect").val(),
			accountId : $("#accountSelect").val(),
			projectId : $("#projectSelect").val(),
			businessId : $("#businessSelect").val(),
			memberId : $("#memberSelect").val(),
			money : $("#tb-money").val(),
			createTime : $("#tb-datepicker").val(),
			remark : $("#tb-memo").val()
		});
		params = {
			"userName" : spending
		};
	} else if ($("#tallyTpe").val() == "income") {
		url = "ajax_addIncome";
		var income = JSON.stringify({
			image : "",
			firstLevelId : $("#firstSpendingLevel").val(),
			secondLevelId : $("#secondLevelSelect").val(),
			accountId : $("#accountSelect").val(),
			projectId : $("#projectSelect").val(),
			memberId : $("#memberSelect").val(),
			money : $("#tb-money").val(),
			createTime : $("#tb-datepicker").val(),
			remark : $("#tb-memo").val()
		});
		params = {
			"userName" : income
		};
	}
	$.getJSON(url, params, function callback(data) {
		var resultData = eval("(" + data + ")");
		var str = resultData.result;
		if (str == "Y") {
			window.location.reload();
		}
	});
}

/**
 * 点击添加备注时
 */
function clearMemo() {
	var memoStr = $('#tb-memo').val();
	if(memoStr == '填写备注'){
		$('#tb-memo').val("");
	}
}

// 切换模板时修改信息
function changeTemplate() {
	var tempId = $('#templateSelect').val();
	if (tempId != -1) {
		var url = "ajax_getTemplateById";
		var params = {
			"userName" : tempId
		};
		$.getJSON(url, params, function callback(data) {
			var resultData = eval("(" + data + ")");
			var template = resultData.result;
			$('#firstSpendingLevel').val(template.firstLevelId);
			$('#accountSelect').val(template.accountId);
			$('#projectSelect').val(template.projectId);
			$('#businessSelect').val(template.businessId);
			$('#memberSelect').val(template.memberId);
			$('#tb-money').val(template.money);
			$('#tb-memo').val(template.remark);
			changeSecondVal(template.firstLevelId, template.secondLevelId);
		});
	} else {

	}
}

// 改变二级分类的值
function changeSecondVal(firstId, secondId) {
	$("#secondLevelSelect").empty();
	var url = "ajax_getSecondLevelByFirstId";
	var params = {
		"userName" : firstId + ""
	};
	$.getJSON(url, params, function callback(data) {
		var resultData = eval("(" + data + ")");
		var result = resultData.result;
		for ( var i = 0; i < result.length; i++) {
			var str1 = "";
			str1 = "<option value=" + result[i].secondLevelId + ">";
			if (result[i].secondLevelId == secondId) {
				str1 = "<option value=" + result[i].secondLevelId
						+ " selected='selected' >";
			}
			$("#secondLevelSelect").append(
					str1 + result[i].secondLevelName + "</option>");
		}
	});
}

// 改变显示类型时触发
function changeParType(id) {
	var fbList = new Array('fb-all', 'fb-year', 'fb-month');
	for ( var i in fbList) {
		$('#' + fbList[i]).removeClass().addClass('button fb-btn');
	}
	$('#' + id).removeClass().addClass('button fb-btn choose');
	$('#parText').val(id);
	window.location.href = "tally_init?par=" + id;
}

/**
 * 搜索
 */
function searchTally() {
	var str = $('#search-key').val();
	if (str == "") {
		return;
	}
	window.location.href = "tally_init?par=fb-all&searchKey=" + str;
}

/**
 * 展开全部
 */
function openAll() {
	$('div[id^=list-box-]').slideToggle(100);
	var str = $('#openAllText').text();
	if (str == "全部折叠") {
		$('#openAllText').text('全部展开');
	} else {
		$('#openAllText').text('全部折叠');
	}
}

/**
 * 删除收支信息
 * 
 * @param id
 *            收支信息ID
 */
function delTally(id) {
	$.layer({
		shade : [ 0.3, '#000', true ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '您确定要删除这条记录么？',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var url = "ajax_delTally";
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
 * 更新收支信息
 */
function saveTally(id) {
	var s = id.split('-');
	var url = "ajax_saveTally";
	var params = {};
	if (s[0] == 'O') {
		var spending = JSON.stringify({
			spendingId : s[1],
			image : "",
			firstLevelId : $('#firstSelect-' + id).val(),
			secondLevelId : $('#secondSelect-' + id).val(),
			accountId : $('#account-' + id).val(),
			projectId : $('#projectSelect-' + id).val(),
			businessId : $('#business-' + id).val(),
			memberId : $('#member-' + id).val(),
			money : $('#tallyMoney-' + id).val(),
			createTime : $('#tallyTime-' + id).val(),
			remark : $('#memo-' + id).val()
		});
		params = {
			"userName" : spending + '@' + s[0]
		};
	} else if (s[0] == 'I') {
		var income = JSON.stringify({
			incomeId : s[1],
			image : "",
			firstLevelId : $('#firstSelect-' + id).val(),
			secondLevelId : $('#secondSelect-' + id).val(),
			accountId : $('#account-' + id).val(),
			projectId : $('#projectSelect-' + id).val(),
			memberId : $('#member-' + id).val(),
			money : $('#tallyMoney-' + id).val(),
			createTime : $('#tallyTime-' + id).val(),
			remark : $('#memo-' + id).val()
		});
		params = {
			"userName" : income + '@' + s[0]
		};
	}
	$.getJSON(url, params, function callback(data) {
		var resultData = eval("(" + data + ")");
		var str = resultData.result;
		if (str == "Y") {
			window.location.reload();
		}
	});
}

/**
 * 改变一级分类时
 * 
 * @param id
 */
function changeFirst(id) {
	$("#secondSelect-" + id).empty();
	var firstId = $('#firstSelect-' + id).val();
	alert(firstId);
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