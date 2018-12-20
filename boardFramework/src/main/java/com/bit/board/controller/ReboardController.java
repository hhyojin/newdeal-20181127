package com.bit.board.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.board.model.ReboardDto;
import com.bit.board.service.ReboardService;
import com.bit.member.model.MemberDto;

@Controller
@RequestMapping("/reboard")
public class ReboardController {
	
	@Autowired
	private ReboardService reboardService;
	
	@RequestMapping(value="write.bit", method=RequestMethod.GET)
	public String write (@RequestParam Map<String, String> param) {
		return "reboard/write";
	}
	
	@RequestMapping(value="write.bit", method=RequestMethod.POST)
	public String write (ReboardDto reboardDto, HttpSession session,
						@RequestParam Map<String, String> param) {
		
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		
		if(memberDto != null) {
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmail());
		
			int seq = reboardService.writeArticle(reboardDto);
		}
		
		
		return "redirect:/index.jsp";
	}

}
