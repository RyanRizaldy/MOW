package com.group6.MoM.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Donatur {

	private String name;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private int amount;
	
}
