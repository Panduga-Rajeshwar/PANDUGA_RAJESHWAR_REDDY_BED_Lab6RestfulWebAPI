package com.greatlearning.studentmanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.greatlearning.studentmanagement.entity.Role;
import com.greatlearning.studentmanagement.entity.User;

public class UserDecorator implements UserDetails {

	public User user;

	public UserDecorator(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;

	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		LocalDate accExpiryDate = user.getAccountExpiryDate();
		LocalDate todaysDate = LocalDate.now();
		if (accExpiryDate.isAfter(todaysDate))
			return true;
		else
			return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		int accLockedStatus = user.getAccountLockedStatus();
		if (accLockedStatus == 1)
			return true;
		else
			return false;

	}

	@Override
	public boolean isCredentialsNonExpired() {
		LocalDate credentialExpiryDate = user.getCredentialsExpiryDate();
		LocalDate todaysDate = LocalDate.now();
		if (credentialExpiryDate.isAfter(todaysDate))
			return true;
		else
			return false;
	}

	@Override
	public boolean isEnabled() {
		int accEnabledStatus = user.getAccountEnabledStatus();
		if (accEnabledStatus == 1)
			return true;
		else
			return false;
	}
}
