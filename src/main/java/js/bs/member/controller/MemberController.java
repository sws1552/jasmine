package js.bs.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import js.bs.member.vo.MemberVO;

public interface MemberController {
	//�α���
	public ModelAndView login(@RequestParam Map<String, String> loginMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	//�α׾ƿ�
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//�ɹ��߰�
	public ResponseEntity  addMember(@ModelAttribute("member") MemberVO member,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	//��ø
	public ResponseEntity   overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView memberForm(@RequestParam(value = "access_Token",required = false) String Token,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
