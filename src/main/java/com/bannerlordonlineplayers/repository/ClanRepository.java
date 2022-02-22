package com.bannerlordonlineplayers.repository;

import com.bannerlordonlineplayers.model.Clan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *@author  Oleksandr Kononiuk
 */

public interface ClanRepository extends JpaRepository<Clan, Long>, CustomClanRepository {

}
