package com.bannerlordonlineplayers.repository;

import com.bannerlordonlineplayers.model.Player;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerCustomRepository {

    Player findByName(String name);
    Player update(Long id, Player p);
    List<Player> findAllByName(String name, Pageable pageable);
    boolean deleteOneById(Long id);
}
