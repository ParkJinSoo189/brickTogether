package org.kosta.LetsGo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kosta.LetsGo.model.mapper.FreeCommentMapper;
import org.kosta.LetsGo.model.vo.FreeCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitTest3FreeComment {
	private FreeCommentMapper freeCommentMapper;
	@Autowired
	public JUnitTest3FreeComment(FreeCommentMapper freeCommentMapper) {
		super();
		this.freeCommentMapper = freeCommentMapper;
	}
	
	@Test
		void freeCommentListByFreeNo() {
		int freeNo=24;
		List<FreeCommentVO> list=freeCommentMapper.freeCommentListByFreeNo(freeNo);
		for(FreeCommentVO vo:list) {
			System.out.println(vo);
		}
	}
}
