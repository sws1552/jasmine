package js.bs.goods.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import js.bs.goods.vo.GoodsVO;
import js.bs.goods.vo.ImageFileVO;

public interface GoodsDAO {
	// 상품 리스트 검색
	public List<GoodsVO> selectGoodsList(String goodsStatus) throws DataAccessException;
	// 키워드 검색 조회
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
	// 상품 디테일 검색
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException;
	// 상품 디테일 이미지 조회
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException;
	// 문자검색에 의한 상품 조회
	public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException;
	// 카테고리에 의한 상품 조회
	public List selectGoodsByCategory(String keyWord) throws DataAccessException;

}
