package com.model2.mvc.service.like;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Like;

public interface LikeService {
	

		public Like addLike (Like like) throws Exception; //좋아요 누른 유저아이디로 추가
		
		public int getLike (String likeUserId, int likeProdNo) throws Exception; //좋아요 누른 상품 찾기
		
		public Map<String, Object> getLikeList(Search search) throws Exception;  //좋아요 누른 것을 모은 리스트
		
		public Map<String, Object> getWishLikeList(Search search, String userId) throws Exception; //각 사용자가 좋아요 누른 것을 모은 리스트
		
		public int countLikeProd(int likeProdNo) throws Exception;  //좋아요 눌린 상품 번호의 Push_Like 컬럼의 1을 count해서 알려줌
		
		public void deleteLike(Like like)throws Exception; //좋아요 취소시 삭제
		
		
}
