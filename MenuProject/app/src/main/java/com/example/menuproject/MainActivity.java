package com.example.menuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button_newActivity;
    private Button button_SMS;
    private Button button_PHONE;
    private Button button_WEB;
    private Button button_MAP;
    private Button button_SHARE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_newActivity = (Button) findViewById(R.id.button_newActivity);
        button_newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();

            }

        });

        button_SMS = (Button) findViewById(R.id.button_sms);
        button_SMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }

        });

        button_PHONE = (Button) findViewById(R.id.button_phone);
        button_PHONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }

        });

        button_WEB = (Button) findViewById(R.id.button_web);
        button_WEB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSite();
            }

        });

        button_MAP = (Button) findViewById(R.id.button_map);
        button_MAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMap();
            }

        });

        button_SHARE = (Button) findViewById(R.id.button_share);
        button_SHARE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchShare();
            }

        });
    }

    public void openNewActivity() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    public void sendSMS() {
        final Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("smsto:" + Uri.encode("1234567890")));
        smsIntent.putExtra("sms_body", "Ramesh Kyasaram");
        startActivity(smsIntent);
    }

    public void makeCall() {
        final Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:1234567890"));
        startActivity(callIntent);
    }

    public void launchSite() {
        final Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
        startActivity(websiteIntent);
    }

    public void launchMap(){
        String geoUri = String.format("geo:19.2307,72.8567");
        Uri geo = Uri.parse(geoUri);
        Intent geoMap = new Intent(Intent.ACTION_VIEW, geo);
        startActivity(geoMap);
    }

    public void launchShare(){
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent,"Share the Love"));
    }

    //MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Toast t = Toast.makeText(this, "Settings",Toast.LENGTH_SHORT);
            t.show();
            return true;
        }

        else if(id == R.id.action_help){
            Intent helpIntent = new Intent(this, HelpActivity.class);
            startActivity(helpIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);


    }
}