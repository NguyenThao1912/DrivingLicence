package com.example.drivinglicense.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drivinglicense.R;
import com.example.drivinglicense.adapter.LicenceAdapter;
import com.example.drivinglicense.db.DBManager;
import com.example.drivinglicense.global.AppGlobal;
import com.example.drivinglicense.model.Licence;

import java.util.ArrayList;

public class TestKit extends AppCompatActivity {
    ArrayList<Licence> licenceArrayList;
    GridView gv;
    LicenceAdapter licenceAdapter;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_kit);
        dbManager = new DBManager(this);
        if (AppGlobal.licence == null)
        {
            AppGlobal.licence = DBManager.getInstance(this).get_Licence_By_ID(61);
        }
        ArrayList<Licence> licenceList = (ArrayList<Licence>) dbManager.getLicenceData(AppGlobal.licence.getZ_PK());
        Log.d(" xx", "onCreate: " + AppGlobal.licence.getZ_PK());
        AppGlobal.licence.setZNAME(licenceList.get(0).getZNAME());
        licenceArrayList = new ArrayList<>();
        licenceArrayList = getAllTest(licenceList);


        getSupportActionBar().setTitle("Đề Thi Bằng " + AppGlobal.licence.getZNAME());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        gv = (GridView) findViewById(R.id.gvTest);
        gv.setPadding(10,10,10,10);
        licenceAdapter = new LicenceAdapter(TestKit.this, licenceArrayList);
        gv.setAdapter(licenceAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(TestKit.this, QuestionActivity.class);
                AppGlobal.currentTestId = position + 1;
                startActivity(intent);
            }
        });
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

}