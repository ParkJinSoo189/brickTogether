package org.kosta.LetsGo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeCommentVO {
	private int freeCommentNo;
	private String content;
	private String timePosted;
	private BTMemberVO btMemberVO;
	private FreeBoardVO freeBoardVO;
}
