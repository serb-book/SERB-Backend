package com.serb_backend.dal;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class hiDAO {

    private JdbcTemplate jdbcTemplate;

    public hiDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String sayhi(){
        return jdbcTemplate.queryForObject("SELECT 'connection is working' FROM DUAL", String.class);        
    }

}