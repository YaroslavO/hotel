package com.yaroslav.hotel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by employee on 6/5/15.
 */

@Configuration
@ComponentScan(basePackages = "com.yaroslav.hotel")
@Import(DatabaseConfig.class)
public class AppConfig {

}
