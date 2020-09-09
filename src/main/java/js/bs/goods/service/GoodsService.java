package js.bs.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import js.bs.goods.vo.GoodsVO;

public interface GoodsService {
	//��ǰ ����Ʈ
	public Map<String,List<GoodsVO>> listGoods() throws Exception;
	//��ǰ ������
	public Map goodsDetail(String _goods_id) throws Exception;
	//Ű���� �˻�
	public List<String> keywordSearch(String keyword) throws Exception;
	//�˻��ܾ ���� ��ǰ�˻�
	public List<GoodsVO> searchGoods(String searchWord) throws Exception;
	//ī�װ� �˻�
	public List<GoodsVO> searchCategoryGoods(String keyWord) throws Exception ;

}
