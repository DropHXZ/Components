package com.base.components.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.base.components.R;

/**
 * ContentProvider ContentResolver
 */
public class ProviderActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_contact, btn_provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        initView();
    }

    private void initView() {
        btn_contact = findViewById(R.id.btn_contact);
        btn_provider = findViewById(R.id.btn_provider);
        btn_contact.setOnClickListener(this);
        btn_provider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_contact:
                startActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.btn_provider:

                break;
            default:
                break;
        }
    }
}
