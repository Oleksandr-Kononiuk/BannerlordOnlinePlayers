package com.bannerlordonlineplayers.model;

import com.bannerlordonlineplayers.util.validation.HasId;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * @author Oleksandr Kononiuk
 */

@Entity
@Table(name = "players")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Player implements HasId {

    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "clan")
    @JsonIgnoreProperties(value="members")
//    @JsonBackReference
    private Clan clan;

    @Column(name = "army", nullable = false, columnDefinition = "int default 0")
    @Size(max = 150)
    private Integer army = 0;

    @Column(name = "is_clan_leader", columnDefinition = "int default false")
    private boolean is_clan_leader = false;

    @Column(name = "is_twink", columnDefinition = "int default false")
    private boolean is_twink = false;

    @Column(name = "profile_link", nullable = false)
    @NotBlank
    private String profile_link;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "name_history",
            joinColumns = @JoinColumn(name = "id"))
    @Setter(AccessLevel.PRIVATE)
    private Set<String> nameHistory = new HashSet<>();

    public void addNameInHistory(String name) {
        nameHistory.add(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(this.id, player.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.intValue();
    }

    @Override
    public String toString() {
        return "```css\n" +
                "id: '" + id + "'" +
                ", ?????????????? '" + name + "'" +
                ", ???????? '" + (clan != null ? clan.getName() : "????????????????????") + "'" +
                ", ???????????? ???????????? '" + army + "'" +
                ", ?????????? ?????????? '" + (is_clan_leader ? "????" : "??????") + "'" +
                ", ?????????? '" + (is_twink ? "????" : "??????") + "'" +
                ", ???????????????? ?????? '" + Arrays.toString(nameHistory.toArray()) + "'" +
                ", ???????????? ???? ?????????????? '" + profile_link + "'" +
                "```";
    }

    public String toClanMemberString() {
        return "id '" + id + "'" +
                ", ???????????????????? ?????? '" + name + "'" +
                ", ?????????? ?????????? '" + (is_clan_leader ? "????" : "??????") + "'" +
                ", ???????????? ???????????? '" + army + "'" +
                ", ?????????? '" + (is_twink ? "????" : "??????") + "'";
    }
}
