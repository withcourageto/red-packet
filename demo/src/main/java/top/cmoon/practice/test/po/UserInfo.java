package top.cmoon.practice.test.po;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Created by cool_moon on 2018/2/28.
 */

@Entity
@Table(name = "r_user")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
