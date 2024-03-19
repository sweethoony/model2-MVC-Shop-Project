package com.model2.mvc.service.domain;

public class Like {

	private User likeUserId;  //���ƿ並 ���� user_id
	private int likeNo;       //���ƿ� ������ ��ȣ
	private Product likeProdNo; //���ƿ䰡 ���� ��ǰ
	private String pushLikeNo;  //���ƿ� ������ 1, �ƴϸ� 0(���)
	private int likeCount;  //��ǰ �� ���� ���ƿ� �� 
		
	public Like() {
		// TODO Auto-generated constructor stub
	}



	
	public User getLikeUserId() {
		return likeUserId;
	}


	public int getLikeNo() {
		return likeNo;
	}


	public Product getLikeProdNo() {
		return likeProdNo;
	}


	public String getPushLikeNo() {
		return pushLikeNo;
	}


	public void setLikeUserId(User likeUserId) {
		this.likeUserId = likeUserId;
	}


	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}


	public void setLikeProdNo(Product likeProdNo) {
		this.likeProdNo = likeProdNo;
	}


	public void setPushLikeNo(String pushLikeNo) {
		this.pushLikeNo = pushLikeNo;
	}


	public int getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	@Override
	public String toString() {
		return "Like [likeUserId=" + likeUserId + ", likeNo=" + likeNo + ", likeProdNo=" + likeProdNo + ", pushLikeNo="
				+ pushLikeNo + "]";
	}
	

}
