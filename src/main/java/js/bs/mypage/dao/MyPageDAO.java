package js.bs.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import js.bs.member.vo.MemberVO;
import js.bs.order.vo.OrderVO;

public interface MyPageDAO {
	// 내 주문 상품 리스트 조회
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException;
	// 내 주문 정보 조회
	public List selectMyOrderInfo(String order_id) throws DataAccessException;
	// 내 주문 이력 리스트 조회
	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException;
	// 내 정보 수정
	public void updateMyInfo(Map memberMap) throws DataAccessException;
	// 내 디테일 정보 조회
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException;
	// 주문 취소
	public void updateMyOrderCancel(String order_id) throws DataAccessException;
}
