<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="page" uri="http://com.cpw.pas/page"%>
<c:set var="path_css" value="resources/css" />
<c:set var="path_js" value="resources/js" />
<c:set var="path_image" value="resources/image" />
<c:set var="path_image_set" value="resources/image/set" />
<c:set var="path_layer" value="resources/layer" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${path_css}/set.css">
<script type="text/javascript" src="${path_js}/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${path_layer}/layer.js"></script>
<script type="text/javascript" src="${path_js}/set.js"></script>
</head>
<body>
	<input id="flag" type="hidden" value="${flag }" />
	<div class="t png"></div>
	<div id="divBox" class="png">
		<div class="right">
			<div class="left">
				<h3></h3>
				<ul>
					<li class="select" id="li-user" onclick="changeLi('li-user');"><a
						href="javascript:;"> <span class="icon png"
							style="background-position: -312px 0;"></span> 个人中心
					</a></li>
					<li class="" id="li-log" onclick="changeLi('li-log');"><a
						href="javascript:;"> <span class="icon png"
							style="background-position: -72px 0;"></span>操作日志
					</a></li>
					<li class="" id="li-safe" onclick="changeLi('li-safe');"><a
						href="javascript:;"> <span class="icon png"
							style="background-position: -96px 0;"></span> <span
							style="display: inline-block;">安全设置 </span>
					</a></li>
				</ul>
				<div
					style="height: 2px; line-height: 0px; width: 187px; margin-left: 1px; backg…round: url('') left top no-repeat;"></div>
			</div>
			<div id="li-user-div" class="rContent">
				<div class="rTit">
					<span class="rTit-icon png"></span> <a class="text"
						href="javascript:;">个人中心</a>
				</div>
				<div class="box1">
					<div id="box-photo">
						<input id="box-photo-val-default" type="hidden" value=""></input>
						<input id="box-photo-val" type="hidden" value=""></input> <img
							id="box-photo-img" src="${path_image_set }/photo_default.gif"></img>
						<div id="box-photo-panel" class="hidden" style="display: none;">
							<div id="box-photo-panel-bg"></div>
							<div id="box-photo-panel-btn-1" class="box-photo-panel-btn"
								style="display: none;">
								<a id="box-photo-add" class="png" onclick="upImagePanel();"
									href="javascript:;"> 上传头像 </a>
							</div>
							<div id="box-photo-panel-btn-2" class="box-photo-panel-btn">
								<a id="box-photo-edit" class="png" onclick="upImagePanel();"
									href="javascript:;"> 更改 </a> <span> | </span> <a
									id="box-photo-del" class="png" onclick="delPhoto();"
									href="javascript:;"> 删除 </a>
							</div>
						</div>
						<div id="upImagePanel" style="display: none;">
							<form action="" id="upImageForm" method="post"
								onsubmit="return chackImage();">
								<div id="fileMsg"></div>
								<input type="file" id="imagefile" name="imagefile" /> <input
									type="submit" value="" class="imagefileSubmit"
									id="imagefileSubmit" />
							</form>
						</div>
					</div>
					<dl id="box-info">
						<dt>
							<a id="nickName" onclick="editNickname();" href="javascript:;"
								style="display: inline-block;">${userInfo.nickName }</a> <input
								id="nickNameInput" class="hidden" type="text"
								value="${userInfo.nickName }" maxlength="20"
								name="nickNameInput" style="display: none;"
								onblur="changeNickName();"></input>
						</dt>
						<dd>
							<br /> <label class="label1">账</label><label>号:</label> <span
								class="email">${userInfo.userName }</span><br /> <br /> <label>绑定邮箱:</label><span
								class="email">${userInfo.email }</span>
						</dd>
					</dl>
					<div class="clear"></div>
				</div>
			</div>
			<div id="li-log-div" class="rContent" style="display: none;">
				<div class="cat_t">
					<div class="rTit">
						<span class="rTit-icon png"></span> <span class="text">操作日志</span>
					</div>
				</div>
				<div id="list">
					<div class="list-top">
						<ul></ul>
					</div>
					<div class="list-tit">
						<ul style="float: left;">
							<li class="l-1"><span>操作类型</span></li>
							<li class="l-line"><span></span></li>
							<li class="l-2">操作描述</li>
							<li class="l-line"><span></span></li>
							<li class="l-3">操作时间</li>
							<li class="l-line"><span></span></li>
							<li class="l-4">操 作</li>
						</ul>
					</div>
				</div>
				<div id="des">
					<c:forEach items="${logList }" var="log" varStatus="i">
						<div class="des-div"
							<c:if test="${i.index % 2 == 1}">style="background-color: #E5EFEE;"</c:if>>
							<ul>
								<li class="des-1">
									<c:if test="${log.operateType == 'I'}">登录</c:if>
									<c:if test="${log.operateType == 'O'}">退出</c:if>
									<c:if test="${log.operateType == 'A'}">添加</c:if>
									<c:if test="${log.operateType == 'U'}">更新</c:if>
									<c:if test="${log.operateType == 'D'}">删除</c:if>
								</li>
								<li class="des-2">${log.operateDes}</li>
								<li class="des-3">${log.operateTime}</li>
								<li class="des-4"><a class="btnDelete"
									onclick="delLog('${log.logId}');" href="javascript:;"></a></li>
							</ul>
						</div>
					</c:forEach>
				</div>
				<br />
				<page:pageTage pageSize="${pageInfo.pageSize }" url="set_init?"
					count="${pageInfo.count }" pageNum="${pageInfo.pageNum }"></page:pageTage>
			</div>
			<div id="li-safe-div" class="rContent" style="display: none;">
				<div class="rTit">
					<span class="rTit-icon png"
						style="background-position: -191px top;"></span> <span
						class="text"> 安全设置 </span>
				</div>
				<div class="label">
					<div class="labelname">密码保护</div>
					<a class="Btn" href="javascript:;" onclick="showChangePanel();">
						修改密码 </a>
					<dl>
						<dt>密码设置说明:</dt>
					</dl>
					<ul class="desc">
						<li><span> 1 </span>
							密码长度6－16位，建议设置8位以上的复杂密码，最好是英文字母、数字、特殊符号组合！</li>
						<li><span> 2 </span> 您也可以定期修改密码，以增强安全性。</li>
					</ul>
				</div>
				<div id="changePass" class="changePass">
					<h1 class="changePassTitle">密码修改</h1>
					<hr width="100%" />
					<fieldset id="changePassInput">
						<h3 class="lable">原始密码</h3>
						<div id="checkOldPass" class="checkLable"></div>
						<input id="oldPass" type="password" onblur="checkOldPass();" />
						<h3 class="lable">新密码</h3>
						<div id="checkNewPass" class="checkLable"></div>
						<input id="newPass" type="password" onblur="checkNewPass();" />
						<h3 class="lable">确认密码</h3>
						<div id="checkConfirmPass" class="checkLable"></div>
						<input id="confirmPass" type="password"
							onblur="checkConfirmPass();" />
					</fieldset>
					<input class="changePassSubmit" type="button"
						value="修&nbsp;&nbsp;&nbsp;&nbsp;改"
						onclick="changePass();" />
				</div>
			</div>
		</div>
	</div>
	<div class="b png"></div>
</body>
</html>