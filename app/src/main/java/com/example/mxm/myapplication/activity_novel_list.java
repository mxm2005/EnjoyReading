package com.example.mxm.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.mxm.myapplication.db.db_dao;
import com.example.mxm.myapplication.novel.model.ChatMessage;
import com.example.mxm.myapplication.novel.model.NovelInfo;
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

        mListView =  findViewById(R.id.id_listview_list);
        mEmptyView = findViewById(R.id.id_empty_view);
        /*mListView.setAdapter(new CommonAdapter<String>(this, R.layout.item_list, mDatas)
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
        });*/

        /*List<NovelInfo> lstN=new ArrayList<>();
        NovelInfo no=new NovelInfo();
        no.setId(1);
        no.setTitle("标题1");
        no.setImg("/img1.jpg");
        no.setAuthor("作者1");
        no.setUpdateTime("2019-10-9");
        lstN.add(no);

        NovelInfo no2=new NovelInfo();
        no2.setId(2);
        no2.setTitle("标题2");
        no2.setImg("/img2.jpg");
        no2.setAuthor("作者2");
        no2.setUpdateTime("2019-10-10");
        lstN.add(no2);*/

        List<NovelInfo> lstN=getNovelList();

        mListView.setAdapter(new CommonAdapter<NovelInfo>(this, R.layout.item_novel, lstN)
        {
            @Override
            public void convert(ViewHolder holder, NovelInfo o, int pos)
            {
                holder.setText(R.id.id_item_list_title, o.title);
                holder.setText(R.id.id_item_list_summary, o.img);
                holder.setText(R.id.id_item_list_date, o.updateTime);
                holder.setText(R.id.id_item_list_auth, o.author);
            }

            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView)
            {
                super.onViewHolderCreated(holder, itemView);
            }
        });

        mListView.setEmptyView(mEmptyView);
    }

    List<NovelInfo> getNovelList() {
        List<NovelInfo> reVal;
        db_dao dao = new db_dao(this);
        if(!dao.isTableExist())
            dao.initTable();
        reVal = dao.getAllDate();
        if (reVal == null)
            reVal = new ArrayList<>();
        return reVal;
    }
}
