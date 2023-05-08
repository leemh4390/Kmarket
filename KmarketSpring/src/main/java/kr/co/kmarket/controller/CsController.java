package kr.co.kmarket.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;

/*
 * 담당 : 이민혁
 * 내용기록
 * 2023-05-08
 * 고객센터 기능구현 작업완료
*/
@Controller
@MapperScan("kr.co.kmarket.dao")
public class CsController {

	@Autowired
	private CsService service;
	
	
	
}