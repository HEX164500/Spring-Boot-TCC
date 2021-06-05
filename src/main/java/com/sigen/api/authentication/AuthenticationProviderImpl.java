package com.sigen.api.authentication;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl detailsService;

	private static final AnonymousAuthenticationToken anonymousToken = new AnonymousAuthenticationToken("unknow",
			"unknow", Arrays.asList(new SimpleGrantedAuthority("Anonymous")));

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetailsImpl user;
		try {
			user = (UserDetailsImpl) detailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			return anonymousToken;
		}

		if (user.getPassword().equals(password) == false || user.isEnabled() == false)
			return anonymousToken;

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
		
		token.setDetails(user);
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
