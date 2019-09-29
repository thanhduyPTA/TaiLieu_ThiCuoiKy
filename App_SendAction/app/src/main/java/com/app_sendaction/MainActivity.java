package com.app_sendaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtCMND, edtInfor;
    RadioGroup radGroup;
    CheckBox ckbBao, ckbSach, ckbCode;
    Button btnGui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtCMND = findViewById(R.id.edtCMND);
        edtInfor = findViewById(R.id.edtInfo);

        radGroup = findViewById(R.id.radGroup);
        ckbBao = findViewById(R.id.ckbBao);
        ckbSach = findViewById(R.id.ckbSach);
        ckbCode = findViewById(R.id.ckbCode);
        btnGui = findViewById(R.id.btnGui);


        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String cmnd = edtCMND.getText().toString();
                String infor = edtInfor.getText().toString();

                String bangcap = "Đại học";
                if(radGroup.getCheckedRadioButtonId() == R.id.radCD)
                    bangcap = "Cao đẳng";
                else if(radGroup.getCheckedRadioButtonId() == R.id.radTC)
                    bangcap = "Trung cấp";

                List<String> hobbies = new ArrayList<>();
                if (ckbBao.isChecked())
                    hobbies.add("Đọc báo");
                if(ckbSach.isChecked())
                    hobbies.add("Đọc sách");
                if(ckbCode.isChecked())
                    hobbies.add("Đọc coding");

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                Bundle bundle = new Bundle();
                Person p = new Person(name, cmnd, bangcap, hobbies, infor);
                bundle.putSerializable("person", p);

                intent.putExtra("data", bundle);

//                startActivity(intent);          // chỉ dùng để chuyền đi
                startActivityForResult(intent, 999);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 999 && resultCode == RESULT_OK && data != null) {
            String information = data.getStringExtra("information");
            edtInfor.setText(information);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
