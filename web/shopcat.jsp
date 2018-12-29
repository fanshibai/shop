<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<title>购物车页面</title>

<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet"
	type="text/css" />
<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />
<link href="css/cartstyle.css" rel="stylesheet" type="text/css" />
<link href="css/optstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript">
/*	$(function () {
		var sum=0;
		$(".sums").each(function (i) {
			var price=parseFloat($(".prices").eq(i).val());
			var value = parseInt($(".values").eq(i).val());
			$(this).text((price*value).toFixed(1));
			sum=sum+price*value
		})
		$("#J_Total").text(sum.toFixed(1));
        function addCount(index){
			var price=parseFloat($(".prices").eq(index).val());
			var value = parseInt($(".values").eq(index).val());
			$(".sums").eq(index).text((price*value).toFixed(1));
			sum=sum+price;
		}
		function minCount(index){
			var price=parseFloat($(".prices").eq(index).val());
			var value = parseInt($(".values").eq(index).val());
			$(".sums").eq(index).text((price*value).toFixed(1));
			sum=sum-price;
		}
		//2.添加的点击事件
		$(".add_btn").each(function (i) {
			var max=parseInt($(".stock").eq(i).val());
			//获取当前输入框的value值
			$(this).click(function () {
				var value = $(".values").eq(i).val();
				//判断数量是否在合理的范围内
				if(value < max){
					//vlaue值为String类型,必须转换成int类型
					$(".values").eq(i).val(parseInt(value)+1);
					addCount(i);
					$("#J_Total").text(sum.toFixed(1));
				}else{
					$(".values").eq(i).val(max);
				}

			})

		})

		//3.减少的点击事件
		$(".min_btn").each(function (i) {
			//获取当前输入框的value值
			$(this).click(function () {
				var value = $(".values").eq(i).val();
				//判断数量是否在合理的范围内
				if(value > 1){
					//vlaue值为String类型,必须转换成int类型
					$(".values").eq(i).val(parseInt(value)-1);
					minCount(i);
					$("#J_Total").text(sum.toFixed(1));
				}else{
					$(".values").eq(i).val(1);
				}

			})

		})

		//4.输入框的失去光标焦点事件
		$(".values").each(function (i) {
			var price=parseFloat($(".prices").eq(i).val());
			var max=parseInt($(".stock").eq(i).val());
			//获取当前数量
			var oldValue = parseInt($(".values").eq(i).val());
			$(this).blur(function () {
				var value = parseInt($(".values").eq(i).val());
				if(value > max){
					$(this).val(max)
					$(".sums").eq(i).text((price*max).toFixed(1));
					sum=sum+(max-oldValue)*price;
				}else if(value <1){
					$(this).val(1);
					$(".sums").eq(i).text((price*1).toFixed(1));
					sum=sum-(oldValue-1)*price;
				}else if(value>=1&&value<=max){
					$(this).val(value)
					$(".sums").eq(i).text((price*value).toFixed(1));
					sum=sum+(value-oldValue)*price;
				}
				$("#J_Total").text(sum.toFixed(1));
			})

		})
	})*/
$(function () {
	//2.添加的点击事件
	$(".add_btn").each(function (i) {
		var max=parseInt($(".stock").eq(i).val());
		var id=parseInt($(".id").eq(i).val());
		//获取当前输入框的value值
		$(this).click(function () {
			var value = $(".values").eq(i).val();
			//判断数量是否在合理的范围内
			if(value < max){
				//vlaue值为String类型,必须转换成int类型
				$(".values").eq(i).val(parseInt(value)+1);
				$.post("ShopCartServlet?action=shopCart&id="+id+"&count=1",function (data) {
					location.reload();
				})
			}else{
				$(".values").eq(i).val(max);
			}

		})

	})

//3.减少的点击事件
	$(".min_btn").each(function (i) {
		//获取当前输入框的value值
		$(this).click(function () {
			var value = $(".values").eq(i).val();
			var id=parseInt($(".id").eq(i).val());
			//判断数量是否在合理的范围内
			if (value > 1) {
				//vlaue值为String类型,必须转换成int类型
				$(".values").eq(i).val(parseInt(value) - 1);
				$.post("ShopCartServlet?action=deleteOne&id="+id,function (data) {
					location.reload();
				})
			} else {
				$(".values").eq(i).val(1);
			}

		})
	})
})

</script>
</head>

<body>

	<!--顶部导航条 -->
	<%@include file="head.jsp"%>

	<!--购物车 -->
	<div class="concent">
		<div id="cartTable">
			<div class="cart-table-th">
				<div class="wp">
					<div class="th th-chk">
						<div id="J_SelectAll1" class="select-all J_SelectAll"></div>
					</div>
					<div class="th th-item">
						<div class="td-inner">商品信息</div>
					</div>
					<div class="th th-price">
						<div class="td-inner">单价</div>
					</div>
					<div class="th th-amount">
						<div class="td-inner">数量</div>
					</div>
					<div class="th th-sum">
						<div class="td-inner">金额</div>
					</div>
					<div class="th th-op">
						<div class="td-inner">操作</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>

			<tr class="item-list">
				<div class="bundle  bundle-last ">
					<div class="bundle-hd">
						<div class="bd-promos"></div>
					</div>
					<div class="clear"></div>
					<div class="bundle-main"></div>
				</div>
			</tr>
			<div class="clear"></div>
			
				<tr class="item-list">
					<div class="bundle  bundle-last ">

						<div class="clear"></div>
						<div class="bundle-main">
							<c:forEach items="${list}" var="info">
						<!-- 购物车 -->
								<input class="id" value="${info.id}" type="hidden"/>
								<input class="stock" value="${info.goods_stock}" type="hidden"/>
							<ul class="item-content clearfix">
								<li class="td td-item">
									<div class="item-pic">
										<a href="ShopCartServlet?action=getGoodsInfo&id=${info.id}" target="_blank" class="J_MakePoint"
											data-point="tbcart.8.12"> <img
											src="images/${info.goods_pic}"
											style="width: 80px; height: 80px" class="itempic J_ItemImg"></a>
									</div>
									<div class="item-info">
										<div class="item-basic-info">
											<a href="ShopCartServlet?action=getGoodsInfo&id=${info.id}" target="_blank" title=""
												class="item-title J_MakePoint" data-point="tbcart.8.11">${info.goods_name}</a>
										</div>
									</div>
								</li>
								<li class="td td-info">
									<div class="item-props item-props-can">
											<span>${info.goods_description}</span>
										<i class="theme-login am-icon-sort-desc"></i>
									</div>
								</li>
								<li class="td td-price">
									<div class="item-price price-promo-promo">
										<div class="price-content">
													<div class="price-line">
														<em class="price-original">${info.goods_price}</em>
													</div>
														
													<div class="price-line">
														<em class="J_Price price-now" tabindex="0">${info.goods_price_off}</em>
													</div>
										</div>
									</div>
								</li>
								<li class="td td-amount">
									<div class="amount-wrapper ">
										<div class="item-amount ">
											<div class="sl">
												<input class="min am-btn min_btn" name="" type="button" value="-" />
											 <input class="text_box values"  type="text" value="${info.count}" style="width: 30px; text-align: center;" />
												<input class="add am-btn add_btn" name="" type="button" value="+" id="add" />
										
											</div>
										</div>
									</div>
								</li>
								<li class="td td-sum">
									<div class="td-inner">
										<em tabindex="0" class="J_ItemSum number sums" ><fmt:formatNumber value="${info.sum}" pattern="0.00"></fmt:formatNumber> </em>
									</div>
								</li>
								<li class="td td-op" id="delete">
									<div class="td-inner">
									<a href="ShopCartServlet?action=delete&id=${info.id}" data-point-url="#" class="delete"> 删除</a>
									</div>
								</li>
							
							</ul>
							</c:forEach>
						</div>
					</div>
				</tr>
		</div>
		<div class="clear"></div>

		<div class="float-bar-wrapper">
			<div class="float-bar-right">
				<div class="amount-sum">
					<span class="txt">已选商品</span> <em id="getnum"></em><span
						class="txt">${sessionScope.shopCart.count}件</span>
					<div class="arrow-box">
						<span class="selected-items-arrow"></span> <span class="arrow"></span>
					</div>
				</div>
				<div class="price-sum">
					<span class="txt">合计:</span> 
					<strong class="price">
					¥<em id="J_Total"><fmt:formatNumber value="${sessionScope.shopCart.sums}" pattern="0.00"></fmt:formatNumber> </em>
					</strong>
				</div>
				<div class="btn-area">
					<a href="ShopCartServlet?action=getAddressList" id="J_Go" class="submit-btn submit-btn-disabled"
						aria-label="请注意如果没有选择宝贝，将无法结算"> <span>结&nbsp;算</span></a>
				</div>
			</div>

		</div>

		<div class="footer">
			<div class="footer-hd">
				<p>
					<a href="#">恒望科技</a> <b>|</b> <a href="#">商城首页</a> <b>|</b> <a
						href="#">支付宝</a> <b>|</b> <a href="#">物流</a>
				</p>
			</div>
			<div class="footer-bd">
				<p>
					<a href="#">关于恒望</a> <a href="#">合作伙伴</a> <a href="#">联系我们</a> <a
						href="#">网站地图</a> <em>© 2015-2025 Hengwang.com 版权所有</em>
				</p>
			</div>
		</div>

	</div>

	<!--操作页面-->

	<div class="theme-popover-mask"></div>

	<div class="theme-popover">
		<div class="theme-span"></div>
		<div class="theme-poptit h-title">
			<a href="javascript:;" title="关闭" class="close">×</a>
		</div>
		<div class="theme-popbod dform">
			<form class="theme-signin" name="loginform" action="" method="post">

				<div class="theme-signin-left">

					<li class="theme-options">
						<div class="cart-title">颜色：</div>
						<ul>
							<li class="sku-line selected">12#川南玛瑙<i></i></li>
							<li class="sku-line">10#蜜橘色+17#樱花粉<i></i></li>
						</ul>
					</li>
					<li class="theme-options">
						<div class="cart-title">包装：</div>
						<ul>
							<li class="sku-line selected">包装：裸装<i></i></li>
							<li class="sku-line">两支手袋装（送彩带）<i></i></li>
						</ul>
					</li>
					<div class="theme-options">
						<div class="cart-title number">数量</div>
						<dd>
							<input class="min am-btn am-btn-default" name="" type="button" value="-" />
							 <input class="text_box" name="" type="text" value="1" style="width: 30px;" />
							  <input class="add am-btn am-btn-default" name="" type="button" value="+" /> 
							  <span class="tb-hidden">库存<span class="stock">1000</span>件
							</span>
						</dd>

					</div>
					<div class="clear"></div>
					<div class="btn-op">
						<div class="btn am-btn am-btn-warning">确认</div>
						<div class="btn close am-btn am-btn-warning">取消</div>
					</div>

				</div>
				<div class="theme-signin-right">
					<div class="img-info">
						<img src="images/kouhong.jpg_80x80.jpg" />
					</div>
					<div class="text-info">
						<span class="J_Price price-now">¥39.00</span> <span id="Stock"
							class="tb-hidden">库存<span class="stock">1000</span>件
						</span>
					</div>
				</div>

			</form>
		</div>
	</div>
	<!--引导 -->
	<div class="navCir">
		<li><a href="home.html"><i class="am-icon-home "></i>首页</a></li>
		<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
		<li class="active"><a href="shopcart.html"><i
				class="am-icon-shopping-basket"></i>购物车</a></li>
		<li><a href="person/index.html"><i class="am-icon-user"></i>我的</a></li>
	</div>
</body>

</html>