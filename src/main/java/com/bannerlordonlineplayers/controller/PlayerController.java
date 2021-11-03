package com.bannerlordonlineplayers.controller;

import com.bannerlordonlineplayers.model.Player;
import com.bannerlordonlineplayers.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @PostMapping("/players")
    @ResponseStatus(HttpStatus.OK)
    public Player save(@RequestParam String link) { //todo add save by id too
        return service.save(link);
    }

    @GetMapping("/players/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Player find(@PathVariable String name) {
        return service.find(name);
    }

    @PostMapping("/players/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Player update(@PathVariable Long id, @RequestBody Player player) {
        //add boolean filed: false (default) updating from updateForm (front-end), true updating some data using forum
        return service.update(id, player);
    }

    @DeleteMapping("/players/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/players")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Player> getAll(@RequestParam(required = false) String name,
                               @RequestParam(required = false, defaultValue = "NAME") PlayerOrder order,
                               @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return service.getAll(name, order, pageNumber, pageSize);
    }
}
