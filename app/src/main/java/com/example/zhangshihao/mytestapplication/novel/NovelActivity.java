package com.example.mxm.myapplication.novel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mxm.myapplication.BaseActivity;
import com.example.mxm.myapplication.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangshihao on 2017/9/7.
 */

public class NovelActivity extends BaseActivity {
    public final static String TAG = NovelActivity.class.getSimpleName();
    public final static boolean DEBUG = true;
    public final static int NOVEL_BODY_REQUEST_CODE = 0;
    private EditText editSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);
        //do not show softinput by default
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        init();

    }

    //init widget
    private void init(){
        editSearch = (EditText) findViewById(R.id.edit_search);
    }

    //onclick button search
    public void searchNovel(View view){
        String key = editSearch.getText().toString();
        if(TextUtils.isEmpty(key)){
            showShortToast(this,R.string.search_cannot_null);
            return;
        }
        doSearch(key);
    }
    // onclick button goto ic_bookmark
    public void gotoBookMark(View view){
        startActivity(new Intent(this,BookMarkActivity.class));
    }

    //url = "http://zhannei.baidu.com/cse/search?s=2041213923836881982&q=$key&p=$index&isNeedCheckDomain=1&jump=1"
    private void doSearch(String key){
        String url = "http://zhannei.baidu.com/cse/search?s=2041213923836881982"
                .concat("&q="+key)
                .concat("&p=0&isNeedCheckDomain=1&jump=1");
        logw(url);
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
                .url(url)
                ;
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logd("do Search failed---\n"+e.getMessage());
                Looper.prepare();
                showShortToast(NovelActivity.this,"网络请求失败，请稍后再试");
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Intent intentToReading = new Intent(NovelActivity.this,NovelResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("novelBody",body);
                intentToReading.putExtras(bundle);
                startActivity(intentToReading);
            }
        });
    }
}
