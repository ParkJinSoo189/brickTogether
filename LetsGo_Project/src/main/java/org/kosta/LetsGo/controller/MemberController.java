package org.kosta.LetsGo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kosta.LetsGo.model.service.BTMemberService;
import org.kosta.LetsGo.model.vo.BTMemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final BTMemberService btMemberService;
	// 로그인 폼
	@RequestMapping("loginForm")
	public String loginForm() {
		return "member/login-form";
	}
	// 로그인
	@PostMapping("login")
	public String login(String id,String password,HttpServletRequest request) {
		return btMemberService.login(id, password,request);
	}
	// 로그아웃
	@PostMapping("logout")
	public String logout(HttpServletRequest request) {
		return btMemberService.logout(request);
	}
	// 아이디 찾기 폼
	@RequestMapping("findIdForm")
	public String findIdForm() {
		return "member/findId-form";
	}
	// 아이디 찾기
	@RequestMapping("findId")
	public String findId(String name,String tel,Model model) {
		return btMemberService.findId(name,tel,model);
	}
	// 비밀번호 찾기 폼
	@RequestMapping("findPasswordForm")
	public String findPasswordForm(Model model) {
		List<String> question=btMemberService.question();
		model.addAttribute("questionList", question);
		return "member/findPassword-form";
	}
	// 비밀번호 찾기
	@RequestMapping("findPassword")
	public String findPassword(String id,String question,String answer,String tel,Model model) {
		return btMemberService.findPassword(id, question, answer, tel,model);
	}
	// 회원가입 폼
	@RequestMapping("registerForm")
	public String registerForm(Model model) {
		List<String> question=btMemberService.question();
		model.addAttribute("questionList", question);
		return "member/register-form";
	}
	// 회원가입
	@PostMapping("register")
	public String register(BTMemberVO btMemberVO) {
		btMemberService.register(btMemberVO);
		return "redirect:registerResult";
	}
	// 회원가입 결과
	@RequestMapping("registerResult")
	public String registerResult() {
		return "member/register-result";
	}
	// 아이디 중복체크 Ajax
	@RequestMapping("checkIdAjax")
	@ResponseBody
	public BTMemberVO checkIdAjax(String id) {
		BTMemberVO checkId=btMemberService.findBTMemberById(id);
		return checkId;
	}
	// 닉네임 중복체크 Ajax
	@RequestMapping("checkNickAjax")
	@ResponseBody
	public int checkNickAjax(String nick) {
		int checkNick=btMemberService.checkNickAjax(nick);
		return checkNick;
	}
	// 연락처 중복체크 Ajax
	@RequestMapping("checkTelAjax")
	@ResponseBody
	public int checkTelAjax(String tel) {
		int checkTel=btMemberService.checkTelAjax(tel);
		return checkTel;
	}
	// 마이페이지
	@RequestMapping("myPage")
	public String myPage(HttpServletRequest request) {
		return btMemberService.myPage(request);
	}
	// 회원정보수정 폼
	@RequestMapping("updateForm")
	public String updateForm(Model model) {
		List<String> question=btMemberService.question();
		model.addAttribute("questionList", question);
		return "member/update-form";
	}
	// 회원정보수정
	@PostMapping("update")
	public String update(BTMemberVO btMemberVO,HttpServletRequest request) {
		btMemberService.update(btMemberVO,request);
		return "redirect:updateResult";
	}
	// 회원정보수정결과
	@RequestMapping("updateResult")
	public String updateResult() {
		return "member/update-result";
	}
	// 회원탈퇴 체크
	@RequestMapping("checkDelete")
	public String checkDelete() {
		return "member/delete-form";
	}
	// 회원탈퇴
	@PostMapping("delete")
	@ResponseBody
	public String delete(BTMemberVO btMemberVO,HttpServletRequest request) {
		return btMemberService.delete(btMemberVO,request);
	}
}










