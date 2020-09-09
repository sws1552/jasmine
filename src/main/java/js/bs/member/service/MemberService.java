package js.bs.member.service;

import java.util.Map;

import js.bs.member.vo.MemberVO;

public interface MemberService {
	// 로그인
	public MemberVO login(Map  loginMap) throws Exception;
	
	// 카카오 로그인
	public MemberVO kakaologin(String kakao_id) throws Exception;
	
	// 맴버추가
	public void addMember(MemberVO memberVO) throws Exception;
	// 중복 확인
	public String overlapped(String id) throws Exception;
	// 카카오 아이디로 회원가입 여부
	public String kakaoOverlap(String id) throws Exception;
}
