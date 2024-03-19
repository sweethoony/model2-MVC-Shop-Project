package com.model2.mvc.service.like.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Like;
import com.model2.mvc.service.like.LikeDao;


//==> 회원관리 DAO CRUD 구현
@Repository("LikeDaoImpl")
public class LikeDaoImpl implements LikeDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public LikeDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void insertLike(Like like) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("LikeMapper.addLike", like);
	}

	@Override
	public int countFindLike(String likeUserId, int likeProdNo) throws Exception {
		// TODO Auto-generated method stub
		 Map<String, Object> map = new HashMap<>();
		 map.put("likeUserId", likeUserId);
		 map.put("likeProdNo", likeProdNo);
		    
		    return sqlSession.selectOne("LikeMapper.countFindLike", map);
	}

	@Override
	public List<Like> getLikeList(Search search) throws Exception {
		// TODO Auto-generated method stub
		Map<String , Object>  map = new HashMap<String, Object>();
		
		System.out.println("map :: " + map);
		
			map.put("search", search);
		
			System.out.println("map :: " + map);
			List<Like> list = sqlSession.selectList("LikeMapper.getLikeList", map); 
			
			System.out.println("list :: " + list);

		return list;
	}

	@Override
	public List<Like> getWishLikeList(Search search, String userId) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		System.out.println("map :: " + map);
		
			map.put("search", search);
			map.put("userId", userId);
		
			System.out.println("map :: " + map);
			List<Like> list = sqlSession.selectList("LikeMapper.getWishLikeList", map); 
			
			System.out.println("list :: " + list);

		return list;
	}

	@Override
	public int countPushLike(int likeProdNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("LikeMapper.countPushLike", likeProdNo);
	}

	@Override
	public void deleteLikeUser(Like like) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("LikeMapper.deleteLikeUser",like);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("LikeMapper.getTotalCount", search);
	}

	@Override
	public int getWishTotalCount(Search search, String buyerId) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		System.out.println("map :: " + map);
		
		map.put("search", search);
		map.put("userId", buyerId);
	
		System.out.println("map :: " + map);
		return sqlSession.selectOne("LikeMapper.getWishTotalCount", map);
	}

	
	
}