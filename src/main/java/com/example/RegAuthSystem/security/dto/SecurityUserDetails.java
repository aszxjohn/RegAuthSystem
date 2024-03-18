package com.example.RegAuthSystem.security.dto;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.orm.entity.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


/**
 * @author John
 * @date 2024/03/13
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String token;
	private int type = 0;
	private String username;
	private String password;
	private String clientNumber;
	private String mainMail;
	private long rootId;

	
	public SecurityUserDetails(Client client,String token) {
		this.type = 0;
		this.token = token;
		this.rootId = client.getClientId();
		this.clientNumber = client.getCustomerNumber();
		this.username = client.getEmail();
		this.password = client.getPassword();
		System.out.println("user  : "+client.getCustomerNumber());
	
	}

	public SecurityUserDetails() {
		
	}

	/**
	 * 取得權限，因簡化系統故此不使用
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO
		return true;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
