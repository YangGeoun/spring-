package com.geonu.study.repository;

import java.util.List;
import java.util.Optional;

import com.geonu.study.domain.Member;

public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findByName(String username);
	Optional<Member> findById(Long id);
	List<Member> findAll();
}
