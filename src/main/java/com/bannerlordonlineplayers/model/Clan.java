package com.bannerlordonlineplayers.model;

import com.bannerlordonlineplayers.util.ViewUtils;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

/**
 *@author  Oleksandr Kononiuk
 */

@Entity
@Table(name = "clans")
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Clan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "clan", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnoreProperties(value={"clan", "name_history"})
    private List<Player> members = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "war_list",
            joinColumns = @JoinColumn(name = "id"))
    private Set<Long> warList = new HashSet<>();

    public Clan(String name) {
        this.name = name;
    }

    //helper methods
    //todo add ****list helper methods
    public void removeMember(Player player) {
        members.remove(player);
        player.setClan(null);
    }

    public void removeMembers() {
        members.forEach(player -> player.setClan(null));
        members = new ArrayList<>();
    }

    public void addMember(Player player) {
        members.add(player);
        player.setClan(this);
    }

    public void addWar(Clan clan) {
        this.getWarList().add(clan.getId());
        clan.getWarList().add(this.getId());
    }

    public void removeWar(Clan clan) {
        this.getWarList().remove(clan.getId());
        clan.getWarList().remove(this.getId());
    }

    public void addRelation(List<Long> list, Long clanId) {

    }

    //
//    @Column(name = "relation", nullable = false, columnDefinition = "int default 0")
//    private Integer relation = 0;           //["Neutral", "War", "Friend", "Alliance"]

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clan)) return false;
        Clan clan = (Clan) o;
        return id == clan.getId() || name.equals(clan.name);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.intValue();
    }

    public Set<Long> getWarList() {
        return warList;
    }

    public void setWarList(Set<Long> warList) {
        this.warList = warList;
    }
}
