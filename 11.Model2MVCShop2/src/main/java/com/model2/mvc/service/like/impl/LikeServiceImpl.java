package com.model2.mvc.service.like.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Like;
import com.model2.mvc.service.like.LikeDao;
import com.model2.mvc.service.like.LikeService;


@Service("LikeServiceImpl")
public class LikeServiceImpl implements LikeService{
	
	@Autowired
	@Qualifier("LikeDaoImpl")
	private LikeDao likedao;

	public LikeServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}

	@Override
	public Like addLike(Like like) throws Exception { 
		
		likedao.insertLike(like);
		
		return like;
	}

	@Override
	public int getLike(String likeUserId, int likeProdNo) throws Exception {
		
		return likedao.countFindLike(likeUserId, likeProdNo);
	}

	@Override
	public Map<String, Object> getLikeList(Search search) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();

        List<Like> list = likedao.getLikeList(search);
        int totalCount = likedao.getTotalCount(search);
        
        System.out.println("likedao getLikeList list ::" + list);
        System.out.println("likedao getLikeList totalCount ::" + totalCount);
        map.put("list", list);
        map.put("totalCount", new Integer(totalCount));

        return map;
	}


	@Override
	public int countLikeProd(int likeProdNo) throws Exception {
		// TODO Auto-generated method stub
		return likedao.countPushLike(likeProdNo);
	}

	@Override
	public void deleteLike(Like like) throws Exception {
		// TODO Auto-generated method stub
		likedao.deleteLikeUser(like);
	}

	@Override
	public Map<String, Object> getWishLikeList(Search search, String userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();

        List<Like> list = likedao.getWishLikeList(search,userId);
        int totalCount = likedao.getWishTotalCount(search,  userId);
        
        System.out.println("likedao getWishLikeList list ::" + list);
        System.out.println("likedao getWishLikeList totalCount ::" + totalCount);
        map.put("list", list);
        map.put("totalCount", new Integer(totalCount));

        return map;
	}

}
