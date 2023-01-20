package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	// 1) /like?postId=13 -> @RequestParam
	// 2) /like/13 -> @PathVariable
	@GetMapping("/like/{postId}")
	public Map<String, Object> like(@PathVariable int postId, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		int userId = (int)session.getAttribute("userId");
		
		if (likeBO.isLiked(userId, postId)) {
			if (likeBO.removeLike(userId, postId) > 0) {
				result.put("code", 1);
				result.put("result", "성공");
			} else {
				result.put("code", 500);
				result.put("errorMessage", "좋아요 취소에 실패했습니다");
			}
		} else {
			if (likeBO.addLike(userId, postId) > 0) {
				result.put("code", 1);
				result.put("result", "성공");
			} else {
				result.put("code", 500);
				result.put("errorMessage", "좋아요 추가에 실패했습니다");
			}
		}
		
		return result;
	}

}
