package com.ing.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
			Member mOne = service.checkMemberLogin(member);
			if ( mOne != null) {
				
				//성공 ㅋㅋ
				session.setAttribute("memberId", mOne.getMemberId());
				session.setAttribute("memberName", mOne.getMemberName());
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
		
		@RequestMapping(value="/member/logout.kh", method=RequestMethod.GET)
		public String memberLogout(HttpSession session ,Model model) {
			
			if ( session != null) {
				session.invalidate();
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("error","로그아웃 실패");
				model.addAttribute("msg","로그아웃을 완료하지 못했습니다.");
				model.addAttribute("url","/index.jsp");
				return "common/errorPage";
			}
			
		}

		@RequestMapping(value="/member/mypage.kh" , method=RequestMethod.GET)
		public String memberMypage(@ModelAttribute Member member,Model model,HttpSession session) {
				
			Member mOne = service.checkMEmberById(member);
			
			try {
				
				if(mOne != null) {
					model.addAttribute("member", mOne);
					return "member/mypage";
				}else {
					model.addAttribute("msg","마이페이지 조회 실패");
					model.addAttribute("error","회원 조회 실패");
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
	
		@RequestMapping(value="/member/updateView.kh",method=RequestMethod.GET)
		public String ShowModifyMember(@ModelAttribute Member member,Model model) {
			
			
			try {
				Member mOne = service.checkMEmberById(member);
				
				
				
				if( mOne != null) {
					
					model.addAttribute("member",mOne);
					return "member/modify";
				}else {
					model.addAttribute("msg","마이페이지 조회 실패");
					model.addAttribute("error","회원 조회 실패");
					model.addAttribute("url","/index.jsp");
					
					return "common/errorPage";
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("error","관리자 문의");
				model.addAttribute("msg",e.getMessage());
				model.addAttribute("url","/member/register.do");
				return "common/errorPage";
			}
		
}
		
		@RequestMapping(value="/member/update.kh" , method=RequestMethod.POST)
		public String modifyMember(@ModelAttribute Member member,Model model) {
			
			try {
				int result = service.modifyMember(member);
				
				if( result > 0 ) {
					
					//성공
					return "redirect:/index.jsp";
					
				}else {
					//실패
					model.addAttribute("msg","마이페이지 조회 실패");
					model.addAttribute("error","회원 조회 실패");
					model.addAttribute("url","/index.jsp");
					
					return "redirect:/index.jsp";
				}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("msg","마이페이지 조회 실패");
				model.addAttribute("error","회원 조회 실패");
				model.addAttribute("url","/index.jsp");
				
				return "redirect:/index.jsp";
			}
			
		}
		
		@RequestMapping(value="/member/delete.kh",method=RequestMethod.GET)
		public String deleteMember(@ModelAttribute Member member,Model model) {
			
			try {
				
				int result = service.deleteMeber(member);
				if ( result > 0 ) {
					Member mOne = service.checkMEmberById(member);
					
					//N으로 바꾸기 성공
					model.addAttribute("member",mOne);
					return "member/mypage";
				} else {
					model.addAttribute("msg","마이페이지 조회 실패");
					model.addAttribute("error","회원 조회 실패");
					model.addAttribute("url","/index.jsp");
					
					return "redirect:/index.jsp";
				}
				
				
			
				
			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("msg","마이페이지 조회 실패");
				model.addAttribute("error","회원 조회 실패");
				model.addAttribute("url","/index.jsp");
				
				return "redirect:/index.jsp";
			}
			
			
			
			
		}
}
