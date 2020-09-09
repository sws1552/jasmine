package js.bs.admin.datavisualization.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import js.bs.admin.datavisualization.dao.AdmindataVisDAO;
import js.bs.admin.goods.dao.AdminGoodsDAO;
import js.bs.goods.vo.GoodsVO;
import js.bs.goods.vo.ImageFileVO;
import js.bs.order.vo.OrderVO;


@Service("admindataVisService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdmindataVisServiceImpl implements AdmindataVisService {
	@Autowired
	private AdmindataVisDAO admindataVisDAO;

	@Override
	public Map selectAgeList() throws Exception {
		// TODO Auto-generated method stub
		
		Map dataMap = admindataVisDAO.selectAgeList();
		
		return dataMap;
	}

//	@Override
//	public List goodsImageFile(int goods_id) throws Exception{
//		List imageList =adminGoodsDAO.selectGoodsImageFileList(goods_id);
//		return imageList;
//	}
	
	

	
}
