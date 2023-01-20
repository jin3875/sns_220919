package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public boolean isLiked(int userId, int postId) {
		return likeDAO.isLiked(userId, postId);
	}
	
	public int addLike(int userId, int postId) {
		return likeDAO.insertLike(userId, postId);
	}
	
	public int removeLike(int userId, int postId) {
		return likeDAO.deleteLike(userId, postId);
	}

}
