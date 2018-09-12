package com.lvbin.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.DriverManager;
import java.sql.SQLException;


@SpringBootApplication
@EnableAuthorizationServer
public class AuthenticationApplication {

    @Autowired
    private WebConfig webConfig;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationApplication.class);

    public static void main(String[] args) throws SQLException {

        //initDatabase();

        new SpringApplicationBuilder(AuthenticationApplication.class)
                .run(args);
    }

    /*
    private static void initDatabase() throws SQLException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding("utf-8");
        populator.addScript(new DefaultResourceLoader().getResource("schema.sql"));
        String DBUrl = "jdbc:mysql://127.0.0.1:3306/dangjian?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai";
        populator.populate(DriverManager.getConnection(DBUrl, "root", "Lvbin@48777742"));
        log.info("database init complete");
    }
    */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return webConfig;
    }

}
