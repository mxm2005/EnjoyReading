package com.example.mxm.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import com.example.mxm.myapplication.novel.model.ChatMessage;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class activity_novel_list extends AppCompatActivity {

    private ListView mListView;
    private CommonAdapter mAdapter;
    private List<String> mDatas = new ArrayList<>(Arrays.asList("MultiItem ListView",
            "RecyclerView",
            "MultiItem RecyclerView"));

    private View mEmptyView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_list);

        mListView = ((ListView) findViewById(R.id.id_listview_list));
        mEmptyView = findViewById(R.id.id_empty_view);
        mListView.setAdapter(new CommonAdapter<String>(this, R.layout.item_list, mDatas)
        {
            @Override
            public void convert(ViewHolder holder, String o, int pos)
            {
                holder.setText(R.id.id_item_list_title, o);//test title
            }

            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView)
            {
                super.onViewHolderCreated(holder, itemView);
            }
        });

        mListView.setEmptyView(mEmptyView);
    }
}
