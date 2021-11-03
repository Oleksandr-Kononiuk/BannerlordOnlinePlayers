package com.bannerlordonlineplayers.service;

import com.bannerlordonlineplayers.controller.ClanOrder;
import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClanService {

    private final ClanRepository repository;

    @Autowired
    public ClanService(ClanRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Clan save(String name) {
        return repository.save(new Clan(name));
    }

    @Transactional(readOnly = true)
    public Clan find(String name) {
        return repository.findByName(name);
        //return repository.findOneByName(name);
    }

    @Transactional
    public Clan update(long id, Clan clan) {
        return repository.update(id, clan);
    }

    @Transactional
    public void delete(long id) {
        repository.delete(repository.getById(id));
    }

    @Transactional(readOnly = true)
    public List<Clan> getAll(String name, ClanOrder order, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, order.getFieldName());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        if (name == null) {
            return repository.findAll(pageable).getContent();
        } else {
            return repository.findAllByName(name, pageable);
        }
    }
}
