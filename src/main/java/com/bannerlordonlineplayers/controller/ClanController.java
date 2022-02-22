package com.bannerlordonlineplayers.controller;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bannerlordonlineplayers.util.Utils.getPagination;

@RestController
@RequestMapping("/clans")
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
        Clan neww = new Clan(name);
        return repository.save(neww);
    }

    @GetMapping("/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Clan find(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PostMapping("/clans/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Clan update(@PathVariable Long id, @RequestBody Clan clan) {
        return repository.update(id, clan);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam(name = "id") Long id) {
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
            return repository.findAll(getPagination(order.getFieldName(), pageNumber, pageSize)).getContent();
        } else {
            return repository.findAllByName(name, getPagination(order.getFieldName(), pageNumber, pageSize));
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Clan> getAll() {
        return getAllByFilter(null, ClanOrder.NAME, 0, 10);
    }
}
