package com.example.mxm.myapplication.novel.adapter;

import android.content.Context;
import com.example.mxm.myapplication.novel.model.ChatMessage;
import com.zhy.adapter.abslistview.MultiItemTypeAdapter;

import java.util.List;

public class ChatAdapter extends MultiItemTypeAdapter<ChatMessage>
{
    public ChatAdapter(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

//        addItemViewDelegate(new MsgSendItemDelagate());
//        addItemViewDelegate(new MsgComingItemDelagate());
    }

}