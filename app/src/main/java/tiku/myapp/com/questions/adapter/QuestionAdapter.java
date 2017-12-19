package tiku.myapp.com.questions.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import tiku.myapp.com.questions.R;
import tiku.myapp.com.questions.activity.QuestionActivity;
import tiku.myapp.com.questions.bean.AnswerBean;
import tiku.myapp.com.questions.bean.UserBean;


/**
 * Created by Administrator on 2017/12/18.
 * 题adapter
 */

public class QuestionAdapter extends BaseQuickAdapter<UserBean,BaseViewHolder> {


    private int currentPosition= -1;
    public QuestionAdapter(@Nullable List<UserBean> data) {
        super(R.layout.adapter_question, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final UserBean item) {
        currentPosition = helper.getLayoutPosition();
        ImageView iv_answer = helper.getView(R.id.iv_answer);
        RecyclerView recyclerView = helper.getView(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<AnswerBean> answerS = ((QuestionActivity) mContext).getAnswerS(item.getAnswerID());

        int stuanswerId = ((QuestionActivity) mContext).quaryAnswer(item.getAnswerID());
        final AnswerAdapter answerAdapter = new AnswerAdapter(answerS);

        recyclerView.setAdapter(answerAdapter);

        if (stuanswerId!=-1){
            answerAdapter.addFooterView(View.inflate(mContext,R.layout.adapter_answer_current,null));
            iv_answer.setVisibility(View.VISIBLE);
        }else {
            iv_answer.setVisibility(View.GONE);
        }
        answerAdapter.setSelectPosition(stuanswerId);

        answerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                answerAdapter.setSelectPosition(position);
                ((QuestionActivity) mContext).updataAnswer(item.getAnswerID(),position);
            }
        });

    }

    //获取当前position
    public int getCurrentPosition(){
        return currentPosition;
    }
}
