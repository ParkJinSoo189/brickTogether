package org.kosta.LetsGo.model.service;

import javax.servlet.http.HttpServletRequest;

import org.kosta.LetsGo.model.vo.FreeBoardVO;
import org.springframework.ui.Model;

public interface FreeBoardService {

	String findFreeBoardList(Model model,Integer pageNo);

	String freeBoardDetail(Model model, Integer freeNo,HttpServletRequest request);

	void writeFreeBoard(FreeBoardVO freeBoardVO, HttpServletRequest request) throws Exception;

	void deleteFreeBoard(int freeNo);

	String updateFreeBoardForm(Model model, Integer freeNo);

	void updateFreeBoard(FreeBoardVO freeBoardVO);

	String findFreeBoardListByKeyword(String keyword,Model model,Integer pageNo);
	
}
