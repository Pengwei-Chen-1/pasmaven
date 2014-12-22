<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path_css" value="resources/css" />
<c:set var="path_js" value="resources/js" />
<c:set var="path_image" value="resources/image" />
<c:set var="path_image_tally" value="resources/image/tally" />
<c:set var="path_layer" value="resources/layer" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${path_css}/tally.css">
<script type="text/javascript" src="${path_js}/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${path_js}/jquery.formatDate.js"></script>
<script type="text/javascript"
	src="${path_js}/jquery.formatCurrency-1.4.0.js"></script>
<script type="text/javascript" src="${path_layer}/layer.js"></script>
<script type="text/javascript" src="${path_js}/main.js"></script>
<script type="text/javascript"
	src="${path_js}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<input type="hidden" id="tallyTpe" value="spending" />
	<input id="parText" type="hidden" value="fb-month" />
	<div id="tallyType">
		<div id="type-l"></div>
		<div id="type-m">
			<div id="type-menu">
				<ul id="type-menu-ul">
					<li id="tm-1" class="tm-n"><a class="select"
						onclick="javascript:changeTallyType(1);"></a></li>
					<li class="tm-l tm-l-no"></li>
					<li id="tm-5" class="tm-n"><a class=""
						onclick="javascript:changeTallyType(5);"></a></li>
				</ul>
			</div>
			<div id="type-box">
				<div id="tb-l">
					<div id="tb-img" class="img-box png"
						onmouseover="javascript:showImagePanel('over');"
						onmouseout="javascript:showImagePanel('out');">
						<img alt="" id="img-show-add" width="130" height="94"
							src="${path_image_tally }/default.gif">
						<div id="img-panel-add" class="img-panel" style="display: none;">
							<input id="img-name-add" class="img-name" type="hidden" value=""
								name="img-name" /> <span id="img-panel-upload"
								class="img-upload img-upload-center png"
								onclick="javascript:upImagePanel();">上传</span> <span
								id="img-del png" style="display: none;">删除</span>
						</div>
					</div>
				</div>
				<div id="upImagePanel">
					<form action="" id="upImageForm" method="post"
						onsubmit="return chackImage();">
						<div id="fileMsg"></div>
						<input type="file" id="imagefile" name="imagefile" /> <input
							type="submit" value="" class="imagefileSubmit"
							id="imagefileSubmit" />
					</form>
				</div>
				<div id="upFirstSpendingPanel" style="display: none;">
					<div class="panelDiv">
						<span class="panel-spanName">一级分类名称：</span> <input type="text"
							class="panel-inputName" id="firstSpendingLevelName" />
					</div>
					<input type="button" value="" class="imagefileSubmit"
						onclick="addFirstLevel('O');" />
				</div>
				<div id="upFirstIncomePanel" style="display: none;">
					<div class="panelDiv">
						<span class="panel-spanName">一级分类名称：</span> <input type="text"
							class="panel-inputName" id="firstIncomeLevelName" />
					</div>
					<input type="button" value="" class="imagefileSubmit"
						onclick="addFirstLevel('I');" />
				</div>
				<div id="upSecondSpendingPanel" style="display: none;">
					<div class="panelDiv" style="margin-top: 5px;">
						<span class="panel-spanName">上级分类：</span> <select
							class="choceSelect" id="upLevel">
							<c:forEach items="${firstLevelList}" var="firstLevel">
								<option value="${firstLevel.firstLevelId }">
									${firstLevel.firstLevelName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="panelDiv" style="margin-top: 5px;">
						<span class="panel-spanName">二级分类名称：</span> <input type="text"
							class="panel-inputName" id="secondSpendingLevelName" />
					</div>
					<input type="button" value="" class="imagefileSubmit"
						onclick="addSecondLevel();" />
				</div>
				<div id="upSecondIncomePanel" style="display: none;">
					<div class="panelDiv" style="margin-top: 5px;">
						<span class="panel-spanName">上级分类：</span> <select
							class="choceSelect" id="upSecondIncomeLevel">
						</select>
					</div>
					<div class="panelDiv" style="margin-top: 5px;">
						<span class="panel-spanName">二级分类名称：</span> <input type="text"
							class="panel-inputName" id="secondIncomeLevelName" />
					</div>
					<input type="button" value="" class="imagefileSubmit"
						onclick="addSecondLevel();" />
				</div>
				<div id="upAccountPanel" style="display: none;">
					<div class="panelDiv">
						<span class="panel-spanName">账户名称：</span> <input type="text"
							class="panel-inputName" id="accountName" />
					</div>
					<input type="button" value="" class="imagefileSubmit"
						onclick="addAccount();" />
				</div>
				<div id="upMemberPanel" style="display: none;">
					<div class="panelDiv">
						<span class="panel-spanName">家庭成员名称：</span> <input type="text"
							class="panel-inputName" id="memberName" />
					</div>
					<input type="button" value="" class="imagefileSubmit"
						onclick="addMember();" />
				</div>
				<div id="upProjectPanel" style="display: none;">
					<div class="panelDiv">
						<span class="panel-spanName">项目名称：</span> <input type="text"
							class="panel-inputName" id="projectrName" />
					</div>
					<input type="button" value="" class="imagefileSubmit"
						onclick="addProject()" />
				</div>
				<div id="upBusinessPanel" style="display: none;">
					<div class="panelDiv">
						<span class="panel-spanName">商家名称：</span> <input type="text"
							class="panel-inputName" id="businessName" />
					</div>
					<input type="button" value="" class="imagefileSubmit"
						onclick="addBusiness()" />
				</div>
				<div id="tb-m">
					<ul id="tbul-1" class="tb-ul tb-ul-1">
						<li class="tb-li"><label>一级分类:&nbsp;&nbsp;</label> <a
							class="fadd-pay" onclick="showAddPanel('firstLevel');"></a> <select
							class="choceSelect" onchange="changeFirstLevel();"
							id="firstSpendingLevel">
								<c:forEach items="${firstLevelList}" var="firstLevel">
									<option value="${firstLevel.firstLevelId }">
										${firstLevel.firstLevelName}</option>
								</c:forEach>
						</select></li>
						<li class="tb-li"><label>二级分类:&nbsp;&nbsp;</label> <a
							class="fadd-pay" onclick="showAddPanel('secondLevel');"></a> <select
							class="choceSelect" id="secondLevelSelect">
								<c:forEach items="${secondLevelList }" var="secondLevel">
									<option value="${secondLevel.secondLevelId }">
										${secondLevel.secondLevelName}</option>
								</c:forEach>
						</select></li>
						<li class="tb-li"><label>账户:&nbsp;&nbsp;</label> <a
							class="fadd-pay" onclick="showAddPanel('account');"></a> <select
							class="choceSelect" id="accountSelect">
								<c:forEach items="${accountList }" var="account">
									<option value="${account.accountId }">
										${account.accountName}</option>
								</c:forEach>
						</select></li>
					</ul>
					<ul class="tb-ul tb-ul-2">
						<li class="tb-li"><label>时间:&nbsp;&nbsp;</label> <input
							id="tb-datepicker" class="input" type="text" value=""
							name="tb-datepicker"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" /></li>
						<li class="tb-li"><label>项目:&nbsp;&nbsp;</label> <a
							class="fadd-pay" onclick="showAddPanel('project');"></a> <select
							class="choceSelect" id="projectSelect">
								<c:forEach items="${projectList }" var="project">
									<option value="${project.projectId }">
										${project.projectName}</option>
								</c:forEach>
						</select></li>
						<li class="tb-li" id="businessLi"><label>商家:&nbsp;&nbsp;</label>
							<a class="fadd-pay" onclick="showAddPanel('business');"></a> <select
							class="choceSelect" id="businessSelect">
								<c:forEach items="${businessList }" var="business">
									<option value="${business.businessId }">
										${business.businessName}</option>
								</c:forEach>
						</select></li>
					</ul>
					<ul class="tb-ul tb-ul-3">
						<li class="tb-li"><input id="tb-memo" onfocus="clearMemo();"
							class="input default-memo" type="text" value="填写备注"
							name="tb-memo"></input></li>
						<li class="tb-li"><label>模板:&nbsp;&nbsp;</label> <a
							class="fadd-pay" onclick=""></a> <select class="choceSelect"
							id="templateSelect" onchange="changeTemplate();">
								<option value="-1" selected="selected">选择模板</option>
								<c:forEach items="${templateList }" var="template">
									<option value="${template.templateId }">
										${template.templateName }</option>
								</c:forEach>
						</select></li>
					</ul>
				</div>
				<div id="tb-r">
					<ul class="tb-ul">
						<li id="tb-li-member" class="tb-li"><label>成员:&nbsp;&nbsp;</label>
							<a class="fadd-pro" onclick="showAddPanel('member')"></a> <select
							class="choceSelect" id="memberSelect">
								<c:forEach items="${familyList }" var="family">
									<option value="${family.memberId }">
										${family.memberName}</option>
								</c:forEach>
						</select></li>
					</ul>
					<ul class="tb-ul" style="margin-top: 6px;">
						<li id="tb-li-money" class="tb-li"><label>金额:&nbsp;&nbsp;</label>
							<input id="tb-money" class="input money" type="text"
							name="tb-money" /></li>
					</ul>
					<div class="tb-r-btn">
						<input id="tb-save" class="gg gg-exp-newitem-gg" type="button"
							onclick="addTally();"></input>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="box">
		<div id="box-t" class="png"></div>
		<div id="box-m" class="png">
			<div id="filter-tit">
				<ul id="ft-date" class="ft-date">
					<li id="ft-date-begin">${tallyDto.beginDate }</li>
					<li>&nbsp;-&nbsp;</li>
					<li id="ft-date-end">${tallyDto.endDate }</li>
				</ul>
				<ul class="ft-total">
					<li>总支出： <span class="green">-</span> <span id="ft-payout"
						class="green number">${tallyDto.allSpendingMoney }&nbsp;</span>&nbsp;总收入：<span
						class="orange"> &nbsp;+</span> <span id="ft-income"
						class="orange number">${tallyDto.allIncomeMoney }&nbsp;</span> <span
						class="normal">&nbsp;（默认单位：元）&nbsp;</span>
					</li>
				</ul>
			</div>
			<div id="filter-bar">
				<div class="fb-left fb-l-bg"></div>
				<div class="fb-left fb-tit"></div>
				<div class="fb-left fb-all">
					<a id="fb-all" class="button fb-btn"
						onclick="changeParType('fb-all');">全&nbsp;部</a>
				</div>
				<div class="fb-left fb-all">
					<a id="fb-year" class="button fb-btn"
						onclick="changeParType('fb-year');">本&nbsp;年</a>
				</div>
				<div class="fb-left fb-all">
					<a id="fb-month" class="button fb-btn"
						onclick="changeParType('fb-month');">本&nbsp;月</a>
				</div>
				<div class="fb-left fb-key">
					<div class="fb-key-box">
						<span>搜备注：</span> <input id="search-key" class="" type="text"
							name="search-key" value=""></input> <a id="search-btn"
							class="button fb-btn" onclick="searchTally();"></a>
					</div>
				</div>
				<div class="fb-left fb-edit">
					<a id="fb-ctrlall" class="panelbtn fb-btn" onclick="openAll();">
						<span id="openAllText">全部展开 </span>
					</a>
				</div>
			</div>
			<div style="clear: both;"></div>
			<div id="filter-content">
				<div id="fc-left"></div>
				<div id="fc-right">
					<div id="fc-tit">
						<a id="fcy-prev" class=""> <span class="fcy-pl"></span>
						</a>
						<ul>
							<li id="sort-tran_time" class="fct-li l-1 sort choose" onclick="">
								<span class="span"><span class="out">时&nbsp;&nbsp;间</span></span>
							</li>
							<li id="sort-category_id" class="fct-li l-2 sort" onclick="">
								<span class="out">分&nbsp;&nbsp;类</span>
							</li>
							<li id="sort-item_amount" class="fct-li l-3 sort" onclick="">
								<span class="out">金&nbsp;&nbsp;额</span>
							</li>
							<li id="sort-buyer_name" class="fct-li l-4 sort" onclick="">
								<span class="out">账&nbsp;&nbsp;户</span>
							</li>
							<li class="fct-li l-5"><span class="out">商&nbsp;&nbsp;家</span></li>
							<li id="sort-project_id" class="fct-li l-6 sort" onclick=""><span
								class="out">项&nbsp;&nbsp;目</span></li>
							<li class="fct-li l-7">备&nbsp;&nbsp;注</li>
							<li class="fct-li l-8"><span></span></li>
						</ul>
					</div>
					<c:forEach items="${dayTallyList }" var="dayTally" varStatus="i">
						<div id="list">
							<div class="list-date">
								<ul class="ul1">
									<li><span class="day">${dayTally.day }</span>/<span>${dayTally.month
											}</span></li>
								</ul>
								<ul class="ul2">
									<li class="lt-l"></li>
									<li class="lt-income">收入：<span class="orange">+${dayTally.incomeMoney
											}</span></li>
									<li class="lt-payout">支出：<span class="green">-${dayTally.spendingMoney
											}</span>
									</li>
								</ul>
							</div>
							<div class="list-line"></div>
							<c:forEach items="${dayTally.ioTallyList}" var="ioTally"
								varStatus="j">
								<div
									id="list-tit-${ioTally.ioTallyType }-${ioTally.spendingId }"
									class="list-tit list-tit-last"
									onmouseover="listOver('list-tit-${ioTally.ioTallyType }-${ioTally.spendingId }');"
									onmouseout="listOut('list-tit-${ioTally.ioTallyType }-${ioTally.spendingId }');"
									onclick="listClick('list-tit-${ioTally.ioTallyType }-${ioTally.spendingId }');">
									<ul class="ul1">
										<li></li>
									</ul>
									<ul class="ul2">
										<li class="bt-1" title="${ioTally.secondLevelName }"><span
											class="icon icon-1" style="background-position: -928px 0px;"></span>
											<span class="catename">${ioTally.secondLevelName }</span> <c:if
												test="${ioTally.ioTallyType == 'O' }">
												<span class="typename typename1">(支)</span>
											</c:if> <c:if test="${ioTally.ioTallyType == 'I' }">
												<span class="typename typename5">(收)</span>
											</c:if></li>
										<li class="bt-2">${ioTally.money }</li>
										<li class="bt-3" title="">${ioTally.accountName }</li>
										<li class="bt-4" title=""><c:if
												test="${ioTally.ioTallyType == 'O' }"> ${ioTally.businessName }</c:if></li>
										<li class="bt-5" title="">${ioTally.projectName }</li>
										<li class="bt-6" title="">${ioTally.remark }</li>
										<li class="bt-7"></li>
									</ul>
								</div>
								<div
									id="list-box-${ioTally.ioTallyType }-${ioTally.spendingId }"
									class="list-box list-box-payout list-box-nobg"
									style="display: none;">
									<div class="list-box-in">
										<div class="lb-image">
											<div class="lb-imgbox img-box" onmouseout="" onmouseover="">
												<img
													id="img-show-${ioTally.ioTallyType }-${ioTally.spendingId }"
													class="img-show" width="130" height="94"
													src="${path_image_tally }/default.gif"></img>
												<div
													id="img-panel-${ioTally.ioTallyType }-${ioTally.spendingId }"
													class="img-panel" style="display: none;"></div>
											</div>
										</div>
										<div class="lb-main">
											<ul class="lb-ul">
												<li class="lb-li"><label class="list-label-0">一级分类：</label>
													<span class="selectspan" style="position: static;">
														<select class="choceSelect"
														onchange="changeFirst('${ioTally.ioTallyType }-${ioTally.spendingId }');"
														id="firstSelect-${ioTally.ioTallyType }-${ioTally.spendingId }">
															<c:if test="${ioTally.ioTallyType == 'O' }">
																<c:forEach items="${firstLevelList}" var="firstLevel">
																	<option value="${firstLevel.firstLevelId }"
																		<c:if test="${firstLevel.firstLevelId == ioTally.firstLevelId }">selected="selected"</c:if>>
																		${firstLevel.firstLevelName}</option>
																</c:forEach>
															</c:if>
															<c:if test="${ioTally.ioTallyType == 'I' }">
																<c:forEach items="${incomeFristList}" var="firstLevel">
																	<option value="${firstLevel.firstLevelId }"
																		<c:if test="${firstLevel.firstLevelId == ioTally.firstLevelId }">selected="selected"</c:if>>
																		${firstLevel.firstLevelName}</option>
																</c:forEach>
															</c:if>
													</select>
												</span></li>
												<li class="lb-li"><label class="list-label-1">二级分类：</label>
													<span class="selectspan" style="position: static;">
														<select class="choceSelect"
														id="secondSelect-${ioTally.ioTallyType }-${ioTally.spendingId }">
															<c:forEach items="${ioTally.secondList }"
																var="secondLevel">
																<option value="${secondLevel.secondLevelId }"
																	<c:if test="${secondLevel.secondLevelId == ioTally.secondLevelId }"> selected="selected"</c:if>>
																	${secondLevel.secondLevelName}</option>
															</c:forEach>
													</select>
												</span></li>
												<li class="lb-li"><label class="list-label-2">金额：</label>
													<input class="input list-money" type="text"
													value="${ioTally.money }"
													id="tallyMoney-${ioTally.ioTallyType }-${ioTally.spendingId }"
													name="list-money"></input></li>
												<li class="lb-li"><label class="list-label-3">成员：</label>
													<span class="selectspan"> <select
														class="choceSelect"
														id="member-${ioTally.ioTallyType }-${ioTally.spendingId }">
															<c:forEach items="${familyList }" var="family">
																<option value="${family.memberId }"
																	<c:if test="${family.memberId == ioTally.memberId }">selected="selected"</c:if>>
																	${family.memberName}</option>
															</c:forEach>
													</select>
												</span></li>
											</ul>
											<ul class="lb-ul">
												<li class="lb-li"><label class="list-label-4">时间：</label>
													<input class="input list-datepicker" type="text"
													name="list-datepicker" value="${ioTally.createTime}"
													onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
													id="tallyTime-${ioTally.ioTallyType }-${ioTally.spendingId }"></input></li>
												<li class="lb-li"><label class="list-label-5">项目：</label>
													<span class="selectspan"> <select
														class="choceSelect"
														id="projectSelect-${ioTally.ioTallyType }-${ioTally.spendingId }">
															<c:forEach items="${projectList }" var="project">
																<option value="${project.projectId }"
																	<c:if test="${project.projectId == ioTally.projectId }">selected="selected"</c:if>>
																	${project.projectName}</option>
															</c:forEach>
													</select>
												</span></li>
												<li class="lb-li"><label class="list-label-6">账户：</label>
													<span class="selectspan"> <select
														class="choceSelect"
														id="account-${ioTally.ioTallyType }-${ioTally.spendingId }">
															<c:forEach items="${accountList }" var="account">
																<option value="${account.accountId }"
																	<c:if test="${account.accountId == ioTally.accountId }">selected="selected"</c:if>>
																	${account.accountName}</option>
															</c:forEach>
													</select>
												</span></li>
												<c:if test="${ioTally.ioTallyType == 'O' }">
													<li class="lb-li"><label class="list-label-6">商家：</label>
														<span class="selectspan"> <select
															class="choceSelect"
															id="business-${ioTally.ioTallyType }-${ioTally.spendingId }">
																<c:forEach items="${businessList }" var="business">
																	<option value="${business.businessId }"
																		<c:if test="${business.businessId == ioTally.businessId }">selected="selected"</c:if>>
																		${business.businessName}</option>
																</c:forEach>
														</select>
													</span></li>
												</c:if>
											</ul>
											<ul class="lb-ul">
												<li class="lb-li"></li>
												<li class="lb-li"><input
													id="memo-${ioTally.ioTallyType }-${ioTally.spendingId }"
													class="input list-memo default-memo" type="text" onblur=""
													onfocus="" name="list-memo" value="${ioTally.remark }"></input></li>
											</ul>
										</div>
										<div class="lb-btn">
											<ul>
												<li><a class=""
													onclick="saveTally('${ioTally.ioTallyType }-${ioTally.spendingId }');">保存</a></li>
												<li><a class="del-btn"
													onclick="delTally('${ioTally.ioTallyType }-${ioTally.spendingId }');">删除</a></li>
											</ul>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="box-b" class="png"></div>
	</div>
</body>
</html>