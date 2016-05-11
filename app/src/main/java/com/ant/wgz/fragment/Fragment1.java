package com.ant.wgz.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ant.wgz.OrderActivity;
import com.ant.wgz.R;
import com.ant.wgz.adapter.MsgRecyclerViewAdapter;
import com.ant.wgz.adapter.RycViewOnItemClickListener;
import com.ant.wgz.util.RefreshList;

import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2016/5/9.
 */
public class Fragment1 extends Fragment {
    private List<Map<String,Object>> mList;
    private RecyclerView recyclerView;
    private MsgRecyclerViewAdapter adapter;
    private SwipeRefreshLayout mfresh;
    private LinearLayout root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,null);
        initview(view);

        return view;
    }

    private void initview(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.id_fragm1_rycview);
        mfresh = (SwipeRefreshLayout) view.findViewById(R.id.id_frag1_refresh);
        root = (LinearLayout) view.findViewById(R.id.id_fragm1_rootView);
        mfresh.setColorSchemeResources(R.color.colorPrimary, R.color.red2);
        mfresh.setSize(SwipeRefreshLayout.LARGE);
        mfresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RefreshList refreshList = new RefreshList(mfresh,root,getActivity());
                refreshList.setRefreshVoid(new RefreshList.RefreshListener() {
                    @Override
                    public void Refresh() {
                        //刷新操作

                    }
                });
                refreshList.execute();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter =new MsgRecyclerViewAdapter(mList,getActivity());
        adapter.setOnItemClickListener(new RycViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), OrderActivity.class));
                //Toast.makeText(getActivity(), "点击了：" + position + "号", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);


    }
}
