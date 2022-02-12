package com.example.drivinglicense.views.tip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.drivinglicense.adapter.tip.TipAdapter;
import com.example.drivinglicense.databinding.FragmentTipBinding;
import com.example.drivinglicense.db.DBManager;
import com.example.drivinglicense.model.Tip;
import com.example.drivinglicense.model.TipCategory;

import java.util.ArrayList;
import java.util.List;

public class FragmentTip extends Fragment {
    private FragmentTipBinding binding;
    private int tipType;
    private DBManager dbManager;
    private List<TipCategory> tipCategories = new ArrayList<>();
    private List<TipCategory> allTipCategories = new ArrayList<>();
    private List<Tip> tips = new ArrayList<>();

    public FragmentTip(int tipType) {
        this.tipType = tipType;
    }

    public FragmentTip() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTipBinding.inflate(inflater,container,false);
        setList();
        binding.rc.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rc.setAdapter(new TipAdapter(tipCategories,tips));
        return binding.getRoot();
    }

    private void setList(){
        dbManager = new DBManager(getContext());
        allTipCategories = dbManager.getListTipCategory();
        tips = dbManager.getListTip();
        for (TipCategory model:
                allTipCategories) {
            if(model.getType()==tipType){
                tipCategories.add(model);
            }
        }
    }
}
