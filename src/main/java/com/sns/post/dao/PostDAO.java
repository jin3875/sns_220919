package com.sns.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {
	
	public List<Map<String, Object>> selectPostListTEST();
	
	// 글 추가
	public int insertPost(
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	// 글 목록
	public List<Post> selectPostList();
	
	// 글 삭제
	public int deletePostByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	// userId의 postId 글
	public Post selectPostByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);

}
