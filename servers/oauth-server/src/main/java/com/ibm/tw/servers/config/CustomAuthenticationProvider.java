package com.ibm.tw.servers.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	public static final String DETAILS_ROLE_KEY = "role";
	public static final String DEFAULT_ROLE_GRANTED = "ROLE_SME";
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		@SuppressWarnings({"rawtypes", "unchecked"})
		LinkedHashMap<String, String> details = (LinkedHashMap) authentication.getDetails();
		String authority = details.containsKey(DETAILS_ROLE_KEY) ? details.get(DETAILS_ROLE_KEY) : DEFAULT_ROLE_GRANTED;
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority auth = new SimpleGrantedAuthority(authority);
		authorities.add(auth);
		return new UsernamePasswordAuthenticationToken(name, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
