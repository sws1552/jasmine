package js.bs.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import js.bs.order.vo.OrderVO;

public interface OrderDAO {
	// 내 주문 상품 리스트
	public List<OrderVO> listMyOrderGoods(OrderVO orderBean) throws DataAccessException;
	// 새로운 주문 추가
	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;
	// 내 주문 찾기
	public OrderVO findMyOrder(String order_id) throws DataAccessException;
	// 카트로 부터 상품들 제거
	public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException;
}
