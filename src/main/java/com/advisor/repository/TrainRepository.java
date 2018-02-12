package com.advisor.repository;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trainRepository")
public interface TrainRepository extends JpaRepository<Train, Integer> {

    @Query("SELECT t FROM Train t WHERE t.id = :id AND t.createdBy = :creator")
    List<Train> findByCreatorAndId(@Param("creator") User creator, @Param("id") long id);

    Train findOneById(long dietId);

    List<Train>  findByCreatedBy(User user);
}