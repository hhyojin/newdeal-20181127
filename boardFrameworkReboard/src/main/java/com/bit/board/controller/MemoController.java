package com.bit.board.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.board.model.MemoDto;
import com.bit.board.service.MemoService;
import com.bit.member.model.MemberDto;

// @RestController //RestController로 하면 headers 안 써도 됨
@Controller 
public class MemoController {

	@Autowired
	private MemoService memoService;
	
	@RequestMapping(value="memo/{seq}", method=RequestMethod.GET, headers= {"Content-Type=application/json;charset=UTF-8"})
	public @ResponseBody String listMemo(@PathVariable(value="seq") int seq) {
		String memolist = memoService.listMemo(seq);
		return memolist;
	}
	
	@RequestMapping(value="memo", method=RequestMethod.POST, headers= {"Content-Type=application/json;charset=UTF-8"})
	public @ResponseBody String write(@RequestBody MemoDto memoDto, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			memoDto.setId(memberDto.getId());
			memoDto.setName(memberDto.getName());
			int cnt = memoService.writeMemo(memoDto);
		}
		String memolist = memoService.listMemo(memoDto.getSeq());
		return memolist;
	}
	
	@RequestMapping(value="memo", method=RequestMethod.PUT, headers= {"Content-Type=application/json;charset=UTF-8"})
	public @ResponseBody String modify(@RequestBody MemoDto memoDto, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");	
		if(memberDto != null) {
			int cnt = memoService.modifyMemo(memoDto);
		}
		String memolist = memoService.listMemo(memoDto.getSeq());
		return memolist;
	}
	
	/*@RequestMapping(value="memo/{seq}/{mseq}", method=RequestMethod.DELETE, headers= {"Content-Type=application/json;charset=UTF-8"})
	public @ResponseBody String delete(@PathVariable(value="mseq") int mseq, @PathVariable(value="seq") int seq) {*/
	@RequestMapping(value="memo/{mseq}", method=RequestMethod.DELETE, headers= {"Content-Type=application/json;charset=UTF-8"})
	public @ResponseBody String delete(@PathVariable(value="mseq") int mseq) {
		memoService.deleteMemo(mseq);
		/*String memolist=memoService.listMemo(seq);*/
		return JSONObject.valueToString("");
		/*return memolist;*/
	}
	
}
