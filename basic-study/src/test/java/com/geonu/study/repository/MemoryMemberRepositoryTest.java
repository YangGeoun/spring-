package com.geonu.study.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.geonu.study.domain.Member;

public class MemoryMemberRepositoryTest {
	MemberRepository memberRepository = new MemoryMemberRepository();

	// @AfterEach
	// public void afterEach() {
	// 	memberRepository.clearStore();
	// }

	@Test
	public void save() {
		Member member = new Member();
		member.setName("양건우");

		memberRepository.save(member);

		Member result = memberRepository.findById(member.getId()).get();
		Assertions.assertThat(result).isEqualTo(member);

	}

	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("양건우");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("스프링");
		memberRepository.save(member2);

		Member result = memberRepository.findByName("양건우").get();
		Assertions.assertThat(result).isEqualTo(member1);
	}

	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("양건우");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("스프링");
		memberRepository.save(member2);

		List<Member> result = memberRepository.findAll();
		Assertions.assertThat(result.size()).isEqualTo(2);
	}
}
