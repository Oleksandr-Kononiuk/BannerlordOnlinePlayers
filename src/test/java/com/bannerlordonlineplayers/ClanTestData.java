package com.bannerlordonlineplayers;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.bannerlordonlineplayers.PlayerTestData.*;

/**
 * @author Oleksandr Kononiuk
 * 10.02.2022
 */
public class ClanTestData {
//    public static Clan clan1 = new Clan(1L, "DEV", null, null, Set.of(), null, Set.of());
//    public static Clan clan2 = new Clan(2L, "HUN", null, null, Set.of(), null, Set.of());
//    public static Clan clan3 = new Clan(3L, "FMC", null, null, Set.of(), null, Set.of());
//    public static Clan clan4 = new Clan(4L, "GAL", null, null, Set.of(), null, Set.of());
//    public static Clan clan5 = new Clan(5L, "LS",  null, null, Set.of(), null, Set.of());//3, 4
//    public static Clan clan6 = new Clan(6L, "RS",  null, null, Set.of(), null, Set.of());

    public static Clan clan1 = new Clan(1L, "DEV", null, null, Set.of());
    public static Clan clan2 = new Clan(2L, "HUN", null, null, Set.of());
    public static Clan clan3 = new Clan(3L, "FMC", null, null, Set.of());
    public static Clan clan4 = new Clan(4L, "GAL", null, null, Set.of());
    public static Clan clan5 = new Clan(5L, "LS",  null, null, Set.of());//3, 4
    public static Clan clan6 = new Clan(6L, "RS",  null, null, Set.of());

    public static List<Clan> clans = List.of(clan1, clan2, clan3, clan4, clan5, clan6);

    static {
        clan1.addMembers(player1, player2, player3);
        clan5.addMember(player5);

        clan5.addWar(clan3);
        clan5.addWar(clan4);

//        clan6.addAlliance(clan1);
    }

    public static Clan getNew() {
        return new Clan(7L, "New",  Collections.emptyList(), null, Set.of());
    }

    public static Clan getUpdated() {
        return new Clan(5L, "Updated", Collections.emptyList(), null, Set.of());
    }
}
