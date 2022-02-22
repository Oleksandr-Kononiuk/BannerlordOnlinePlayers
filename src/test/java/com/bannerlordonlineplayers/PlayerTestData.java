package com.bannerlordonlineplayers;

import com.bannerlordonlineplayers.model.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.bannerlordonlineplayers.ClanTestData.*;

/**
 * @author Oleksandr Kononiuk
 * 10.02.2022
 */
public class PlayerTestData {

    private static final String forumLink = "https://bannerlord-online.com/forum/index.php?members";

    public static Player player1 = new Player(1L, "Storm",          null, 0, true, false, forumLink.concat("/storm.1/"),         Set.of("SStorm", "STOr"));
    public static Player player2 = new Player(2L, "Slender",        null, 0, false, false, forumLink.concat("slender.2/"),        Set.of("Tester", "Best Slender"));
    public static Player player3 = new Player(3L, "moonlight.exe",  null, 0, false, false, forumLink.concat("/moonlight-exe.3/"), Set.of("Moonchik"));
    public static Player player4 = new Player(4L, "Вячеслав",       null, 0, false, false, forumLink.concat("/Вячеслав.4/"),      new HashSet<>());
    public static Player player5 = new Player(5L, "Vyacheslav",     null, 0, false, false, forumLink.concat("/vyacheslav.5/"),    new HashSet<>());
    public static Player player6 = new Player(6L, "Линбоу",         null, 0, false, false, forumLink.concat("/Линбоу.6/"),        new HashSet<>());
    public static Player player7 = new Player(7L, "Nekroch",        null, 0, false, false, forumLink.concat("/nekroch.7/"),       new HashSet<>());
    public static Player player8 = new Player(10L, "klimsat",        null, 0, false, false, forumLink.concat("/klimsat.10/"),      new HashSet<>());

    public static List<Player> players = List.of(player1, player2, player3, player4, player5, player6, player7, player8);

    static {
        player1.setClan(clan1);
        player2.setClan(clan1);
        player3.setClan(clan1);
        player5.setClan(clan5);
    }

    public static Player getNew() {
        return new Player(12L, "Emtea", null, 0, false, false, forumLink.concat("/emtea.12/"), new HashSet<>());
    }

    public static Player getUpdated() {
        return new Player(1L, "Updated", null, 10, false, true, forumLink.concat("/storm.1/"), new HashSet<>());
    }
}
