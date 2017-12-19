package tiku.myapp.com.questions.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import tiku.myapp.com.questions.R;
import tiku.myapp.com.questions.bean.AnswerBean;

/**
 * Created by Administrator on 2017/12/18.
 * 答案adapter
 */

public class AnswerAdapter extends BaseQuickAdapter<AnswerBean,BaseViewHolder> {

    private int selectPosition = -1;

    public AnswerAdapter( @Nullable List<AnswerBean> data) {
        super(R.layout.adapter_answer, data);
    }


    public void setSelectPosition(int currentPosition) {
        this.selectPosition = currentPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, AnswerBean item) {
        helper.setTextColor(R.id.tv_a,
                        selectPosition == helper.getAdapterPosition()
                                ? Color.parseColor("#0000ff")
                                : Color.parseColor("#333333"))
                .setText(R.id.tv_a, "        "+item.getAnswer()+"对方尽快抗静电对方尽快抗静电剂风格的可靠的方法看过剂风格的可靠的方法看过");
    }
}
