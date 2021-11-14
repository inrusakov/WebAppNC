package com.example.model.Traveling;

import com.example.model.User;
import com.example.model.community.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

import static com.example.service.traveling.JourneyService.isValidJourneyTitle;
import static com.example.service.traveling.JourneyService.journeyTitleCorrector;

@ToString(onlyExplicitlyIncluded = true)
@Setter
@Getter
@NoArgsConstructor  // POJO class

@Entity
@Table(name = "journey")
public class Journey {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="group_id")
    private Group group = new Group();

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
