package js.bs.order.service;

import java.util.List;
import java.util.Map;

import js.bs.order.vo.OrderVO;

public interface OrderService {
	// ���� �ֹ���ǰ�� ����Ʈ
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception;
	// ���ο� �ֹ� �߰�
	public void addNewOrder(List<OrderVO> myOrderList) throws Exception;
	// �� �ֹ� ã��
	public OrderVO findMyOrder(String order_id) throws Exception;
	
	
}
