package com.model2.mvc.service.like;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Like;
import com.model2.mvc.service.domain.Product;

public interface LikeDao {
	
	public void insertLike(Like like) throws Exception ;
	
	public int countFindLike(String likeUserId, int likeProdNo) throws Exception ;
	
	public  List<Like> getLikeList(Search search) throws Exception;
	
	public  List<Like> getWishLikeList(Search search, String userId) throws Exception;
	
	public int countPushLike(int likeProdNo) throws Exception;
	
	public void deleteLikeUser(Like like) throws Exception;

	public int getTotalCount(Search search) throws Exception ;
	
	public int getWishTotalCount(Search search, String buyerId) throws Exception ;
}
