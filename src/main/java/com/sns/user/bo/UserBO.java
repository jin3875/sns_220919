package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	// loginId 존재 유무
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
	
	// 유저 추가
	public void addUser(String loginId, String password, String name, String email) {
		userDAO.insertUser(loginId, password, name, email);
	}
	
	// loginId와 password로 유저 select
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}
	
	// id로 유저 select
	public User getUserById(int id) {
		return userDAO.selectUserById(id);
	}

}
