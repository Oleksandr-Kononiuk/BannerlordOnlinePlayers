package com.bannerlordonlineplayers.model;

import com.bannerlordonlineplayers.util.DataUtils;
import com.bannerlordonlineplayers.util.ViewUtils;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

/**
 * @author Oleksandr Kononiuk
 */

@Entity
@Table(name = "players")
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Player {

    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "clan")
    @JsonIgnoreProperties(value="members")
    private Clan clan;

    @Column(name = "army", nullable = false, columnDefinition = "int default 0")
    private Integer army = 0;

    @Column(name = "is_clan_leader", columnDefinition = "int default false")
    private boolean is_clan_leader = false;

    @Column(name = "is_twink", columnDefinition = "int default false")
    private boolean is_twink = false;

    @Column(name = "profile_link", nullable = false)
    private String profile_link;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "name_history",
            joinColumns = @JoinColumn(name = "id"))
    private Set<String> nameHistory = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public boolean isClanLeader() {
        return is_clan_leader;
    }

    public void setClanLeader(boolean clanLeader) {
        is_clan_leader = clanLeader;
    }

    public boolean isTwink() {
        return is_twink;
    }

    public void setTwink(boolean twink) {
        is_twink = twink;
    }

    public String getProfileLink() {
        return profile_link;
    }

    public void setProfileLink(String profileLink) {
        this.profile_link = profileLink;
    }

    public Integer getArmy() {
        return army;
    }

    public void setArmy(int army) {
        this.army = army;
    }

    public Set<String> getNameHistory() {
        return nameHistory;
    }

    public void setNameHistory(Set<String> nameHistory) {
        this.nameHistory = nameHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return id == player.getId();
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.intValue();
    }

    @Override
    public String toString() {
        return "```css\n" +
                "id: '" + id + "'" +
                ", Никнейм '" + name + "'" +
                ", Клан '" + (clan != null ? clan.getName() : "отсуствует") + "'" +
                ", Размер отряда '" + army + "'" +
                ", Лидер клана '" + (is_clan_leader ? "Да" : "Нет") + "'" +
                ", Твинк '" + (is_twink ? "Да" : "Нет") + "'" +
                ", Известен как '" + Arrays.toString(nameHistory.toArray()) + "'" +
                ", Ссылка на профиль '" + profile_link + "'" +
                "```";
    }

    public String toClanMemberString() {
        return "id '" + id + "'" +
                ", Актуальный ник '" + name + "'" +
                ", Лидер клана '" + (is_clan_leader ? "Да" : "Нет") + "'" +
                ", Размер отряда '" + army + "'" +
                ", Твинк '" + (is_twink ? "Да" : "Нет") + "'";
    }
}
