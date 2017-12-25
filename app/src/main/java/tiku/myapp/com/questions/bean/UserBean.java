package tiku.myapp.com.questions.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */
@Entity
public class UserBean {
    @Id
    private Long id;
    @Property(nameInDb = "USERNAME")
    private String username;
    @Property(nameInDb = "NICKNAME")
    private String nickname;


    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 607418162)
    public UserBean(Long id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
    }
}
