package com.oncuoiky;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<NhanVien> {
    Activity context = null;
    int layoutID;
    ArrayList<NhanVien> myArray = null;

    public MyArrayAdapter(Activity context, int textViewResourceId, ArrayList<NhanVien> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.layoutID = textViewResourceId;
        this.myArray = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);

        if(myArray.size() > 0 && position >= 0) {
            TextView edtContent = (TextView) convertView.findViewById(R.id.edtContent);
            NhanVien nv = myArray.get(position);
            ImageView img = convertView.findViewById(R.id.img_item);

            edtContent.setText(nv.toString());
            if(nv.getGender())
                img.setImageResource(R.drawable.girlicon);
            else
                img.setImageResource((R.drawable.boyicon));
        }
        return  convertView;
    }
}