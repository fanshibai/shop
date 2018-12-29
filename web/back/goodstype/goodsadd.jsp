<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript">
		$(function () {
			$.post("GoodsTypeServlet?action=goodsTypeByParentIdList&parent_id=0",function (data) {
				for (var i = 0;i < data.length;i++){
					//创建option对象
					var option = document.createElement("option");
					option.value = data[i].id;
					option.text = data[i].name;
					//把option添加到select标签中
					$("#parent_id").append(option);
				}
			},"JSON")
		})
	</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>添加商品类别信息</span></div>
    <form method="post" action="GoodsTypeServlet?action=addGoodsType">
    	<ul class="forminfo">
	    <li><label>商品类别名称</label><input name="name" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
	    <li><label>父类别</label>
	    		<select name="parent_id" id="parent_id">
	    			<option value="0">无</option>
	    		</select>
	    </li>
	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认添加"/></li>
	    </ul>
    
    </form>
    </div>


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

