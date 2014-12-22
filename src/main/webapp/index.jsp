<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path_css" value="resources/css" />
<c:set var="path_js" value="resources/js" />
<c:set var="path_image" value="resources/image" />
<c:set var="path_layer" value="resources/layer" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>记帐通 -- 人人皆可理财！</title>
<link rel="shortcut icon" type="image/x-icon" href="${path_image}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${path_css}/index.css">
<link rel="stylesheet" type="text/css" href="${path_css}/login.css">
<link rel="stylesheet" type="text/css" href="${path_css}/register.css">
<script type="text/javascript" src="${path_js}/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${path_layer}/layer.js"></script>
<script type="text/javascript" src="${path_js}/login.js"></script>
</head>
<body>
	<div id="header" class="header">
		<div id="logo">
			<img alt="" src="${path_image}/logo01.png">
		</div>
		<div id="regTest">
			没有账号？&nbsp;&nbsp;&nbsp;<a href="javascript:register();">马上注册</a>
		</div>
	</div>
	<div class="scroll" id="idTransformView">
		<ul class="slider" id="idSlider">
			<li><img src="${path_image}/bg1.jpg" /></li>
			<li><img src="${path_image}/bg2.jpg" /></li>
			<li><img src="${path_image}/bg3.jpg" /></li>
		</ul>
		<ul class="num" id="idNum" style="display: none;">
			<li>1</li>
			<li>2</li>
			<li>3</li>
		</ul>
	</div>
	<div id="form">
		<div id="loginForm">
			<form id="login" action="user_login" method="post"
				onsubmit="return checkAll()">
				<fieldset id="inputs">
					<input id="username" type="text" name="userLogin.userName"
						onFocus="nameClear();" /> <input id="password" type="password"
						name="userLogin.userPass" onFocus="passClear();" />
				</fieldset>
				<fieldset id="actions">
					<input type="submit" id="submit" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" />
					<a href="">忘记密码?</a>
				</fieldset>
			</form>
		</div>
	</div>
	<input id="hiddenMessage" type="hidden" value="${messgae}" />
	<div id="registerDiv" class="registerDiv">
		<form id="registerForm" action="user_register" method="post"
			name="registerForm">
			<h1 class="registerTitle">用户注册</h1>
			<hr width="100%" />
			<fieldset id="registerInput">
				<h3 class="lable">用户名</h3>
				<div id="checkName" class="checkLable"></div>
				<input id="registerName" type="text" name="userLogin.userName"
					onblur="checkName();" />
				<h3 class="lable">密码</h3>
				<div id="checkPass" class="checkLable"></div>
				<input id="registerPass" type="password" name="userLogin.userPass"
					onblur="checkPass();" />
				<h3 class="lable">确认密码</h3>
				<div id="checkConfirmPass" class="checkLable"></div>
				<input id="confirmPass" type="password" onblur="checkConfirmPass();" />
			</fieldset>
			<input class="registerSubmit" type="button"
				value="注&nbsp;&nbsp;&nbsp;&nbsp;册" onclick="registerFormSubmit();" />
		</form>
	</div>
	<div id="south" class="south">
		<div class="container">
			<div class="exciting">
				<div class="e-l">
					<div class="el-div el-div-1">
						<h3>
							<a>超支预警，多渠道按需提醒</a>
						</h3>
						<a class="el-2 el">预算不再是一个参考，可以按照您的需要及时提醒您花钱超支。</a>
					</div>
					<div class="el-div">
						<h3>
							<a>动态图文报表直观呈现</a>
						</h3>
						<a class="el-3 el">动态呈现的图文报表，可以直观的报告您的开支情况。</a>
					</div>
					<div class="el-div el-div-last">
						<h3>
							<a>个人财报，短信、邮件按时报告</a>
						</h3>
						<a class="el-4 el">按需定制提醒和选择报告，用自己喜欢的方式去了解个人财报。</a>
					</div>
				</div>
				<div class="e-r"></div>
			</div>
		</div>
	</div>
	<div>
		<jsp:include page="web/bottom.jsp"></jsp:include>
	</div>
</body>
</html>