package com.ing.spring.notice.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ing.spring.notice.domain.PageInfo;
import com.ing.spring.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService service;

	@RequestMapping(value = "/notice/insert.kh", method = RequestMethod.GET)
	public String showInsertForm() {
		return "notice/insert";
	}

	@RequestMapping(value = "/notice/insert.kh", method = RequestMethod.POST)
	public String insertNotice(@ModelAttribute Notice notice,
			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest request,
			Model model) {

		try {

			if (!uploadFile.getOriginalFilename().equals("")) {
				// **********************파일 이름*************************
				String fileName = uploadFile.getOriginalFilename();
				// 파일 경로 ( 내가 저장한 후 그 경로를 DB에 저장하도록 준비하는 것)

				// 파일 경로는 request 통해서 가져옴, HttpServletRequest 씀
				// resources 폴더의 경로를 알 수 있음 = /IngSpringMVC/src/main/webapp/resources
				String root = request.getSession().getServletContext().getRealPath("resources");

				// 폴더가 없을 경우 자동 생성 (내가 업로드 한 파일을 저장할 폴더)
				String saveFolder = root + "\\nuploadFiles";
//				String saveFolder = root + File.separator + "nuploadFiles";
				File folder = new File(saveFolder);
				if (!folder.exists()) {
					folder.mkdir();
				}
//				*************** 파일 경로 **********************
				String savePath = saveFolder + "\\" + fileName;
				File file = new File(savePath);
				// *************파일 저장 ******************
				uploadFile.transferTo(file);
				// ********************파일 크기 **********************
				long fileLength = uploadFile.getSize();

//				************** DB에 저장하기 위해 notice에 데이터를 Set 하는 부분임.
				notice.setNoticeFilename(fileName);
				notice.setNoticeFilepath(savePath);
				notice.setNoticeFilelength(fileLength);
			}

			int result = service.insertNotice(notice);

			if (result > 0) {
				// 성공
				return "redirect:/notice/list.kh";
			} else {
				// 실패
				model.addAttribute("error", "관리자 문의");
				model.addAttribute("msg", "실패함 ㅋㅋ");
				model.addAttribute("url", "/member/register.do");
				return "common/errorPage";
			}

		} catch (Exception e) {
			model.addAttribute("error", "관리자 문의");
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "/member/register.do");
			return "common/errorPage";
		}

	}

	@RequestMapping(value = "/notice/list.kh", method = RequestMethod.GET)
	public String showNoticeList(
			@RequestParam(value= "page", required = false, defaultValue="1") Integer curruntPage,
			Model model) {

		try {
//			int curruntPage = page != 0 ? page : 1;
			
			//쿼리문 SELECT COUNT(*) FROM NOTICE_TBL
			int totalCount = service.getListCount();
			PageInfo pInfo = this.getPageInfo(curruntPage, totalCount);
			List<Notice> nList = service.selectNoticeList(pInfo);
			// List 데이터 요소 체크
			// 1. isEmpty
			// 2. Size()

			if (nList.size() > 0) {
				model.addAttribute("pInfo",pInfo);
				model.addAttribute("nList", nList);
				return "notice/list";
			} else {
				model.addAttribute("error", "공지사항 조회에 실패하였습니다.");
				model.addAttribute("msg", "공지사항 조회 실패 ㅋ");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}

		} catch (Exception e) {
			model.addAttribute("error", "관리자 문의");
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}

	// 페이징 처리
	public PageInfo getPageInfo(int curruntPage, int totalCount) {

		PageInfo pi = null;

		// 10개씩 보여준다
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		// 위에까지는 고정되는 값임

		// 100개를 10개씩 보여주면 몇페이지가 나오나요
		int naviTotalCount;

		// start,end 구할때 curruntpage 필요함
		// 페이지 시작값
		int startNavi;

		// 페이지 끝값
		int endNavi;

		// totalcount는 매개변수로 넘김
		// 102/10이면 10.2 나와서 올림 1페이지(나머지가 0보다 크면 +1)
		// 쉬운 방법은 + 0.9
		// double 로 소수점 계산 후 떨구기 위해 int 로 형변환
		// Math.ceil 도 가능(조금더 안정적이지만 형변환 공부 위해 int)
		naviTotalCount = (int) ((double) totalCount / recordCountPerPage + 0.9);

		// startNavi는 현재 페이지에서 나누고 0.9 더해줌
		// curruntPage 값이 1~5 일때 startNavi 가 1로 고정 되도록 구해주는 식임
		startNavi = (((int) ((double) curruntPage / naviCountPerPage + 0.9)) - 1) * naviCountPerPage + 1;

		// 엔드페이지구하기
		endNavi = startNavi + naviCountPerPage - 1;
		// endNavi 는 startNavi 에 naviCountPerPage 를 무조건 더하기때문에
		// 실제 최대값보다 커질 수 있음
		if (endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}

		// 리턴해주기 위해서 클래스를 하나 만들어서 넣어줌
//	 return recordCountPerPage,naviCountPerPage,naviTotalCount,startNavi,endNavi
		pi = new PageInfo(curruntPage, recordCountPerPage, naviCountPerPage, startNavi, endNavi, totalCount,
				naviTotalCount);
		return pi;
	}
	
	@RequestMapping(value="/notice/search.kh" , method=RequestMethod.GET)
	public String searchNoticeList(@RequestParam(value= "page", required = false, defaultValue="1") Integer curruntPage,@RequestParam()String searchCondiition
			, @RequestParam("searchKeyword")String searchKeyword
			,Model model) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("searchCondition", searchCondiition);
		paramMap.put("searchKeyword", searchKeyword);
		int totalCount = service.getListCount(paramMap);
		PageInfo pInfo = this.getPageInfo(curruntPage, totalCount);

		List<Notice> searchList = service.searchNoticeByKeyword(pInfo,paramMap);

			//switch 문이 mapper 들어갔음
		//		switch(searchCondiition) {
//		case "all" : 
//		searchList = service.searchNoticeAll(searchKeyword,pInfo);
//		break;
//		case "writer" :
//			searchList = service.searchNoticeByWriter(searchKeyword,pInfo);
//			break;
//		case "title" : 
//		searchList = service.searchNoticeByTitle(searchKeyword,pInfo);
//			
//			break;
//		case "content" :
//			searchList = service.searchNoticeByContent(searchKeyword,pInfo);
//			break;
//		
//		
//		}
		
		if(!searchList.isEmpty()) {
			
			model.addAttribute("searchCondition",searchCondiition);
			model.addAttribute("searchKeyword",searchKeyword);
			model.addAttribute("pInfo",pInfo);
			model.addAttribute("sList",searchList);
			return "notice/search";
		}else {
			model.addAttribute("error", "관리자 문의");
			model.addAttribute("msg", "실패함 ㅋㅋ");
			model.addAttribute("url", "/notice/list.kh");
			return "common/errorPage";
		}
		
	}
	
}
