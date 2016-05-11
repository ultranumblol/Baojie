package com.ant.wgz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ant.wgz.R;

/**
 * Created by qwerr on 2016/5/9.
 */
public class Fragment3 extends Fragment{



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment3,null);
        initview(view);
        return  view;
    }

    private void initview(View view) {


    }
}
