package com.sg.facturacion.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sg.facturacion.models.UserLogin;
import com.sg.facturacion.models.UserPrincipal;
import com.sg.facturacion.repositories.UserloginRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired UserloginRepository userLoginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserLogin user = userLoginRepository.findByUsername(username);	
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new UserPrincipal(user);
		
		
		
	}

}
