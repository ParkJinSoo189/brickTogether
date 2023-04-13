package org.kosta.LetsGo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardVO {
	private int freeNo;
	private String title;
	private String content;
	private String timePosted;
	private int hits;
	private String image;
	private String filePath;
	private BTMemberVO btMemberVO;
	
	// 자유게시판 글쓰기 시 필요한 생성자
	public FreeBoardVO(int freeNo, String title, String content, BTMemberVO btMemberVO) {
		super();
		this.freeNo = freeNo;
		this.title = title;
		this.content = content;
		this.btMemberVO = btMemberVO;
	}

	// 자유게시판 글수정 시 필요한 생성자
	public FreeBoardVO(int freeNo, String title, String content) {
		super();
		this.freeNo = freeNo;
		this.title = title;
		this.content = content;
	}
	
}
