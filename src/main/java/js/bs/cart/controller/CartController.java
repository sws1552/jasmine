package js.bs.cart.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface CartController {
	
	//내 카트 리스트
	public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	// 카트안에 상품 추가
	public @ResponseBody String addGoodsInCart(@RequestParam("goods_id") int goods_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	// 카트 수량 수정
	public  @ResponseBody String modifyCartQty(@RequestParam("goods_id") int goods_id,@RequestParam("cart_goods_qty") int cart_goods_qty,
			                  HttpServletRequest request, HttpServletResponse response)  throws Exception;
	// 카트에 상품 제거
	public ModelAndView removeCartGoods(@RequestParam("cart_id") int cart_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
	

}
