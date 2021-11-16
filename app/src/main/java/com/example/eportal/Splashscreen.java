package com.example.eportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {

    ImageView logo;
    TextView one;
    TextView two;
    TextView three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
//        LinearLayout ly = (LinearLayout) view;
        logo = (ImageView) findViewById(R.id.main);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        Animation animOne = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.one);
        Animation animTwo = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.two);
        Animation animThree = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.three);
        logo.startAnimation(animation);
        one.startAnimation(animOne);
        two.startAnimation(animTwo);
        three.startAnimation(animThree);


        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };thread.start();
    }
}