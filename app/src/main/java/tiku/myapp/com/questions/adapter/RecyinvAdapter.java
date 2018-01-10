package tiku.myapp.com.questions.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import tiku.myapp.com.questions.R;

/**
 * Created by swh on 2017/12/18.
 * 答案adapter
 */

public class RecyinvAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private int selectPosition = -1;

    public RecyinvAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_recyh, data);
    }


    public void setSelectPosition(int currentPosition) {
        this.selectPosition = currentPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setTextColor(R.id.tv_h,
                        selectPosition == helper.getAdapterPosition()
                                ? Color.parseColor("#0000ff")
                                : Color.parseColor("#333333"))
                .setText(R.id.tv_h, item);
    }
}
