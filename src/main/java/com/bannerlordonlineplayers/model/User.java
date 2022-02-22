package com.bannerlordonlineplayers.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.GUEST;

    @Column(name = "login", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @Column(name = "blocked", columnDefinition = "bool default false")
    private boolean blocked = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    @OrderBy("dateTime DESC")
    @JsonManagedReference
    private List<Suggestion> suggestions = new ArrayList<>();
}
