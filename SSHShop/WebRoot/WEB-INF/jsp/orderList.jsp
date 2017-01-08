<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="传智播客"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	
</div>
	<%@ include file="menu.jsp" %>
</div>	

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					<span><h3>订单列表</h3> </span>
					<li  class="current"></li>
				</ul>
			</div>
	
				
				<table>
					<tbody>
					
					<s:iterator value="pageBean.list" var="orders">
						<tr>
							<th colspan="2"><s:property value="#orders.ordertime" /></th>
							<th colspan="2">订单号：<s:property value="#orders.oid" /></th>
							<s:if test="#orders.state == 1">
								<th colspan="1"><a href="${pageContext.request.contextPath }/order_payOrder.action?oid=<s:property value="#orders.oid" />"><font color="red">未付款</font> </a></th>
							</s:if>
							<s:elseif test="#orders.state == 2">
								<th colspan="1"><a href="#"><font color="purple">已付款</font> </a></th>
							</s:elseif>
							<s:elseif test="#orders.state == 3">
								<th colspan="1"><a href="#"><font color="purple">未收货</font> </a></th>
							</s:elseif>
							<s:elseif test="#orders.state == 4">
								<th colspan="1"><a href="#"><font color="purple">交易完成</font> </a></th>
							</s:elseif>
						</tr>
						
						<s:iterator value="#orders.orderItems" var="orderItems">
						<tr>
							<td width="80">
								<img src="${pageContext.request.contextPath }/<s:property value="#orderItems.product.image" />"/>
							</td>
							<td>
								<a target="_blank"><s:property value="#orderItems.product.pname"/></a>
							</td>
							<td class="quantity" width="60">
								<s:property value="#orderItems.count" />
							</td>
							<td width="140">
								<span class="subtotal"><s:property value="#orderItems.subTotal" /></span>
							</td>
							<td>
								<a href="${pageContext.request.contextPath }/order_removeOrderItem.action?itemid=<s:property value="#orderItems.itemid" />" class="delete">删除</a>
							</td>
						</tr>
						</s:iterator>
						</s:iterator>
				</tbody>
			</table>
		</div>
		<div class="pagination">
					<span>第<s:property value="pageBean.page" />/<s:property value="pageBean.totalPage" />页</span>
						<s:if test="pageBean.page != 1">
							<a href="${pageContext.request.contextPath }/order_findOrderByOid.action?cid=<s:property value="cid" />&page=1" class="firstPage"> &nbsp;</a>
							<a href="${pageContext.request.contextPath }/order_findOrderByOid.action?cid=<s:property value="cid" />&page=<s:property value="pageBean.page-1" />" class="previousPage"> &nbsp;</a>
						</s:if>
						<s:iterator var="i" begin="1" end="pageBean.totalPage">
							<s:if test="pageBean.page != #i">
								<a href="${pageContext.request.contextPath }/order_findOrderByOid.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i" /></span>
							</s:else>
						</s:iterator>
						<s:if test="pageBean.page != pageBean.totalPage">
							<a class="nextPage" href="${pageContext.request.contextPath }/order_findOrderByOid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1" />">&nbsp;</a>
							<a class="lastPage" href="${pageContext.request.contextPath }/order_findOrderByOid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage" />">&nbsp;</a>
						</s:if>
					
				</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>


