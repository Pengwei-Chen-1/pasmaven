package com.cpw.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义分页标签
 * 
 * @author pengwei_chen
 * @date 2014-4-22 下午6:14:45
 */
@SuppressWarnings("serial")
public class Page extends TagSupport {

	// 请求URL
	private String url;
	// 当前页码
	private int pageNum;
	// 每页记录数
	private int pageSize;
	// 总记录数
	private int count;
	// 总页码数
	private int allPageNum;

	public int doEndTag() throws JspException {
		// 计算总页码数
		allPageNum = count % pageSize == 0 ? (count / pageSize) : (count / pageSize + 1);
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer("");
		sb.append("<script type=\"text/javascript\">");
		sb.append("function a(){var obj = document.getElementById(\"select\").value;");
		sb.append("var pageSize = 10;");
		sb.append("location.href=\"" + url + "pageNum=\"+obj+\"&pageSize=\"+pageSize;}");
		sb.append("function b(pageNum){");
		sb.append("var pageSize = 10;");
		sb.append("location.href=\"" + url + "pageNum=\"+pageNum+\"&pageSize=\"+pageSize;}");
		sb.append("</script>");
		sb.append("<div style=\"FONT-SIZE: 12px;margin-left: 3%;margin-top: 10px;\">");
		sb.append("&nbsp;&nbsp;共[" + count + "]条记录  [" + allPageNum + "]页  ");
		sb.append("当前是[" + ((pageNum - 1) * pageSize + 1) + "-");
		if (pageNum == allPageNum) {
			sb.append("" + count + "]条");
		} else {
			sb.append("" + (pageNum * pageSize) + "]条");
		}
		// 首页
		sb.append("&nbsp;[<a href=\"javascript:b(1)\">首&nbsp;页</a>]");
		// 上一页处理
		if (pageNum == 1) {
			sb.append("<span class=\"disabled\">&nbsp;[上一页]").append("</span>\r\n");
		} else {
			sb.append("&nbsp;[<a href=\"javascript:b(").append((pageNum - 1))
					.append(")\">上一页</a>]&nbsp;\r\n");
		}
		// 页码选择
		sb.append("<select id=\"select\" scrollHeight='10' onchange=\"a()\">");
		for (int i = 1; i <= allPageNum; i++) {
			if (i == pageNum) {
				sb.append("<option value='" + i + "' selected>第" + i + "页</option>");
			} else {
				sb.append("<option value='" + i + "'>第" + i + "页</option>");
			}
		}
		sb.append("</select>");
		// 下一页处理
		if (pageNum == allPageNum) {
			sb.append("<span class=\"disabled\">&nbsp;[下一页]&nbsp;").append("</span>\r\n");
		} else {
			sb.append("&nbsp;[<a href=\"javascript:b(").append((pageNum + 1))
					.append(")\">下一页</a>]\r\n");
		}
		// 尾页
		sb.append("[<a href=\"javascript:b(" + allPageNum + ")\">尾&nbsp;页</a>]");
		// 设置每页显示的记录数量
		/*sb.append("&nbsp;&nbsp;每页显示<select id=\"pageSizeSelect\" onchange=\"a()\">");
		sb.append("<option value='5' ");
		if (pageSize == 5) {
			sb.append("selected");
		}
		sb.append(">5</option>");
		sb.append("<option value='10' ");
		if (pageSize == 10) {
			sb.append("selected");
		}
		sb.append(">10</option>");
		sb.append("<option value='15' ");
		if (pageSize == 15) {
			sb.append("selected");
		}
		sb.append(">15</option>");
		sb.append("</select>条信息");*/
		sb.append("</div>");
		try {
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
