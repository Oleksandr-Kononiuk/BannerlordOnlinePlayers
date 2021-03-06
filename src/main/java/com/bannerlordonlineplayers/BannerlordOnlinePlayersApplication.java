package com.bannerlordonlineplayers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackages="com.bannerlordonlineplayers.repository")
@EnableTransactionManagement
@EntityScan(basePackages="com.bannerlordonlineplayers.model")
public class BannerlordOnlinePlayersApplication {

    public static void main(String[] args) {
        SpringApplication.run(BannerlordOnlinePlayersApplication.class, args);
    }

}
