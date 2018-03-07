package top.cmoon.practice.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.cmoon.practice.test.po.UserInfo;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Repository
public interface UserRepo extends JpaRepository<UserInfo, Integer> {


    UserInfo findFirstByUsername(String username);

}
