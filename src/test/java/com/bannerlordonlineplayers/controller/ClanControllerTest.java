package com.bannerlordonlineplayers.controller;

import com.bannerlordonlineplayers.UnitTestBase;
import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.repository.ClanRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.bannerlordonlineplayers.ClanTestData.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleksandr Kononiuk
 * 09.02.2022
 */

class ClanControllerTest extends UnitTestBase {

    @Autowired
    private ClanController controller;
    @Autowired
    private ClanRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByName() {
        Clan actual = controller.find("DEV");

        assertEquals(clan1.getName(), actual.getName());
        assertEquals(clan1.getId(), actual.getId());
        assertEquals(clan1.getMembers().size(), actual.getMembers().size());
    }

    @Test
    void findById() {
        Optional<Clan> actual = repository.findById(1L);
        assertEquals(clan1.getName(), actual.get().getName());
    }

    @Test
    void getAll() {
        List<Clan> actual = controller.getAll();

        assertEquals(clans.size(), actual.size());
        //todo add collection element assertion
    }

    @Test
    void getAllByFilter() {
        List<Clan> actual = controller.getAllByFilter("DEV", ClanOrder.NAME, 0, 10);

        assertEquals(1, actual.size());
        assertEquals(List.of(controller.find("DEV")), actual);
    }

    @Test
    void save() {
        Clan neww = getNew();
        Clan saved = controller.save(neww.getName());

        assertEquals(neww.getName(), saved.getName());
        assertEquals(neww.getId(), saved.getId());
        //assertEquals(neww.getMembers().size(), saved.getMembers().size());
    }

    @Test
    void delete() {
        List<Clan> before = controller.getAll();
        controller.delete(1L);
        List<Clan> after = controller.getAll();
        assertEquals(before.size() - 1, after.size());
    }

    @Test
    void update() {
        Clan before = controller.find("LS");
        assertNotEquals(before.getName(), getUpdated().getName(), "before name update");
        assertEquals(before.getMembers().size(), 1, "before members update");
        assertEquals(before.getWarList().size(), 0, "before war update");

        Clan updated = getUpdated();
        updated.addWar(clan3);
        updated.addWar(clan4);

        Clan after = controller.update(5L, updated);
        assertEquals(getUpdated().getName(), after.getName(), "after name update");
        assertEquals(after.getMembers().size(), 0, "after members update");
        assertEquals(after.getWarList().size(), 2, "after war update");
    }
}