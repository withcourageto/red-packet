package top.cmoon.practice.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.cmoon.practice.test.po.RedPacket;

/**
 * Created by cool_moon on 2018/2/28.
 */
@Repository
public interface RedPacketRpo extends JpaRepository<RedPacket, Integer> {


}
