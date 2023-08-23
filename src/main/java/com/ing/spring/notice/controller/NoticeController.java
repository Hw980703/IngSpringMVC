package com.ing.spring.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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

			if (uploadFile != null&&!uploadFile.getOriginalFilename().equals("")) {
				// **********************파일 이름*************************
//				String fileName = uploadFile.getOriginalFilename();
				// 파일 경로 ( 내가 저장한 후 그 경로를 DB에 저장하도록 준비하는 것)

				// 파일 경로는 request 통해서 가져옴, HttpServletRequest 씀
				// resources 폴더의 경로를 알 수 있음 = /IngSpringMVC/src/main/webapp/resources
//				String root = request.getSession().getServletContext().getRealPath("resources");

				// 폴더가 없을 경우 자동 생성 (내가 업로드 한 파일을 저장할 폴더)
//				String saveFolder = root + "\\nuploadFiles";
//				String saveFolder = root + File.separator + "nuploadFiles";
//				File folder = new File(saveFolder);
//				if (!folder.exists()) {
//					folder.mkdir();
//				}
//				*************** 파일 경로 **********************
//				String savePath = saveFolder + "\\" + fileName;
//				File file = new File(savePath);
				// *************파일 저장 ******************
//				uploadFile.transferTo(file);
				// ********************파일 크기 **********************
//				long fileLength = uploadFile.getSize();

//				************** DB에 저장하기 위해 notice에 데이터를 Set 하는 부분임.
			
				Map<String,Object> nMap = this.saveFile(uploadFile, request);
				String fileName = (String)nMap.get("fileName");
				String savePath = (String)nMap.get("filePath");
				long fileLength = (long)nMap.get("fileLength");
				String fileRename = (String)nMap.get("fileRename");
				notice.setNoticeFilename(fileName);
				notice.setNoticeFileRename(fileRename);
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
	public String searchNoticeList(@RequestParam(value= "page", required = false, defaultValue="1") Integer curruntPage,@RequestParam("searchCondiition")String searchCondiition
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
	
	
	@RequestMapping(value="/notice/detail.kh",method=RequestMethod.GET)
	public String showNoticeDetail(Model model, @RequestParam("noticeNo") Integer noticeNo ){
			
		
		try {
			//Integer 은 null 인지 체크를 할 수 있음.
			Notice noticeOne = service.selectNoticeByNo(noticeNo);
			if ( noticeOne != null) {
				//성공
				model.addAttribute("notice",noticeOne);
				return "notice/detail";
			} else {
				
				model.addAttribute("error", "관리자 문의");
				model.addAttribute("msg", "실패함 ㅋㅋ");
				model.addAttribute("url", "/notice/list.kh");
				return "common/errorPage";
			}
			
			
//		servlet-context 에서 설정을 했기에 /WEB-INF/views로 감
			
		} catch (Exception e) {
			model.addAttribute("error", "관리자 문의");
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "/notice/list.kh");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/notice/modify.kh",method=RequestMethod.GET)
	public String noticeModifyByNo(@RequestParam("noticeNo")Integer noticeNo,Model model) {
		
		Notice noticeOne = service.selectNoticeByNo(noticeNo);
		
		model.addAttribute("notice",noticeOne);
		return "notice/modify";
		
	}
	
	@RequestMapping(value="/notice/modify.kh",method=RequestMethod.POST)
	public String updateNotice(@ModelAttribute Notice notice
			,@RequestParam(value="uploadFile",required = false) MultipartFile uploadFile
			,Model model, HttpServletRequest request) {
		//ModelAttribute 이지만 file 네임은 바꾸면 안됨. 네임을 uploadFile로 냅둬야함
		
		try {
			
			if(uploadFile != null && !uploadFile.isEmpty()) {
				// 수정
				// 1. 대체 2. 삭제 후 등록
				// 삭제 후 등록 시 기존 업로드 된 파일이 있는지 체크함!!!!
				// 있으면 기존 파일 삭제
				// 없으면 새로 업로드 하려는 파일을 저장
				
				
				String fileName = notice.getNoticeFileRename();
				if(fileName != null) {
					//있으면 기존 파일 삭제함
					this.deleteFile(request,fileName);
					
				}
				
				//같은 패키지에 있는 saveFile 메소드를 this 사용으로 호출
				//saveFile 은 Map 을 return 하기 때문에 Map 으로 받아줌
				Map<String,Object> infoMap =  this.saveFile(uploadFile, request);
				
				//각각 변수에 Map.get 을 활용해서 값 넣어줌
				String noticeFilename = (String)infoMap.get("fileName");
				String noticeFilePath = (String)infoMap.get("filePath");
				long noticeFileLength = (long)infoMap.get("fileLength");
				String noteiceFileRename = (String)infoMap.get("fileRename");
				
				// setter 활용으로 변수 값 부여
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFilepath(noticeFilePath);
				notice.setNoticeFilelength(noticeFileLength);
				notice.setNoticeFileRename(noteiceFileRename);
				
				
			}
			
			int result = service.updateNotice(notice);
			if ( result > 0) {
				return "redirect:/notice/detail.kh?noticeNo="+notice.getNoticeNo();
			}
			else {

				model.addAttribute("error", "관리자 문의");
				model.addAttribute("msg", "실패함 ㅋㅋ");
				model.addAttribute("url", "/notice/list.kh");
				return "common/errorPage";
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", "관리자 문의");
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "/notice/list.kh");
			return "common/errorPage";
		}
	
	}

	private void deleteFile(HttpServletRequest request,String filename) {
		
			String root = request.getSession().getServletContext().getRealPath("resources");
			String delFilepath = root+"\\nuploadFiles\\"+filename;
			File file = new File(delFilepath);
			if(file.exists()) {
				file.delete();
			}
	}

	public Map<String,Object> saveFile(MultipartFile uploadFile,HttpServletRequest request) throws Exception {
			//return 해야 하는 값이 여러개 일때 사용하는 방법
			
			//1. VO 클래스를 만들어서 생성자 후 넘김
			//2. HashMap 을 사용하는 방법
			Map<String,Object> infoMap = new HashMap<String, Object>();
			
			// **********************파일 이름*************************
			String fileName = uploadFile.getOriginalFilename();
			// 파일 경로 ( 내가 저장한 후 그 경로를 DB에 저장하도록 준비하는 것)
	
			// 파일 경로는 request 통해서 가져옴, HttpServletRequest 씀
			// resources 폴더의 경로를 알 수 있음 = /IngSpringMVC/src/main/webapp/resources
			String root = request.getSession().getServletContext().getRealPath("resources");
	
			// 폴더가 없을 경우 자동 생성 (내가 업로드 한 파일을 저장할 폴더)
			String saveFolder = root + "\\nuploadFiles";
	//		String saveFolder = root + File.separator + "nuploadFiles";
			File folder = new File(saveFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}
	//		*************** 파일 경로 **********************
//			-- 리네임 랜덤숫자로 하기 방법 --
//			Random rand = new Random();
//			String strResult = "N";
//			for ( int i=0; i< 7; i++) {
//				int result  = rand.nextInt(100)+1;
//				strResult += result + "";
//			}
			
//			-- 현재 시간으로 설정해서 업로드하기 --
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
			// 나중에 SS랑 비교
			String strResult = sdf.format(new Date(System.currentTimeMillis()));
			
			
			String ext = fileName.substring(fileName.lastIndexOf(".")+1);
			String fileRename = "N" + strResult + "."+ext;
			String savePath = saveFolder + "\\" + fileRename;
			File file = new File(savePath);
			// *************파일 저장 ******************
			uploadFile.transferTo(file);
			// ********************파일 크기 **********************
			long fileLength = uploadFile.getSize();
			
			
			//파일이름, 경로, 크기를 넘겨주기 위해서 Map에 정보를 저장 한 후 return 함
			//왜 reutrn 하는가 ? DB에 저장하기 위해서 필요한 정보니카!
			infoMap.put("fileName", fileName);
			infoMap.put("fileRename", fileRename);
			infoMap.put("filePath", savePath);
			infoMap.put("fileLength", fileLength);
			return infoMap;
		}
	
}
