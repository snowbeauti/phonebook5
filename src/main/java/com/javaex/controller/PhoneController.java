package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

	// (메소드 단위로 기능을 정의)

	// 필드
	@Autowired
	private PhoneDao pdao; 

	// 생성자
	// 메소드 g/s

	/** 메소드 일반**메소드 마다 기능 1개씩 ->기능마다 url부여 **/

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {

		System.out.println("list");
		List<PersonVo> personList = pdao.getPersonList();


		model.addAttribute("pList", personList);
		return "List"; 
	}

	// 등록폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {

		return "WriteForm";
	}

	// 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo pvo) {
		System.out.println("write");
		
		System.out.println(pvo.toString());
		pdao.personInsert(pvo);
		return "redirect:/phone/list";

	}


	// 삭제
	@RequestMapping(value = "/delete/{personId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("personId") int id) {
		System.out.println("delete");

		pdao.PersonDelete(id);

		return "redirect:/phone/list";

	}

	// 수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("id") int id, Model model) {

		PersonVo pvo = pdao.getPerson(id);

		model.addAttribute("pvo", pvo);
		System.out.println(pvo);

		return "modifyForm";
	}

	
	// 수정 -->modify -->@RequestParam
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute PersonVo pvo) {
		System.out.println("modify");

		pdao.personUpdate(pvo);

		return "redirect:/phone/list";
	}

}
