package com.group6.MoM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.MoM.entity.User;
import com.group6.MoM.entity.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer>{
	public Volunteer findByUser(User user);
}
