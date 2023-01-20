package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	public boolean isLiked(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public int insertLike(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public int deleteLike(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public int countLike(int postId);

}
