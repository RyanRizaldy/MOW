package com.group6.MoM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group6.MoM.entity.Donatur;

@Repository
public interface DonaturRepository extends JpaRepository<Donatur, Integer> {

}
