package js.bs.mypage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import js.bs.member.vo.MemberVO;
import js.bs.order.vo.OrderVO;

public interface MyPageService{
	//내 주문상품 리스트
	public List<OrderVO> listMyOrderGoods(String member_id) throws Exception;
	// 내 주문정보 찾기
	public List findMyOrderInfo(String order_id) throws Exception;
	// 내 주문 이력 리스트
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception;
	// 내 정보 수정
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception;
	// 주문 취소
	public void cancelOrder(String order_id) throws Exception;
	// 내 디테일 정보
	public MemberVO myDetailInfo(String member_id) throws Exception;

}
