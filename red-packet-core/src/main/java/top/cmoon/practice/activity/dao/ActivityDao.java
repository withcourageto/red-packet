package top.cmoon.practice.activity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.cmoon.practice.activity.po.ActivityPO;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Repository
public interface ActivityDao extends JpaRepository<ActivityPO, Integer> {


    @Modifying
    @Query("update r_activity  set remain_count = remain_count -1  where id = :activityId")
    void decrementRemainNum(@Param(value = "activityId") int activityId);
}
