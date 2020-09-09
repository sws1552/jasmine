package js.bs.mypage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import js.bs.member.vo.MemberVO;
import js.bs.order.vo.OrderVO;

public interface MyPageService{
	//�� �ֹ���ǰ ����Ʈ
	public List<OrderVO> listMyOrderGoods(String member_id) throws Exception;
	// �� �ֹ����� ã��
	public List findMyOrderInfo(String order_id) throws Exception;
	// �� �ֹ� �̷� ����Ʈ
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception;
	// �� ���� ����
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception;
	// �ֹ� ���
	public void cancelOrder(String order_id) throws Exception;
	// �� ������ ����
	public MemberVO myDetailInfo(String member_id) throws Exception;

}
