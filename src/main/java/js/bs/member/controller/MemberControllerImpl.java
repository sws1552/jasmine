package js.bs.member.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import js.bs.common.base.BaseController;
import js.bs.common.kakao.KakaoAPI;
import js.bs.member.service.MemberService;
import js.bs.member.vo.MemberVO;

@Controller("memberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl extends BaseController implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private KakaoAPI kakao;

	// 로그인
	@Override
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String kakao_id = (String)session.getAttribute("kakao_id");
		
		// 카카오 아이디가 세션에 저장되어있으면 카카오 로그인 아니면  일반 로그인
		if(kakao_id != null && loginMap == null) {
			memberVO = memberService.kakaologin(kakao_id);
		}else {
			memberVO = memberService.login(loginMap);	
		}
		
		if (memberVO != null && memberVO.getMember_id() != null) {
		
			session = request.getSession();
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo", memberVO); // 서버의 세션메모리에 조회한 회원 정보와 , 회원이 로그인해있다라는 정보를 세션으로 저장함.

			String action = (String) session.getAttribute("action");
			if (action != null && action.equals("/order/orderEachGoods.do")) { // 상품 주문 과정에서 로그인했다면 로그인 후 다시 주문화면으로 진행하고
				mav.setViewName("forward:" + action); // 그 외에는 메인 페이지를 표시.
			} else {
				mav.setViewName("redirect:/main/main.do");
			}
		} else {
			String message = "아이디나  비밀번호가 틀립니다. 다시 로그인해주세요";
			mav.addObject("message", message);
			mav.setViewName("/member/loginForm");
		}
		return mav;
	}

	// 로그아웃
	@Override
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session.setAttribute("isLogOn", false);
		session.removeAttribute("memberInfo"); // 세션에 로그인 되어있는 회원정보(value는vo로 잡혀있음.) 이거 세션관리로 지워서 로그인/로그아웃 관리.
		session.removeAttribute("kakao_id");
		session.removeAttribute("access_Token");
		session.invalidate();
		mav.setViewName("redirect:/main/main.do"); // 로그아웃 누르면 메인페이지로 돌아가도록 설정.
		return mav;
	}

	// 맴버 추가
	@Override
	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String email2 = _memberVO.getEmail2();
		System.out.println(email2);
		if(email2.contains(",")) {
			int ei = email2.indexOf(",");
			String testemail1 = email2.substring(0,ei);
			String testemail2 = email2.substring(ei);
			if(testemail1 != null || !(testemail1.trim().equals("")) ) {
				_memberVO.setEmail2(testemail1);
			}else if(testemail2 != null) {
				_memberVO.setEmail2(testemail2);
			}
			
		}
		
		String age_range = _memberVO.getAge_range();
		
		if(age_range.trim().equals("") || age_range == null) {
		String birth_y = _memberVO.getMember_birth_y();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int age = (year-Integer.parseInt(birth_y)+1);

		age = age / 10;
			if(age == 0) {
				_memberVO.setAge_range("0~9");
			}else if(age == 1) {
				_memberVO.setAge_range("10~19");
			}else if(age == 2) {
				_memberVO.setAge_range("20~29");
			}else if(age == 3) {
				_memberVO.setAge_range("30~39");
			}else if(age == 4) {
				_memberVO.setAge_range("40~49");
			}else if(age == 5) {
				_memberVO.setAge_range("50~59");
			}else if(age == 6) {
				_memberVO.setAge_range("60~69");
			}else if(age == 7) {
				_memberVO.setAge_range("70~79");
			}else if(age == 8) {
				_memberVO.setAge_range("80~89");
			}else if(age == 9) {
				_memberVO.setAge_range("90~99");
			}
		
		}
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			memberService.addMember(_memberVO);
			message = "<script>";
			message += " alert('회원 가입을 마쳤습니다.로그인창으로 이동합니다.');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/memberForm.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	// 중첩
	@Override
	@RequestMapping(value = "/overlapped.do", method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseEntity resEntity = null;
		String result = memberService.overlapped(id); // id 중복 검사.
		resEntity = new ResponseEntity(result, HttpStatus.OK);
		System.out.println("중복체크시 result에 담기는 값 확인 :: " + result);
		return resEntity;
	}

	@Override
	@RequestMapping(value = "/memberForm.do")
	public ModelAndView memberForm(@RequestParam(value = "code", required = false) String code,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		if (code != null) {
			String access_Token = kakao.getAccessToken(code);
			HttpSession session = request.getSession();
			
			//session.setAttribute("access_Token", access_Token);
			
			HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
			String kakao_id = (String)userInfo.get("kakao_id");
			System.out.println("kakao_id >>> " + kakao_id);
			if(kakao_id != null) {
				String kakaoOverlap = memberService.kakaoOverlap(kakao_id);
				
				// 디비에 값 비교해서 id가 kakaoid랑 같은게 있으면 바로 로그인됨 없으면 회원가입 폼
				if(kakaoOverlap.equals("true")) {
					session.setAttribute("kakao_id", kakao_id);
					// 중복 된 경우 (카카오 로그인으로 회원가입을 했었던 경우)
					System.out.println("회원가입 되어있음");
					//mav.setViewName("/member/kakaoLogin");
					mav = loginflag(request, response);
					//mav.setViewName("redirect:/member/login.do");
					// 회원가입이 되어있을때 member/login.do 로 이동해서 
					// kakao_id에 맞는 member_id를 가져오면
				}else {
					System.out.println("회원가입 안되어있음");
					session.setAttribute("userInfo", userInfo);
					mav.addObject("userInfo", userInfo);
				}
				
			}
			//System.out.println("login Controller : " + userInfo);
			/* mav.addObject("userEmail", userInfo.get("email")); 
			mav.addObject("nickName", userInfo.get("nickname"));*/
			//System.out.println("userInfo.get(\"email\") >> " + userInfo.get("email"));
			//System.out.println("userInfo.get(\"nickname\") >> " + userInfo.get("nickname"));
		}
		return mav ;
	}
	
	//Test Method
	private ModelAndView loginflag(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String kakao_id = (String)session.getAttribute("kakao_id");
		System.out.println("loginFalg 호출 :: " + kakao_id);
		
		// 카카오 아이디가 세션에 저장되어있으면 카카오 로그인 아니면  일반 로그인
		if(kakao_id != null) {
			System.out.println("kakao_id 있음 :: ");
			memberVO = memberService.kakaologin(kakao_id);
		}else {
			System.out.println("Test");
			//memberVO = memberService.login(loginMap);	
		}
		
		if (memberVO != null && memberVO.getMember_id() != null) {
		
			session = request.getSession();
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo", memberVO); // 서버의 세션메모리에 조회한 회원 정보와 , 회원이 로그인해있다라는 정보를 세션으로 저장함.

			String action = (String) session.getAttribute("action");
			if (action != null && action.equals("/order/orderEachGoods.do")) { // 상품 주문 과정에서 로그인했다면 로그인 후 다시 주문화면으로 진행하고
				mav.setViewName("forward:" + action); // 그 외에는 메인 페이지를 표시.
			} else {
				System.out.println("redirect:/main/main.do :: ");
				mav.setViewName("redirect:/main/main.do");
			}
		} else {
			String message = "아이디나  비밀번호가 틀립니다. 다시 로그인해주세요";
			mav.addObject("message", message);
			mav.setViewName("/member/loginForm");
		}
		return mav;
	}


}
