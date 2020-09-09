package js.bs.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import js.bs.cart.vo.CartVO;
import js.bs.goods.vo.GoodsVO;

public interface CartService {
	//�� īƮ ����Ʈ
	public Map<String ,List> myCartList(CartVO cartVO) throws Exception;
	// īƮ�� ��ǰ �˻� (��ǰ ��ȣ�� ��ٱ��� ���̺� �̹� �ִ��� ��ȸ)
	public boolean findCartGoods(CartVO cartVO) throws Exception;
	// īƮ�ȿ� ��ǰ �߰�
	public void addGoodsInCart(CartVO cartVO) throws Exception;
	// īƮ ���� ����
	public boolean modifyCartQty(CartVO cartVO) throws Exception;
	// īƮ ��ǰ ����
	public void removeCartGoods(int cart_id) throws Exception;
}
