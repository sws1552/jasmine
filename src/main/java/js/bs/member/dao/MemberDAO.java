package js.bs.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import js.bs.member.vo.MemberVO;

public interface MemberDAO {
	// �α���
	public MemberVO login(Map loginMap) throws DataAccessException;
	
	// īī�� �α���
	public MemberVO kakaologin(String kakao_id) throws DataAccessException;
	
	//����߰�
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	//���̵� �ߺ�üũ
	public String selectOverlappedID(String id) throws DataAccessException;
	
	//īī�� ���̵� �ߺ�üũ
	public String kakaoOverlap(String id) throws DataAccessException;
}
