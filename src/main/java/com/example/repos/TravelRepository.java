package com.example.repos;

import com.example.model.Traveling.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface TravelRepository extends JpaRepository<Journey, Integer> {

    @Query(value = "SELECT DISTINCT jor.* FROM journey jor, journey_participants prt " +
            "WHERE jor.journey_id = prt.journey_id AND prt.user_id = :request_id",
            nativeQuery = true)
    java.util.List<Journey> findWhereUserIsParticipant(@Param("request_id") Integer user_id);

    java.util.List<Journey> findByIsPrivateFalse();

    @Query(value = "SELECT DISTINCT jor.* FROM journey jor, journey_participants prt " +
            "WHERE (jor.journey_id = prt.journey_id AND prt.user_id = :request_id) OR (jor.is_private = false)",
            nativeQuery = true)
    java.util.List<Journey> findAvailableToUser(@Param("request_id") Integer user_id);

    @Query(value = "SELECT DISTINCT jor.* FROM journey jor " +
            "WHERE jor.title like CONCAT('%', :title_search, '%') AND jor.is_private = false",
            nativeQuery = true)
    java.util.List<Journey> findByTitleContaining(@Param("title_search")String title_search);

    @Query(value = "SELECT EXISTS(SELECT * FROM journey_participants " +
            "WHERE journey_id = :jor_id AND user_id = :user_id)",
            nativeQuery = true)
    Boolean isUserParticipantOfJourney(@Param("user_id") Integer user_id, @Param("jor_id") Integer journey_id);
}