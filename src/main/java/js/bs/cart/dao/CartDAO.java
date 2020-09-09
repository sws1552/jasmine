package js.bs.cart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import js.bs.cart.vo.CartVO;
import js.bs.goods.vo.GoodsVO;

public interface CartDAO {
	// īƮ ����Ʈ ��ȸ
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException;
	// ��ǰ ����Ʈ ��ȸ
	public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException;
	// īƮ�ȿ� ��ǰ�� �ִ��� ī��Ʈ üũ
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException;
	// īƮ�ȿ� ��ǰ �߰�
	public void insertGoodsInCart(CartVO cartVO) throws DataAccessException;
	// ��ǰ ���� ����
	public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException;
	// īƮ�ȿ� ��ǰ ����
	public void deleteCartGoods(int cart_id) throws DataAccessException;
	
	

}
