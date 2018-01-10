package tiku.myapp.com.questions.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import tiku.myapp.com.questions.R;

/**
 * Created by swh on 2017/12/18.
 * 答案adapter
 */

public class RecyvAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private int selectPosition = 2;

    public RecyvAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_recyv, data);
    }


    public void setSelectPosition(int currentPosition) {
        this.selectPosition = currentPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        Log.e("ddddddd","//" + helper.getLayoutPosition());

        helper.setTextColor(R.id.tv,
                selectPosition == helper.getAdapterPosition()
                        ? Color.parseColor("#0000ff")
                        : Color.parseColor("#333333"))
                .setText(R.id.tv, item);

        RecyclerView recyclerView = helper.getView(R.id.recy_in_1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<String> list = new ArrayList<>();
        for (int i=0 ;i<20 ;i++){
            list.add("我是纵向内"+i);
        }
        RecyinvAdapter answerAdapter = new RecyinvAdapter(list);
        recyclerView.setAdapter(answerAdapter);

    }

}
