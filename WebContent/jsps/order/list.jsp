<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
		border: solid 2px gray;
		width: 75px;
		height: 75px;
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
  
<h1>我的订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">
<c:forEach items="${orderList}" var="order">
		<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			订单编号：${order.oid }成交时间：${order.ordertime }　金额：<font color="red"><b>${order.total }</b></font>　
			<c:choose>
				<c:when test="${order.state eq 1 }">
					<a href="<c:url value='/load?oid=${order.oid}'/>">付款</a>
				</c:when>
				<c:when test="${order.state eq 2 }">
					<font color="red">等待发货</font>
				</c:when>
				<c:when test="${order.state eq 3 }">
					<a href="<c:url value=''/>">确认收货</a>
				</c:when>
				<c:when test="${order.state eq 4 }">
					 完成交易
				</c:when>
			</c:choose>
		</td>
	</tr>
	<c:forEach items='${order.orderItemsList }' var='orderItem'>
		<tr bordercolor="gray" align="center">
			<td width="15%">
				<div align="center"><img width="75" src="${orderItem.product.image }" height="75"/></div>
			</td>
			<td>品名：${orderItem.product.pname }</td>
			<td>单价：${orderItem.product.price }</td>
			<td>描述：${orderItem.product.describle }</td>
			<td>数量：${orderItem.count }</td>
			<td>小计：${orderItem.subtotal }</td>
		</tr>
	</c:forEach>
</c:forEach>
</table>
  </body>
</html>
