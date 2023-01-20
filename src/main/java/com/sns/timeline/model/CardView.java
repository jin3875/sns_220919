package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

// View용 객체
public class CardView {
	
	// 글 1개
	private Post post;
	
	// 글쓴이 정보
	private User user;
	
	// 댓글 N개
	private List<CommentView> commentList;
	
	// 로그인된 사람이 좋아요를 눌렀는지 (boolean)
	private boolean filledLike;
	
	// 좋아요 개수
	

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}

	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}

}
