package com.group6.MoM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group6.MoM.entity.User;
import com.group6.MoM.model.Donatur;
import com.group6.MoM.model.Member;
import com.group6.MoM.model.Volunteer;
import com.group6.MoM.repository.DonaturRepository;
import com.group6.MoM.repository.MemberRepository;
import com.group6.MoM.repository.RoleRepository;
import com.group6.MoM.repository.UserRepository;
import com.group6.MoM.repository.VolunteerRepository;

@Service
public class UsersService {

	@Autowired
	private UserRepository ur;
	
	@Autowired
	private MemberRepository mr;
	
	@Autowired
	private VolunteerRepository vr;
	
	@Autowired
	private DonaturRepository dr;
	
	@Autowired
	private RoleRepository rr;
	
	@Autowired
	private PasswordEncoder pwe;
	
	public User nyoba() {
		return ur.findByUsername("samsul345");

	}
	
	public void registerMember( Member member) {
		String encodePaswword = pwe.encode(member.getPassword());
		User savedUser = new User();
		
		savedUser.setEmail(member.getEmail());
		savedUser.setUsername(member.getUsername());
		savedUser.setPassword(encodePaswword);
		savedUser.setRole(rr.findBySpecificRole("member"));
		ur.save(savedUser);
		
		com.group6.MoM.entity.Member savedMember = new com.group6.MoM.entity.Member();
		
		savedMember.setUser(savedUser);
		savedMember.setName(member.getName());
		savedMember.setPhone(member.getPhone());
		savedMember.setAddress(member.getAddress());
		mr.save(savedMember);
	}
	
	public boolean registerVolunteer(Volunteer volunteer) {
	    try {
	        String encodePassword = pwe.encode(volunteer.getPassword());
	        User savedUser = new User();
	        savedUser.setEmail(volunteer.getEmail());
	        savedUser.setUsername(volunteer.getUsername());
	        savedUser.setPassword(encodePassword);
	        savedUser.setRole(rr.findBySpecificRole("volunteer"));
	        ur.save(savedUser);

	        com.group6.MoM.entity.Volunteer savedVolunteer = new com.group6.MoM.entity.Volunteer();
	        savedVolunteer.setUser(savedUser);
	        savedVolunteer.setName(volunteer.getName());
	        savedVolunteer.setAddress(volunteer.getAddress());
	        vr.save(savedVolunteer);

	        return true; 
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public boolean registerDonatur(Donatur donatur) {
	    try {
	        String encodePassword = pwe.encode(donatur.getPassword());
	        User savedUser = new User();
	        savedUser.setEmail(donatur.getEmail());
	        savedUser.setUsername(donatur.getUsername());
	        savedUser.setPassword(encodePassword);
	        savedUser.setRole(rr.findBySpecificRole("donatur"));
	        ur.save(savedUser);

	        com.group6.MoM.entity.Donatur savedDonatur = new com.group6.MoM.entity.Donatur();
	        savedDonatur.setUser(savedUser);
	        savedDonatur.setName(donatur.getName());
	        savedDonatur.setAmount(donatur.getAmount());
	        dr.save(savedDonatur);

	        return true; // Operasi berhasil
	    } catch (Exception e) {
	        return false; // Operasi gagal
	    }
	}

}
