package js.bs.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import js.bs.cart.dao.CartDAO;
import js.bs.cart.vo.CartVO;
import js.bs.goods.vo.GoodsVO;

@Service("cartService")
@Transactional(propagation=Propagation.REQUIRED)
public class CartServiceImpl  implements CartService{
	@Autowired
	private CartDAO cartDAO;
	//내 카트 리스트 
	public Map<String ,List> myCartList(CartVO cartVO) throws Exception{
		Map<String,List> cartMap=new HashMap<String,List>();
		List<CartVO> myCartList=cartDAO.selectCartList(cartVO);
		if(myCartList.size()==0){ //카트에 저장된 상품이없는 경우
			return null; // 메소드 종료
		}
		List<GoodsVO> myGoodsList=cartDAO.selectGoodsList(myCartList);
		cartMap.put("myCartList", myCartList);
		cartMap.put("myGoodsList",myGoodsList);
		return cartMap;
	}

	public boolean findCartGoods(CartVO cartVO) throws Exception{
		 return cartDAO.selectCountInCart(cartVO);
		 // 테이블에 추가하기 전에 동일한 상품 번호의 개수를 조회
		 // 상품 추가하기 전에 354 라는 상품번호가 있는지 체크를하고 있으면 안넣고 없으면 넣게하려는 거임.
		
	}	
	public void addGoodsInCart(CartVO cartVO) throws Exception{
		cartDAO.insertGoodsInCart(cartVO);
	}
	
	public boolean modifyCartQty(CartVO cartVO) throws Exception{
		boolean result=true;
		cartDAO.updateCartGoodsQty(cartVO);
		return result;
	}
	public void removeCartGoods(int goods_id) throws Exception{
		cartDAO.deleteCartGoods(goods_id);
	}
	
}
