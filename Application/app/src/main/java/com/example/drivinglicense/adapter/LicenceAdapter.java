package com.example.drivinglicense.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drivinglicense.R;
import com.example.drivinglicense.model.Licence;

import java.util.ArrayList;

public class LicenceAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Licence> licenceArrayList;

    public LicenceAdapter(Context context, ArrayList<Licence> arrayList) {
        this.context = context;
        this.licenceArrayList = arrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.licenceArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return licenceArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_of_gridview, null, false);
        }

        TextView kind = (TextView) convertView.findViewById(R.id.txtLicensename);
        TextView numOfKind = (TextView) convertView.findViewById(R.id.txtNumberTestKit);

        Licence licence = licenceArrayList.get(position);

        kind.setText("Đề thi số");
        numOfKind.setText(String.valueOf(licence.getMS()));


        return convertView;
    }
}
