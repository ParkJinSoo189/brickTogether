package org.kosta.LetsGo.model.service;

import javax.servlet.http.HttpServletRequest;

import org.kosta.LetsGo.model.vo.FreeBoardVO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface FreeBoardService {

	String findFreeBoardList(Model model,Integer pageNo);

	String freeBoardDetail(Model model, Integer freeNo,HttpServletRequest request);

	void writeFreeBoard(FreeBoardVO freeBoardVO, HttpServletRequest request, MultipartFile file) throws Exception;

	void deleteFreeBoard(int freeNo);

	String updateFreeBoardForm(Model model, Integer freeNo);

	void updateFreeBoard(FreeBoardVO freeBoardVO);

	String findFreeBoardListByKeyword(String keyword,Model model,Integer pageNo);
	
}
