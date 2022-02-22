package com.bannerlordonlineplayers.controller;

import com.bannerlordonlineplayers.model.Player;
import com.bannerlordonlineplayers.repository.PlayerRepository;
import com.bannerlordonlineplayers.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bannerlordonlineplayers.util.Utils.getPagination;

@RestController
@RequestMapping("/players")
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
        Player player = dataUtils.getPlayerByLink(link);
        return repository.save(player);
    }

    @GetMapping("/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Player find(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Player update(@PathVariable Long id, @RequestBody Player player) {
        return repository.update(id, player);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam(name = "id") Long id) {
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
            return repository.findAll(getPagination(order.getFieldName(), pageNumber, pageSize)).getContent();
        } else {
            return repository.findAllByName(name, getPagination(order.getFieldName(), pageNumber, pageSize));
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Player> getAll() {
        return getAllByFilter(null, PlayerOrder.NAME, 0, 10);
    }
}
