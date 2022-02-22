package com.bannerlordonlineplayers.repository;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.model.Player;
import com.bannerlordonlineplayers.repository.PlayerCustomRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *@author  Oleksandr Kononiuk
 */

@Repository
@Transactional(readOnly = true)
public class PlayerCustomRepositoryImpl implements PlayerCustomRepository {

    @PersistenceContext
    private EntityManager em;

    private static final String FIND_ALL_BY_NAME_JPQL = "SELECT p FROM Player p WHERE p.name LIKE :name";
    private static final String FIND_BY_NAME_JPQL = "SELECT p FROM Player p WHERE p.name = :name";

    @Override
    public Player findByName(String name) {
        return em.createQuery(FIND_BY_NAME_JPQL, Player.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Player> findAllByName(String name, Pageable pageable) {
        TypedQuery<Player> typedQuery = em.createQuery(FIND_ALL_BY_NAME_JPQL, Player.class)
                .setParameter("name", "%" + name + "%");

        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(pageable.getPageSize());

        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public boolean deleteOneById(Long id) {
        Player player = em.find(Player.class, id);
        if (player == null) return false;

        if (player.getClan() != null) {
            Clan playerClan = player.getClan();
            playerClan.getMembers().remove(player);
            em.merge(playerClan);
        }
        em.remove(player);
        return true;
    }

    @Transactional
    public boolean deleteMemberFromClanById(Long id) {
        Player player = em.find(Player.class, id);
        if (player == null) return false;

        if (player.getClan() != null) {
            Clan playerClan = player.getClan();
            playerClan.getMembers().remove(player);
            em.merge(playerClan);
            player.setClan(null);
        }
        return true;
    }

    @Override
    @Transactional
    public Player update(Long id, Player player) {
        if (player.getId() == null) {
            player.setId(id);
            em.persist(player);
            return player;
        } else {
            return em.merge(player);
        }
    }
    //todo implement method for updating from forum
}
