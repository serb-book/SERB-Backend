package com.serb_backend.dal;

import javax.sql.DataSource;

import com.serb_backend.dto.AccountDTO;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class AccountDAOimp /*implements AccountDAO*/{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountDAOimp(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    public void save(AccountDTO account){
        
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(account);

        this.namedParameterJdbcTemplate.update(
            "INSERT INTO ACCOUNT VALUES (:id, :Email, :password, :username, :profilePictureURL, NULL)"
            , namedParameters);
    }
}