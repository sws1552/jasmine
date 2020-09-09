package js.bs.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import js.bs.cart.vo.CartVO;
import js.bs.goods.vo.GoodsVO;

public interface CartService {
	//내 카트 리스트
	public Map<String ,List> myCartList(CartVO cartVO) throws Exception;
	// 카트에 상품 검색 (상품 번호가 장바구니 테이블에 이미 있는지 조회)
	public boolean findCartGoods(CartVO cartVO) throws Exception;
	// 카트안에 상품 추가
	public void addGoodsInCart(CartVO cartVO) throws Exception;
	// 카트 수량 수정
	public boolean modifyCartQty(CartVO cartVO) throws Exception;
	// 카트 상품 제거
	public void removeCartGoods(int cart_id) throws Exception;
}
