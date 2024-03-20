package com.model2.mvc.service.domain;

public class Like {

	private User likeUserId;  //좋아요를 누른 user_id
	private int likeNo;       //좋아요 시퀀스 번호
	private Product likeProdNo; //좋아요가 눌린 상품
	private String pushLikeNo;  //좋아요 눌릴때 1, 아니면 0(취소)
	private int likeCount;  //상품 당 받은 좋아요 수 
		
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
