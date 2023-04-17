package org.kosta.LetsGo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kosta.LetsGo.model.mapper.FreeBoardMapper;
import org.kosta.LetsGo.model.vo.BTMemberVO;
import org.kosta.LetsGo.model.vo.FreeBoardVO;
import org.kosta.LetsGo.model.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitTest2FreeBoard {
	private FreeBoardMapper freeBoardMapper;

	@Autowired
	public JUnitTest2FreeBoard(FreeBoardMapper freeBoardMapper) {
		super();
		this.freeBoardMapper = freeBoardMapper;
	}

	// 자유게시판 리스트 + 페이지네이션
	@Test
	void findFreeBoardList() {
		int freeBoardTotalCount = freeBoardMapper.freeBoardTotalCount();
		String pageNo = "";
		Pagination pagination = null;
		if (pageNo == "") {
			pagination = new Pagination(freeBoardTotalCount);
		} else {
			pagination = new Pagination(Integer.parseInt(pageNo), freeBoardTotalCount);
		}
		List<FreeBoardVO> list = freeBoardMapper.findFreeBoardList(pagination);
		for (FreeBoardVO vo : list) {
			System.out.println(vo);
		}
	}

	// 자유게시판 상세 글 보기
	@Test
	void freeBoardDetail() {
		int freeNo = 1;
		FreeBoardVO freeBoardVO = freeBoardMapper.freeBoardDetail(freeNo);
		System.out.println(freeBoardVO);
	}

	// 자유게시판 글쓰기

	@Test
	void writeFreeBoard() {
		BTMemberVO btMemberVO = new BTMemberVO();
		btMemberVO.setId("spring");
		FreeBoardVO freeBoardVO = new FreeBoardVO(189, "189제목", null, btMemberVO);
		int writeResult = freeBoardMapper.writeFreeBoard(freeBoardVO);
		System.out.println("글쓰기 결과= " + writeResult);
	}

	// 자유게시판 글삭제
	@Test
	void deleteFreeBoard() {
		int freeNo = 11;
		int deleteResult = freeBoardMapper.deleteFreeBoard(freeNo);
		System.out.println("글삭제 결과= " + deleteResult);
	}
	// 자유게시판 글수정

	@Test
	void updateFreeBoard() {
		FreeBoardVO freeBoardVO = new FreeBoardVO(8, "제목8", "내용");
		int updateFreeBoard = freeBoardMapper.updateFreeBoard(freeBoardVO);
		System.out.println("글수정 결과= " + updateFreeBoard);
	}

	// 자유게시판 검색
	@Test
	void findFreeBoardByKeyword() {
		int keywordTotalCount = freeBoardMapper.freeBoardTotalCount();
		String pageNo = "";
		Pagination pagination = null;
		if (pageNo == "") {
			pagination = new Pagination(keywordTotalCount);
		} else {
			pagination = new Pagination(Integer.parseInt(pageNo), keywordTotalCount);
		}
		String keyword = "제";
		Map<String, Object> map = new HashMap<>();
		map.put("KEYWORD", keyword);
		map.put("PAGINATION", pagination);
		List<FreeBoardVO> list = freeBoardMapper.findFreeBoardByKeyword(map);
		for (FreeBoardVO vo : list) {
			System.out.println(vo);
		}
	}

	// 자유게시판 조회수
	@Test
	void freeBoardHits() {
		int freeNo = 24;
		int freeBoardHits = freeBoardMapper.freeBoardHits(freeNo);
		System.out.println(freeBoardHits);
	}
}
