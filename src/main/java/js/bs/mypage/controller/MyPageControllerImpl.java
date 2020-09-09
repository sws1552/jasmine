package js.bs.mypage.controller;

import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import js.bs.common.base.BaseController;
import js.bs.delivery.service.DeliveryAPI;
import js.bs.member.vo.MemberVO;
import js.bs.mypage.service.MyPageService;
import js.bs.order.vo.OrderVO;

@Controller("myPageController")
@RequestMapping(value="/mypage")
public class MyPageControllerImpl extends BaseController  implements MyPageController{
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Autowired
	private DeliveryAPI deliveryAPI;
	
	// ���������� ����
	@Override
	@RequestMapping(value="/myPageMain.do" ,method = RequestMethod.GET)
	public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message,
			   HttpServletRequest request, HttpServletResponse response)  throws Exception {
		// (@RequestParam(required = false,value="message" :: �ֹ� ��� �� ��� �޽����� ����.
		HttpSession session=request.getSession();
		session=request.getSession();
		session.setAttribute("side_menu", "my_page"); //���������� ���̵� �޴��� �����Ѵ�.
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		
		String member_id=memberVO.getMember_id();
		List<OrderVO> myOrderList=myPageService.listMyOrderGoods(member_id);
		// ȸ�� ID�� �̿��� �ֹ� ��ǰ�� ��ȸ.
		
		mav.addObject("message", message);
		System.out.println("myPageMain ���� message Ȯ�� :: " + message);
		mav.addObject("myOrderList", myOrderList);

		return mav;
	}
	
	// �� �ֹ� ��
	@Override
	@RequestMapping(value="/myOrderDetail.do" ,method = RequestMethod.GET)
	public ModelAndView myOrderDetail(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		MemberVO orderer=(MemberVO)session.getAttribute("memberInfo");
		
		List<OrderVO> myOrderList=myPageService.findMyOrderInfo(order_id);
		mav.addObject("orderer", orderer);        // ���״�� �α��� �� ������ ȸ���� ȸ�� ����
		mav.addObject("myOrderList",myOrderList); // �ֹ��� ���̵�� �ֹ�����
		return mav;
	}
	
	// �� �ֹ� �̷� ����Ʈ
	@Override
	@RequestMapping(value="/listMyOrderHistory.do" ,method = RequestMethod.GET)
	public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap,
			                               HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();
		
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		System.out.println("dateMap.get(\"fixedSearchPeriod\") :: " + fixedSearchPeriod);
		String beginDate=null,endDate=null;
		
		String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
		beginDate=tempDate[0];
		endDate=tempDate[1];
		dateMap.put("beginDate", beginDate);
		dateMap.put("endDate", endDate);
		dateMap.put("member_id", member_id);
		List<OrderVO> myOrderHistList=myPageService.listMyOrderHistory(dateMap);
		
		String beginDate1[]=beginDate.split("-"); //�˻����ڸ� ��,��,�Ϸ� �и��ؼ� ȭ�鿡 �����մϴ�.
		String endDate1[]=endDate.split("-");
		mav.addObject("beginYear",beginDate1[0]);
		mav.addObject("beginMonth",beginDate1[1]);
		mav.addObject("beginDay",beginDate1[2]);
		mav.addObject("endYear",endDate1[0]);
		mav.addObject("endMonth",endDate1[1]);
		mav.addObject("endDay",endDate1[2]);
		mav.addObject("myOrderHistList", myOrderHistList);
		return mav;
	}	
	
	// �� �ֹ� ���
	@Override
	@RequestMapping(value="/cancelMyOrder.do" ,method = RequestMethod.POST)
	public ModelAndView cancelMyOrder(@RequestParam("order_id")  String order_id,
			                         HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		myPageService.cancelOrder(order_id);
		mav.addObject("message", "cancel_order");
		mav.setViewName("redirect:/mypage/myPageMain.do");
		return mav;
	}
	
	// �� ���� ������
	@Override
	@RequestMapping(value="/myDetailInfo.do" ,method = RequestMethod.GET)
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}	
	
	// �� ���� ����
	@Override
	@RequestMapping(value="/modifyMyInfo.do" ,method = RequestMethod.POST)
	public ResponseEntity modifyMyInfo(@RequestParam("attribute")  String attribute,
			                 @RequestParam("value")  String value,
			               HttpServletRequest request, HttpServletResponse response)  throws Exception {
		Map<String,String> memberMap=new HashMap<String,String>();
		String val[]=null;
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();
		if(attribute.equals("member_birth")){
			val=value.split(",");
			memberMap.put("member_birth_y",val[0]);
			memberMap.put("member_birth_m",val[1]);
			memberMap.put("member_birth_d",val[2]);
			memberMap.put("member_birth_gn",val[3]);
		}else if(attribute.equals("tel")){
			val=value.split(",");
			memberMap.put("tel1",val[0]);
			memberMap.put("tel2",val[1]);
			memberMap.put("tel3",val[2]);
		}else if(attribute.equals("hp")){
			val=value.split(",");
			memberMap.put("hp1",val[0]);
			memberMap.put("hp2",val[1]);
			memberMap.put("hp3",val[2]);
			memberMap.put("smssts_yn", val[3]);
		}else if(attribute.equals("email")){
			val=value.split(",");
			memberMap.put("email1",val[0]);
			memberMap.put("email2",val[1]);
			memberMap.put("emailsts_yn", val[2]);
		}else if(attribute.equals("address")){
			val=value.split(",");
			memberMap.put("zipcode",val[0]);
			memberMap.put("roadAddress",val[1]);
			memberMap.put("jibunAddress", val[2]);
			memberMap.put("namujiAddress", val[3]);
		}else {
			memberMap.put(attribute,value);	
		}
		
		memberMap.put("member_id", member_id);
		
		//������ ȸ�� ������ �ٽ� ���ǿ� �����Ѵ�.
		memberVO=(MemberVO)myPageService.modifyMyInfo(memberMap);
		session.removeAttribute("memberInfo");
		session.setAttribute("memberInfo", memberVO);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@Override
	@RequestMapping(value = "/selectDelivery.do" , method = RequestMethod.GET)
	public ModelAndView selectDelivery(@RequestParam(value = "t_key",required = false)String t_key,
										@RequestParam(value = "t_code",required = false)String t_code,
										@RequestParam(value = "t_invoice",required = false)String t_invoice,
										HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		System.out.println("test12333");
		if(t_key !=null) {
			System.out.println("test");
			List deliveryInfo = deliveryAPI.DeliveryInfo(t_key,t_code,t_invoice);
			mav.addObject("deliveryInfo", deliveryInfo);
		}
		
		return mav;
	}	
	
	
}
