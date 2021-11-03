package com.bannerlordonlineplayers.repository;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.repository.custom.CustomClanRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

/**
 *@author  Oleksandr Kononiuk
 */

public interface ClanRepository extends JpaRepository<Clan, Long>, CustomClanRepository {

}
