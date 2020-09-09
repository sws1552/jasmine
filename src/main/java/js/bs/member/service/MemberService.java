package js.bs.member.service;

import java.util.Map;

import js.bs.member.vo.MemberVO;

public interface MemberService {
	// �α���
	public MemberVO login(Map  loginMap) throws Exception;
	
	// īī�� �α���
	public MemberVO kakaologin(String kakao_id) throws Exception;
	
	// �ɹ��߰�
	public void addMember(MemberVO memberVO) throws Exception;
	// �ߺ� Ȯ��
	public String overlapped(String id) throws Exception;
	// īī�� ���̵�� ȸ������ ����
	public String kakaoOverlap(String id) throws Exception;
}
