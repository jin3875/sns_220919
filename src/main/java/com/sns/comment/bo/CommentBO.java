package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	
	// 댓글 추가
	public int addComment(int postId, int userId, String content) {
		return commentDAO.insertComment(postId, userId, content);
	}
	
	// postId의 댓글 목록
	private List<Comment> getCommentListByPostId(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	// postId의 CommentView 목록
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		List<CommentView> commentViewList = new ArrayList<>();
		
		// postId의 댓글 목록
		List<Comment> commentList = getCommentListByPostId(postId);
		
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 1개
			commentView.setComment(comment);
			
			// 댓글쓴이
			User user = userBO.getUserById(comment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		
		return commentViewList;
	}
	
	// postId의 댓글 삭제
	public void deleteCommentListByPostId(int postId) {
		commentDAO.deleteCommentListByPostId(postId);
	}
	
	// 본인 댓글 삭제
	public int deleteCommentByCommentIdUserId(int commentId, int userId) {
		return commentDAO.deleteCommentByCommentIdUserId(commentId, userId);
	}

}
