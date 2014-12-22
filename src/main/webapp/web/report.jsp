<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="path_css" value="resources/css" />
<c:set var="path_js" value="resources/js" />
<c:set var="path_image" value="resources/image" />
<c:set var="path_layer" value="resources/layer" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${path_css}/report.css">
<script type="text/javascript" src="${path_js}/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${path_js}/report.js"></script>

</head>
<body>
	<div class="t png"></div>
	<div id="divBox" class="png">
		<div class="left">
			<h3></h3>
			<ul id="navUl">
				<li class="select" id="dailyLi"><a class=""
					onclick="change('daily');"> <span class="icon icon1 png"></span>
						日常收支表
				</a></li>
				<li class="" id="trendLi"><a class=""
					onclick="change('trend');"> <span class="icon icon8 png"></span>
						收支趋势图
				</a></li>
				<li class="" id="memberLi"><a class=""
					onclick="change('member');"> <span class="icon icon10 png"></span>
						成员收支表
				</a></li>
			</ul>
		</div>
		<div class="right" id="report_daily">
			<div class="rTit"></div>
			<div id="daily">
				<div class="h3">
					<h3>日常收支表</h3>
					<div id="Unit">
						<div style="text-align: right;">(单位：元)</div>
					</div>
				</div>
				<div id="all" style="display: block;">
					<div class="rowH" id="spendingTitle">支出</div>
					<div id="s">
						<iframe src="flex/report.html" frameborder="0" width="760px"
							height="340px"></iframe>
					</div>
					<table class="tableList" border="0" cellspacing="0" cellpadding="0"
						style="width: 100%;">
						<tbody>
							<tr>
								<th class="first" width="70%"><span> 一级分类 </span></th>
								<th class="end" width="30%"><span>金额</span></th>
							</tr>
						</tbody>
					</table>
					<div id="tbOut" class="listDiv">
						<c:forEach items="${firstSpendingMoneyList }"
							var="firstSpendingMoney">
							<ul class="listRow row0">
								<li class="li1">${firstSpendingMoney.name }</li>
								<li class="tRight money li3">¥${firstSpendingMoney.money }</li>
							</ul>
						</c:forEach>
					</div>
					<div class="list-shadow">
						<div class="list-shadow-in"></div>
					</div>
					<div id="allSpendingMoneyText" class="allMoneyText">
						总计： <span class="money">¥${allSpendingMoney }</span>
					</div>
					<br></br>
					<div class="rowH" id="incomeText">收入</div>
					<div id="chartInP">
						<iframe src="flex/incomeReport.html" frameborder="0" width="760px"
							height="340px"></iframe>
					</div>
					<table class="tableList" border="0" cellspacing="0" cellpadding="0"
						style="width: 100%;">
						<tbody>
							<tr>
								<th class="first" width="70%"><span> 一级分类 </span></th>
								<th class="end" width="30%"><span>金额</span></th>
							</tr>
						</tbody>
					</table>
					<div id="tbIn" class="listDiv">
						<c:forEach items="${firstIncomeMoneyList }" var="firstIncomeMoney">
							<ul class="listRow row0">
								<li class="li1">${firstIncomeMoney.name }</li>
								<li class="tRight money li3">¥${firstIncomeMoney.money }</li>
							</ul>
						</c:forEach>
					</div>
					<div class="list-shadow">
						<div class="list-shadow-in"></div>
					</div>
					<div id="allIncomeMoneyText" class="allMoneyText"
						style="color: #891B1A;">
						总计： <span class="money">¥${allIncomeMoney }</span>
					</div>
				</div>
			</div>
		</div>
		<div class="right" style="display: none;" id="report_trend">
			<div class="rTit"></div>
			<div id="trend">
				<div class="h3">
					<h3>收支趋势图</h3>
					<div id="hTime1">2014</div>
					<div id="Unit1">
						<div style="text-align: right;">(单位：元)</div>
					</div>
				</div>
				<div id="trendDivP" class="chartBox">
					<iframe src="flex/lineChart.html" frameborder="0" width="760px"
						height="350px"></iframe>
				</div>
				<table id="trendTable" class="tableList" border="0" cellspacing="0"
					cellpadding="0" style="width: 100%;">
					<tbody>
						<tr>
							<th class="first" width="120"><span>时间</span></th>
							<th>收入</th>
							<th>支出</th>
							<th class="end"><span>结余</span></th>
						</tr>
					</tbody>
					<tbody id="tb1">
						<tr class="rowH3">
							<td class="td1">总计：</td>
							<td class="td2 money">¥${allIncomeMoney }</td>
							<td class="td3 money">¥${allSpendingMoney }</td>
							<td class="td4 money">¥${allIncomeMoney - allSpendingMoney }</td>
						</tr>
						<c:forEach items="${monthMoneyList }" var="monthMoney">
							<tr class="rowH1">
								<td class="td1">${monthMoney.month }月</td>
								<td class="td2 money">¥${monthMoney.incomeM }</td>
								<td class="td3 money">¥${monthMoney.spendingM }</td>
								<td class="td4 money">¥${monthMoney.incomeM -
									monthMoney.spendingM }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="list-shadow">
					<div class="list-shadow-in"></div>
				</div>
			</div>
		</div>
		<div class="right" style="display: none;" id="report_member">
			<div class="rTit"></div>
			<div class="h3">
				<h3>成员收支表</h3>
				<div id="hTime"></div>
				<div id="Unit">
					<div style="text-align: right;">(单位：元)</div>
				</div>
			</div>
			<div>
				<iframe src="flex/pieChart.html" frameborder="0" width="760px"
					height="340px"></iframe>
			</div>
			<div id="all">
				<div style="overflow: auto;">
					<div id="assetOut" class="rp_tab"
						style="width: 365px; float: left;">
						<div style="text-align: center;">
							<div class="bigtit">
								<div class="bigtit-out">
									<div class="bigtit-in">
										总支出： <span id="outAmount" class="money"
											style="color: #097D76;">¥${allSpendingMoney }</span>
									</div>
								</div>
							</div>
						</div>
						<br></br>
						<table class="tableList" border="0" cellspacing="0"
							cellpadding="0" style="width: 100%;">
							<tbody>
								<tr>
									<th class="first" width="140"><span>成员</span></th>
									<th width="155"><span>金额</span></th>
									<th class="end"><span>占比</span></th>
								</tr>
							</tbody>
							<tbody id="tbOut">
								<c:forEach items="${memberMoneyList }" var="memberMoney">
									<tr class="row-0">
										<td class="tLeft" style="padding-left: 30px;">${memberMoney.memberName}</td>
										<td class="money double"
											style="text-align: right; padding-right: 40px;">${memberMoney.memberO}</td>
										<td class="tRight"><fmt:formatNumber type="number"
												value="${memberMoney.memberO
											/ allSpendingMoney *100}"
												maxFractionDigits="2" />%</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="list-shadow">
							<div class="list-shadow-in"></div>
						</div>
					</div>
					<div id="assetIn" class="rp_tab"
						style="width: 365px; float: right; margin-right: 10px;">
						<div style="text-align: center;">
							<div class="bigtit">
								<div class="bigtit-out">
									<div class="bigtit-in">
										总收入：<span id="inAmount" class="money" style="color: #F00;">¥${allIncomeMoney
											}</span>
									</div>
								</div>
							</div>
						</div>
						<br></br>
						<table class="tableList" border="0" cellspacing="0"
							cellpadding="0" style="width: 100%;">
							<tbody>
								<tr>
									<th class="first" width="140"><span>成员</span></th>
									<th width="155"><span>金额</span></th>
									<th class="end"><span>占比</span></th>
								</tr>
							</tbody>
							<tbody id="tbIn">
								<c:forEach items="${memberMoneyList }" var="memberMoney">
									<tr class="row-0">
										<td class="tLeft" style="padding-left: 30px;">${memberMoney.memberName
											}</td>
										<td class="money double"
											style="text-align: right; padding-right: 40px;">${memberMoney.memberI
											}</td>
										<td class="tRight"><fmt:formatNumber type="number"
												value="${memberMoney.memberI
											/ allIncomeMoney *100}"
												maxFractionDigits="2" />%</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="list-shadow">
							<div class="list-shadow-in"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="b png"></div>
</body>
</html>