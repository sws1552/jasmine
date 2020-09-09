package js.bs.admin.datavisualization.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import js.bs.goods.vo.GoodsVO;
import js.bs.goods.vo.ImageFileVO;
import js.bs.order.vo.OrderVO;

@Repository("admindataVisDAO")
public class AdmindataVisDAOImpl  implements AdmindataVisDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public Map selectAgeList() throws DataAccessException {
		String dataflag = null;
		String testflag = "3";
		//List dataList = new ArrayList();
		Map dataMap = new HashMap();
		dataflag=String.valueOf(sqlSession.selectList("mapper.admin.datavis.selectDataVisListtwenty"));
		//dataflag = String.valueOf(dataList.get(0));
		System.out.println("dataflag 20~29 :: " + dataflag);
		//System.out.println("String.length() test :: " + dataflag.length());
		dataMap.put("20대", dataflag);
		
		dataflag=String.valueOf(sqlSession.selectList("mapper.admin.datavis.selectDataVisListthirty"));
		System.out.println("dataflag 30~39 :: " + dataflag);
		dataMap.put("30대", dataflag);
		
		dataflag=String.valueOf(sqlSession.selectList("mapper.admin.datavis.selectDataVisListforty"));
		System.out.println("dataflag 40~49 :: " + dataflag);
		dataMap.put("40대", dataflag);
		
		dataflag=String.valueOf(sqlSession.selectList("mapper.admin.datavis.selectDataVisListfifty"));
		System.out.println("dataflag 50~59 :: " + dataflag);
		dataMap.put("50대", dataflag);
		
//		dataList2=(List)sqlSession.selectList("mapper.admin.datavis.selectDataVisList");
//		dataList=(List)sqlSession.selectList("mapper.admin.datavis.selectDataVisList");
//		dataList=(List)sqlSession.selectList("mapper.admin.datavis.selectDataVisList");
		//list안에 String 담겨져 리턴
		// dataVis.selectDataVisList
		return dataMap;
	}

}
