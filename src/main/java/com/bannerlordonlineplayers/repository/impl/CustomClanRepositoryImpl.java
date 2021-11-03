package com.bannerlordonlineplayers.repository.impl;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.model.Player;
import com.bannerlordonlineplayers.repository.custom.CustomClanRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/*
 * @author Oleksandr Kononiuk
 */

@Repository
public class CustomClanRepositoryImpl implements CustomClanRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Clan findByName(String name) {
        return em.createQuery("SELECT c FROM Clan c WHERE c.name = :name", Clan.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    private Clan findById(long id) {
        return em.find(Clan.class, id);
    }

    @Override
    public Clan update(long id, Clan clan) {
        Clan c = findById(id);

        if (c != null) {
            c.setRelation(clan.getRelation());
            c.setName(clan.getName());
            updateMemberList(c.getMembers(), clan.getMembers());

            return em.merge(c);
        }
        return null;
    }

    private void updateMemberList(List<Player> current, List<Player> updated) {
        //todo
        System.out.println("Members arn`t updated!!!");
    }

    public List<Clan> findAllByName(String name, Pageable pageable) {
        TypedQuery<Clan> typedQuery = em.createQuery("SELECT c FROM Clan c WHERE c.name LIKE :name", Clan.class)
                                        .setParameter("name", "%" + name + "%");

        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        return typedQuery.getResultList();
    }

////    @Override
////    public boolean addMember(String clanName, String[] playerIdOrName) {
////        Clan clan = findByName(clanName);
////        Player player = playerDAO.getPlayer(playerDAO.buildStringFromArgs(playerIdOrName));
////
////        return JpaUtil.performReturningWithinPersistenceContext(
////                em -> {
////                    Clan mergedClan = em.merge(clan);
////                    Player mergedPlayer = em.merge(player);
////                    mergedPlayer.setClanLeader(false);
////
////                    int oldSize = mergedClan.getMembers().size();
////                    mergedClan.addMember(mergedPlayer);
////                    int newSize = mergedClan.getMembers().size();
////                    return oldSize == newSize;
////                }
////        );
//        return false;
//    }
//
//    @Override
//    public boolean deleteMember(String clanName, long id) {
////        Clan clan = findByName(clanName);
////        Player player = playerDAO.getPlayer(Long.toString(id));
////
////        return JpaUtil.performReturningWithinPersistenceContext(
////                em -> {
////                    Clan mergedClan = em.merge(clan);
////                    Player mergedPlayer = em.merge(player);
////                    mergedPlayer.setClanLeader(false);
////
////                    int oldSize = mergedClan.getMembers().size();
////                    mergedClan.deleteMember(mergedPlayer);
////                    int newSize = mergedClan.getMembers().size();
////                    return oldSize == newSize;
////                }
////        );
//        return false;
//    }
//
//    public List<Player> getMembers(String clanName) {
//        return findByName(clanName).getMembers();
//    }
//
//    @Override
//    public String getLeader(String clanName) {
//        return getMembers(clanName).stream()
//                .filter(Player::isClanLeader)
//                .map(Player::getName)
//                .findFirst()
//                .orElseThrow(NullPointerException::new);
//    }
//
//    @Override
//    public boolean changeLeader(String clanName, long oldId, long newId) {
////        Clan clan = findByName(clanName);
////
////        return JpaUtil.performReturningWithinPersistenceContext(
////                em -> {
////                    Clan mergedClan = em.merge(clan);
////
////                    mergedClan.getMembers().stream()
////                            .filter(player -> player.isClanLeader() && player.getId() == oldId)
////                            .findFirst()
////                            .orElseThrow(() -> new NoSuchElementException("Лидер клана не найден в списке мемберов."))
////                            .setClanLeader(false);
////
////                    mergedClan.getMembers().stream()
////                            .filter(player -> player.getId() == newId)
////                            .findFirst()
////                            .orElseThrow(() -> new NoSuchElementException("Кандидат в лидера клана не найден в списке мемберов."))
////                            .setClanLeader(true);
////                    return true;
////                }
////        );
//        return false;
//    }
//
//    @Override
//    public boolean setRelation(String clanName, int relation) {
////        if (relation < 0 || relation > 2) throw new IllegalArgumentException();
////
////        Clan clan = findByName(clanName);
////        return JpaUtil.performReturningWithinPersistenceContext(
////                em -> {
////                    Clan merged = em.merge(clan);
////                    merged.setRelation(relation);
////                    return true;
////        });
//        return false;
//    }
//
//    @Override
//    public Map<Integer, List<Clan>> buildDiplomacy() {
////        List<Clan> clans = JpaUtil.performReturningWithinPersistenceContext(
////                em -> em.createQuery("select c from Clan c", Clan.class).getResultList()
////        );
////
////        return clans.stream()
////                .collect(Collectors.groupingBy(Clan::getRelation));
//        return null;
//    }
}
