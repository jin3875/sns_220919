package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private TimelineBO timelineBO;
	
	/**
	 * 타임라인 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/timeline_view")
	public String timelineView(Model model, HttpSession session) {
		// 카드 목록
		List<CardView> cardList = timelineBO.generateCardList((Integer)session.getAttribute("userId"));
		model.addAttribute("cardList", cardList);
		
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}

}
