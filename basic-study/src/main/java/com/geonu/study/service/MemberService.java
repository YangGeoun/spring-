package com.geonu.study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonu.study.domain.Member;
import com.geonu.study.repository.MemberRepository;
import com.geonu.study.repository.MemoryMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	// 회원가입
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	// 회원 중복 검사
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다");});
	}

	// 전체 회원 조회
	public List<Member> findMemberAll() {
		return memberRepository.findAll();
	}

	// 전체 회원 조회
	public Optional<Member> findOne(Long id) {
		return memberRepository.findById(id);
	}
}
