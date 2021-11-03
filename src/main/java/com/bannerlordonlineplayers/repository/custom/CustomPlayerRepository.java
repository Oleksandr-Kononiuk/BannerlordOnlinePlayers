package com.bannerlordonlineplayers.repository.custom;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.model.Player;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomPlayerRepository {

    Player findByName(String name);
    Player update(Long id, Player player);
    List<Player> findAllByName(String name, Pageable pageable);

//    Clan getClan(String[] playerIdOrName);
//    boolean isClanLeader(String[] playerIdOrName);
//    boolean isTwink(String[] playerIdOrName);
//    boolean setClanLeader(boolean status, String[] playerIdOrName);
//    boolean setTwink(boolean status, String[] playerIdOrName);
//    boolean changeName(Long playerId, String[] newName);
//    boolean changeClan(Long playerId, String newClan);
//    boolean setArmy(int army, String[] playerIdOrName);
}
