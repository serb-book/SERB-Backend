package com.serb_backend.dal;

import javax.sql.DataSource;

import com.serb_backend.dto.AccountDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOimp /*implements AccountDAO*/{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
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