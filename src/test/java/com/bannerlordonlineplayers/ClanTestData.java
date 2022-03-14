package com.bannerlordonlineplayers;

import com.bannerlordonlineplayers.model.Clan;
import com.bannerlordonlineplayers.model.Player;

import java.util.*;

import static com.bannerlordonlineplayers.PlayerTestData.*;

/**
 * @author Oleksandr Kononiuk
 * 10.02.2022
 */
public class ClanTestData {

    public static Clan clan1 = new Clan(1L, "DEV", new ArrayList<>(), null, new HashSet<>(), null, new HashSet<>());
    public static Clan clan2 = new Clan(2L, "HUN", new ArrayList<>(), null, new HashSet<>(), null, new HashSet<>());
    public static Clan clan3 = new Clan(3L, "FMC", new ArrayList<>(), null, new HashSet<>(), null, new HashSet<>());
    public static Clan clan4 = new Clan(4L, "GAL", new ArrayList<>(), null, new HashSet<>(), null, new HashSet<>());
    public static Clan clan5 = new Clan(5L, "LS",  new ArrayList<>(), null, new HashSet<>(), null, new HashSet<>());//3, 4
    public static Clan clan6 = new Clan(6L, "RS",  new ArrayList<>(), null, new HashSet<>(), null, new HashSet<>());

    public static List<Clan> clans = List.of(clan1, clan2, clan3, clan4, clan5, clan6);

    static {
        clan1.addMembers(player1, player2, player3);
        clan5.addMember(player5);

        clan5.addWar(clan3);
        clan5.addWar(clan4);

        clan5.addAlliance(clan1);
    }

    public static Clan getNew() {
        return new Clan(7L, "New",  Collections.emptyList(), null, new HashSet<>(), null, new HashSet<>());
    }

    public static Clan getUpdated() {
        return new Clan(5L, "Updated", Collections.emptyList(), null, new HashSet<>(), null, new HashSet<>());
    }
}
