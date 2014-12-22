<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path_css" value="resources/css" />
<c:set var="path_js" value="resources/js" />
<c:set var="path_image" value="resources/image" />
<c:set var="path_image_main" value="resources/image/main" />
<c:set var="path_layer" value="resources/layer" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>记帐通 -- 人人皆可理财！</title>
<link rel="shortcut icon" type="image/x-icon"
	href="${path_image}/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${path_css}/main.css">
<script type="text/javascript" src="${path_js}/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${path_js}/jquery.formatCurrency-1.4.0.js"></script>
<script type="text/javascript" src="${path_layer}/layer.js"></script>
<script type="text/javascript" src="${path_js}/main.js"></script>
</head>
<body>
	<input id="hiddenMessage" type="hidden" value="${msg}" />
	<div id="header">
		<div id="header-menu">
			<div id="header-menu-container">
				<ul id="header-menu-ul">
					<li class="l-welcome">欢迎您: <span id="header-nickname">${sessionScope.nickName}
					</span>
					</li>
					<li><a href="set_init" style="color: #FFF;">个人中心</a></li>
					<li class="l-exit"><a href="user_logout" style="color: #AAA;">退出</a></li>
				</ul>
			</div>
		</div>
		<div id="header-container">
			<div id="logo">
				<img alt="记帐通" src="${path_image}/logo01.png">
			</div>
			<div id="nav">
				<ul>
					<li class="l-tally"><a
						<c:if test="${msg != 'tally'}">
							class="a"
						</c:if>
						<c:if test="${msg == 'tally'}">
							class="a select"
						</c:if>
						href="tally_init?par=fb-month"></a></li>
					<li class="l-report"><a
						<c:if test="${msg != 'report'}">
							class="a"
						</c:if>
						<c:if test="${msg == 'report'}">
							class="a select"
						</c:if>
						href="report_init"></a></li>
					<li class="l-classify"><a
						<c:if test="${msg != 'classify'}">
							class="a"
						</c:if>
						<c:if test="${msg == 'classify'}">
							class="a select"
						</c:if>
						href="classify_init"></a></li>
					<li class="l-set"><a
						<c:if test="${msg != 'set'}">
							class="a"
						</c:if>
						<c:if test="${msg == 'set'}">
							class="a select"
						</c:if>
						href="set_init"></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="bg1">
		<div id="bg1-c"></div>
	</div>
	<div id="bg2"></div>
	<div id="mainContent">
		<c:if test="${msg == 'tally'}">
			<jsp:include page="tally.jsp"></jsp:include>
		</c:if>
		<c:if test="${msg == 'report'}">
			<jsp:include page="report.jsp"></jsp:include>
		</c:if>
		<c:if test="${msg == 'classify'}">
			<jsp:include page="classify.jsp"></jsp:include>
		</c:if>
		<c:if test="${msg == 'set'}">
			<jsp:include page="set.jsp"></jsp:include>
		</c:if>
	</div>
	<div>
		<jsp:include page="bottom.jsp"></jsp:include>
	</div>
</body>
</html>