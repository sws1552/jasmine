package js.bs.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import js.bs.order.vo.OrderVO;

public interface OrderController {
	//각각 상품 주문
	public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	//카트에 담긴 전체 상품 주문
	public ModelAndView orderAllCartGoods(@RequestParam  String[] cart_goods_qty,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	// 주문상품 결제
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> orderMap,HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
