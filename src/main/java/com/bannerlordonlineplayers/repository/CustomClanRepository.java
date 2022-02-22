package com.bannerlordonlineplayers.repository;

import com.bannerlordonlineplayers.model.Clan;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomClanRepository {

    Clan findByName(String clanName);
    List<Clan> findAllByName(String name, Pageable pageable);
    boolean deleteOneById(Long id);
    Clan update(long id, Clan clan);

}
