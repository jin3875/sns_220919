package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	// 댓글 추가
	public int insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content);
	
	// postId의 댓글 목록
	public List<Comment> selectCommentListByPostId(int postId);
	
	// postId의 댓글 삭제
	public void deleteCommentListByPostId(int postId);
	
	// 본인 댓글 삭제
	public int deleteCommentByCommentIdUserId(
			@Param("commentId") int commentId,
			@Param("userId") int userId);

}
