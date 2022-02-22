package com.bannerlordonlineplayers.controller;

import com.bannerlordonlineplayers.UnitTestBase;
import com.bannerlordonlineplayers.model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.bannerlordonlineplayers.PlayerTestData.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleksandr Kononiuk
 * 09.02.2022
 */
class PlayerControllerTest extends UnitTestBase {

    @Autowired
    private PlayerController controller;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByName() {
        Player actual = controller.find("Storm");

        assertEquals(player1.getName(), actual.getName());
        assertEquals(player1.getId(), actual.getId());
        assertEquals(player1.getClan(), actual.getClan());
        assertEquals(player1.getArmy(), actual.getArmy());
    }

    @Test
    void getAll() {
        List<Player> actual = controller.getAll();

        assertEquals(players.size(), actual.size());
        //todo add collection comparing
    }

    @Test
    void getAllByFilter() {
        List<Player> actual = controller.getAllByFilter("Storm", PlayerOrder.NAME, 0, 10);

        assertEquals(1, actual.size());
        assertEquals(List.of(controller.find("Storm")), actual);
        //todo add collection comparing
    }

    @Test
    void save() {
        Player neww = getNew();
        Player saved = controller.save(neww.getProfileLink());

        assertEquals(neww.getName(), saved.getName());
        assertEquals(neww.getId(), saved.getId());
        //assertEquals(neww.getMembers().size(), saved.getMembers().size());
    }

    @Test
    void delete() {
        List<Player> before = controller.getAll();
        controller.delete(1L);
        List<Player> after = controller.getAll();
        assertEquals(before.size() - 1, after.size());
    }

    @Test
    void update() {
        Player before = controller.find("Storm");
        assertNotEquals(before.getName(), getUpdated().getName());
        assertNotEquals(before.getClan(), getUpdated().getClan());
        assertNotEquals(before.getArmy(), getUpdated().getArmy());
        assertTrue(before.isClanLeader());
        assertFalse(before.isTwink());

        Player after = controller.update(1L, getUpdated());
        assertEquals(getUpdated().getName(), after.getName());
        assertEquals(getUpdated().getClan(), after.getClan());
        assertEquals(getUpdated().getArmy(), after.getArmy());
        assertFalse(after.isClanLeader());
        assertTrue(after.isTwink());
    }
}