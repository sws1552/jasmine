package js.bs.cart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import js.bs.cart.vo.CartVO;
import js.bs.goods.vo.GoodsVO;

public interface CartDAO {
	// 카트 리스트 조회
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException;
	// 상품 리스트 조회
	public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException;
	// 카트안에 상품이 있는지 카운트 체크
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException;
	// 카트안에 상품 추가
	public void insertGoodsInCart(CartVO cartVO) throws DataAccessException;
	// 상품 수량 수정
	public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException;
	// 카트안에 상품 삭제
	public void deleteCartGoods(int cart_id) throws DataAccessException;
	
	

}
