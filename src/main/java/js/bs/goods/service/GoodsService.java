package js.bs.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import js.bs.goods.vo.GoodsVO;

public interface GoodsService {
	//상품 리스트
	public Map<String,List<GoodsVO>> listGoods() throws Exception;
	//상품 디테일
	public Map goodsDetail(String _goods_id) throws Exception;
	//키워드 검색
	public List<String> keywordSearch(String keyword) throws Exception;
	//검색단어에 대한 상품검색
	public List<GoodsVO> searchGoods(String searchWord) throws Exception;
	//카테고리 검색
	public List<GoodsVO> searchCategoryGoods(String keyWord) throws Exception ;

}
