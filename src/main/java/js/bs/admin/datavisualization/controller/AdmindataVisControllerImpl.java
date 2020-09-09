package js.bs.admin.datavisualization.controller;

import java.io.File;


import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import js.bs.admin.datavisualization.service.AdmindataVisService;
import js.bs.admin.goods.service.AdminGoodsService;
import js.bs.common.base.BaseController;
import js.bs.goods.vo.GoodsVO;
import js.bs.goods.vo.ImageFileVO;
import js.bs.member.vo.MemberVO;

@Controller("AdmindataVisController")
@RequestMapping(value="/admin/datavis")
public class AdmindataVisControllerImpl extends BaseController  implements AdmindataVisController{
	
	@Autowired
	private AdmindataVisService admindataVisService ;
	@Autowired
	private MemberVO memberVO;
	
	
	@Override
	@RequestMapping(value = "/dataVisualization.do" , method = {RequestMethod.POST , RequestMethod.GET })
	public ModelAndView adminDataVisMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName); 
		
		Map dataMap = (Map) admindataVisService.selectAgeList();
		mav.addObject("dataMap",dataMap);
		//System.out.println("Check viewNaem :: " + viewName);
		return mav;
	}
	
	
	//d3.js test 구글차트에서는 쓰이지 않음.
	@RequestMapping(value = "/sales.csv" , method = {RequestMethod.POST , RequestMethod.GET })
	public ModelAndView adminDataVisMaincsv(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// ���ͼ��Ϳ��� ���Ͻ�Ų viewNaem ����
		String viewName = "/admin/datavis/dataVisualization";
		System.out.println("Check viewNaem :: " + viewName);
		ModelAndView mav = new ModelAndView(viewName); // �����ڷ� ����
		mav.addObject("salesperson","salesperson");
//		salesperson,sales
//		Bob,33
//		Robin,12
//		Anne,41
//		Mark,16
//		Joe,59
//		Eve,38
//		Karen,21
//		Kirsty,25
//		Chris,30
//		Lisa,47
//		Tom,5
//		Stacy,20
//		Charles,13
//		Mary,29
		
	    return mav;
	
	}
	
	 
	
	
	

}
