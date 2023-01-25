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
	
	/**
	 * 좋아요 API
	 * @param postId
	 * @param session
	 * @return
	 */
	// 1) /like?postId=13 -> @RequestParam
	// 2) /like/13 -> @PathVariable
	@GetMapping("/like/{postId}")
	public Map<String, Object> like(@PathVariable int postId, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code", 500);
			result.put("errorMessage", "로그인을 해주세요");
			return result;
		}
		
		// 좋아요 추가 <-> 삭제
		likeBO.likeToggle(userId, postId);
		
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}

}
