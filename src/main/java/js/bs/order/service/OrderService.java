package js.bs.order.service;

import java.util.List;
import java.util.Map;

import js.bs.order.vo.OrderVO;

public interface OrderService {
	// 나의 주문상품들 리스트
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception;
	// 새로운 주문 추가
	public void addNewOrder(List<OrderVO> myOrderList) throws Exception;
	// 내 주문 찾기
	public OrderVO findMyOrder(String order_id) throws Exception;
	
	
}
