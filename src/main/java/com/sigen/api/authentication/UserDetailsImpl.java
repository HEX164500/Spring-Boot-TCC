package com.sigen.api.authentication;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sigen.api.entities.Usuario;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Boolean active;
	private final List<SimpleGrantedAuthority> authorities;

	public UserDetailsImpl(Usuario user) {
		this.username = user.getEmail();
		this.password = user.getSenha();
		this.active = user.isAtivo();
		authorities = Arrays.asList(new SimpleGrantedAuthority(user.getAcesso().toString()),
				new SimpleGrantedAuthority(user.getId().toString()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return active;
	}

	@Override
	public boolean isAccountNonLocked() {
		return active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return active;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
