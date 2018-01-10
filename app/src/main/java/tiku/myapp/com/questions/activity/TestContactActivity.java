package tiku.myapp.com.questions.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tiku.myapp.com.questions.R;
import tiku.myapp.com.questions.adapter.RecyhAdapter;
import tiku.myapp.com.questions.adapter.RecyvAdapter;

/**
 * Created by Administrator on 2018/1/10.
 */

public class TestContactActivity extends Activity {

    @BindView(R.id.rcyh)
    RecyclerView rcyh;
    @BindView(R.id.rcyv)
    RecyclerView rcyv;

    private List<String>  list1 = new ArrayList<>();
    private List<String>  list2 = new ArrayList<>();
    private int lastItemPosition = 0;
    private RecyhAdapter recyhAdapter;
    private RecyvAdapter recyvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcontact);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManagerh = new LinearLayoutManager(this);
        linearLayoutManagerh.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcyh.setLayoutManager(linearLayoutManagerh);


        LinearLayoutManager linearLayoutManagerv = new LinearLayoutManager(this);
        linearLayoutManagerv.setOrientation(LinearLayoutManager.VERTICAL);
        rcyv.setLayoutManager(linearLayoutManagerv);
    }

    private void initData() {
        for (int i=0 ;i<10 ;i++){
            list1.add("我是横向"+i);
            list2.add("我是纵向"+i);
        }

        recyhAdapter = new RecyhAdapter(list1);
        recyvAdapter = new RecyvAdapter(list2);


        rcyh.setAdapter(recyhAdapter);
        rcyv.setAdapter(recyvAdapter);


        rcyv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    lastItemPosition = linearManager.findLastVisibleItemPosition();

                    recyhAdapter.setSelectPosition(lastItemPosition);

                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();

                    Log.e("dddd1111",lastItemPosition + "///" + firstItemPosition);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

}
