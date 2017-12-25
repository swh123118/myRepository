package tiku.myapp.com.questions.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/12/18.
 * 答案表
 */

@Entity
public class AnswerBean {
    @Property(nameInDb = "ANSWERID") // 主键
    private int answerID;
    @Property(nameInDb = "ANSWERNUM") //答案编号
    private int answernum;
    @Property(nameInDb = "ANSWER")   //答案内容
    private String answer;

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getAnswernum() {
        return this.answernum;
    }

    public void setAnswernum(int answernum) {
        this.answernum = answernum;
    }

    public int getAnswerID() {
        return this.answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    @Generated(hash = 2061247884)
    public AnswerBean(int answerID, int answernum, String answer) {
        this.answerID = answerID;
        this.answernum = answernum;
        this.answer = answer;
    }

    @Generated(hash = 1597358991)
    public AnswerBean() {
    }
}
