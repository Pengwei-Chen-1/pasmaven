<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path_css" value="resources/css" />
<c:set var="path_js" value="resources/js" />
<c:set var="path_image" value="resources/image" />
<c:set var="path_image_classify" value="resources/image/classify" />
<c:set var="path_layer" value="resources/layer" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${path_css}/classify.css">
<script type="text/javascript" src="${path_js}/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${path_layer}/layer.js"></script>
<script type="text/javascript" src="${path_js}/classify.js"></script>
</head>
<body>
	<input type="hidden" id="callbackText" value="l-out" />
	<div class="t png"></div>
	<div id="divBox" class="png">
		<div class="left">
			<h3></h3>
			<ul>
				<li class="select"><a> <span class="icon png"
						style="background-position: -48px 0;"></span> 分类设置
				</a></li>
			</ul>
		</div>
		<div class="right">
			<div class="cat_t">
				<div class="rTit">
					<span class="rTit-icon png"></span> <span class="text">分类设置</span>
				</div>
			</div>
			<div id="list">
				<div class="list-top">
					<ul>
						<li id="l-out" class="select"><a
							href="javascript:changeClassify('l-out');"><span></span>支出分类</a></li>
						<li id="l-in" class=""><a
							href="javascript:changeClassify('l-in');"><span></span>收入分类</a></li>
						<li id="l-store" class=""><a
							href="javascript:changeClassify('l-store');"><span></span>商家分类</a></li>
						<li id="l-pro"><a href="javascript:changeClassify('l-pro');"><span></span>项目分类</a></li>
						<li id="l-member"><a
							href="javascript:changeClassify('l-member');"><span></span>成员设置</a></li>
						<li id="l-template"><a
							href="javascript:changeClassify('l-template');"><span></span>账单模板</a></li>
						<li class="last"></li>
					</ul>
				</div>
				<div id="l-outClassify">
					<div class="list-tit">
						<ul style="float: left;">
							<li class="l-1"><span>一级分类</span></li>
							<li class="l-line"><span></span></li>
							<li class="l-2">二级分类</li>
							<li class="l-line"><span></span></li>
							<li class="l-3">支出统计</li>
							<li class="l-line"><span></span></li>
							<li class="l-4">操 作</li>
						</ul>
					</div>
					<c:forEach items="${firstList }" var="firstClassify">
						<div id="" class="list-msg"
							onclick="showSecondLevel('${firstClassify.firstId }')">
							<ul>
								<li class="li-level1"><span class="png" title=""></span>${firstClassify.firstName
									}</li>
								<li class="li-level2"></li>
								<li class="li-tj">
									<div class="tj-bar" style="height: 31px;">
										<div class="tjz">${firstClassify.firstMoney }</div>
									</div>
								</li>
								<li class="l-operate"><a class="btnEdit"
									onclick="showUpdatePanel('first-${firstClassify.firstId }-${firstClassify.firstName }-O');"
									href="javascript:;"></a> <span
									style="float: left; display: block; width: 20px; height: 25px;"></span>
									<a class="btnDelete"
									onclick="delFirstOut('${firstClassify.firstId }')"
									href="javascript:;"></a></li>
								<li class="cat-r-bg"><span></span></li>
							</ul>
						</div>
						<div id="${firstClassify.firstId }" class="list-box hidden">
							<c:forEach items="${firstClassify.secondList }"
								var="secondClassify" varStatus="i">
								<div id="" class="lb-row">
									<ul
										<c:if test="${i.index % 2 == 1}">style="background-color: #E5EFEE;"</c:if>>
										<li class="li-level1"></li>
										<li class="li-level2">${secondClassify.secondLevelName }</li>
										<li class="l-operate" style="margin-left: 26%;"><a
											class="btnEdit"
											onclick="showUpdatePanel('second-${secondClassify.secondLevelId}-${secondClassify.secondLevelName}-O');"
											href="javascript:;"></a> <span
											style="float: left; display: block; width: 20px; height: 25px;"></span>
											<a class="btnDelete"
											onclick="delSecondOut('${secondClassify.secondLevelId }')"
											href="javascript:;"></a></li>
									</ul>
								</div>
							</c:forEach>
							<div id="" class="lb-row addRow bg-gray">
								<ul>
									<li class="li-level1"></li>
									<li class="li-level2"><a class="addContent png"
										onclick="showAddClassifyPanel('second-O-${firstClassify.firstId }');"
										href="javascript:;"> <span class="addText">添加二级分类</span>
									</a></li>
									<li class="li-tj"></li>
									<li class="l-operate"><a></a></li>
								</ul>
							</div>
							<p class="hr"></p>
						</div>
					</c:forEach>
					<br />
					<div id="addFirstCtg">
						<a class="addContent png"
							onclick="showAddClassifyPanel('first-O')" href="javascript:;">
							<span class="addText">添加一级分类</span>
						</a>
					</div>
				</div>
				<div id="l-inClassify" style="display: none;">
					<div class="list-tit">
						<ul style="float: left;">
							<li class="l-1"><span>一级分类</span></li>
							<li class="l-line"><span></span></li>
							<li class="l-2">二级分类</li>
							<li class="l-line"><span></span></li>
							<li class="l-3">支出统计</li>
							<li class="l-line"><span></span></li>
							<li class="l-4">操 作</li>
						</ul>
					</div>
					<c:forEach items="${firstIncomeList }" var="firstClassify">
						<div id="" class="list-msg"
							onclick="showSecondLevel('${firstClassify.firstId }')">
							<ul>
								<li class="li-level1"><span class="png" title=""></span>${firstClassify.firstName
									}</li>
								<li class="li-level2"></li>
								<li class="li-tj">
									<div class="tj-bar" style="height: 31px;">
										<div class="tjz">${firstClassify.firstMoney }</div>
									</div>
								</li>
								<li class="l-operate"><a class="btnEdit"
									onclick="showUpdatePanel('first-${firstClassify.firstId }-${firstClassify.firstName }-I');"
									href="javascript:;"></a> <span
									style="float: left; display: block; width: 20px; height: 25px;"></span>
									<a class="btnDelete"
									onclick="delFirstIn('${firstClassify.firstId }');"
									href="javascript:;"></a></li>
								<li class="cat-r-bg"><span></span></li>
							</ul>
						</div>
						<div id="${firstClassify.firstId }" class="list-box hidden">
							<c:forEach items="${firstClassify.secondList }"
								var="secondClassify" varStatus="i">
								<div id="" class="lb-row">
									<ul
										<c:if test="${i.index % 2 == 1}">style="background-color: #E5EFEE;"</c:if>>
										<li class="li-level1"></li>
										<li class="li-level2">${secondClassify.secondLevelName }</li>
										<li class="l-operate" style="margin-left: 26%;"><a
											class="btnEdit"
											onclick="showUpdatePanel('second-${secondClassify.secondLevelId}-${secondClassify.secondLevelName}-I');"
											href="javascript:;"></a> <span
											style="float: left; display: block; width: 20px; height: 25px;"></span>
											<a class="btnDelete"
											onclick="delSecondIn('${secondClassify.secondLevelId}')"
											href="javascript:;"></a></li>
									</ul>
								</div>
							</c:forEach>
							<div id="" class="lb-row addRow bg-gray">
								<ul>
									<li class="li-level1"></li>
									<li class="li-level2"><a class="addContent png"
										onclick="showAddClassifyPanel('second-I-${firstClassify.firstId }')"
										href="javascript:;"> <span class="addText">添加二级分类</span>
									</a></li>
									<li class="li-tj"></li>
									<li class="l-operate"><a></a></li>
								</ul>
							</div>
							<p class="hr"></p>
						</div>
					</c:forEach>
					<br />
					<div id="">
						<a class="addContent png"
							onclick="showAddClassifyPanel('first-I')" href="javascript:;">
							<span class="addText">添加一级分类</span>
						</a>
					</div>
				</div>
				<div id="l-storeClassify" style="display: none;">
					<div class="list-tit">
						<ul style="clear: none; float: left;">
							<li class="l-2"
								style="width: 505px !important; text-align: center;">商家名称</li>
							<li class="l-line"><span></span></li>
							<li class="l-4">操 作</li>
						</ul>
					</div>
					<div id="list-display" class="list-box">
						<div id="" class="lb-row">
							<c:forEach items="${businessList }" var="business" varStatus="i">
								<ul
									<c:if test="${i.index % 2 == 1}">style="background-color: #E5EFEE;"</c:if>>
									<li class="li-level2"
										style="padding-left: 0px; text-align: center; width: 513px !important;">${business.businessName
										}</li>
									<li class="l-operate"><a class="btnEdit"
										onclick="showUpdatePanel('business-${business.businessId }-${business.businessName }');"
										href="javascript:;"></a> <span
										style="float: left; display: block; width: 20px; height: 25px;"></span>
										<a class="btnDelete"
										href="javascript:delBusiness('${business.businessId }');"></a></li>
								</ul>
							</c:forEach>
						</div>
					</div>
					<br />
					<div id="">
						<a class="addContent png"
							onclick="showAddClassifyPanel('business');" href="javascript:;">
							<span class="addText">添加商家分类</span>
						</a>
					</div>
				</div>
				<div id="l-proClassify" style="display: none;">
					<div class="list-tit">
						<ul style="clear: none; float: left;">
							<li class="l-2"
								style="width: 505px !important; text-align: center;">项目名称</li>
							<li class="l-line"><span></span></li>
							<li class="l-4">操 作</li>
						</ul>
					</div>
					<div id="list-display" class="list-box">
						<div id="" class="lb-row">
							<c:forEach items="${projectList }" var="project" varStatus="i">
								<ul
									<c:if test="${i.index % 2 == 1}">style="background-color: #E5EFEE;"</c:if>>
									<li class="li-level2"
										style="padding-left: 0px; text-align: center; width: 513px !important;">${project.projectName
										}</li>
									<li class="l-operate"><a class="btnEdit"
										onclick="showUpdatePanel('project-${project.projectId }-${project.projectName}');"
										href="javascript:;"></a> <span
										style="float: left; display: block; width: 20px; height: 25px;"></span>
										<a class="btnDelete"
										href="javascript:delPro('${project.projectId }');"></a></li>
								</ul>
							</c:forEach>
						</div>
					</div>
					<br />
					<div id="">
						<a class="addContent png"
							onclick="showAddClassifyPanel('project')" href="javascript:;">
							<span class="addText">添加项目分类</span>
						</a>
					</div>
				</div>
				<div id="l-memberClassify" style="display: none;">
					<div class="list-tit">
						<ul style="clear: none; float: left;">
							<li class="l-2"
								style="width: 505px !important; text-align: center;">成员名称</li>
							<li class="l-line"><span></span></li>
							<li class="l-4">操 作</li>
						</ul>
					</div>
					<div id="list-display" class="list-box">
						<div id="" class="lb-row">
							<c:forEach items="${memberList }" var="member" varStatus="i">
								<ul
									<c:if test="${i.index % 2 == 1}">style="background-color: #E5EFEE;"</c:if>>
									<li class="li-level2"
										style="padding-left: 0px; text-align: center; width: 513px !important;">${member.memberName
										}</li>
									<li class="l-operate"><a class="btnEdit"
										onclick="showUpdatePanel('member-${member.memberId }-${member.memberName}');"
										href="javascript:;"></a> <span
										style="float: left; display: block; width: 20px; height: 25px;"></span>
										<a class="btnDelete"
										href="javascript:delMember('${member.memberId }');"></a></li>
								</ul>
							</c:forEach>
						</div>
					</div>
					<br />
					<div id="">
						<a class="addContent png" onclick="showAddClassifyPanel('member')"
							href="javascript:;"> <span class="addText">&nbsp;&nbsp;添加成员</span>
						</a>
					</div>
				</div>
				<div id="l-templateClassify" style="display: none;">
					<div class="list-tit">
						<ul style="float: left; text-align: center;">
							<li style="width: 136px;">模板名称</li>
							<li class="l-line"><span></span></li>
							<li style="width: 172px;">分类</li>
							<li class="l-line"><span></span></li>
							<li style="width: 119px;">账户</li>
							<li class="l-line"><span></span></li>
							<li style="width: 131px;">金额</li>
							<li class="l-line"><span></span></li>
							<li style="width: 200px;">操作</li>
						</ul>
					</div>
					<div id="title-payout" class="level1 hidden"
						style="display: block;">支出</div>
					<c:forEach items="${templateDtoList }" var="templateDto">
						<c:if test="${templateDto.ioType == 'O' }">
							<div id="list-payout-${templateDto.templateId }"
								class="tmp-list hidden" style="display: block;">
								<ul id="${templateDto.templateId }" class="list-msg tit-first"
									onclick="showBox('box-${templateDto.templateId }');">
									<li class="li1" title="${templateDto.templateName }">${templateDto.templateName
										}</li>
									<li class="li2 li-level1">${templateDto.secondLevelName }</li>
									<li class="li3" title="${templateDto.accountName }">${templateDto.accountName
										}</li>
									<li class="li4">${templateDto.money }</li>
									<li class="li5 l-operate"><a class="btnEdit"
										href="javascript:;"></a> <span
										style="float: left; display: block; width: 20px; height: 25px;"></span>
										<a class="btnDelete"
										onclick="delTemp('${templateDto.templateId }');"
										href="javascript:;"></a></li>
								</ul>
								<div id="box-${templateDto.templateId }" class="editbox hidden">
									<div class="lb-main">
										<ul class="lb-ul">
											<li class="lb-li"><label class="list-label-0">
													模板名称： </label> <input id="templateName-${templateDto.templateId }"
												class="input" type="text"
												value="${templateDto.templateName }"></input></li>
											<li class="lb-li"><label class="list-label-1">一级分类：
											</label> <span class="selectspan select-payout"> <select
													class="temSelect"
													id="firstSelect-${templateDto.templateId }"
													onchange="changeTempFirst('${templateDto.templateId }');"><c:forEach
															items="${firstLevelList}" var="firstLevel">
															<option value="${firstLevel.firstLevelId }"
																<c:if test="${firstLevel.firstLevelId == templateDto.firstLevelId }"> selected="selected" </c:if>>
																${firstLevel.firstLevelName}</option>
														</c:forEach></select>
											</span></li>
											<li class="lb-li"><label class="list-label-2">二级分类：
											</label> <span class="selectspan"><select class="temSelect"
													id="secondSelect-${templateDto.templateId }"><c:forEach
															items="${templateDto.secondLevelList }" var="secondLevel">
															<option value="${secondLevel.secondLevelId }"
																<c:if test="${secondLevel.secondLevelId == templateDto.secondLevelId  }">selected="selected"</c:if>>
																${secondLevel.secondLevelName}</option>
														</c:forEach></select> </span></li>
										</ul>
										<ul class="lb-ul">
											<li class="lb-li"><label class="list-label-0"> 金
													额： </label> <input id="templateMoney-${templateDto.templateId }"
												class="input" type="text" value="${templateDto.money }"></input></li>
											<li class="lb-li"><label class="list-label-1">项目：
											</label> <span class="selectspan"><select class="temSelect"
													id="projectSelect-${templateDto.templateId }"><c:forEach
															items="${projectList }" var="project">
															<option value="${project.projectId }"
																<c:if test="${project.projectId == templateDto.projectId  }">selected="selected"</c:if>>
																${project.projectName}</option>
														</c:forEach></select> </span></li>
											<li class="lb-li li-store"><label class="list-label-2">商家：</label>
												<span class="selectspan select-store"><select
													class="temSelect"
													id="businessSelect-${templateDto.templateId }"><c:forEach
															items="${businessList }" var="business">
															<option value="${business.businessId }"
																<c:if test="${business.businessId == templateDto.businessId  }">selected="selected"</c:if>>
																${business.businessName}</option>
														</c:forEach></select></span></li>
										</ul>
										<ul class="lb-ul">
											<li class="lb-li"><label class="list-label-0">成员：</label>
												<span class="selectspan"><select class="temSelect"
													id="memberSelect-${templateDto.templateId }"><c:forEach
															items="${memberList }" var="family">
															<option value="${family.memberId }"
																<c:if test="${family.memberId == templateDto.memberId  }">selected="selected"</c:if>>
																${family.memberName}</option>
														</c:forEach></select></span></li>
											<li class="lb-li"><label class="list-label-1">账户：
											</label> <span class="selectspan"><select class="temSelect"
													id="accountSelect-${templateDto.templateId }"><c:forEach
															items="${accountList }" var="account">
															<option value="${account.accountId }"
																<c:if test="${account.accountId == templateDto.accountId  }">selected="selected"</c:if>>
																${account.accountName}</option>
														</c:forEach></select> </span></li>
											<li class="lb-li"><label class="list-label-1">备注：
											</label> <input id="tempMemo-${templateDto.templateId }"
												class="input list-memo default-memo" type="text"
												value="${templateDto.remark }"></input></li>
										</ul>
									</div>
									<div class="lb-btn">
										<ul>
											<li class="lb-li"><a
												onclick="showBox('box-${templateDto.templateId }');"
												href="javascript:;"> 取消 </a></li>
											<li class="lb-li"><a
												onclick="saveTemp('${templateDto.templateId }-O');"
												href="javascript:;"> 保存 </a></li>
										</ul>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<div id="title-income" class="level1 hidden"
						style="display: block;">收入</div>
					<c:forEach items="${templateDtoList }" var="templateDto">
						<c:if test="${templateDto.ioType == 'I' }">
							<div id="list-income-${templateDto.templateId }"
								class="tmp-list hidden" style="display: block;">
								<ul id="${templateDto.templateId }" class="list-msg tit-first"
									onclick="showBox('box-${templateDto.templateId }');">
									<li class="li1" title="">${templateDto.templateName }</li>
									<li class="li2 li-level1"
										title="${templateDto.secondLevelName }">${templateDto.secondLevelName
										}</li>
									<li class="li3" title="${templateDto.accountName }">${templateDto.accountName
										}</li>
									<li class="li4">${templateDto.money }</li>
									<li class="li5 l-operate"><a class="btnEdit"
										href="javascript:;"></a> <span
										style="float: left; display: block; width: 20px; height: 25px;"></span>
										<a class="btnDelete" onclick=""
										href="javascript:delTemp('${templateDto.templateId }');"></a></li>
								</ul>
								<div id="box-${templateDto.templateId }" class="editbox hidden">
									<div class="lb-main">
										<ul class="lb-ul">
											<li class="lb-li"><label class="list-label-0">
													模板名称： </label> <input id="templateName-${templateDto.templateId }"
												class="input" type="text"
												value="${templateDto.templateName }"></input></li>
											<li class="lb-li"><label class="list-label-1">一级分类：
											</label> <span class="selectspan select-income"><select
													class="temSelect"
													id="firstSelect-${templateDto.templateId }"
													onchange="changeTempFirst('${templateDto.templateId }');"><c:forEach
															items="${firstLevelIncomeList}" var="firstIncomeLevel">
															<option value="${firstIncomeLevel.firstLevelId }"
																<c:if test="${firstIncomeLevel.firstLevelId == templateDto.firstLevelId  }">selected="selected"</c:if>>
																${firstIncomeLevel.firstLevelName}</option>
														</c:forEach></select></span></li>
											<li class="lb-li"><label class="list-label-2">二级分类：
											</label> <span class="selectspan"><select class="temSelect"
													id="secondSelect-${templateDto.templateId }"><c:forEach
															items="${templateDto.secondLevelList }" var="secondLevel">
															<option value="${secondLevel.secondLevelId }"
																<c:if test="${secondLevel.secondLevelId == templateDto.secondLevelId  }">selected="selected"</c:if>>
																${secondLevel.secondLevelName}</option>
														</c:forEach></select></span></li>
										</ul>
										<ul class="lb-ul">
											<li class="lb-li"><label class="list-label-0"> 金
													额： </label> <input id="templateMoney-${templateDto.templateId }"
												class="input" type="text" value="${templateDto.money }"></input></li>
											<li class="lb-li"><label class="list-label-1">
													项目： </label> <span class="selectspan"><select
													class="temSelect"
													id="projectSelect-${templateDto.templateId }"><c:forEach
															items="${projectList }" var="project">
															<option value="${project.projectId }"
																<c:if test="${project.projectId == templateDto.projectId  }">selected="selected"</c:if>>
																${project.projectName}</option>
														</c:forEach></select></span></li>
											<li class="lb-li"><label class="list-label-2">
													账户： </label> <span class="selectspan"><select
													class="temSelect"
													id="accountSelect-${templateDto.templateId }"><c:forEach
															items="${accountList }" var="account">
															<option value="${account.accountId }"
																<c:if test="${account.accountId == templateDto.accountId  }">selected="selected"</c:if>>
																${account.accountName}</option>
														</c:forEach></select></span></li>
										</ul>
										<ul class="lb-ul">
											<li class="lb-li"><label class="list-label-0"> 成
													员： </label> <span class="selectspan"><select
													class="temSelect"
													id="memberSelect-${templateDto.templateId }"><c:forEach
															items="${memberList }" var="family">
															<option value="${family.memberId }"
																<c:if test="${family.memberId == templateDto.memberId  }">selected="selected"</c:if>>
																${family.memberName}</option>
														</c:forEach></select></span></li>
											<li class="lb-li"><label class="list-label-1">
													备注： </label> <input id="tempMemo-${templateDto.templateId }"
												style="width: 298px;" class="input list-memo" type="text"
												value="${templateDto.remark }"></input></li>
										</ul>
									</div>
									<div class="lb-btn">
										<ul>
											<li class="lb-li"><a
												onclick="showBox('box-${templateDto.templateId }');"
												href="javascript:;"> 取消 </a></li>
											<li class="lb-li"><a
												onclick="saveTemp('${templateDto.templateId }-I');"
												href="javascript:;"> 保存 </a></li>
										</ul>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<br />
					<div id="">
						<a class="addContent png" onclick="showAddTem();"
							href="javascript:;"> <span class="addText">&nbsp;&nbsp;添加模板</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="b png"></div>
	<div id="addTempPanel" style="display: none;">
		<input id="tempType" type="hidden" value="spending" />
		<div class="pl-main">
			<div class="addbtn-div">
				<a class="addbtn-payout addbtn-payout-s" id="addBtnOut"
					onfocus=this.blur() onclick="changeAddTem('payout');"
					href="javascript:;"></a> <a class="addbtn-income"
					onclick="changeAddTem('income');" id="addBtnIn" onfocus=this.blur()
					href="javascript:;"></a>
			</div>
			<div id="am-editbox" class="editbox">
				<div class="lb-main">
					<ul class="lb-ul">
						<li class="lb-li"><label class="list-label-0"> 模板名称：
						</label> <input id="templateName" class="input" type="text"></input></li>
						<li class="lb-li"><label class="list-label-1">一级分类： </label>
							<span class="selectspan select-payout"> <select
								class="temSelect" id="firstSpendingLevel"
								onchange="changeFirstLevel();">
									<c:forEach items="${firstLevelList}" var="firstLevel">
										<option value="${firstLevel.firstLevelId }">
											${firstLevel.firstLevelName}</option>
									</c:forEach>
							</select>
						</span></li>
						<li class="lb-li"><label class="list-label-2">二级分类：</label> <span
							class="selectspan select-payout"> <select
								class="temSelect" id="secondLevelSelect">
									<c:forEach items="${secondLevelList }" var="secondLevel">
										<option value="${secondLevel.secondLevelId }">
											${secondLevel.secondLevelName}</option>
									</c:forEach>
							</select>
						</span></li>
					</ul>
					<ul class="lb-ul">
						<li class="lb-li"><label class="list-label-0">金额： </label> <input
							id="templateMoney" class="input" type="text"></input></li>
						<li class="lb-li"><label class="list-label-1">项目： </label> <span
							class="selectspan select-payout"> <select
								class="temSelect" id="projectSelect"><c:forEach
										items="${projectList }" var="project">
										<option value="${project.projectId }">
											${project.projectName}</option>
									</c:forEach></select>
						</span></li>
						<li class="lb-li" id="businessLi"><label class="list-label-2">商家：</label>
							<span class="selectspan select-payout"> <select
								class="temSelect" id="businessSelect"><c:forEach
										items="${businessList }" var="business">
										<option value="${business.businessId }">
											${business.businessName}</option>
									</c:forEach></select>
						</span></li>
					</ul>
					<ul class="lb-ul">
						<li class="lb-li"><label class="list-label-0">成员：</label> <span
							class="selectspan"><select class="temSelect"
								id="memberSelect"><c:forEach items="${memberList }"
										var="family">
										<option value="${family.memberId }">
											${family.memberName}</option>
									</c:forEach></select></span></li>
						<li class="lb-li"><label class="list-label-1">账户： </label> <span
							class="selectspan"><select class="temSelect"
								id="accountSelect"><c:forEach items="${accountList }"
										var="account">
										<option value="${account.accountId }">
											${account.accountName}</option>
									</c:forEach></select> </span></li>
						<li class="lb-li"><label class="list-label-1">备注： </label> <input
							id="tempMemo" class="input list-memo default-memo" type="text"></input></li>
					</ul>
				</div>
			</div>
			<div class="popup-btndiv fast_model_btn">
				<input class="popup-submit nofocus" type="button"
					onclick="addTemplate();" value=""></input>
			</div>
		</div>
	</div>
	<div id="addClassifyPanel" style="display: none;">
		<input type="hidden" value="first-O" id="addClassifyText" />
		<div class="panelDiv">
			<span class="panel-spanName">分类名称：</span> <input type="text"
				class="panel-inputName" id="classifyName" />
		</div>
		<input type="button" value="" class="imagefileSubmit"
			onclick="addClassify();" />
	</div>
	<div id="updateClassifyPanel" style="display: none;">
		<input type="hidden" value="first-Id" id="updateClassifyText" /> <input
			type="hidden" value="O" id="updateClassifyIOType" />
		<div class="panelDiv">
			<span class="panel-spanName">分类名称：</span> <input type="text"
				class="panel-inputName" id="updateClassifyName" />
		</div>
		<input type="button" value="" class="imagefileSubmit"
			onclick="updateClassify();" />
	</div>
</body>
</html>