package com.bannerlordonlineplayers.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Oleksandr Kononiuk
 * 21.03.2022
 */

@Slf4j
@Controller
public class RootController {

//    @PreAuthorize("hasRole('GUEST')")
    @GetMapping("/players")
    public String greeting() {
        log.info("players page");
        return "players";
    }

    @GetMapping("/clans")
    public String clans() {
        log.info("clans page");
        return "clans";
    }

}
