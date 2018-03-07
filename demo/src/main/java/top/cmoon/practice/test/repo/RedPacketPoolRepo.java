package top.cmoon.practice.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import top.cmoon.practice.test.po.RedPacketPool;

import java.util.List;

/**
 * Created by cool_moon on 2018/2/28.
 */
@Repository
public interface RedPacketPoolRepo extends JpaRepository<RedPacketPool, Integer> {


    List<RedPacketPool> findAllByActivityId(Integer activityId);


    RedPacketPool findFirstByActivityId(Integer activityId);

    @Modifying
    @Query("update r_red_packet_pool set red_packet_remain_nums = red_packet_remain_nums-1 where id = :redPacketPoolId")
    void decrementRedPacketNum(@Param("redPacketPoolId") int redPacketPoolId);
}
