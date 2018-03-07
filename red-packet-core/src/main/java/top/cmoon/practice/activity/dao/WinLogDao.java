package top.cmoon.practice.activity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.cmoon.practice.activity.po.WinRedPacketLogPO;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Repository
public interface WinLogDao extends JpaRepository<WinRedPacketLogPO, Integer> {
}
