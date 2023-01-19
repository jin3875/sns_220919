package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	public int addComment(int postId, int userId, String content) {
		return commentDAO.insertComment(postId, userId, content);
	}
	
	public List<Comment> getCommentList() {
		return commentDAO.selectCommentList();
	}

}
