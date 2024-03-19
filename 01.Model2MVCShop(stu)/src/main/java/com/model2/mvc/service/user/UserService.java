package com.model2.mvc.service.user;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.vo.UserVO;


public interface UserService {
	
	public void addUser(UserVO userVO) throws Exception;
	
	public UserVO loginUser(UserVO userVO) throws Exception;
	
	public UserVO getUser(String userId) throws Exception;
	
	public HashMap<String, Object> getUserList(SearchVO searchVO) throws Exception;  //map으로 바꿔 인터페이스 기반으로하기
	
	public void updateUser(UserVO userVO) throws Exception;
	
	public boolean checkDuplication(String userId) throws Exception;
	
}