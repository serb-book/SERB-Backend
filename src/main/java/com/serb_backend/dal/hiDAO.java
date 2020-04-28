package com.serb_backend.dal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class hiDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public hiDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String sayhi(){
        return jdbcTemplate.queryForObject("SELECT 'connection is working' FROM DUAL", String.class);        
    }

}