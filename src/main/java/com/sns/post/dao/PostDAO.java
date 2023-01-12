package com.sns.post.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {
	
	public List<Map<String, Object>> selectPostListTEST();

}
