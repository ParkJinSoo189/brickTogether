package org.kosta.LetsGo.model.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kosta.LetsGo.model.vo.BTMemberVO;
import org.springframework.ui.Model;

public interface BTMemberService {

	String login(String id, String password, HttpServletRequest request);
	
	String logout(HttpServletRequest request);

	String findId(String name, String tel, Model model);
	
	List<String> question();

	String findPassword(String id, String question, String answer, String tel, Model model);

	void register(BTMemberVO btMemberVO);

	BTMemberVO findBTMemberById(String id);

	int checkNickAjax(String nick);

	int checkTelAjax(String tel);

	String myPage(HttpServletRequest request);

	void update(BTMemberVO btMemberVO,HttpServletRequest request);

	String delete(BTMemberVO btMemberVO, HttpServletRequest request);

}