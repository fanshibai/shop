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
            //2.获取大类别和小类别的对象
            var goods_parentid = $("#goods_parentid");
            var goods_fatherid = $("#goods_fatherid");

            //3.初始化大类别
            initGoodsType(0,goods_parentid,0);
            //4.给大类别添加改变事件
            goods_parentid.change(function () {
                //1.先清除当前小类别的所有option
                goods_fatherid.find("option").remove();
                //2.获取改变后大类别的val
                var newParentId = $(this).val();
                //3.初始化小类别
                initGoodsType(newParentId,goods_fatherid,0);
            })

            //图片的初始化--根据图片的选择文件按钮触发事件
            $("#uploadFile").change(function () {
                //1.获取用户上传的图片对象
                var fileObject = this.files[0];
                //2.把图片对象转换成url
                var fileUrl = window.URL.createObjectURL(fileObject);
                //3.改变图片的src
                // $("#goods_pic_ele").attr("src",fileUrl);
                var img = document.createElement("img");
                $(img).attr("src",fileUrl);
                // img.src=fileUrl;
                var div= $("#img")
                div.append(img);
            })
        })



        /**
         * 初始化类别的函数
         * @param goods_parent_id	父类的id,用来传递到后台获取对应的对象集合
         * @param parentEle			需要添加option标签的selcet标签的对象
         * @param value				改变选中的状态的值
         */
        function initGoodsType(goods_parent_id,parentEle,value) {
            $.post("GoodsTypeServlet?action=goodsTypeByParentIdList&parent_id="+goods_parent_id,function (data) {
                for (var i = 0; i < data.length; i++) {
                    //1.创建option对象
                    var option = document.createElement("option");
                    //给option对象添加value值和内容
                    option.value = data[i].id;
                    option.text = data[i].name;
                    //option添加到小类对象中
                    parentEle.append(option);
                }
                //改变小类的选中状态
                parentEle.val(value);
            },"JSON");
        }
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
    
    <div class="formtitle"><span>添加商品信息</span></div>
    <form method="post" enctype="multipart/form-data" action="GoodsInfoServlet?action=addGoodsInfo">
    	<ul class="forminfo">
	    <li><label>商品名称</label><input name="goods_name" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
	    <li><label>所属大类</label>
	    		<select name="goods_parentid" id="goods_parentid">
	    			<option value="0">无</option>
	    		</select>
	    		
	    </li>
	    <li><label>所属小类</label>
	    		<select name="goods_fatherid" id="goods_fatherid">
	    		<option value="0">无</option>
	    		</select>
	    </li>
	     <li><label>商品图片</label><input name="goods_pic" type="file" id="uploadFile" /></li>
            <div id="img"></div>
	    <li><label>商品价格</label><input name="goods_price" type="text" class="dfinput" /></li>
	    <li><label>商品描述</label><textarea rows="8" cols="40" name="goods_description" ></textarea></li>
	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    
    </form>
    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

