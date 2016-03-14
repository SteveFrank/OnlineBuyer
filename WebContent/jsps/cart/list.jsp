<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>购物车列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
</head>
  
<body>
<h1>购物车</h1>
<c:choose>
	<%--
		如果没有车，或者车的内容集合为0长 
		or fn:length(sessionScope.cart.cartItems) eq 0
	--%>
	<c:when test="${empty sessionScope.cart or fn:length(sessionScope.cart.cartItems) eq 0 }">
		<img src="<c:url value='/images/cart.png' />"  width="200"/>
	</c:when>
	<c:otherwise>
		<table border="1" width="100%" cellspacing="0" background="black">
			<tr>
			<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
				<a href="<c:url value='/clearCart'/>">清空购物车</a>
			</td>
			</tr>
			<tr>
				<th>图片</th>
				<th>名称</th>
				<th>单价</th>
				<th>数量</th>
				<th>小计</th>
				<th>操作</th>
			</tr>
		<c:forEach items='${sessionScope.cart.cartItems}' var='cartItem'>
			<tr>
				<td><div><img width="150" height="150" src="<c:url value='/${cartItem.product.image }'/>"/></div></td>
				<td>${cartItem.product.pname }</td>
				<td>${cartItem.product.price }</td>
				<td>${cartItem.count }</td>
				<td>${cartItem.subtotal }</td>
				<td><a href="<c:url value='/deleteProductByPid?pid=${cartItem.product.pid}'/>">删除</a></td>
			</tr>
		</c:forEach>
			<tr>
				<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
					合计：${sessionScope.cart.total}元
				</td>
			</tr>
			<tr>
				<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
					<a id="buy" href="<c:url value='/OrderServlet?method=addOrder'/>"></a>
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>
