package com.example.model.Traveling;

import com.example.model.User;
import com.example.util.constants.JourneyConst;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@ToString(onlyExplicitlyIncluded = true)
@Setter
@Getter
@NoArgsConstructor  // POJO class

@Entity
@Table(name = "journey")
public class Journey {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "journey_id")
    @ToString.Include
    Integer id;

    @Column(name = "title", nullable = false)
    @ToString.Include
    String title = "";

    @Column(name = "description", nullable = false)
    @ToString.Include
    String description = "";

    @Column(name = "isPrivate", nullable = false,columnDefinition = "BOOL default TRUE")
    @ToString.Include
    Boolean isPrivate = true;

    // FIXME: Unidirectional MANY-TO-MANY how to delete ???
    @ManyToMany(
            cascade = {CascadeType.MERGE},
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
    @ToString.Include
    private JourneyStatus status = JourneyStatus.NONE;

    // Инициализируется в момент отправки в БД ( не в момент сохранения в БД )
    @Column(name = "creation_time", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    @ToString.Include
    private Timestamp creation_time;

    //Route? Geolocation

    //Creators?

    //Chat?

    //Tags?

    //Types?

    public void addParticipants(java.util.Set<User> users){
        this.participants.addAll(users);
    }
    public void addParticipants(User... users){
        this.participants.addAll(Set.of(users));
    }

    public static boolean isValidJourneyId(@NotNull String string){
        try{
            Integer.parseInt(string);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static boolean isValidTitle(@NotNull String title){
        return title.length() >= JourneyConst.title_length_min &&
                title.length() <= JourneyConst.title_length_max &&
                title.matches(JourneyConst.title_validator_regEx);
    }

    public static boolean isValidTitleSearch(@NotNull String title){
        return  title.length() >= 1 &&
                title.length() <= JourneyConst.title_length_max &&
                title.matches(JourneyConst.title_search_validator_regEx);
    }

    public boolean isParticipant(User user){
        for (User participant : this.participants){
            if(participant.equals(user)){
                return true;
            }
        }
        return false;
    }

    public static String title_BlankCorrector(@NotNull String title){
        String new_title = title.strip().replaceAll("[\\s]{2,}"," ");
        if(Journey.isValidTitle(new_title)){
            return new_title;
        }
        return null;
    }

    public boolean optimize_and_validate(){
        String new_title = Journey.title_BlankCorrector(this.title);
        if(new_title != null){
            this.title = new_title;
            return true;
        }
        return false;
    }
}
