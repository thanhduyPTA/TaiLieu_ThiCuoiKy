package com.oncuoiky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<NhanVien> arrayNV = null;
    MyArrayAdapter adapter = null;
    ListView listView = null;
    Button btnNhap;
    ImageButton imbXoa;
    EditText edtID, edtName;
    RadioGroup radGroup;

    // dùng để lưu vào vùng nhớ
    SharedPreferences sharedPreferences;

    Spinner spinCombobox;
    ArrayList<NhanVien> arraySpiner = null;
    ArrayAdapter<NhanVien> adapterSpnier = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNhap = findViewById(R.id.btnNhap);
        radGroup = findViewById(R.id.radGroup);
        imbXoa = findViewById(R.id.imbXoa);
        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);

        // tạo spiner
        createSpiner();

        listView = findViewById(R.id.listView);
        arrayNV = new ArrayList<>();
        adapter = new MyArrayAdapter(this, R.layout.my_item_layout, arrayNV);
        listView.setAdapter(adapter);

        // lưu trạng thái
        sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE);
        // nạp lại trạng thái trước đó
        loadTrangThai(sharedPreferences);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luuTrangThai(sharedPreferences);

                xuLyNhap();
            }
        });

        imbXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyXoa();
            }
        });
    }

    // tạo spiner
    public void createSpiner() {
        spinCombobox = findViewById(R.id.spinCombox);

        arraySpiner = new ArrayList<>();
        adapterSpnier = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, arraySpiner);

        adapterSpnier.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCombobox.setAdapter(adapterSpnier);

        NhanVien nv1 = new NhanVien("1", "Hùng", true);
        NhanVien nv2 = new NhanVien("2", "Bảo", false);
        NhanVien nv3 = new NhanVien("3", "Chí", false);

        arraySpiner.add(nv1);
        arraySpiner.add(nv2);
        arraySpiner.add(nv3);
        adapterSpnier.notifyDataSetChanged();

        // bắt sự kiện của Spninner
        spinCombobox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arraySpiner.get(i).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // Tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // bắt sự kiện menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.dsSV:
                Toast.makeText(MainActivity.this, "Danh sách nhân viên", Toast.LENGTH_LONG).show();
                break;
            case R.id.dsLop:
                Toast.makeText(MainActivity.this, "Danh sách lớp", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // lưu trạng thái ứng dụng với Shared Preference
    public void luuTrangThai(SharedPreferences shared) {

        // tạo một editor từ SharedPreference
        SharedPreferences.Editor editor = shared.edit();
        // lưu trữ các trạng thái nhập giá trị
        editor.putString("Id", edtID.getText().toString());
        editor.putString("Name", edtName.getText().toString());

        boolean gender = false;
        if (radGroup.getCheckedRadioButtonId() == R.id.radNu)
            gender = true;

        editor.putBoolean("Gender", gender);

        editor.commit();
    }
    public void loadTrangThai(SharedPreferences share) {
        edtID.setText(share.getString("Id", ""));
        edtName.setText(share.getString("Name", ""));
        boolean ckb = share.getBoolean("Gender", false);
        if (ckb == true)
            findViewById(R.id.radNu).setClickable(true);
        else
            findViewById(R.id.radNam).setClickable(true);
    }

    public void xuLyNhap() {
        String ma = edtID.getText().toString();
        String ten = edtName.getText().toString();
        boolean gt = false;
        if (radGroup.getCheckedRadioButtonId() == R.id.radNu)
            gt = true;

        NhanVien nv = new NhanVien(ma, ten, gt);

        arrayNV.add(nv);
        adapter.notifyDataSetChanged();

        edtID.setText("");
        edtName.setText("");
        edtID.requestFocus();
    }

    public void xuLyXoa() {
        for (int i = listView.getChildCount() - 1; i >= 0; i--) {

            // lấy view
            View v = listView.getChildAt(i);
            CheckBox checkBox = v.findViewById(R.id.ckbChoose);

            if (checkBox.isChecked())
                arrayNV.remove(i);
        }

        adapter.notifyDataSetChanged();
    }
}