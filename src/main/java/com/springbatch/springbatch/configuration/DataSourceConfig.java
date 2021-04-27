package com.springbatch.springbatch.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

//    @Bean
//    public DataSource springDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
//        dataSourceBuilder.url("jdbc:mysql://localhost:3306/test");
//        dataSourceBuilder.username("root");
//        dataSourceBuilder.password("");
//        return dataSourceBuilder.build();
//    }

    @Bean
    public DataSource springDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("oracle.jdbc.driver.OracleDriver");
        dataSourceBuilder.url("jdbc:oracle:thin:@10.129.195.104:1521:GFDASHQA");
        dataSourceBuilder.username("openrisow");
        dataSourceBuilder.password("Viv0#202l");
        return dataSourceBuilder.build();
    }

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource springDataSource() {
//        return DataSourceBuilder.create().build();
//    }
}
