package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	// postId의 (userId) 좋아요 개수
	public int selectLikeCountByPostIdOrUserId(
			@Param("userId") Integer userId, 
			@Param("postId") int postId);
	
	// postId의 userId 좋아요 추가
	public void insertLike(
			@Param("userId") int userId, 
			@Param("postId") int postId);
	
	// postId의 (userId) 좋아요 삭제
	public void deleteLikeByPostIdOrUserId(
			@Param("userId") Integer userId, 
			@Param("postId") int postId);

}
