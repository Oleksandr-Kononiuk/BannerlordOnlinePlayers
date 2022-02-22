package com.bannerlordonlineplayers.repository;

import com.bannerlordonlineplayers.model.Clan;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Oleksandr Kononiuk
 */

@Repository
@Transactional(readOnly = true)
public class CustomClanRepositoryImpl implements CustomClanRepository {

    @PersistenceContext
    private EntityManager em;

    private static final String FIND_BY_NAME_JPQL = "SELECT c FROM Clan c WHERE c.name = :name";
    private static final String FIND_ALL_BY_NAME_JPQL = "SELECT c FROM Clan c WHERE c.name LIKE :name";

    @Override
    public Clan findByName(String name) {
        return em.createQuery(FIND_BY_NAME_JPQL, Clan.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    @Transactional
    public Clan update(long id, Clan clan) {
        if (clan.getId() == null) {
            clan.setId(id);
            em.persist(clan);
            return clan;
        } else {
            return em.merge(clan);
        }
    }

    @Override
    public List<Clan> findAllByName(String name, Pageable pageable) {
        TypedQuery<Clan> typedQuery = em.createQuery(FIND_ALL_BY_NAME_JPQL, Clan.class)
                                        .setParameter("name", "%" + name + "%");

        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public boolean deleteOneById(Long id) {
        Clan clan = em.find(Clan.class, id);
        if (clan == null) return false;

        clan.removeMembers();
        em.remove(clan);
        return true;
    }
}
