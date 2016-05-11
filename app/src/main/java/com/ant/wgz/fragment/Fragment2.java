package com.ant.wgz.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ant.wgz.MainActivity;
import com.ant.wgz.OrderActivity;
import com.ant.wgz.R;
import com.ant.wgz.adapter.OrderRecyclerViewAdapter;
import com.ant.wgz.adapter.RycViewOnItemClickListener;

import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2016/5/9.
 */
public class Fragment2 extends Fragment {
    private List<Map<String,Object>> mList;
    private RecyclerView mRecyclerView;
    private OrderRecyclerViewAdapter adapter;
    private SwipeRefreshLayout mrefresh;
    private LinearLayout root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mrefresh = (SwipeRefreshLayout) view.findViewById(R.id.id_fragm2_refresh);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_fragm2_rycview);
        root = (LinearLayout) view.findViewById(R.id.id_fragm2_rootView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mrefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RefreshList refreshList = new RefreshList();
                refreshList.execute();
            }
        });
        adapter = new OrderRecyclerViewAdapter(mList,getActivity());
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RycViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), OrderActivity.class));
                //Toast.makeText(getActivity(), "点击了：" + position + "号", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class RefreshList extends AsyncTask{


        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            mrefresh.setRefreshing(false);
            RefreshRadio();
            Snackbar.make(root,"刷新成功！",Snackbar.LENGTH_SHORT).show();
            super.onPostExecute(o);
        }
    }

    public void RefreshRadio(){
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent3 = PendingIntent.getActivity(getActivity(), 0,
                new Intent(getActivity(), MainActivity.class), 0);
        Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        // 通过Notification.Builder来创建通知，注意API Level
        // API16之后才支持
        Notification notify3 = null; // 需要注意build()是在API
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notify3 = new Notification.Builder(getActivity())
                   /* .setSmallIcon(R.drawable.mabu)
                    .setTicker("TickerText:" + "您有新短消息，请注意查收！")
                    .setContentTitle("Notification Title")
                    .setContentText("This is the notification message")*/
                    /*.setContentIntent(pendingIntent3)*/.setSound(ringUri).build();
        }
        // level16及之后增加的，API11可以使用getNotificatin()来替代
        notify3.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
        manager.notify(1, notify3);// 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示


    }
}
