package com.sns.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserDAO {
	
	// loginId 존재 유무
	public boolean existLoginId(String loginId);
	
	// 유저 추가
	public void insertUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("email") String email);
	
	// loginId와 password로 유저 select
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
	// id로 유저 select
	public User selectUserById(int id);

}
