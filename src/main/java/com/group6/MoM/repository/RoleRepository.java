package com.group6.MoM.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group6.MoM.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{


	@Query( "select r from Role r where r.name in :rolename")
	Role findBySpecificRole(@Param("rolename") String role);
}
