package com.example.model.Traveling;

import com.example.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor  // POJO class

@Entity
@Table(name = "journey")
public class Journey {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "journey_id")
    Integer id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "isPrivate", nullable = false, columnDefinition = "BOOL default TRUE")
    boolean isPrivate = true;

    // FIXME: Unidirectional MANY-TO-MANY
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "journey_participants",
            joinColumns = @JoinColumn(name = "journey_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "journey_status", nullable = false, columnDefinition = "VARCHAR default 'NONE'")
    private JourneyStatus status = JourneyStatus.NONE;

    //Route? Geolocation

    //Creators?

    //Chat?

    //Tags?

    //Types?
}
