package com.example.drivinglicense.views.notice_board;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.drivinglicense.R;
import com.example.drivinglicense.adapter.notice_board.ViewPagerAdapter;
import com.example.drivinglicense.databinding.ActivityNoticeBoardBinding;
import com.example.drivinglicense.db.DBManager;

public class ActivityNoticeBoard extends AppCompatActivity{
    private ActivityNoticeBoardBinding binding;
    private DBManager dbManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_board);
        dbManager = new DBManager(this);
        binding.viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),dbManager.getListNoticeBoardType()));
        binding.tab.setupWithViewPager(binding.viewPager);
        setOnClick();
    }
    //TODO Bắt sự kiện click cho các button
    private void setOnClick(){
        binding.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(R.anim.close_open_activity,R.anim.close_activity);
    }
}
