package com.duoyi.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.duoyi.model.po.Order;
import com.duoyi.model.vo.OrderVo;
import com.duoyi.web.service.GoodsService;
import com.duoyi.web.service.OrderService;

import net.sf.json.JSONObject;

/**订单类的controller
 * @author 浩子
 *2018年11月2日
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/insertOrder")
	@ResponseBody
	public JSONObject insertOrder(@RequestBody Order order,HttpServletRequest request){
		
		int userid = (int) request.getSession().getAttribute("userid");
		order.setUserid(userid);
		
		//order.setUserid(1);
		int status = orderService.insertOrder(order);
		
		JSONObject json = new JSONObject();
		json.put("status", status);
		if(status == 1){
			json.put("message", "下单成功");
			json.put("orderid",order.getId());
		}else if (status == 2) {
			json.put("message", "该物品已被买走");
		}else if(status == 0){
			json.put("message", "该商品已被下架");
		}else{
			json.put("message", "下单失败");
		}
		return json;
	}
	
	@PostMapping("/delectOrder")
	@ResponseBody  //用户删除订单
	public JSONObject delectOrder(@RequestBody Order order,HttpServletRequest request){
		
		int id = order.getId();
		int status = orderService.delectOrder(id);
		
		JSONObject json = new JSONObject();
		json.put("status", status);
		if(status == 1){
			json.put("message", "删除订单成功");
		}else{
			json.put("message", "删除订单失败");
		}
		
		return json;
	}
	
	@PostMapping("/selectOrders")
	@ResponseBody
	public JSONObject seleceOrders(HttpServletRequest request){
		
		int userid = (int) request.getSession().getAttribute("userid");
		List<OrderVo> list = orderService.selectOrders(userid);
		JSONObject json = new JSONObject();
		
		if(list == null){
			json.put("status", -1);
			json.put("message", "查询失败,请重试");
		}else if (list.size() == 0) {
			json.put("status", 0);
			json.put("message", "该用户暂时没有订单");
		}else{
			//System.out.println(list.size());
			json.put("status", 1);
			json.put("message", "查询成功");
			json.put("result", list);
		}
		
		
		return json;
	}
	
	
	@PostMapping("/mybuy")
	@ResponseBody
	public JSONObject mybuy(HttpServletRequest request){
		
		int userid = (int) request.getSession().getAttribute("userid");
		List<OrderVo> list = orderService.selectOrderResult(userid);
		JSONObject json = new JSONObject();
		
		if(list == null){
			json.put("status", -1);
			json.put("message", "查询失败,请重试");
		}else if (list.size() == 0) {
			json.put("status", 0);
			json.put("message", "该用户还没有买下的东西");
		}else{
			//System.out.println(list.size());
			json.put("status", 1);
			json.put("message", "查询成功");
			json.put("result", list);
		}
		
		
		return json;
	}
	
	@PostMapping("/pay")
	@ResponseBody
	public JSONObject payGoods(@RequestBody Map map,HttpServletRequest request){
		
		JSONObject json = new JSONObject();
		int userid = (int)request.getSession().getAttribute("userid");
		
		map.put("userid", userid);
		int status = orderService.payMoney(map);
		json.put("status", status);
		if(status == 1){
			json.put("message", "支付成功");
		}else{
			json.put("message", "支付失败,请确认您的余额");
		}
		return json;
		
	}
}
