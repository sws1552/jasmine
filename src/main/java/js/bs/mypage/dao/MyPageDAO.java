package js.bs.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import js.bs.member.vo.MemberVO;
import js.bs.order.vo.OrderVO;

public interface MyPageDAO {
	// �� �ֹ� ��ǰ ����Ʈ ��ȸ
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException;
	// �� �ֹ� ���� ��ȸ
	public List selectMyOrderInfo(String order_id) throws DataAccessException;
	// �� �ֹ� �̷� ����Ʈ ��ȸ
	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException;
	// �� ���� ����
	public void updateMyInfo(Map memberMap) throws DataAccessException;
	// �� ������ ���� ��ȸ
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException;
	// �ֹ� ���
	public void updateMyOrderCancel(String order_id) throws DataAccessException;
}
