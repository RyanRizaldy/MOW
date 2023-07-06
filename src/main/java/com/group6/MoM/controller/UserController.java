package com.group6.MoM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group6.MoM.dto.LoginRequest;
import com.group6.MoM.dto.UserDto;
import com.group6.MoM.entity.User;
import com.group6.MoM.model.Donatur;
import com.group6.MoM.model.Member;
import com.group6.MoM.model.Volunteer;
import com.group6.MoM.repository.MemberRepository;
import com.group6.MoM.repository.UserRepository;
import com.group6.MoM.security.JwtTokenProvider;
import com.group6.MoM.security.UserDetailsServiceImpl;
import com.group6.MoM.service.UsersService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UsersService us;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	MemberRepository mr;
	
	@Autowired
    PasswordEncoder pwe;
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
    
	@Autowired
	private JwtTokenProvider jwtToken;
	
	@PostMapping("/new_member")
	public ResponseEntity<String> registerMember(@RequestBody Member member){
		us.registerMember(member);
		return ResponseEntity.ok("berhasil menambahkan");
	}
	
	@PostMapping("/new_volunteer")
	public ResponseEntity<String> registerVolunteer(@RequestBody Volunteer volunteer){
		boolean success = us.registerVolunteer(volunteer);

	    if (success) {
	        return ResponseEntity.ok("Berhasil menambahkan");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal menambahkan");
	    }
	}
	
	
	@PostMapping("/new_donatur")
	public ResponseEntity<String> registerDonatur(@RequestBody Donatur donatur) {
	    boolean success = us.registerDonatur(donatur);

	    if (success) {
	        return ResponseEntity.ok("Berhasil menambahkan");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal menambahkan");
	    }
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest login){
		
		User user;
    	UserDetails userDetails;
    	UserDto dto = null;
		//login with email
		user = ur.findByEmail(login.getUsernameOrEmail());
		
		if(user != null) {
			userDetails  = userDetailsService.loadUserByUsername(user.getUsername());
		}else {
			userDetails  = userDetailsService.loadUserByUsername(login.getUsernameOrEmail());
			user = ur.findByUsername(login.getUsernameOrEmail());	
		}
		
		if(!pwe.matches(login.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username atau password salah");
		}
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, login.getPassword(),userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtToken.generateToken(authentication);
        if(user.getRole().getName().equals("member")) {
        	com.group6.MoM.entity.Member roleData = mr.findByUser(user);
            dto = new UserDto(user, token, roleData);
        }
		return ResponseEntity.ok(dto);
	}
	
//	@GetMapping("/test")
//	public com.group6.MoM.entity.Member testAja() {
//		User user = ur.findByUsername("dayatoyoy");
//		return mr.findByUser(user);
//	}
}
