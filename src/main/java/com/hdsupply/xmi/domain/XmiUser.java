package com.hdsupply.xmi.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class XmiUser extends User implements UserDetails {
	
	private static final long serialVersionUID = 4491532840921346011L;
	
	private String email;
	private String phone;
	
	public XmiUser(String username, String password, String email, String phone, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, enabled, accountNonExpired, 
				credentialsNonExpired, accountNonLocked, authorities);
		
		this.email = email;
		this.phone = phone;
	}
	
	

	public XmiUser(String username, String password, String email, String phone,
			Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, authorities);
		
		this.email = email;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
