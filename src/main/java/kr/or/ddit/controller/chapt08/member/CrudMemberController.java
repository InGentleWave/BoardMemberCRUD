package kr.or.ddit.controller.chapt08.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.controller.chapt08.member.service.IMemberService;
import kr.or.ddit.vo.crud.CrudMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/crud/member")
public class CrudMemberController {
	
	@Autowired
	private IMemberService service;

	@GetMapping("/register")
	public String crudMemberRegisterForm() {
		log.info("crudMembSerRegisterForm()실행!");
		 return "chapt08/member/register";
	}
	
	@PostMapping("/register")
	public String crudMemberRegister(CrudMember member,Model model) {
		log.info("crudMemberRegister()실행!");
		service.register(member);
		model.addAttribute("msg","등록이 완료되었습니다.");
		return "chapt08/member/success";
	}
	
	@GetMapping("/list")
	public String crudMemberList(Model model) {
		log.info("crudMemberList()실행!");
		List<CrudMember> memberList = service.list();
		model.addAttribute("memberList",memberList);
		 return "chapt08/member/list";
	}
	
	@GetMapping("/read")
	public String crudMemberRead(int userNo,Model model) {
		log.info("crudMemberRead()실행!");
		CrudMember member= service.read(userNo);
		model.addAttribute("member",member);
		return "chapt08/member/read";
	}
	
	@GetMapping("/modify")
	public String crudMemberModifyForm(int userNo,Model model) {
		log.info("crudMemberModifyForm()실행!");
		CrudMember member= service.read(userNo);
		model.addAttribute("member",member);
		return "chapt08/member/modify";
	}
	@PostMapping("/modify")
	public String crudMemberModify(CrudMember member,Model model) {
		log.info("crudMemberModify()실행!");
		service.modify(member);
		model.addAttribute("msg","수정이 완료되었습니다.");
		return "chapt08/member/success";
	}
	@PostMapping("/remove")
	public String crudMemberDelete(int userNo,Model model) {
		log.info("crudMemberDelete()실행!");
		service.remove(userNo);
		model.addAttribute("msg","삭제가 완료되었습니다.");
		return "chapt08/member/success";
	}
}
