package com.zgf.otherlibrary.vlayout;

import static com.alibaba.android.vlayout.layout.FixLayoutHelper.TOP_RIGHT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.DelegateAdapter.Adapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.zgf.otherlibrary.R;

import java.util.ArrayList;
import java.util.List;

public class DelegateActivity extends AppCompatActivity {

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate);

        initData();
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.other_vlayout_delegate_rv);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        List<Adapter> adapterList = new ArrayList<>();
        // TODO: 每一个adapter对应自己的数据源
        adapterList.add(new SubAdapter(new LinearLayoutHelper(20), 20, list));
        adapterList.add(new SubAdapter(new StickyLayoutHelper(true), 1, list));
        adapterList.add(new SubAdapter(new LinearLayoutHelper(20), 20, list));
        adapterList.add(new SubAdapter(new GridLayoutHelper(4), 80, list));
        // adapterList.add(new SubAdapter(new FixLayoutHelper(0, 0), 1));
        adapterList.add(new SubAdapter(new FixLayoutHelper(TOP_RIGHT, 0, 0), 1, list));
        delegateAdapter.addAdapters(adapterList);
        recyclerView.setLayoutManager(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 121; i++) {
            list.add("item is " + i);
        }
    }

    private static class SubAdapter extends DelegateAdapter.Adapter<SubViewHolder> {

        private LayoutHelper mLayoutHelper;
        private int mItemCount;
        private List<String> list;

        private SubAdapter(LayoutHelper layoutHelper, int itemCount, List<String> list) {
            mLayoutHelper = layoutHelper;
            mItemCount = itemCount;
            this.list = list;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public SubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new SubViewHolder(inflater.inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(SubViewHolder holder, int position) {
            // do nothing
        }

        @Override
        protected void onBindViewHolderWithOffset(SubViewHolder holder, int position, int offsetTotal) {
            super.onBindViewHolderWithOffset(holder, position, offsetTotal);
            Log.e("zgf", "=========position==" + position + "==offsetTotal==" + offsetTotal);
            // TODO: position当前adapter的位置
            // TODO: offsetTotal在列表总长度中的位置
            String item = list.get(position);
            holder.setText(item);
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

    }

    private static class SubViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public SubViewHolder(View itemView) {
            super(itemView);
            createdTimes++;
            existing++;
        }

        public void setText(String title) {
            ((TextView) itemView.findViewById(R.id.title)).setText(title);
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }
}