function change(type){
	var list = $("#navUl").children();
	for ( var int = 0; int < list.length; int++) {
		if(list[int].className == 'select'){
			list[int].className = "";
		}
	}
	if(type == 'daily'){
		$("#dailyLi").removeClass().addClass("select");
		$("#report_member").css("display","none");
		$("#report_trend").css("display","none");
		$("#report_daily").css("display","block");
	} else if(type=='trend'){
		$("#trendLi").removeClass().addClass("select");
		$("#report_daily").css("display","none");
		$("#report_member").css("display","none");
		$("#report_trend").css("display","block");
	}else if(type=='member'){
		$("#memberLi").removeClass().addClass("select");
		$("#report_daily").css("display","none");
		$("#report_trend").css("display","none");
		$("#report_member").css("display","block");
	}
}