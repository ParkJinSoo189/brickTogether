package org.kosta.LetsGo;

import org.junit.jupiter.api.Test;
import org.kosta.LetsGo.model.mapper.BTMemberMapper;
import org.kosta.LetsGo.model.vo.BTMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitTest1Member {
	private BTMemberMapper btMemberMapper;
	@Autowired
	public JUnitTest1Member(BTMemberMapper btMemberMapper) {
		super();
		this.btMemberMapper = btMemberMapper;
	}
	
	// 로그인
	@Test
	void login() {
		String id="test";
		String password="a";
		BTMemberVO btMemberVO=btMemberMapper.login(id,password);
		System.out.println(btMemberVO);
	}
	// 아이디 찾기
	@Test
	void findId() {
		String name="테스트";
		String tel="01011111111";
		BTMemberVO findIdResult=btMemberMapper.findId(name,tel);
		System.out.println("조회결과= "+findIdResult);
	}
	// 회원상태조회(아이디 찾기)
	@Test
	void findEnabledByNameAndTel() {
		String name="테스트";
		String tel="01011111111";
		Integer findEnabledResult=btMemberMapper.findEnabledByNameAndTel(name,tel);
		System.out.println("조회결과= "+findEnabledResult);
	}
	// 비밀번호 찾기
	@Test
	void findPassword() {
		String id="test";
		String question="첫 구매 레고는?";
		String answer="10263";
		String tel="01011111111";
		BTMemberVO findPasswordResult=btMemberMapper.findPassword(id,question,answer,tel);
		System.out.println("조회결과= "+findPasswordResult);
	}
	// 회원상태조회(비밀번호 찾기)
	@Test
	void findEnabledByIdAndQuestionAndAnswerAndTel() {
		String id="test";
		String question="첫 구매 레고는?";
		String answer="10263";
		String tel="01011111111";
		Integer findPasswordResult=btMemberMapper.findEnabledByIdAndQuestionAndAnswerAndTel(id,question,answer,tel);
		System.out.println("조회결과= "+findPasswordResult);
	}
	
	// 회원가입
	@Test
	void register() {
		BTMemberVO btMemberVO=new BTMemberVO("java", "a", "이지은", "아이유", "01012345678", "주종 제품군은?", "크리에이트");
		int registerResult=btMemberMapper.register(btMemberVO);
		System.out.println("회원가입결과= "+registerResult);
	}
	// 회원정보수정
	@Test
	void update() {
		BTMemberVO btMemberVO=new BTMemberVO("spring", "aaaaaa", "봄봄", "스프링걸", "01012341234", "주종 제품군은?", "아이디어");
		int updateResult=btMemberMapper.update(btMemberVO);
		System.out.println("회원정보수정결과= "+updateResult);
	}
	// 회원탈퇴
	@Test
	void delete() {
		BTMemberVO btMemberVO=new BTMemberVO("test", "a", 0);
		int deleteResult=btMemberMapper.delete(btMemberVO);
		System.out.println("회원탈퇴결과= "+deleteResult);
	}
	
}








