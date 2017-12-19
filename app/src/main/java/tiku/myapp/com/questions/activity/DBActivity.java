package tiku.myapp.com.questions.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tiku.myapp.com.questions.R;
import tiku.myapp.com.questions.bean.AnswerBean;
import tiku.myapp.com.questions.bean.AnswerBeanDao;
import tiku.myapp.com.questions.bean.DaoMaster;
import tiku.myapp.com.questions.bean.DaoSession;
import tiku.myapp.com.questions.bean.UserBean;
import tiku.myapp.com.questions.bean.UserBeanDao;

/**
 * Created by Administrator on 2017/12/18.
 * 数据库操作类
 */
public class DBActivity extends Activity {

    @BindView(R.id.tv_instert)
    Button tvInstert;
    @BindView(R.id.tv_quary)
    Button tvQuary;
    @BindView(R.id.tv_delet)
    Button tvDelet;
    @BindView(R.id.tv_updata)
    Button tvUpdata;
    @BindView(R.id.iv_back)
    ImageButton ivBack;

    private UserBeanDao userBeanDao;
    private AnswerBeanDao answerBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
        //c初始化greendao
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        //题库表操作类
        userBeanDao = daoSession.getUserBeanDao();
        //答案表操作类
        answerBeanDao = daoSession.getAnswerBeanDao();
    }

    @OnClick({R.id.tv_instert, R.id.tv_quary, R.id.tv_delet, R.id.tv_updata,R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_instert:
                for (int i = 10; i < 100; i++) {
                    long num = i;
                    String username = "2017" + i;
                    String nickname = "张三" + i;
                    int answera = i + 1;
                    userBeanDao.insert(new UserBean(num, username, nickname, answera, ""));

                    for (int k = 0; k < 4; k++) {
                        answerBeanDao.insert(new AnswerBean(answera, k, "我是答案" + k + 1));
                    }
                }

                break;
            case R.id.tv_quary:
                List<UserBean> list = userBeanDao.queryBuilder().where(UserBeanDao.Properties.Id.between(10, 20)).limit(5).build().list();
                Log.e("google_lenve", "search: " + list.size());
                for (int i = 0; i < list.size(); i++) {
                    Log.e("google_lenve", "search: " + list.get(i).getNickname().toString());
                }
                break;
            case R.id.tv_delet:
                userBeanDao.deleteAll();
                break;
            case R.id.tv_updata:
                UserBean user = userBeanDao.queryBuilder()
                        .where(UserBeanDao.Properties.Id.eq(10), UserBeanDao.Properties.Nickname.like("%张三%")).build().unique();
                if (user == null) {
                    Toast.makeText(DBActivity.this, "用户不存在!", Toast.LENGTH_SHORT).show();
                } else {
                    user.setUsername("王五");
                    userBeanDao.update(user);
                }
                break;

        }
    }

}
