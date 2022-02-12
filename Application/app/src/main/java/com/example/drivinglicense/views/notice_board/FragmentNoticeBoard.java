package com.example.drivinglicense.views.notice_board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.drivinglicense.adapter.notice_board.NoticeBoardAdapter;
import com.example.drivinglicense.databinding.FragmentNoticeBoardBinding;
import com.example.drivinglicense.db.DBManager;
import com.example.drivinglicense.model.NoticeBoard;

import java.util.ArrayList;
import java.util.List;

public class FragmentNoticeBoard extends Fragment {
    private FragmentNoticeBoardBinding binding;
    private int typeId;
    private List<NoticeBoard> noticeBoards = new ArrayList<>();
    private List<NoticeBoard> allNoticeBoards = new ArrayList<>();
    private DBManager dbManager;

    public FragmentNoticeBoard(int typeId) {
        this.typeId = typeId;
    }

    public FragmentNoticeBoard() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoticeBoardBinding.inflate(inflater,container,false);
        setNoticeBoards();
        binding.rc.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rc.setAdapter(new NoticeBoardAdapter(noticeBoards));
        return binding.getRoot();
    }

    private void setNoticeBoards(){
        dbManager = new DBManager(getContext());
        allNoticeBoards = dbManager.getListNoticeBoard();
        for (NoticeBoard model:
             allNoticeBoards) {
            if(model.getTypeId()==typeId){
                noticeBoards.add(model);
            }
        }
    }

}
