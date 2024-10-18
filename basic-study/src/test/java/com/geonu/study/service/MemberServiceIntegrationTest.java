package com.geonu.study.service;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.geonu.study.domain.Member;
import com.geonu.study.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	void join() {
		// given
		Member member = new Member();
		member.setName("김김김");
		// when
		Long savedId = memberService.join(member);
		// then
		Member findMember = memberService.findOne(savedId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
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
}
