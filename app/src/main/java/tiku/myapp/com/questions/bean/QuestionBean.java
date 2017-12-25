package tiku.myapp.com.questions.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by swh on 2017/12/20.
 * 问题bean
 */

@Entity
public class QuestionBean {

    @Property(nameInDb = "QTYPE")
    private int qType;//问题类型
    @Id               //问题ID
    private long qId;
    @Property(nameInDb = "QUESTION") //问题
    private String querstion;
    @Property(nameInDb = "AID")  //答案表ID
    private int aId;
    @Property(nameInDb = "ASTUAID") //用户答案
    private int aStuId;
    @Property(nameInDb = "ACURID") // 正确答案
    private int aCurId;


    public int getACurId() {
        return this.aCurId;
    }

    public void setACurId(int aCurId) {
        this.aCurId = aCurId;
    }

    public int getAStuId() {
        return this.aStuId;
    }

    public void setAStuId(int aStuId) {
        this.aStuId = aStuId;
    }

    public int getAId() {
        return this.aId;
    }

    public void setAId(int aId) {
        this.aId = aId;
    }

    public String getQuerstion() {
        return this.querstion;
    }

    public void setQuerstion(String querstion) {
        this.querstion = querstion;
    }

    public long getQId() {
        return this.qId;
    }

    public void setQId(long qId) {
        this.qId = qId;
    }

    public int getQType() {
        return this.qType;
    }

    public void setQType(int qType) {
        this.qType = qType;
    }

    @Generated(hash = 1388510352)
    public QuestionBean(int qType, long qId, String querstion, int aId, int aStuId,
                        int aCurId) {
        this.qType = qType;
        this.qId = qId;
        this.querstion = querstion;
        this.aId = aId;
        this.aStuId = aStuId;
        this.aCurId = aCurId;
    }

    @Generated(hash = 842286453)
    public QuestionBean() {
    }

}
