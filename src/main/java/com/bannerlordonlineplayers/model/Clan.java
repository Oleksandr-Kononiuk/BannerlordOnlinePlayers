package com.bannerlordonlineplayers.model;

import com.bannerlordonlineplayers.util.validation.HasId;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

/**
 * @author Oleksandr Kononiuk
 */

@Entity
@Table(name = "clans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clan implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "clan", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnoreProperties(value = {"clan", "nameHistory", "profile_link"})
//    @JsonManagedReference
    @Setter(AccessLevel.PRIVATE)
    private List<Player> members = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "war_id")
    private Clan inWar;

    @OneToMany(mappedBy = "inWar")
    @Setter(AccessLevel.PRIVATE)
    private Set<Clan> warList = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alliance_id")
    private Clan inAlliance;

    @OneToMany(mappedBy = "inAlliance")
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnoreProperties(value = {"members", "allianceList", "warList", "inWar", "inAlliance"})
    private Set<Clan> allianceList = new HashSet<>();

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

    public void addMembers(Player... players) {
        members.addAll(List.of(players));
        List.of(players).forEach(player -> player.setClan(this));
    }

    public void addWar(Clan clan) {
        if (clan == null) return;

        this.warList.add(clan);
        clan.getWarList().add(this);
    }

    public void removeWar(Clan clan) {
        if (clan == null) return;

        this.warList.remove(clan);
        clan.getWarList().remove(this);
    }

    public void addAlliance(Clan clan) {
        if (clan == null) return;

        this.allianceList.add(clan);
        clan.getAllianceList().add(this);
    }

    public void removeFromAlliance(Clan clan) {
        if (clan == null) return;

        this.allianceList.remove(clan);
        clan.getAllianceList().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clan)) return false;
        Clan clan = (Clan) o;
        return Objects.equals(this.id, clan.getId()) || name.equals(clan.name);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.intValue();
    }

}
