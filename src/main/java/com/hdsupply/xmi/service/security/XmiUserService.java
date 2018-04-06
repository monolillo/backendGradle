package com.hdsupply.xmi.service.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.hdsupply.xmi.domain.XmiUser;

/**
 * An implementation of UserDetailsService based on JdbcDaoImpl but
 * that reads extended user information into XmiUser
 * 
 * @author julian.nunez
 *
 */
public class XmiUserService extends JdbcDaoImpl {
	
	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		
		return getJdbcTemplate().query(this.getUsersByUsernameQuery(),
				new String[] { username }, new RowMapper<UserDetails>() {
					@Override
					public UserDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String username = rs.getString(1);
						String password = rs.getString(2);
						boolean enabled = rs.getBoolean(3);
						String email 	= rs.getString(4);
						String phone 	= rs.getString(5);
						
						return new XmiUser(username, password, email, phone, enabled, true, true, true,
								AuthorityUtils.NO_AUTHORITIES);
					}

				});		
	}
	
	@Value("${userDetailsService.usersByUsername}")
	public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
		super.setUsersByUsernameQuery(usersByUsernameQueryString);
	}

}
