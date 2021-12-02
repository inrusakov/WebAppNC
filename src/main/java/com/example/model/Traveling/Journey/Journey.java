package com.example.model.Traveling.Journey;

import com.example.model.User;
import com.example.model.community.Group;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

import static com.example.service.traveling.JourneyService.isValidJourneyTitle;
import static com.example.service.traveling.JourneyService.journeyTitleCorrector;

@Setter
@Getter
@NoArgsConstructor  // POJO class

@Entity
@Table(name = "journey")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Journey {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "journey_id", nullable = false)
    @JsonView(JourneyViews.list.class)
    Integer id;

    @Column(name = "title", nullable = false)
    @JsonView(JourneyViews.list.class)
    String title = "";

    @Column(name = "description", nullable = false)
    @JsonView(JourneyViews.all.class)
    String description = "";

    @Column(name = "isPrivate", nullable = false, columnDefinition = "BOOL default TRUE")
    @JsonView(JourneyViews.all.class)
    Boolean isPrivate = true;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="group_id")
    @JsonIgnore
    private Group group = new Group();

    @Enumerated(EnumType.STRING)
    @Column(name = "journey_status", nullable = false, columnDefinition = "VARCHAR default 'NONE'")
    @JsonView(JourneyViews.list.class)
    private JourneyStatus status = JourneyStatus.NONE;

    // Инициализируется в момент отправки в БД ( не в момент сохранения в БД )
    @Column(name = "creation_time", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    @JsonView(JourneyViews.all.class)
    private Timestamp creation_time;

    //Route? Geolocation

    //Creators?

    //Chat?

    //Tags?

    //Types?

    public Set<User> getParticipants(){
        return group.getParticipants();
    }
    public void addParticipants(User ... users){
        group.addParticipants(users);
    }

    public boolean optimize(){
        String new_title = journeyTitleCorrector(this.title);
        if(isValidJourneyTitle(new_title)){
            this.title = new_title;
            return true;
        }
        return false;
    }
}
