package com.sigen.api.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl detailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetailsImpl user = (UserDetailsImpl) detailsService.loadUserByUsername(username);
		
		if ( user.getPassword().equals(password) == false || user.isEnabled() == false )
			return new AnonymousAuthenticationToken(null,null,null);
		
		return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals( UsernamePasswordAuthenticationToken.class );
	}
	
	
}
