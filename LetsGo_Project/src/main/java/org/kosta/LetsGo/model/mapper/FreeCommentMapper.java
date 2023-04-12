package org.kosta.LetsGo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.LetsGo.model.vo.FreeCommentVO;

@Mapper
public interface FreeCommentMapper {

	List<FreeCommentVO> freeCommentListByFreeNo(int freeNo);

}
