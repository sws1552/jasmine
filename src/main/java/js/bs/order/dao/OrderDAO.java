package js.bs.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import js.bs.order.vo.OrderVO;

public interface OrderDAO {
	// �� �ֹ� ��ǰ ����Ʈ
	public List<OrderVO> listMyOrderGoods(OrderVO orderBean) throws DataAccessException;
	// ���ο� �ֹ� �߰�
	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;
	// �� �ֹ� ã��
	public OrderVO findMyOrder(String order_id) throws DataAccessException;
	// īƮ�� ���� ��ǰ�� ����
	public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException;
}
