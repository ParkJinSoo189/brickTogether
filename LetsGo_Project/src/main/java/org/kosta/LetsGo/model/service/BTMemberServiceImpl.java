package org.kosta.LetsGo.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.LetsGo.model.mapper.BTMemberMapper;
import org.kosta.LetsGo.model.vo.BTMemberVO;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BTMemberServiceImpl implements BTMemberService {
	private final BTMemberMapper btMemberMapper;

	@Override
	public String login(String id, String password,HttpServletRequest request) {
		String path=null;
		BTMemberVO btMemberVO=btMemberMapper.login(id, password);
		if(btMemberVO==null||btMemberVO.getEnabled()==0) {
			path="member/login-fail";
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("mvo", btMemberVO);
			session.setAttribute("freeBoardHits", new ArrayList<Integer>());
			path="redirect:/";
		}
		return path;
	}
	
	@Override
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	@Override
	public String findId(String name,String tel,Model model) {
		String path=null;
		BTMemberVO btMemberVO=btMemberMapper.findId(name, tel);
		Integer memberEnabled=btMemberMapper.findEnabledByNameAndTel(name, tel);
			if(btMemberVO==null||memberEnabled==0) {
				path="member/findId-fail";
			}else {
				String memberId=btMemberVO.getId();
				model.addAttribute("memberId",memberId);
				path="member/findId-ok";
			}
		return path;
	}

	@Override
	public List<String> question() {
		List<String> question=new ArrayList<>();
		question.add("첫 구매 레고는?");
		question.add("주종 제품군은?");
		question.add("가장 좋아하는 레고는?");
		question.add("나의 가장 비싼 레고는?");
		return question;
	}
	
	@Override
	public String findPassword(String id, String question, String answer, String tel,Model model) {
		String path=null;
		BTMemberVO btMemberVO=btMemberMapper.findPassword(id,question,answer,tel);
		Integer memberEnabled=btMemberMapper.findEnabledByIdAndQuestionAndAnswerAndTel(id, question, answer, tel);
		if(btMemberVO==null||memberEnabled==0) {
			path="member/findPassword-fail";
		}else {
			String memberPassword=btMemberVO.getPassword();
			model.addAttribute("memberPassword", memberPassword);
			path="member/findPassword-ok";
		}
		return path;
	}

	@Override
	public void register(BTMemberVO btMemberVO) {
		btMemberMapper.register(btMemberVO);
	}

	@Override
	public BTMemberVO findBTMemberById(String id) {
		return btMemberMapper.findBTMemberById(id);
	}

	@Override
	public int checkNickAjax(String nick) {
		return btMemberMapper.checkNickAjax(nick);
	}

	@Override
	public int checkTelAjax(String tel) {
		return btMemberMapper.checkTelAjax(tel);
	}

	@Override
	public String myPage(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		BTMemberVO btMemberVO=(BTMemberVO) session.getAttribute("mvo");
		session.setAttribute("mvo", btMemberVO);
		return "member/myPage";
	}
	
	@Override
	public void update(BTMemberVO btMemberVO,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		BTMemberVO mvo=(BTMemberVO) session.getAttribute("mvo");
		btMemberMapper.update(btMemberVO);
		String id=mvo.getId();
		mvo=btMemberMapper.findBTMemberById(id);
		session.setAttribute("mvo", mvo);
	}

	@Override
	public String delete(BTMemberVO btMemberVO, HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		BTMemberVO mvo=(BTMemberVO) session.getAttribute("mvo");
		String id=mvo.getId();
		btMemberVO.setId(id);
		int deleteResult=btMemberMapper.delete(btMemberVO);
		if(deleteResult==1) {
			mvo=btMemberMapper.findBTMemberById(id);
			session.setAttribute("mvo", mvo);
			session.invalidate();
			return "ok";
		}else {
			return "fail";
		}
	}
}
