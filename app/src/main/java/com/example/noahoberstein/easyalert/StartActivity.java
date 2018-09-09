package com.example.noahoberstein.easyalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.blockBtn).setOnClickListener(this);
        findViewById(R.id.controlBtn).setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.blockBtn){
        launchActivityB();
        }else if(v.getId()==R.id.controlBtn) {
            launchActivityC();
        }
    }
    private void launchActivityB(){
        Intent intent = new Intent(this, BlockActivity.class);
        startActivity(intent);
    }
    private void launchActivityC(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}