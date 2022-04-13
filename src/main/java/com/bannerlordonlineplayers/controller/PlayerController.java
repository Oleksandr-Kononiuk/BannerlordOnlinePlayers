package com.bannerlordonlineplayers.controller;

import com.bannerlordonlineplayers.model.Player;
import com.bannerlordonlineplayers.repository.PlayerRepository;
import com.bannerlordonlineplayers.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bannerlordonlineplayers.util.Utils.getPagination;

@Slf4j
@RestController
@RequestMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

    private final PlayerRepository repository;
    private final DataUtils dataUtils;

    @Autowired
    public PlayerController(PlayerRepository repository, DataUtils dataUtils) {
        this.repository = repository;
        this.dataUtils = dataUtils;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player save(@RequestParam String link) { //todo add save by id too
        log.info("save new player by link " + link);
        Player player = dataUtils.getPlayerByLink(link);
        return repository.save(player);
    }

    @GetMapping("name/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Player find(@PathVariable String name) {
        log.info("find player by name " + name);
        return repository.findByName(name);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Player find(@PathVariable Long id) {
        log.info("find player by id " + id);
        return repository.findById(id).get();
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Player update(@RequestBody Player player) {
        log.info("update player " + player.getId());
        return repository.update(player.getId(), player);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("delete player " + id);
        boolean d = repository.deleteOneById(id);
        System.out.println(d);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Player> getAllByFilter(@RequestParam(required = false) String name,
                                    @RequestParam(required = false, defaultValue = "NAME") PlayerOrder order,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        if (name == null) {
            log.info("find all");
            return repository.findAll(getPagination(order.getFieldName(), pageNumber, pageSize)).getContent();
        } else {
            log.info("find all by name" + name);
            return repository.findAllByName(name, getPagination(order.getFieldName(), pageNumber, pageSize));
        }
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Player> getAll() {
        return getAllByFilter(null, PlayerOrder.NAME, 0, 10);
    }
}
