package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public void likeToggle(int userId, int postId) {
		if (likeDAO.selectLikeCountByPostIdOrUserId(userId, postId) > 0) {
			likeDAO.deleteLikeByPostIdUserId(userId, postId);
		} else {
			likeDAO.insertLike(userId, postId);
		}
	}
	
	public boolean existLike(Integer userId, int postId) {
		if (userId == null) { // 비로그인
			return false;
		}
		
		return likeDAO.selectLikeCountByPostIdOrUserId(userId, postId) > 0 ? true : false; // 로그인
	}
	
	public int getLikeCountByPostId(int postId) {
		return likeDAO.selectLikeCountByPostIdOrUserId(null, postId);
	}

}
