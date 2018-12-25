<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String path = request.getContextPath();
    	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <script>
    $(function () {
        //统计用户选中个数
        var count = 0;
        //全选的点击事件
        $("#idFlag").click(function () {
            //获取全选按钮的状态
            var flag = $(this).prop("checked");
            if(flag == false){
                count = 0;
            }else{
                count = $(".ids").length;
            }
            //获取每个用户的对象并且进行选中
            $(".ids").each(function () {
                $(this).prop("checked",flag);
            })
        });

        //用户选中的状态
        $(".ids").each(function () {
            $(this).click(function () {
                //用户选中框选中
                if($(this).prop("checked")){
                    //统计数+1
                    count++;
                    //用户选中数达到长度则选中全选按钮
                    if(count == $(".ids").length){
                        $("#idFlag").prop("checked",true);
                    }
                }else{
                    count--;
                    //全选按钮取消
                    $("#idFlag").prop("checked",false);
                }
            })
        });

        //后台的删除
        $("#batchDel").click(function () {
            //1.创建一个数组存储多个id属性
            var idList = new Array();
            //2.获取用户选中的对象
            $(".ids:checked").each(function () {
                //获取val值
                var id = $(this).val();
                //把val值添加进数组
                idList.push(id);
            })

            //3.创建一个对象,用来存储数组
            var obj = new Object();
            obj.ids = idList;

            //使用AJax进行交互
            $.post("UserServlet?action=batchDel",obj,function (data) {
                //页面刷新
                location.reload();
            })
        });
    })
</script>
</head>

<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span><a href="back/user/adduser.jsp">添加</a></li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li id="batchDel"><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
        </ul>
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
      <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" id="idFlag" value="" /></th>
        <th>用户编号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>电话</th>
        <th>邮箱</th>
        <th>是否管理员</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${page.list}">
            <tr>
         	 <td><input name="" type="checkbox" value="${user.id}" class="ids"/></td>
		        <td>${user.id}</td>
                <td>${user.username}</td>
		        <td>${user.password}</td>
		        <td>${user.phone}</td>
		        <td>${user.email}</td>
		        <td>${user.is_role eq "1" ? "管理员":"普通用户"}</td>
		        <td><a href="UserServlet?action=getUser&id=${user.id}">编辑</a>/<a href="UserServlet?action=delete&id=${user.id}">删除</a></td>
	        </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="${page.url}&currentPage=1">首页</a>

        <c:if test="${page.currentPage>1}">
    <a href="${page.url}&currentPage=${page.currentPage-1}">上一页</a>
        </c:if>

        <c:if test="${page.currentPage<page.pageCount}">
    <a href="${page.url}&currentPage=${page.currentPage+1}">下一页</a>
        </c:if>
    <a href="${page.url}&currentPage=${page.pageCount}">尾页</a>
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>