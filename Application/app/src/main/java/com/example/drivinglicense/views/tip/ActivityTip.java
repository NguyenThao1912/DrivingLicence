package com.example.drivinglicense.views.tip;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.drivinglicense.R;
import com.example.drivinglicense.adapter.tip.ViewPagerAdapter;
import com.example.drivinglicense.databinding.ActivityTipBinding;

public class ActivityTip extends AppCompatActivity {
    private ActivityTipBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tip);
        binding.viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        binding.tab.setupWithViewPager(binding.viewPager);
        setOnClick();
    }
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
        overridePendingTransition(R.anim.close_open_activity, R.anim.close_activity);
    }
}
