package com.example.drivinglicense.views.dethi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.drivinglicense.R;
import com.example.drivinglicense.adapter.dethi.AdapterDeThi;
import com.example.drivinglicense.databinding.ActivityDethiBinding;
import com.example.drivinglicense.db.DBManager;
import com.example.drivinglicense.global.AppGlobal;
import com.example.drivinglicense.model.Licence;
import com.example.drivinglicense.views.QuestionActivity;
import com.example.drivinglicense.views.TestKit;

import java.util.ArrayList;
import java.util.List;

public class ActivityDeThi extends AppCompatActivity implements AdapterDeThi.onItemListener {
    private ActivityDethiBinding binding;
    private List<Licence> licences = new ArrayList<>();
    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dethi);
        dbManager = new DBManager(this);
        if (AppGlobal.licence == null)
        {
            AppGlobal.licence = DBManager.getInstance(this).get_Licence_By_ID(61);
        }

        ArrayList<Licence> licenceList = (ArrayList<Licence>) dbManager.getLicenceData(AppGlobal.licence.getZ_PK());
        Log.d(" xx", "onCreate: " + AppGlobal.licence.getZ_PK());
        getSupportActionBar().setTitle("Đề Thi Bằng " + AppGlobal.licence.getZNAME());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        licences = new ArrayList<>();
        licences = getAllTest(licenceList);
        binding.rc.setLayoutManager(new LinearLayoutManager(this));
        binding.rc.setAdapter(new AdapterDeThi(licences,this));

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home:{
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public ArrayList<Licence> getAllTest(ArrayList<Licence> list) {
        ArrayList<Licence> arrayList = new ArrayList<>();
        int i = 0;
        while (i < list.get(0).getZ_NUMBER_OF_TEST()) {
            arrayList.add(new Licence(list.get(0).getZNAME(), i + 1));
            i++;
        }
        return arrayList;
    }

    @Override
    public void onClick_LamBai(int position) {
        Intent intent = new Intent(ActivityDeThi.this, QuestionActivity.class);
        AppGlobal.currentTestId = position + 1;
        startActivity(intent);
    }
}
