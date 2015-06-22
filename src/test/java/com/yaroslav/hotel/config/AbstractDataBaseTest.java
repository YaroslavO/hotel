package com.yaroslav.hotel.config;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by employee on 6/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AppConfigTest.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractDataBaseTest {

}
