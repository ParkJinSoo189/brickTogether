package org.kosta.LetsGo.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.LetsGo.model.mapper.FreeBoardMapper;
import org.kosta.LetsGo.model.mapper.FreeCommentMapper;
import org.kosta.LetsGo.model.vo.BTMemberVO;
import org.kosta.LetsGo.model.vo.FreeBoardVO;
import org.kosta.LetsGo.model.vo.FreeCommentVO;
import org.kosta.LetsGo.model.vo.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{
	private final FreeBoardMapper freeBoardMapper;
	private final FreeCommentMapper freeCommentMapper;

	@Override
	public String findFreeBoardList(Model model,Integer pageNo) {
		int freeBoardTotalCount=freeBoardMapper.freeBoardTotalCount();
		Pagination pagination=null;
		if(pageNo==null) {
			pagination=new Pagination(freeBoardTotalCount);
		}else {
			pagination=new Pagination(pageNo, freeBoardTotalCount);
		}
		List<FreeBoardVO> list=freeBoardMapper.findFreeBoardList(pagination);
		Map<String, Object> paging=new HashMap<>();
		paging.put("LIST", list);
		if(freeBoardTotalCount==0) {
			pagination=null;
		}
		paging.put("PAGINATION", pagination);
		model.addAttribute("freeBoardList", paging.get("LIST"));
		model.addAttribute("pagination", paging.get("PAGINATION"));
		return "freeBoard/freeBoard-list";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String freeBoardDetail(Model model, Integer freeNo,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		ArrayList<Integer> list=(ArrayList<Integer>) session.getAttribute("freeBoardHits");
		if(list.contains(freeNo)==false) {
			freeBoardMapper.freeBoardHits(freeNo);
			list.add(freeNo);
		}
		FreeBoardVO freeBoardVO=freeBoardMapper.freeBoardDetail(freeNo);
		model.addAttribute("freeBoardDetail", freeBoardVO);
		
		List<FreeCommentVO> commentList=freeCommentMapper.freeCommentListByFreeNo(freeNo);
		model.addAttribute("freeCommentList", commentList);
		return "freeBoard/freeBoard-detail";
	}

	@Override
	public void writeFreeBoard(FreeBoardVO freeBoardVO,HttpServletRequest request,MultipartFile file) throws Exception {
		HttpSession session=request.getSession(false);
		BTMemberVO mvo=(BTMemberVO) session.getAttribute("mvo");
		freeBoardVO.setBtMemberVO(mvo);
		
		/* 조금 더 공부하고!
		 * String filePath="/resources/static/images/"; File dir=new File(filePath);
		 * if(dir.exists()==false) { dir.mkdirs(); } String
		 * fileName=file.getOriginalFilename(); File saveFile=new File(filePath,
		 * fileName); file.transferTo(saveFile); freeBoardVO.setImage(fileName);
		 * freeBoardVO.setFilePath("images/"+fileName);
		 */
		
		freeBoardMapper.writeFreeBoard(freeBoardVO);
	}

	@Override
	public void deleteFreeBoard(int freeNo) {
		freeBoardMapper.deleteFreeBoard(freeNo);
	}

	@Override
	public String updateFreeBoardForm(Model model, Integer freeNo) {
		FreeBoardVO freeBoardVO=freeBoardMapper.freeBoardDetail(freeNo);
		model.addAttribute("updateFreeBoard", freeBoardVO);
		return "freeBoard/updateFreeBoard-from";
	}

	@Override
	public void updateFreeBoard(FreeBoardVO freeBoardVO) {
		freeBoardMapper.updateFreeBoard(freeBoardVO); 
	}

	@Override
	public String findFreeBoardListByKeyword(String keyword, Model model,Integer pageNo) {
		int keywordTotalCount=freeBoardMapper.keywordTotalCount(keyword);
		Pagination pagination=null;
		
		if(pageNo==null) {
			pageNo=1;
			pagination=new Pagination(keywordTotalCount);
		}else {
			pagination=new Pagination(pageNo, keywordTotalCount);
		}
		if(keywordTotalCount==0) {
			pagination=null;
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("KEYWORD", keyword);
		map.put("PAGINATION", pagination);
		
		List<FreeBoardVO>list=freeBoardMapper.findFreeBoardByKeyword(map);
		
		Map<String, Object>keywordMap=new HashMap<>();
		keywordMap.put("LIST", list);
		keywordMap.put("PAGINATION", pagination);
		
		model.addAttribute("freeBoardList",keywordMap.get("LIST"));
		model.addAttribute("pagination",keywordMap.get("PAGINATION"));
		return "freeBoard/freeBoard-list";
	}

}






