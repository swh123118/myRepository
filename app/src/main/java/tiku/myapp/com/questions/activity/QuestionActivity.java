package tiku.myapp.com.questions.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tiku.myapp.com.questions.R;
import tiku.myapp.com.questions.adapter.QuestionAdapter;
import tiku.myapp.com.questions.bean.AnswerBean;
import tiku.myapp.com.questions.bean.AnswerBeanDao;
import tiku.myapp.com.questions.bean.DaoMaster;
import tiku.myapp.com.questions.bean.DaoSession;
import tiku.myapp.com.questions.bean.UserBean;
import tiku.myapp.com.questions.bean.UserBeanDao;
import tiku.myapp.com.questions.utils.MyAsyncTask;

/**
 * Created by Administrator on 2017/12/18.
 * 具体题库类
 */

public class QuestionActivity extends Activity {

    @BindView(R.id.recy1)
    RecyclerView recy1;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_last)
    Button btnLast;
    @BindView(R.id.loading_progress)
    ProgressBar loadingProgress;
    @BindView(R.id.iv_back)
    ImageButton ivBack;

    private UserBeanDao userBeanDao;
    private AnswerBeanDao answerBeanDao;
    private int currentPosition;
    private List<UserBean> list;
    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        initView();

        initData();
    }

    private void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QuestionActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recy1.setLayoutManager(linearLayoutManager);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recy1);

    }


    private void initData() {
        //c初始化greendao
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();

        userBeanDao = daoSession.getUserBeanDao();

        answerBeanDao = daoSession.getAnswerBeanDao();

        //加载数据
        loadFromLocal();

    }

    private void loadFromLocal() {
        new MyAsyncTask() {
            @Override
            public void preTask() {
                loadingProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void doInBack() {
                //子线程
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list = userBeanDao.queryBuilder().where(UserBeanDao.Properties.Id.between(10, 99)).limit(20).build().list();
            }

            @Override
            public void postTask() {
                loadingProgress.setVisibility(View.GONE);
                questionAdapter = new QuestionAdapter(list);
                recy1.setAdapter(questionAdapter);
            }
        }.execute();
    }

    //获取具体答案
    public List<AnswerBean> getAnswerS(int id) {
        List<AnswerBean> list = answerBeanDao.queryBuilder().where(AnswerBeanDao.Properties.AnswerID.eq(id)).build().list();

        return list;

    }

    //保存用户答案
    public void updataAnswer(int id, int answerId) {
        UserBean answerbean = userBeanDao.queryBuilder().where(UserBeanDao.Properties.AnswerID.eq(id)).build().unique();
        if (answerbean != null) {
            answerbean.setStuanswerID(answerId + "");
            userBeanDao.update(answerbean);
        }


    }

    //判断用户是否答题
    public int quaryAnswer(int id) {
        int position = -1;
        UserBean unique = userBeanDao.queryBuilder().where(UserBeanDao.Properties.AnswerID.eq(id)).build().unique();
        if (unique != null && !unique.getStuanswerID().equals("")) {
            position = Integer.parseInt(unique.getStuanswerID());
            return position;
        } else {
            return position;
        }

    }

    @OnClick({R.id.btn_next, R.id.btn_last,R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_next:
                currentPosition = questionAdapter.getCurrentPosition();
                if (currentPosition == 0) {
                    return;
                }
                Log.e("dddddqw11", currentPosition + "==");
                recy1.scrollToPosition(currentPosition - 1);
                break;
            case R.id.btn_last:
                currentPosition = questionAdapter.getCurrentPosition();
                if (currentPosition == list.size() - 1) {
                    return;
                }
                recy1.scrollToPosition(currentPosition + 1);
                Log.e("dddddqw22", currentPosition + "==");
                break;
        }
    }

}
