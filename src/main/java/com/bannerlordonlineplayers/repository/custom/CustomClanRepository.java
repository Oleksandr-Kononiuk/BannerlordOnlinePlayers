package com.bannerlordonlineplayers.repository.custom;

import com.bannerlordonlineplayers.model.Clan;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomClanRepository {

    Clan findByName(String clanName);
    Clan update(long id, Clan clan);
    List<Clan> findAllByName(String name, Pageable pageable);
    //    Clan findOneByName(String clanName); //todo test this spring future

//    boolean setRelation(String clanName, int relation);
//    boolean addMember(String clanName, String[] playerIdOrName);
//    boolean deleteMember(String clanName,long id);
//    String getLeader(String clanName);
//    boolean changeLeader(String clanName, long oldId, long newId);
//    Map<Integer, List<Clan>> buildDiplomacy();
}
