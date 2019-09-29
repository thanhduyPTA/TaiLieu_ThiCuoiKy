package com.app_sendaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    EditText edName, edCMND;
    TextView txtBangCap, txtSoThich, txtInfor;
    Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edName = findViewById(R.id.edName);
        edCMND = findViewById(R.id.edCMND);
        txtBangCap = findViewById(R.id.txtBangCap);
        txtSoThich = findViewById(R.id.edHobbies);
        txtInfor = findViewById(R.id.edInfor);
        btnResult = findViewById(R.id.btnResult);

        // Nhận dữ liệu từ intent
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("data");

        Person person = (Person) bundle.getSerializable("person");

        edName.setText(person.getTen());
        edCMND.setText(person.getCmnd());
        txtBangCap.setText("Bằng cấp: " + person.getBangcap());

        String hobs = "";
        int i = 0;
        for (String s : person.getHobbies()) {
            i++;
            if (i == person.getHobbies().size())
                hobs += s;
            else
                hobs += s + ", ";
        }

        txtSoThich.setText("Sở thích: " + hobs);
        txtInfor.setText("Thông tin: " + person.getInfor());


        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gửi lại dữ liệu
                Intent intentResult = new Intent();
                intentResult.putExtra("information", "Xin chào nhe");
                setResult(RESULT_OK, intentResult);
                finish();
            }
        });
    }

}
