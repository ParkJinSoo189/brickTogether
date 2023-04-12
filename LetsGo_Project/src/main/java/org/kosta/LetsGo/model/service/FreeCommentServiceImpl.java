package org.kosta.LetsGo.model.service;

import org.kosta.LetsGo.model.mapper.FreeCommentMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeCommentServiceImpl implements FreeCommentService {
	private final FreeCommentMapper freeCommentMapper;

}
