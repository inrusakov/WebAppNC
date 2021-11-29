package com.example.repos;

import com.example.model.Traveling.Journey.Journey;
import com.example.repos.CriteriaBuilder.JourneyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Journey, Integer>, JourneyRepositoryCustom {

    @Query(value = "SELECT DISTINCT jor.* FROM journey jor, Users_Groups ug " +
            "WHERE jor.group_id = ug.group_id AND ug.user_id = :user_id",
            nativeQuery = true)
    java.util.List<Journey> findByIsParticipant(@Param("user_id") Integer user_id);

    @Query(value = "SELECT DISTINCT jor.* FROM journey jor " +
            "WHERE jor.is_private = false AND jor.title LIKE CONCAT('%', COALESCE(:title, ''), '%')",
            nativeQuery = true)
    java.util.List<Journey> findByIsPrivateFalse(@Param("title") String title);

    @Query(value = "SELECT DISTINCT jor.* FROM journey jor " +
            "WHERE jor.is_private = false",
            nativeQuery = true)
    java.util.List<Journey> findByIsPrivateFalse();

    @Query(value = "SELECT EXISTS(SELECT ug.* FROM journey jor, Users_Groups ug " +
            "WHERE jor.group_id = ug.GROUP_ID AND jor.group_id = :jor_id AND ug.USER_ID = :user_id)",
            nativeQuery = true)
    Boolean isUserParticipantOfJourney(@Param("user_id") Integer user_id, @Param("jor_id") Integer journey_id);

    @Query(value = "SELECT DISTINCT jor.* FROM journey jor, Users_Groups ug " +
            "WHERE ug.user_id = :user_id AND jor.group_id = ug.group_id AND jor.title LIKE CONCAT('%', :title, '%') ",
            nativeQuery = true)
    java.util.List<Journey> findByIsParticipant(@Param("user_id") Integer user_id, @Param("title") String title);
}