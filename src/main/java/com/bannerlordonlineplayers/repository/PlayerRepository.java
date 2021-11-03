package com.bannerlordonlineplayers.repository;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.model.Player;
import com.bannerlordonlineplayers.repository.custom.CustomPlayerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *@author  Oleksandr Kononiuk
 */

public interface PlayerRepository extends JpaRepository<Player, Long>, CustomPlayerRepository {

}
