package js.bs.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import js.bs.member.vo.MemberVO;

public interface MemberDAO {
	// 로그인
	public MemberVO login(Map loginMap) throws DataAccessException;
	
	// 카카오 로그인
	public MemberVO kakaologin(String kakao_id) throws DataAccessException;
	
	//멤버추가
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	//아이디 중복체크
	public String selectOverlappedID(String id) throws DataAccessException;
	
	//카카오 아이디 중복체크
	public String kakaoOverlap(String id) throws DataAccessException;
}
