package com.base.components.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.base.components.R;
import com.base.components.server.SampleService;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        init();
    }

    private void init() {
        btn_intent = findViewById(R.id.btn_intent);
        btn_intent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_intent:
                Intent intent = new Intent(this,SampleService.class);
                startService(intent);
                break;
            default:
                break;
        }
    }
}
