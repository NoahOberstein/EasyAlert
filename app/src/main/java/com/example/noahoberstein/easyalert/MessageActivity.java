package com.example.noahoberstein.easyalert;


import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class MessageActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String PREFS_NAME = "MyPrefsFile";
    ListView lvMsg;
    SimpleCursorAdapter adapter;
    TextView lblMsg, lblNo;
    String message="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_message);
        findViewById(R.id.lBtn).setOnClickListener(this);
        findViewById(R.id.sBtn).setOnClickListener(this);
        lvMsg = (ListView) findViewById(R.id.lvMsg);
        int GET_MY_PERMISSION = 1;
        if(ContextCompat.checkSelfPermission(MessageActivity.this, android.Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MessageActivity.this,
                    android.Manifest.permission.READ_SMS)){
            /* do nothing*/
            }
            else{

                ActivityCompat.requestPermissions(MessageActivity.this,
                        new String[]{android.Manifest.permission.READ_SMS},GET_MY_PERMISSION);
            }
        }
        // Create Inbox box URI
        Uri inboxURI = Uri.parse("content://sms/inbox");

        // List required columns
        String[] reqCols = new String[] { "_id", "address", "body" };
        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();
        
        String phoneNumber = "NUMBER";
        String sms = "address='"+ phoneNumber + "'";
        // Fetch Inbox SMS Message from Built-in Content Provider
        Cursor c = cr.query(inboxURI, reqCols, sms, null, "date desc limit 10");
        // Attached Cursor with adapter and display in listview
        adapter = new SimpleCursorAdapter(this, R.layout.row, c,
                new String[] { "body", "address" }, new int[] {
                R.id.lblMsg, R.id.lblNumber });
        lvMsg.setAdapter(adapter);
        if(c.moveToFirst() && c.getCount() != 0) {
                message=c.getString(c.getColumnIndex(reqCols[2]));
        }

    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.lBtn){
            launchActivityL();
        }else if(v.getId()==R.id.sBtn) {
            launchActivityS();
        }

    }
    private void launchActivityL(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    private void launchActivityS(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("message", message);
        // Commit the edits!
        editor.apply();
    }

}
