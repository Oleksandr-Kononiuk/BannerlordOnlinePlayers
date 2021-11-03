package com.bannerlordonlineplayers.service;

import com.bannerlordonlineplayers.controller.PlayerOrder;
import com.bannerlordonlineplayers.model.Player;
import com.bannerlordonlineplayers.repository.PlayerRepository;
import com.bannerlordonlineplayers.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository repository;
    private final DataUtils dataUtils;

    @Autowired
    public PlayerService(PlayerRepository repository, DataUtils dataUtils) {
        this.repository = repository;
        this.dataUtils = dataUtils;
    }

    @Transactional
    public Player save(String link) {
        Player player = dataUtils.getNewPlayer(link);
        return repository.save(player);
    }

    @Transactional(readOnly = true)
    public Player find(String name) {
        return repository.findByName(name);
    }

    @Transactional
    public Player update(Long id, Player player) {
        return repository.update(id, player);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(repository.getById(id));
    }

    @Transactional(readOnly = true)
    public List<Player> getAll(String name, PlayerOrder order, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, order.getFieldName());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        if (name == null) {
            return repository.findAll(pageable).getContent();
        } else {
            return repository.findAllByName(name, pageable);
        }
    }
}
