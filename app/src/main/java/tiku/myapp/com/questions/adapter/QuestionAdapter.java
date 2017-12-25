package tiku.myapp.com.questions.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import tiku.myapp.com.questions.R;
import tiku.myapp.com.questions.activity.QuestionActivity;
import tiku.myapp.com.questions.bean.AnswerBean;
import tiku.myapp.com.questions.bean.QuestionBean;
import tiku.myapp.com.questions.bean.UserBean;


/**
 * Created by swh on 2017/12/18.
 * 题adapter
 */

public class QuestionAdapter extends BaseQuickAdapter<QuestionBean, BaseViewHolder> {

    private ImageView iv_answer;
    private int currentPosition = -1;

    public QuestionAdapter(@Nullable List<QuestionBean> data) {
        super(R.layout.adapter_question, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final QuestionBean item) {
        currentPosition = helper.getLayoutPosition();

        helper.setText(R.id.tv_question,item.getQuerstion());

        Log.e("dddddddd",item.getACurId()+ "///");

        iv_answer = helper.getView(R.id.iv_answer);
        RecyclerView recyclerView = helper.getView(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<AnswerBean> answerS = ((QuestionActivity) mContext).getAnswerS(item.getAId());
        //查询学生答案，
        final int stuanswerId = ((QuestionActivity) mContext).quaryAnswer(item.getAId());

        final AnswerAdapter answerAdapter = new AnswerAdapter(answerS);
        recyclerView.setAdapter(answerAdapter);
        //判断学生答案对错，这里是求余
        if (stuanswerId != -1) {
            answerAdapter.addFooterView(View.inflate(mContext, R.layout.adapter_answer_current, null));
            iv_answer.setVisibility(View.VISIBLE);
            if (stuanswerId/2==0){
                iv_answer.setImageResource(R.drawable.ic_check_black_24dp);
            }else {
                iv_answer.setImageResource(R.drawable.ic_clear_black_24dp);
            }
        } else {
            iv_answer.setVisibility(View.GONE);
        }
        //已选答案，设置选择的item
        answerAdapter.setSelectPosition(stuanswerId);

        answerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (stuanswerId==-1){
                    answerAdapter.setSelectPosition(position);
                    //保存学生答案
                    ((QuestionActivity) mContext).updataAnswer(item.getAId(), position);
                    //查看正确答案
                    checkAnswer(position,item.getACurId());
                }else {
                    Toast.makeText(mContext,"答案已选",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void checkAnswer(int position,int curId) {
        if (position==curId){
            iv_answer.setImageResource(R.drawable.ic_check_black_24dp);
        }else {
            iv_answer.setImageResource(R.drawable.ic_clear_black_24dp);
        }
        notifyDataSetChanged();
    }

    //获取当前position
    public int getCurrentPosition() {
        return currentPosition;
    }
}
