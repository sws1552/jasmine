package js.bs.admin.datavisualization.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;

import js.bs.goods.vo.GoodsVO;
import js.bs.goods.vo.ImageFileVO;
import js.bs.order.vo.OrderVO;

public interface AdmindataVisService {
	
	
	public Map selectAgeList() throws Exception;
	
	
//	public int  addNewGoods(Map newGoodsMap) throws Exception;
//	public List<GoodsVO> listNewGoods(Map condMap) throws Exception;
//	public Map goodsDetail(int goods_id) throws Exception;
//	public List goodsImageFile(int goods_id) throws Exception;
//	public void modifyGoodsInfo(Map goodsMap) throws Exception;
//	public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception;
//	public List<OrderVO> listOrderGoods(Map condMap) throws Exception;
//	public void modifyOrderGoods(Map orderMap) throws Exception;
//	public void removeGoodsImage(int image_id) throws Exception;
//	public void addNewGoodsImage(List imageFileList) throws Exception;
	
}