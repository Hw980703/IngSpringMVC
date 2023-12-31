package com.ing.spring.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ing.spring.board.domain.Reply;
import com.ing.spring.board.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService rService;
	
	@RequestMapping(value="/add.kh",method=RequestMethod.POST)
	public ModelAndView insertReply(ModelAndView mv
			,@ModelAttribute Reply reply , HttpSession session) {
		String url ="";
		try {
			String replyWriter = (String)session.getAttribute("memberId");
			reply.setReplyWriter(replyWriter);
			int result = rService.insertReply(reply);
			url = "/board/detail.kh?boardNo="+reply.getRefBoardNo();
			if ( result > 0 ) {
				mv.setViewName("redirect:"+url);
				
				
			}else {
				mv.addObject("msg","댓글이 작성 되지 않았습니다.");
				mv.addObject("error","댓글 등록 실패");
				mv.addObject("url",url);
				mv.setViewName("common/errorPage");
				
			}
			
		} catch (Exception e) {
			mv.addObject("msg","댓글이 작성 되지 않았습니다.");
			mv.addObject("error",e.getMessage());
			mv.addObject("url",url);
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/update.kh", method=RequestMethod.POST)
	public ModelAndView updateReply(ModelAndView mv
			,@ModelAttribute Reply reply , HttpSession session ) {
		String url ="";
		try {
			
			
			String replyWriter = (String)session.getAttribute("memberId");
			if (!replyWriter.equals("")){
				reply.setReplyWriter(replyWriter);
				url = "/board/detail.kh?boardNo="+reply.getRefBoardNo();
				int result = rService.updateReply(reply);
				mv.setViewName("redirect:"+url);

			}else {
				mv.addObject("msg","로그인 안됨.");
				mv.addObject("error","로그인 안됐음 ㅇㅇ");
				mv.addObject("url","/index.jsp");
				mv.setViewName("common/errorPage");
				
			}
			
		} catch (Exception e) {
			mv.addObject("msg","댓글이 작성 되지 않았습니다.");
			mv.addObject("error",e.getMessage());
			mv.addObject("url",url);
			mv.setViewName("common/errorPage");
		}
		
		return mv;
		
		
	}
}
