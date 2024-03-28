package com.model2.mvc.service.like;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Like;

public interface LikeService {
	

		public Like addLike (Like like) throws Exception; //���ƿ� ���� �������̵�� �߰�
		
		public int getLike (String likeUserId, int likeProdNo) throws Exception; //���ƿ� ���� ��ǰ ã��
		
		public Map<String, Object> getLikeList(Search search) throws Exception;  //���ƿ� ���� ���� ���� ����Ʈ
		
		public Map<String, Object> getWishLikeList(Search search, String userId) throws Exception; //�� ����ڰ� ���ƿ� ���� ���� ���� ����Ʈ
		
		public int countLikeProd(int likeProdNo) throws Exception;  //���ƿ� ���� ��ǰ ��ȣ�� Push_Like �÷��� 1�� count�ؼ� �˷���
		
		public void deleteLike(Like like)throws Exception; //���ƿ� ��ҽ� ����
		
		
}
