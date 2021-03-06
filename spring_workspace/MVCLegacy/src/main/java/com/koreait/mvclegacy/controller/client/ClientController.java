package com.koreait.mvclegacy.controller.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.mvclegacy.model.member.MemberService;

//관리자 기능과 관련된 모든 요청을 처리하는 controller

@Controller
public class ClientController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);  //sysout대신 사용할 logger
	//All < DEBUG < INFO < WARN < ERROR < FATAL < OFF
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		logger.debug("client controller의 test()메서드 호출");
		logger.debug("client에서 사용중인 MemberService의 주소값 : " + memberService);  //client의 memberService와 주소값이 다르다. 낭비
		memberService.regist();

		return null;
	}

}
