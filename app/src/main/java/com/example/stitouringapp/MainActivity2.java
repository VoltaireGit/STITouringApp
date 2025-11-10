package com.example.stitouringapp;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.stitouringapp.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private ImageView previewImage;
    private LinearLayout dotsLayout;
    private int[] images = {
            R.drawable.ic_stilogo,
            R.drawable.forgotpassword_sticker,
            R.drawable.otp_sticker,
            R.drawable.ic_stilogo
    };
    private int currentIndex = 0;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_tour,
                R.id.navigation_profile
        ).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
        NavigationUI.setupWithNavController(binding.navView, navController);


        previewImage = findViewById(R.id.previewImage);
        dotsLayout = findViewById(R.id.dotsLayout);

        previewImage.setImageResource(images[currentIndex]);
        updateDots();

        gestureDetector = new GestureDetector(this, new SwipeGestureListener());
        previewImage.setClickable(true);
        previewImage.setFocusable(true);
        previewImage.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });
    }

    private void showNextImage() {
        currentIndex = (currentIndex + 1) % images.length;
        previewImage.setImageResource(images[currentIndex]);
        updateDots();
    }

    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        previewImage.setImageResource(images[currentIndex]);
        updateDots();
    }

    private void updateDots() {
        for (int i = 0; i < dotsLayout.getChildCount(); i++) {
            View dot = dotsLayout.getChildAt(i);
            if (i == currentIndex) {
                dot.setBackgroundResource(R.drawable.dot_active);
            } else {
                dot.setBackgroundResource(R.drawable.dot_inactive);
            }
        }
    }

    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    showPreviousImage();
                } else {
                    showNextImage();
                }
                return true;
            }
            return false;
        }
    }
}
