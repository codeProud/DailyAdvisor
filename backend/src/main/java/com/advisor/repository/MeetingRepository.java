package com.advisor.repository;

import com.advisor.model.entity.Meeting;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository("meetingRepository")
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    Meeting findMeetingById(Long meetingId);

    List<Meeting> findMeetingsByUserIdOrUserId2(User user, User user2);

    Meeting findByIdAndUserId2(long meetingId, User user);

    @Transactional
    @Modifying
    @Query("UPDATE Meeting m SET m.status = :status WHERE m.userId2 = :user")
    void updateMeeting(@Param("user") User user, @Param("status") String status);

    @Query("SELECT m FROM Meeting m WHERE m.id = :id AND (m.userId2 = :user OR m.userId = :user)")
    List<Meeting> findMeetingByIdAndUserId(@Param("id") Long meetingId,@Param("user") User user);

    @Transactional
    @Modifying
    @Query("UPDATE Meeting m SET m = :meeting WHERE m = :meeting")
    void updateMeeting(@Param("meeting") Meeting meeting);

    Meeting findById(Long id);
}