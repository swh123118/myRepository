package tiku.myapp.com.questions.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tiku.myapp.com.questions.R;
import tiku.myapp.com.questions.bean.AnswerBean;
import tiku.myapp.com.questions.bean.AnswerBeanDao;
import tiku.myapp.com.questions.bean.DaoMaster;
import tiku.myapp.com.questions.bean.DaoSession;
import tiku.myapp.com.questions.bean.QuestionBean;
import tiku.myapp.com.questions.bean.QuestionBeanDao;
import tiku.myapp.com.questions.bean.UserBean;
import tiku.myapp.com.questions.bean.UserBeanDao;
import tiku.myapp.com.questions.utils.MyAsyncTask;

/**
 * Created by swh on 2017/12/18.
 * 数据库操作类
 */
public class DBActivity extends Activity {

    @BindView(R.id.tv_instert_user)
    Button tvInstert;
    @BindView(R.id.tv_instert_question)
    Button tvInstertQus;
    @BindView(R.id.tv_quary)
    Button tvQuary;
    @BindView(R.id.tv_delet)
    Button tvDelet;
    @BindView(R.id.tv_updata)
    Button tvUpdata;
    @BindView(R.id.iv_back)
    ImageButton ivBack;
    @BindView(R.id.loading_progress_db)
    ProgressBar loadingProgressDb;

    private UserBeanDao userBeanDao;
    private AnswerBeanDao answerBeanDao;
    private QuestionBeanDao questionBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
        //c初始化greendao
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        //用户表操作类
        userBeanDao = daoSession.getUserBeanDao();
        //答案表操作类
        answerBeanDao = daoSession.getAnswerBeanDao();
        //题库表操作类
        questionBeanDao = daoSession.getQuestionBeanDao();
    }

    @OnClick({R.id.tv_instert_user, R.id.tv_quary, R.id.tv_delet, R.id.tv_updata, R.id.iv_back, R.id.tv_instert_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_instert_user:
                //添加用户数据测试
                instertUserData();

                break;

            case R.id.tv_instert_question:
                //添加题库数据
                instertQusData();

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

    private void instertUserData() {
        new MyAsyncTask() {
            @Override
            public void preTask() {
                loadingProgressDb.setVisibility(View.VISIBLE);
            }

            @Override
            public void doInBack() {
                userBeanDao.deleteAll();
                answerBeanDao.deleteAll();
                for (int i = 10; i < 100; i++) {
                    long num = i;
                    String username = "2017" + i;
                    String nickname = "张三" + i;
                    userBeanDao.insert(new UserBean(num, username, nickname));
                }
            }

            @Override
            public void postTask() {
                loadingProgressDb.setVisibility(View.GONE);
            }
        }.execute();

    }

    private void instertQusData() {
        new MyAsyncTask() {
            @Override
            public void preTask() {
                loadingProgressDb.setVisibility(View.VISIBLE);
            }

            @Override
            public void doInBack() {
                questionBeanDao.deleteAll();
                answerBeanDao.deleteAll();
                for (int i = 1; i < 101; i++) {
                    long num = i;
                    int type = 0;
                    int curId = (int) num % 4;
                    if (num % 2 == 0) {
                        type = 0;
                    } else {
                        type = 1;
                    }

                    String question = i + ".我是但是对方开始都说了付款，就开始的地方了开滦股份大连控股亏大发了看过老地方看过地方的空间疯狂的肌肤抵抗";
                    int aId = i + 1;
                    questionBeanDao.insert(new QuestionBean(type, num, question, aId, -1, curId));

                    for (int k = 0; k < 4; k++) {
                        answerBeanDao.insert(new AnswerBean(aId, k, i + "我是答案" + k));
                    }
                }
            }

            @Override
            public void postTask() {
                loadingProgressDb.setVisibility(View.GONE);
            }
        }.execute();

    }

}
