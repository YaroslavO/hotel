package com.yaroslav.hotel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by employee on 6/15/15.
 */
@Configuration
@Import(DatabaseConfigTest.class)
public class AppConfigTest {
}