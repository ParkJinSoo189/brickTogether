package org.kosta.LetsGo.model.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BTMemberVO implements Serializable{
	private static final long serialVersionUID = -7390864862982797454L;
	private String id;
	private String password;
	private String name;
	private String nick;
	private String tel;
	private String question;
	private String answer;
	private String memberType;
	private int enabled;
	
	// 회원가입시 필요한 생성자
	public BTMemberVO(String id, String password, String name, String nick, String tel, String question,
			String answer) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.nick = nick;
		this.tel = tel;
		this.question = question;
		this.answer = answer;
	}
	// 회원정보수정시 필요한 생성자
	public BTMemberVO(String password, String name, String tel, String question, String answer) {
		super();
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.question = question;
		this.answer = answer;
	}
	// 회원탈퇴시 필요한 생성자
	public BTMemberVO(String id, String password, int enabled) {
		super();
		this.id = id;
		this.password = password;
		this.enabled = enabled;
	}
}
