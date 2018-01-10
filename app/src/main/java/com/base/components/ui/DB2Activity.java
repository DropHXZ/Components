package com.base.components.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.base.components.R;

public class DB2Activity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_age;
    private EditText et_gender;
    private EditText et_phone;
    private Button btn_add_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db2);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_gender = findViewById(R.id.et_gender);
        et_phone = findViewById(R.id.et_phone);
        btn_add_db = findViewById(R.id.btn_add_db);
        initOnclick();
    }

    private void initOnclick() {
        btn_add_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_name.getText().length() != 0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", et_name.getText().toString());
                    bundle.putString("age", et_age.getText().toString());
                    bundle.putString("gender", et_gender.getText().toString());
                    bundle.putString("phone", et_phone.getText().toString());
                    intent.putExtra("bundle",bundle);
                    setResult(0x02, intent);
                    finish();
                }
            }
        });
    }
}
