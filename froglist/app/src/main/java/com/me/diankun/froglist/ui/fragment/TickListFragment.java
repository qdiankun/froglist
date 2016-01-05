package com.me.diankun.froglist.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.diankun.froglist.R;
import com.me.diankun.froglist.adapter.SimpleItemTouchHelperCallback;
import com.me.diankun.froglist.adapter.TickListAdapter;
import com.me.diankun.froglist.ui.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by diankun on 2016/1/5.
 */
public class TickListFragment extends BaseFragment {

    private static final String ARG_TYPE = "year";
    private int mType = -1;

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private TickListAdapter mAdapter;
    private ArrayList<String> mTickList;
    private ItemTouchHelper mItemTouchHelper;


    public TickListFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given
     *
     * @return
     */
    public static TickListFragment newInstance(int type) {
        TickListFragment fragment = new TickListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTickList = new ArrayList<String>();
        initDatas();
        mAdapter = new TickListAdapter(mTickList);
        parseArguments();
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    private void initDatas() {

        for (int i = 0; i < 10; i++) {
            mTickList.add("welcome to " + i);
        }
    }

    private void parseArguments() {
        Bundle bundle = getArguments();
        mType = bundle.getInt(ARG_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ticklist, container, false);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        initRefreshLayout();
        return rootView;
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.loadFirst();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //设置滑动删除，长按拖拽
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
