package com.example.senturk.gyk_proje;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH=6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView gif=(ImageView)findViewById(R.id.splashscreen);
        Ion.with(gif).load("https://s-media-cache-ak0.pinimg.com/originals/90/80/60/9080607321ab98fa3e70dd24b2513a20.gif");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
