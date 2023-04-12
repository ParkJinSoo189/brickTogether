package org.kosta.LetsGo.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.LetsGo.model.vo.FreeBoardVO;
import org.kosta.LetsGo.model.vo.Pagination;

@Mapper
public interface FreeBoardMapper {

	int freeBoardTotalCount();

	List<FreeBoardVO> findFreeBoardList(Pagination pagination);

	FreeBoardVO freeBoardDetail(int freeNo);

	int writeFreeBoard(FreeBoardVO freeBoardVO);

	int deleteFreeBoard(int freeNo);

	int updateFreeBoard(FreeBoardVO freeBoardVO);

	List<FreeBoardVO> findFreeBoardByKeyword(Map<String, Object> map);

	int keywordTotalCount(String keyword);

	int freeBoardHits(int freeNo);

}
