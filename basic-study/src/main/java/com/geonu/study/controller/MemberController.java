package com.geonu.study.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.geonu.study.domain.Member;
import com.geonu.study.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/")
	public String hello() {
		return "home";
	}

	@GetMapping("/members/new")
	public String createForm() {
		return "members/new";
	}

	@PostMapping("/members/new")
	public String createMember(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		memberService.join(member);
		return "redirect:/";
	}

	@GetMapping("/members")
	public String createForm(Model model) {
		List<Member> memberList = memberService.findMemberAll();
		model.addAttribute("memberList", memberList);
		return "members/memberList";
	}

}
