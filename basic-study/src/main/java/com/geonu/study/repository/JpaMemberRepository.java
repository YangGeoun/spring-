package com.geonu.study.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.geonu.study.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

// @Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {

	final private EntityManager em;

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findByName(String username) {
		List<Member> member = em.createQuery("select m from Member m where m.name = :name", Member.class)
			.setParameter("name",username)
			.getResultList();
		return member.stream().findAny();
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(em.find(Member.class, id));
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
}
