package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	// 좋아요 추가 <-> 삭제
	public void likeToggle(int userId, int postId) {
		// postId의 userId 좋아요 개수
		if (likeDAO.selectLikeCountByPostIdOrUserId(userId, postId) > 0) {
			// postId의 userId 좋아요 삭제
			likeDAO.deleteLikeByPostIdOrUserId(userId, postId);
		} else {
			// postId의 userId 좋아요 추가
			likeDAO.insertLike(userId, postId);
		}
	}
	
	// postId의 userId 좋아요 유무
	public boolean existLike(Integer userId, int postId) {
		// 비로그인
		if (userId == null) {
			return false;
		}
		
		// 로그인 - postId의 userId 좋아요 개수
		return likeDAO.selectLikeCountByPostIdOrUserId(userId, postId) > 0 ? true : false;
	}
	
	// postId의 좋아요 개수
	public int getLikeCountByPostId(int postId) {
		return likeDAO.selectLikeCountByPostIdOrUserId(null, postId);
	}
	
	// postId의 좋아요 삭제
	public void deleteLikeByPostId(int postId) {
		likeDAO.deleteLikeByPostIdOrUserId(null, postId);
	}

}
