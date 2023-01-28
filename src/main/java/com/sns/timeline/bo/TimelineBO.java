package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// 카드 목록
	public List<CardView> generateCardList(Integer userId) { // 로그아웃 상태도 가능
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록
		List<Post> postList = postBO.getPostList();
		
		for (Post post : postList) {
			CardView card = new CardView();
			
			// 글
			card.setPost(post);
			
			// 글쓴이
			User user = userBO.getUserById(post.getUserId());
			card.setUser(user);
			
			// 댓글
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			card.setCommentList(commentList);
			
			// 좋아요 유무
			card.setFilledLike(likeBO.existLike(userId, post.getId()));
			
			// 좋아요 개수
			card.setLikeCount(likeBO.getLikeCountByPostId(post.getId()));
			
			cardViewList.add(card);
		}
		
		return cardViewList;
	}

}
