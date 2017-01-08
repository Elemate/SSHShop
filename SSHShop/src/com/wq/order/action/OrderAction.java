/**
 * 
 */
package com.wq.order.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.cart.pojo.Cart;
import com.wq.cart.pojo.CartItem;
import com.wq.order.dao.OrderDao;
import com.wq.order.pojo.Order;
import com.wq.order.pojo.OrderItem;
import com.wq.order.service.OrderService;
import com.wq.user.pojo.User;
import com.wq.utils.PageBean;
import com.wq.utils.PaymentUtil;

/**
 * @author youto8023 订单管理模块action层
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private OrderService orderService;
	private Order order = new Order();
	private Integer itemid;
	//订单当前页数
	private Integer page;
	//支付银行
	private String pd_FrpId;
	//接受付款成功后返回的参数
	private String r6_Order;
	private String r3_Amt;
	
	//保存订单的方法
	public String save(){
		//判断用户是否登录
		User exitUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (exitUser==null) {
			return "login";
		}
		//获得购物车对象
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart==null) {
			this.addActionError("你还没有购物，请去购物");
			return "msg";
		}
		order.setOrdertime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		order.setUser(exitUser);
		order.setState(1);  	//1代表未付款，2代表已付款，3代表未收货，4代表交易完成
		order.setTotal(cart.getTotal());
		order.setAddr(exitUser.getAddr());
		order.setName(exitUser.getName());
		order.setPhone(exitUser.getPhone());
		Set<OrderItem> orderItems = new HashSet<OrderItem>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubTotal(cartItem.getSubCount());
			orderItem.setOrder(order);
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		orderService.save(order);
		cart.clearCart();
		return "save";
	}
	
	//点击我的订单功能实现
	public String findOrderByOid(){
		//判断用户是否登录
		User exitUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (exitUser==null) {
			return "login";
		}
		Integer uid = exitUser.getUid();
		//根据用户id获得其所有订单
		PageBean<Order> pageBean = orderService.findOrderByUid(uid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findOrderByOid";
	}
	
	//删除订单项功能
	public String removeOrderItem(){
		orderService.removeOrderItem(itemid);
		return "removeOrderItem";
	}
	
	//未付款订单进入支付页面
	public String payOrder() throws IOException, ServletException{
//		order = orderService.findOrderByOid(order.getOid());
//		ServletActionContext.getResponse().sendRedirect("https://www.baidu.com");
		ServletActionContext.getRequest().getRequestDispatcher("${pageContext.request.contextPath}/user_regist.action").forward(null, null);;
		return NONE;
	}
	
	//订单支付功能
	public String pay() throws IOException{
		Order _order = orderService.findOrderByOid(order.getOid());
		_order.setAddr(order.getAddr());
		_order.setName(order.getName());
		_order.setPhone(order.getPhone());
		//修改订单
		orderService.update(_order);
		//支付相关信息
		String p0_Cmd = "Buy";	//业务类型
		String p1_MerId = "10001126856";	//商户编号
		String p2_Order = _order.getOid().toString();	//商户订单号
		String p3_Amt = "0.01";  //支付金额
		String p4_Cur = "CNY";	//交易币种
		String p5_Pid = "";		//商品名称
		String p6_Pcat = "";	//商品种类
		String p7_Pdesc = "";	//商品描述
		String p8_Url = "http://127.0.0.1:8080/SSHShop/order_callBack.action";		//商户接受支付成功数据的地址
		String p9_SAF = "";		//送货地址
		String pa_MP ="";		//商户扩展信息
		String pd_FrpId = this.pd_FrpId;
		String pr_NeedResponse = "1";	//应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";	//密钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		//向易宝发送请求
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		ServletActionContext.getResponse().sendRedirect(sb.toString());
//		ServletActionContext.getRequest().getRequestDispatcher("").forward(arg0, arg1);
		return NONE;
	}
	
	public String callBack(){
		//修改订单
		Order _order = orderService.findOrderByOid(Integer.parseInt(r6_Order));
		_order.setState(2);
		orderService.update(_order);
		this.addActionMessage("支付成功！订单号："+r6_Order+",支付金额:"+r3_Amt);
		return "msg";
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	
}
