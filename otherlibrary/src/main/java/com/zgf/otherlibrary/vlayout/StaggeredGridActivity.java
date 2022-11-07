package com.zgf.otherlibrary.vlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zgf.otherlibrary.R;

public class StaggeredGridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.other_vlayout_staggered_grid_rv);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(4, 4, 4, 4);
            }
        });

        recyclerView.setAdapter(
                new RecyclerView.Adapter() {
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                        TextView view = (TextView) LayoutInflater.from(TestActivity.this).inflate(R.layout.item, parent, false);
//                        FrameLayout frameLayout = new FrameLayout(TestActivity.this);
                        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(StaggeredGridActivity.this).inflate(R.layout.item, parent, false);
                        ;
//                        frameLayout.addView(view);
                        return new MainViewHolder(frameLayout);
                    }

                    @Override
                    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, 300);
                        layoutParams.height = (int) (200 + (position % 15) * 10);

                        holder.itemView.findViewById(R.id.title).setLayoutParams(layoutParams);
                        if (position == 30) {
                            StaggeredGridLayoutManager.LayoutParams lp = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            lp.setFullSpan(true);
                            holder.itemView.setLayoutParams(lp);
                        } else {
                            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
                            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                                ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(false);
                            }
                        }
                        ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(position));
                    }

                    @Override
                    public int getItemCount() {
                        return 60;
                    }
                });
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }
}