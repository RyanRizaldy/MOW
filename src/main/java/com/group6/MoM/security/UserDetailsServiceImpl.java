package com.group6.MoM.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group6.MoM.entity.User;
import com.group6.MoM.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository ur;
	
	//using the loadByUsername method, but checking credentials is using email instead of username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = ur.findByUsername(username);
		
		if(user == null) {
            throw new UsernameNotFoundException("User not found");
		}
        return new UserPrincipal(user);
	}

}
