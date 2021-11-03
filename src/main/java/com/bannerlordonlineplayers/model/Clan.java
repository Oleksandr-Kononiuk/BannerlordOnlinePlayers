package com.bannerlordonlineplayers.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *@author  Oleksandr Kononiuk
 */

@Entity
@Table(name = "clans")
public class Clan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "relation", nullable = false, columnDefinition = "int default 0")
    private Integer relation = 0;           //["Neutral", "War", "Friend", "Alliance"]

    @Column(name = "members")
    @OneToMany(mappedBy = "clan", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Player> members = new ArrayList<>();

    public Clan() {
    }

    public Clan(String name) {
        this.name = name;
    }

    //helper methods
    public void deleteMember(Player player) {
        members.remove(player);
        player.setClan(null);
    }

    public void addMember(Player player) {
        members.add(player);
        player.setClan(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long clan_id) {
        this.id = clan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String clanName) {
        this.name = clanName;
    }

    public List<Player> getMembers() {
        return members;
    }

    public void setMembers(List<Player> members) {
        this.members = members;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clan)) return false;
        Clan clan = (Clan) o;
        return name.equals(clan.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
        //return 10;
    }
}
