package com.example.heyfoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.HandlerThread;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class HomeActivity extends AppCompatActivity {

    private TextView textviewuser,textviewtimer;
    String value;
    MainActivity abc;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onUserInteraction();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 60000);

        abc = new MainActivity();

        textviewtimer = findViewById(R.id.timer);
        textviewuser = findViewById(R.id.userhome);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            value = bundle.getString("key");
        }
        textviewuser.setText("WELCOME " + value);

    }

    public void onUserInteraction(){
        super.onUserInteraction();
        timer.cancel();
    }
}