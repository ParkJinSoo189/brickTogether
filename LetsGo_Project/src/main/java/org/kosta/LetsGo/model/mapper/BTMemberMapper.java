package org.kosta.LetsGo.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.LetsGo.model.vo.BTMemberVO;

@Mapper
public interface BTMemberMapper {

	BTMemberVO login(String id, String password);

	BTMemberVO findId(String name, String tel);
	
	Integer findEnabledByNameAndTel(String name, String tel);

	BTMemberVO findPassword(String id, String question, String answer, String tel);
	
	Integer findEnabledByIdAndQuestionAndAnswerAndTel(String id, String question, String answer, String tel);

	int register(BTMemberVO btMemberVO);

	BTMemberVO findBTMemberById(String id);

	int checkNickAjax(String nick);

	int checkTelAjax(String tel);

	int update(BTMemberVO btMemberVO);

	int delete(BTMemberVO btMemberVO);

}
