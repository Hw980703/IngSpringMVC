package com.ing.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ing.spring.member.domain.Member;
import com.ing.spring.member.service.MemberSerivce;

@Controller
public class MemberController {
	
	@Autowired
	private MemberSerivce  service;

		@RequestMapping(value="/member/register.kh" , method=RequestMethod.GET)
		public String showRegisterForm() {
			return "member/register";
		}
		@RequestMapping(value="/member/register.kh" , method=RequestMethod.POST)
		public String registerMember(
//				@RequestParam("memberId")String memberId,
				@ModelAttribute Member member,
				Model model
				) {
			try {
				//INSERT INTO MEMBER_TBL VALUE
				
				int result = service.insertMember(member);
				
				if (result > 0) {
					//성공하면 로그인 페이지
					//hone.jsp 가 로그인 페이지가 되면 됨ㅋㅋ
					return "redirect:/index.jsp";
					
					
				}else {
					//실패하면 에러페이지로 이동
					model.addAttribute("error","실패페이지");
					model.addAttribute("msg","회원가입에 실패하였습니다.");
					model.addAttribute("url","/member/register.do");
					return "common/errorPage";
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("error",e.getMessage());
				model.addAttribute("msg","회원가입에 실패하였습니다.");
				model.addAttribute("url","/member/register.do");
				return "common/errorPage";
			
			}
			
		}
		
		@RequestMapping(value="/member/login.kh",method=RequestMethod.POST)
		public String memberLoginCheck(@ModelAttribute Member member,
				HttpSession session , Model model) {
				
			try {
			
			
			// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?
			int result = service.checkMemberLogin(member);
			if ( result > 0 ) {
				
				//성공 ㅋㅋ
				session.setAttribute("memberId", member.getMemberId());
				return "redirect:/index.jsp";
			}else {
				//실패 ㅋㅋ
				//실패하면 에러페이지로 이동
				model.addAttribute("error","로그인 실패");
				model.addAttribute("msg","로그인 실패.");
				model.addAttribute("url","/index.jsp");
				return "common/errorPage";
			}
				
			} catch (Exception e) {
				// TODO: handle exception
				//실패하면 에러페이지로 이동
				model.addAttribute("error","관리자 문의");
				model.addAttribute("msg",e.getMessage());
				model.addAttribute("url","/member/register.do");
				return "common/errorPage";
			}
		}
		
	
}
