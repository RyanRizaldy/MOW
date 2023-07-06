package com.group6.MoM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.MoM.entity.Member;
import com.group6.MoM.entity.User;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{

	public Member findByUser(User user);
}
