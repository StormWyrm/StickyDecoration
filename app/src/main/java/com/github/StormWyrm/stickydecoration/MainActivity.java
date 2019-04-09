package com.github.StormWyrm.stickydecoration;

import android.graphics.Color;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> data;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = findViewById(R.id.rv);
        mainAdapter = new MainAdapter(data);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,RecyclerView.HORIZONTAL));
        recyclerView.setAdapter(mainAdapter);

    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            data.add("User Data" + i);
        }
    }

    public static class MainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MainAdapter(@Nullable List<String> data) {
            super(R.layout.item_main, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_name, item);
        }
    }
}
