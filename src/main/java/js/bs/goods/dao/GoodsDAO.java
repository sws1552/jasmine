package js.bs.goods.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import js.bs.goods.vo.GoodsVO;
import js.bs.goods.vo.ImageFileVO;

public interface GoodsDAO {
	// ��ǰ ����Ʈ �˻�
	public List<GoodsVO> selectGoodsList(String goodsStatus) throws DataAccessException;
	// Ű���� �˻� ��ȸ
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
	// ��ǰ ������ �˻�
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException;
	// ��ǰ ������ �̹��� ��ȸ
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException;
	// ���ڰ˻��� ���� ��ǰ ��ȸ
	public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException;
	// ī�װ��� ���� ��ǰ ��ȸ
	public List selectGoodsByCategory(String keyWord) throws DataAccessException;

}
