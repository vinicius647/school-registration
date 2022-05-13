package com.project.school.schoolregistration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class BackendUser extends User {

	private static final long serialVersionUID = 1L;

	public BackendUser(String userName, String password, Collection<? extends GrantedAuthority> authorities) {
		super(userName, password, authorities);
	}

}
