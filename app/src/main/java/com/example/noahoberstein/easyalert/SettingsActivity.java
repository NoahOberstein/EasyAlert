package com.example.noahoberstein.easyalert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import static com.example.noahoberstein.easyalert.R.id.password;
import static com.example.noahoberstein.easyalert.R.id.phoneNo;
import static com.example.noahoberstein.easyalert.R.layout.settings;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    EditText phn;
    String phn1="";
    EditText pass;
    String pass1="";
    CheckBox passt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(settings);
        findViewById(R.id.lBtn).setOnClickListener(this);
        findViewById(R.id.mBtn).setOnClickListener(this);
        findViewById(R.id.passt).setOnClickListener(this);
        phn= (EditText) findViewById(phoneNo);
        pass= (EditText) findViewById(password);
        passt=(CheckBox) findViewById(R.id.passt);
        //SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        //phn.setText(prefs.getString("phn1", phn1));


    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.lBtn){
            launchActivityL();
        }else if(v.getId()==R.id.mBtn) {
            launchActivityM();
        }
       /* else if(passt.isChecked()){
                    try {
                        SmsManager.getDefault().sendTextMessage(phn1, null, pass1, null, null);
                    } catch (Exception e) {
                        AlertDialog.Builder alertDialogBuilder = new
                                AlertDialog.Builder(this);
                        AlertDialog dialog = alertDialogBuilder.create();
                        dialog.setMessage(e.getMessage());
                        dialog.show();
                    }
                }*/
            }
    @Override
    protected void onPause(){
        super.onPause();
        phn1=phn.getText().toString();
        pass1=pass.getText().toString();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("phn1", phn1);
        editor.putString("pass1", pass1);
        // Commit the edits!
        editor.apply();
    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        phn1=settings.getString("phn1", null);
        phn.setText(phn1);
        pass1=settings.getString("pass1", null);
        pass.setText(pass1);
    }
    private void launchActivityL(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    private void launchActivityM(){
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }
}
