package com.rich_it.library.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rich_it.library.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button getLocationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initObject();
        requiredTask();
    }

    private void initObject() {
        getLocationButton = findViewById(R.id.getLocationButton);
    }

    private void requiredTask() {
        getLocationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getLocationButton:
                Intent intent = new Intent(this, GoogleMapActivity.class);
                startActivity(intent);
                break;
        }
    }
}