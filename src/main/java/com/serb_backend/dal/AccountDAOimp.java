package com.serb_backend.dal;

import javax.sql.DataSource;

import com.serb_backend.dto.AccountDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOimp implements AccountDAO{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AccountDAOimp(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

	@Override
	public void saveAccount(AccountDTO account) {
		// TODO Auto-generated method stub
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(account);

        this.namedParameterJdbcTemplate.update(
            "INSERT INTO ACCOUNT VALUES (:id, :Email, :password, :username, :profilePictureURL, NULL)"
            , namedParameters);
		
	}
	@Override
	public void grantSuperUserPriviliges(Long id) {
		// TODO Auto-generated method stub
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);

        this.namedParameterJdbcTemplate.update(
            "INSERT INTO SUPER_USER  VALUES (:id)", namedParameters);
		
	}
	
///////////////TODO Unimplemented/////////////////

	@Override
	public String getAccessToken(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTemporaryPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void sendRegisteringConfirmationEmail(String token, String email) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createUser(String token) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUser(String tocken, AccountDTO account) {
		// TODO Auto-generated method stub
		
	}
}