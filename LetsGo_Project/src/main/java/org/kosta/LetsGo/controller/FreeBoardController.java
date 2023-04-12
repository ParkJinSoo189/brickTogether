package org.kosta.LetsGo.controller;

import javax.servlet.http.HttpServletRequest;

import org.kosta.LetsGo.model.service.FreeBoardService;
import org.kosta.LetsGo.model.vo.FreeBoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FreeBoardController {
	private final FreeBoardService freeBoardService;
	
	// 자유게시판 리스트
	@RequestMapping("findFreeBoardList")
	public String findFreeBoardList(Model model,Integer pageNo) {
		return freeBoardService.findFreeBoardList(model,pageNo);
	}
	// 상세 글 보기
	@RequestMapping("freeBoardDetail")
	public String freeBoardDetail(Model model,Integer freeNo,HttpServletRequest request) {
		return freeBoardService.freeBoardDetail(model,freeNo,request);
	}
	// 자유게시판 글쓰기 폼
	@RequestMapping("writeFreeBoardForm")
	public String writeFreeBoardForm() {
		return "freeBoard/writeFreeBoard-form";
	}
	// 자유게시판 글쓰기
	@PostMapping("writeFreeBoard")
	public String writeFreeBoard(FreeBoardVO freeBoardVO,HttpServletRequest request,MultipartFile file) throws Exception {
		freeBoardService.writeFreeBoard(freeBoardVO,request,file);
		return "redirect:writeFreeBoardResult";
	}
	// 자유게시판 글쓰기 결과
	@RequestMapping("writeFreeBoardResult")
	public String writeFreeBoardResult() {
		return "freeBoard/writeFreeBoard-result";
	}
	// 자유게시판 글삭제 
	@PostMapping("deleteFreeBoard")
	public String deleteFreeBoard(int freeNo) {
		freeBoardService.deleteFreeBoard(freeNo);
		return "redirect:deleteFreeBoardResult";
	}
	// 자유게시판 글삭제 결과
	@RequestMapping("deleteFreeBoardResult")
	public String deleteFreeBoardResult() {
		return "freeBoard/deleteFreeBoard-result";
	}
	// 자유게시판 글수정 폼
	@RequestMapping("updateFreeBoardForm")
	public String updateFreeBoardForm(Model model,Integer freeNo) {
		return freeBoardService.updateFreeBoardForm(model,freeNo);
	}
	// 자유게시판 글수정
	@PostMapping("updateFreeBoard")
	public String updateFreeBoard(FreeBoardVO freeBoardVO) {
		freeBoardService.updateFreeBoard(freeBoardVO);
		return "redirect:updateFreeBoardResult";
	}
	// 자유게시판 글수정 결과
	@RequestMapping("updateFreeBoardResult")
	public String updateFreeBoardResult(Model model,Integer freeNo) {
		return "freeBoard/updateFreeBoard-result";
	}
	// 자유게시판 검색
	@RequestMapping("findFreeBoardListByKeyword")
	public String findFreeBoardListByKeyword(String keyword,Model model,Integer pageNo) {
		return freeBoardService.findFreeBoardListByKeyword(keyword, model,pageNo);
	}
}












