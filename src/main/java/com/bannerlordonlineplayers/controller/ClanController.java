package com.bannerlordonlineplayers.controller;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.model.Player;
import com.bannerlordonlineplayers.repository.ClanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bannerlordonlineplayers.util.Utils.getPagination;

@Slf4j
@RestController
@RequestMapping(value = "/clans", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClanController {

    private final ClanRepository repository;

    @Autowired
    public ClanController(ClanRepository clanRepository) {
        this.repository = clanRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Clan save(@RequestParam String name) {
        log.info("save new clan " + name);
        Clan neww = new Clan(name);
        return repository.save(neww);
    }

    @GetMapping("/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Clan find(@PathVariable String name) {
        log.info("find clan " + name);
        return repository.findByName(name);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Clan update(@RequestBody Clan clan) {
        log.info("update clan " + clan.getId());
        return repository.update(clan.getId(), clan);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("delete clan " + id);
        boolean a = repository.deleteOneById(id);
        System.out.println(a);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Clan> getAllByFilter(@RequestParam(required = false) String name,
                             @RequestParam(required = false, defaultValue = "NAME") ClanOrder order,
                             @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        if (name == null) {
            log.info("get all");
            return repository.findAll(getPagination(order.getFieldName(), pageNumber, pageSize)).getContent();
        } else {
            log.info("get all by name " + name);
            return repository.findAllByName(name, getPagination(order.getFieldName(), pageNumber, pageSize));
        }
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Clan> getAll() {
        return getAllByFilter(null, ClanOrder.NAME, 0, 10);
    }
}
