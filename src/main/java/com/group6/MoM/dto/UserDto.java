package com.group6.MoM.dto;

import com.group6.MoM.entity.Member;
import com.group6.MoM.entity.User;
import com.group6.MoM.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String role;
    private Object roleData;
    private String accessToken;

    public UserDto(User user, String accessToken, Object roleData) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole().getName();
        this.accessToken = accessToken;

        this.roleData = roleData;
    }
}
