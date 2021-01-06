package com.koreait.fashionshop.controller.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.exception.MailSendException;
import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.member.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	
	//회원가입폼 요청
	@RequestMapping(value="/shop/member/registForm", method=RequestMethod.GET)
	public String getRegistForm() {
		return "shop/member/signup";
	}
	

	
	//회원 가입 요청 처리
	@RequestMapping(value="/shop/member/regist", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String regist(Member member) {
		logger.debug("아이디 : " + member.getUser_id());
		logger.debug("이름 : " + member.getName());
		logger.debug("비번 : " + member.getPassword());
		logger.debug("이메일 id : " + member.getEmail_id());
		logger.debug("이메일 server : " + member.getEmail_server());
		logger.debug("우편번호 : " + member.getZipcode());
		logger.debug("주소 : " + member.getAddr());
		
		memberService.regist(member);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1,");
		sb.append("\"msg\":\"회원가입 성공\"");
		sb.append("}");
		
		//-------------------------------------------------------
		//원래는 이 자리에 if else로 exception handling을 해야하는 거였지만
		//아래의 예외 핸들러를 통해 코드량을 줄일 수 있었다
		//-------------------------------------------------------
		
		return sb.toString();
	}
	
	
	//예외 핸들러 2가지 처리
	@ExceptionHandler(MemberRegistException.class)
	@ResponseBody
	public String handleException(MemberRegistException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":0,");
		sb.append("\"msg\":\"" + e.getMessage() + "\"");
		sb.append("}");
		//원래는 시스템 관리자들에게도 알려줘야 함
		return sb.toString();
	}
	@ExceptionHandler(MailSendException.class)
	public ModelAndView handleException(MailSendException e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", e.getMessage());  //사용자가 보게될 에러 메세지
		mav.setViewName("shop/error/result");
		//원래는 시스템 관리자들에게도 알려줘야 함
		return mav;
	}
}