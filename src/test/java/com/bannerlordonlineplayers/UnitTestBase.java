package com.bannerlordonlineplayers;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Oleksandr Kononiuk
 * 09.02.2022
 */
@Sql("/test.sql")
//@ActiveProfiles
@SpringBootTest
@Transactional
public abstract class UnitTestBase {

    @BeforeAll
    static void init() {

    }
}
