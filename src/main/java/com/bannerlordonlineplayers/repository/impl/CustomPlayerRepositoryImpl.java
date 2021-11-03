package com.bannerlordonlineplayers.repository.impl;

import com.bannerlordonlineplayers.model.Player;
import com.bannerlordonlineplayers.repository.custom.CustomPlayerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *@author  Oleksandr Kononiuk
 */

@Repository
public class CustomPlayerRepositoryImpl implements CustomPlayerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Player findByName(String name) {
        return em.createQuery("SELECT p FROM Player p WHERE p.name LIKE :name", Player.class)
                .setParameter("name", "%" + name + "%")
                .getSingleResult();
    }

    @Override
    public Player update(Long id, Player player) {
        //Player forumPlayer = dataUtils.getNewPlayer(Long.toString(playerId));
        Player dbPlayer = em.find(Player.class, id);

        if (dbPlayer != null) {
            dbPlayer.setName(player.getName());
            dbPlayer.setArmy(player.getArmy());
            dbPlayer.setClanLeader(player.isClanLeader());
            dbPlayer.setTwink(player.isTwink());
            dbPlayer.setProfileLink(player.getProfileLink());
            //dbPlayer.setClan(clanRepository.findByName(player.getClan())); //todo maybe DTO
            dbPlayer.getNameHistory().add(player.getName());

            return em.merge(dbPlayer);
        }
        return null;

//        if (forumPlayer == null && dbPlayer != null) throw new NullPointerException("Player not found in forum but he is in DB.");
//        if (forumPlayer != null && dbPlayer == null) {
//            save(Long.toString(playerId));
//            return true;
//        }
//        if (dbPlayer == null && forumPlayer == null) throw new NullPointerException("Player not found in DB and forum.");
//
//        String newName = forumPlayer.getTempName();
//        String oldName = dbPlayer.getTempName();
//        return JpaUtil.performReturningWithinPersistenceContext(
//                em -> {
//                    Player merged = em.merge(dbPlayer);
//                    merged.setTempName(newName);
//
//                    if (!merged.getOldNames().contains(oldName))
//                        merged.getOldNames().add(oldName);
//                    return true;
//                }
//        );
    }

    @Override
    public List<Player> findAllByName(String name, Pageable pageable) {
        TypedQuery<Player> typedQuery = em.createQuery("SELECT p FROM Player p WHERE p.name LIKE :name", Player.class)
                .setParameter("name", "%" + name + "%");

        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        return typedQuery.getResultList();
    }

    //    @Override
//    public Clan getClan(String[] playerIdOrName) {
//        String s = buildStringFromArgs(playerIdOrName);
//        Player player = getPlayer(s);
//        return player.getClan();
//    }
//
//    @Override
//    public boolean isClanLeader(String[] playerIdOrName) {
//        String s = buildStringFromArgs(playerIdOrName);
//        Player player = getPlayer(s);
//        return player.isClanLeader();
//    }
//
//    @Override
//    public boolean isTwink(String[] playerIdOrName) {
//        String s = buildStringFromArgs(playerIdOrName);
//        Player player = getPlayer(s);
//        return player.isTwink();
//    }
//
//    @Override
//    public boolean changeName(Long playerId, String[] newNameArray) {
//        String newName = buildStringFromArgs(Arrays.copyOfRange(newNameArray, 1, newNameArray.length));
//        Player old = findById(playerId);
//        String oldName = old.getTempName();
//
//        return JpaUtil.performReturningWithinPersistenceContext(
//                em -> {
//                    Player merged = em.merge(old);
//                    merged.setTempName(newName);
//
//                    if (!merged.getOldNames().contains(oldName))
//                        merged.getOldNames().add(oldName);
//                    return newName.equals(merged.getTempName());
//                }
//        );
//    }
//
//    @Override
//    public boolean changeClan(Long playerId, String newClanName) {
//        Player player = findById(playerId);
//        String oldClanName = (player.getClan() == null ? "" : player.getClan().getClanName());
//        Clan newClan = JpaUtil.performReturningWithinPersistenceContext(
//                em -> em.createQuery("select c from Clan c where c.clan_name = :clanName", Clan.class)
//                        .setParameter("clanName", newClanName)
//                        .getSingleResult()
//        );
//        player.setClan(newClan);
//        player.setClanLeader(false);
//        update(player);
//
//        return oldClanName.equals(player.getClan().getClanName());
//    }
//
//    @Override
//    public boolean setArmy(int army, String[] playerIdOrName) {
//        if (army < 0) throw new IllegalArgumentException();
//
//        String s = buildStringFromArgs(Arrays.copyOfRange(playerIdOrName, 1, playerIdOrName.length));
//        Player player = getPlayer(s);
//
//        return JpaUtil.performReturningWithinPersistenceContext(
//                em -> {
//                    Player merged = em.merge(player);
//                    merged.setArmy(army);
//                    return true;
//                }
//        );
//    }
//
//    @Override
//    public boolean setClanLeader(boolean status, String[] nameArray) {
//        String s = buildStringFromArgs(Arrays.copyOfRange(nameArray, 1, nameArray.length));
//        Player player = getPlayer(s);
//        if (player.getClan() != null) {
//            boolean oldStatus = player.isClanLeader();
//            player.setClanLeader(status);
//            Player updatedPlayer = update(player);
//
//            return oldStatus == updatedPlayer.isClanLeader();//true не змінилось
//        } else
//            return true;
//    }
//
//    @Override
//    public boolean setTwink(boolean status, String[] nameArray) {
//        String s = buildStringFromArgs(Arrays.copyOfRange(nameArray, 1, nameArray.length));
//        Player player = getPlayer(s);
//        boolean oldStatus = player.isTwink();
//        player.setTwink(status);
//        Player updatedPlayer = update(player);
//
//        return oldStatus == updatedPlayer.isTwink();//true не змінилось
//    }
//
//    private Player update(Player player) {
//        return JpaUtil.performReturningWithinPersistenceContext(
//                em -> em.merge(player)
//        );
//    }
//
//
//
//    public String buildStringFromArgs(String[] array) {
//        String result = "";
//
//        for (String s : array) {
//            result = result.concat(s);
//            result = result.concat(" ");
//        }
//        System.out.println("buildStringFromArgs " + result);
//        return result.trim();
//    }
//
//    private boolean isId(String playerIdOrName) {
//        if (playerIdOrName.matches("\\D")) { //contain any non-digit character
//            return false;
//        }
//        return playerIdOrName.matches("^\\d+$"); //is contain digit character with 1+ length
//    }
//
//    public Player getPlayer(String playerIdOrName) {
//        Player player;
//        if (isId(playerIdOrName)) {
//            player = findById(Long.parseLong(playerIdOrName));
//        } else {
//            player = findByTempName(playerIdOrName);
//        }
//        return player;
//    }
//
//    private Player findById(long id) {
//        Player player;
//        player = JpaUtil.performReturningWithinPersistenceContext(
//                em -> em.createQuery("select p from Player p where p.id = :id", Player.class)
//                        .setParameter("id", id)
//                        .getSingleResult()
//        );
//        return player;
//    }
//
//    private Player findByTempName(String tempName) {
//        Player player;
//        player = JpaUtil.performReturningWithinPersistenceContext(
//                em -> em.createQuery("select p from Player p where p.temp_name = :tempName", Player.class)
//                        .setParameter("tempName", tempName)
//                        .getSingleResult()
//        );
//        return player;
//    }
}
