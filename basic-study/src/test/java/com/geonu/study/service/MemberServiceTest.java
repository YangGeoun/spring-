package com.geonu.study.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.geonu.study.domain.Member;
import com.geonu.study.repository.MemberRepository;
import com.geonu.study.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemberRepository memberRepository;

	public MemberServiceTest() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	// @AfterEach
	// void afterEach() {
	// 	memberRepository.clearStore();
	// }

	@Test
	void join() {
		// given
		Member member = new Member();
		member.setName("양건우");
		// when
		Long savedId = memberService.join(member);
		// then
		Member findMember = memberService.findOne(savedId).get();
		Assertions.assertThat(member).isEqualTo(findMember);
	}

	void duplitcatedMember() {
		// given
		Member member1 = new Member();
		member1.setName("양건우");

		Member member2 = new Member();
		member2.setName("양건우");
		// when
		memberService.join(member1);
		assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));
	}

	@Test
	void findMemberAll() {
		Member member1 = new Member();
		member1.setName("양건우");
		memberService.join(member1);

		Member member2 = new Member();
		member2.setName("스피링");
		memberService.join(member2);

		List<Member> members = memberService.findMemberAll();
		Assertions.assertThat(members).hasSize(2);
	}

	@Test
	void findOne() {
		Member member1 = new Member();
		member1.setName("양건우");
		Long savedId = memberService.join(member1);

		Member member2 = new Member();
		member2.setName("스피링");
		memberService.join(member2);

		Member findMember = memberService.findOne(savedId).get();
		Assertions.assertThat(findMember).isEqualTo(member1);
	}
}
