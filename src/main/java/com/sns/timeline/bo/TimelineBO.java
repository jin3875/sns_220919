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
	
	// 로그인이 되지 않은 사람도 카드 목록이 보여야 한다.
	public List<CardView> generateCardList() {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록 가져오기 (post)
		List<Post> postList = postBO.getPostList();
		
		// postList 반복문 -> CardView -> cardViewList에 넣는다.
		for (Post post : postList) {
			CardView card = new CardView();
			
			// 글
			card.setPost(post);
			
			// 글쓴이
			User user = userBO.getUserById(post.getUserId());
			card.setUser(user);
			
			// 글 하나에 해당하는 댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			card.setCommentList(commentList);
			
			// 좋아요
			card.setFilledLike(likeBO.isLiked(post.getUserId(), post.getId()));
			
			// 좋아요 개수
			card.setCountLike(likeBO.countLike(post.getId()));
			
			cardViewList.add(card);
		}
		
		return cardViewList;
	}

}
