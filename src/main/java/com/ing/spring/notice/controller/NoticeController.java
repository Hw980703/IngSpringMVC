package com.ing.spring.notice.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ing.spring.notice.domain.Notice;
import com.ing.spring.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="/notice/insert.kh",method=RequestMethod.GET)
	public String showInsertForm() {
			return "notice/insert";
	}
	@RequestMapping(value="/notice/insert.kh",method=RequestMethod.POST)
	public String insertNotice(@ModelAttribute Notice notice
			,@RequestParam(value="uploadFile",required = false) MultipartFile uploadFile
			,HttpServletRequest request , Model model) {
		
		
		try {
			
			if(!uploadFile.getOriginalFilename().equals("")) {
				// **********************파일 이름*************************
				String fileName = uploadFile.getOriginalFilename();
				//파일 경로  ( 내가 저장한 후 그 경로를 DB에 저장하도록 준비하는 것)
				
				//파일 경로는 request 통해서 가져옴, HttpServletRequest 씀
				// resources 폴더의 경로를 알 수 있음 = /IngSpringMVC/src/main/webapp/resources
				String root = request.getSession().getServletContext()
						.getRealPath("resources");

			
				//폴더가 없을 경우 자동 생성 (내가 업로드 한 파일을 저장할 폴더)
				String saveFolder = root + "\\nuploadFiles";
//				String saveFolder = root + File.separator + "nuploadFiles";
				File folder = new File(saveFolder);
				if (!folder.exists()) {
					folder.mkdir();
				}
//				*************** 파일 경로 **********************
				String savePath = saveFolder + "\\" +fileName;
				File file = new File(savePath);
				//*************파일 저장 ******************
				uploadFile.transferTo(file);
				// ********************파일 크기 **********************
				long fileLength = uploadFile.getSize();
				
//				************** DB에 저장하기 위해 notice에 데이터를 Set 하는 부분임.
				notice.setNoticeFilename(fileName);
				notice.setNoticeFilepath(savePath);
				notice.setNoticeFilelength(fileLength);
			}
			
			int result = service.insertNotice(notice); 
			
			if ( result > 0 ) {
				//성공
				return "redirect:/notice/list.kh";
			}else{
				//실패
				model.addAttribute("error","관리자 문의");
				model.addAttribute("msg","실패함 ㅋㅋ");
				model.addAttribute("url","/member/register.do");
				return "common/errorPage";
			}
			
		} catch (Exception e) {
			model.addAttribute("error","관리자 문의");
			model.addAttribute("msg",e.getMessage());
			model.addAttribute("url","/member/register.do");
			return "common/errorPage";
		}
		
				
		
	}
	
	@RequestMapping(value="/notice/list.kh", method=RequestMethod.GET)
	public String showNoticeList(Model model) {
			
		try {
			List<Notice> nList = service.selectNoticeList();
			// List 데이터 요소 체크
			// 1. isEmpty
			// 2. Size()
			
			if(nList.size() >  0 ) {
				model.addAttribute("nList",nList);
				return "notice/list";
			}else {
				model.addAttribute("error","관리자 문의");
				model.addAttribute("msg","실패함 ㅋㅋ");
				model.addAttribute("url","/index.jsp");
				return "common/errorPage";
			}
			
		} catch (Exception e) {
			model.addAttribute("error","관리자 문의");
			model.addAttribute("msg",e.getMessage());
			model.addAttribute("url","/index.jsp");
			return "common/errorPage";
		}
	}
}
