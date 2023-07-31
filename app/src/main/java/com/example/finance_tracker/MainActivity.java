package com.example.finance_tracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        TextView tagline = findViewById(R.id.tagline);
        TextView welcome_msg = findViewById(R.id.welcome_msg);
        welcome_msg.setText("FinanceFolio");
        tagline.setText("Your personal finance tracker");


        AlphaAnimation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
        fadeInAnimation.setDuration(1000);


        AlphaAnimation fadeInTaglineAnimation = new AlphaAnimation(0.0f, 1.0f);
        fadeInTaglineAnimation.setStartOffset(500);
        fadeInTaglineAnimation.setDuration(1000);


        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(fadeInAnimation);
        animationSet.addAnimation(fadeInTaglineAnimation);

        tagline.startAnimation(animationSet);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, loginscreen.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}
