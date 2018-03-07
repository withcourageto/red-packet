package top.cmoon.practice.activity.po;

import javax.persistence.*;

/**
 * Created by cool_moon on 2018/3/2.
 */
@Entity
@Table(name = "r_user")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;

    public int getId() {
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
