package org.kosta.LetsGo.controller;

import org.kosta.LetsGo.model.service.FreeCommentService;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FreeCommentController {
	private final FreeCommentService freeCommentService;
	
}
