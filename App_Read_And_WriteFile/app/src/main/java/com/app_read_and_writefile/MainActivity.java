package com.app_read_and_writefile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Button btnWriteInternal, btnReadInternal, btnWriteCache, btnReadStatic, btnWriteExternal, btnReadExternal;
    EditText edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWriteInternal = findViewById(R.id.btnWriteInternal);
        btnWriteCache = findViewById(R.id.btnWriteCache);
        btnWriteExternal = findViewById(R.id.btnWriteExternal);

        btnReadInternal = findViewById(R.id.btnReadInternal);
        btnReadStatic = findViewById(R.id.btnReadStatic);
        btnReadExternal = findViewById(R.id.btnReadExternal);

        edtContent = findViewById(R.id.edtContent);
        edtContent.setText(getCacheDir().getAbsolutePath());

        btnWriteInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeInternalFile();
            }
        });

        btnReadInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readInternalFile();
            }
        });

        btnWriteCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeCache();
            }
        });

        btnReadStatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readStaticFile();
            }
        });
    }

    private void writeInternalFile() {
        try {
            // mỡ file
            FileOutputStream fos = openFileOutput("sample.txt", Context.MODE_PRIVATE);
            fos.write(edtContent.getText().toString().getBytes());
            fos.close();

            edtContent.setText("");
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInternalFile() {
        try {
            // load dữ liệu từ bộ nhớ trong
            FileInputStream fis = openFileInput("sample.txt");
            DataInputStream dataInputStream = new DataInputStream(fis);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

            String line = "";
            while (bufferedReader.ready()) {
                line += bufferedReader.readLine();
            }

            Toast.makeText(MainActivity.this, "Loading data", Toast.LENGTH_LONG).show();
            edtContent.setText(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCache() {
        try {
            FileOutputStream fos = openFileOutput(getCacheDir().getAbsolutePath() + "/test.txt", Context.MODE_PRIVATE);
            fos.write(edtContent.getText().toString().getBytes());
            fos.close();
            Toast.makeText(MainActivity.this, "Saved into cache", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readStaticFile() {

        // Tạo thư mục trong res với tên raw và file song.txt
        InputStream inputStream = getResources().openRawResource(R.raw.song);

        Scanner scan = new Scanner(inputStream);

        String data = "";
        while (scan.hasNextLine()) {
            data += scan.nextLine();
            if (scan.hasNext() == true)
                data += "\n";
        }
        scan.close();
        edtContent.setText(data);
    }
}
