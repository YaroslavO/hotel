package com.yaroslav.hotel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by employee on 6/15/15.
 */
@Configuration
@ComponentScan(value = {"com.yaroslav.hotel.dao.*", "com.yaroslav.hotel.service.*"})
@Import(DatabaseConfigTest.class)
public class AppConfigTest {
}
