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
    @Property(nameInDb = "ANSWERID")
    private int answerID;
    @Property(nameInDb = "STUANSWERID")
    private String stuanswerID;



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
    public String getStuanswerID() {
        return this.stuanswerID;
    }
    public void setStuanswerID(String stuanswerID) {
        this.stuanswerID = stuanswerID;
    }
    public int getAnswerID() {
        return this.answerID;
    }
    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }
    @Generated(hash = 1307437772)
    public UserBean(Long id, String username, String nickname, int answerID,
            String stuanswerID) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.answerID = answerID;
        this.stuanswerID = stuanswerID;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
}
