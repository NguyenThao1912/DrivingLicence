package com.example.drivinglicense.adapter.notice_board;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.drivinglicense.model.NoticeBoardType;
import com.example.drivinglicense.views.notice_board.FragmentNoticeBoard;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<NoticeBoardType> list = new ArrayList<>();
    public ViewPagerAdapter(@NonNull FragmentManager fm, List<NoticeBoardType> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        for (int i = 0; i <list.size(); i++) {
            if(position==i){
                return new FragmentNoticeBoard(list.get(i).getTypeId());
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        for (int i = 0; i <list.size(); i++) {
            if(position==i){
                return list.get(i).getTypeName();
            }
        }
        return null;
    }
}
