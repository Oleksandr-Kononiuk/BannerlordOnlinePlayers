package com.bannerlordonlineplayers.controller;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.service.ClanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClanController {

    private final ClanService service;

    @Autowired
    public ClanController(ClanService service) {
        this.service = service;
    }

    @PostMapping("/clans")
    @ResponseStatus(HttpStatus.OK)
    public Clan save(@RequestParam String name) {
        return service.save(name);
    }

    @GetMapping("/clans/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Clan find(@PathVariable String name) {
        return service.find(name);
    }

    @PostMapping("/clans/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Clan update(@PathVariable Long id, @RequestBody Clan clan) {
        return service.update(id, clan);
    }

    @DeleteMapping("/clans/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/clans")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Clan> getAll(@RequestParam(required = false) String name,
                             @RequestParam(required = false, defaultValue = "NAME") ClanOrder order,
                             @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return service.getAll(name, order, pageNumber, pageSize);
    }
}
