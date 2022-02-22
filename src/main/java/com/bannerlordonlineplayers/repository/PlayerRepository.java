package com.bannerlordonlineplayers.repository;

import com.bannerlordonlineplayers.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *@author  Oleksandr Kononiuk
 */

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerCustomRepository {

}
