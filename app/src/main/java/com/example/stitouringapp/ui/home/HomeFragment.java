package com.example.stitouringapp.ui.home;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.stitouringapp.R;
import com.example.stitouringapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private int[] images = {
            R.drawable.ic_stilogo,
            R.drawable.forgotpassword_sticker,
            R.drawable.otp_sticker,
            R.drawable.ic_stilogo
    };
    private int currentIndex = 0;
    private GestureDetector gestureDetector;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Setup first image
        binding.previewImage.setImageResource(images[currentIndex]);
        updateDots();

        // Gesture Detector for swipe
        gestureDetector = new GestureDetector(getContext(), new SwipeGestureListener());
        binding.previewImage.setClickable(true);
        binding.previewImage.setFocusable(true);
        binding.previewImage.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        return root;
    }

    private void showNextImage() {
        currentIndex = (currentIndex + 1) % images.length;
        binding.previewImage.setImageResource(images[currentIndex]);
        updateDots();
    }

    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        binding.previewImage.setImageResource(images[currentIndex]);
        updateDots();
    }

    private void updateDots() {
        for (int i = 0; i < binding.dotsLayout.getChildCount(); i++) {
            View dot = binding.dotsLayout.getChildAt(i);
            dot.setBackgroundResource(i == currentIndex ? R.drawable.dot_active : R.drawable.dot_inactive);
        }
    }

    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) showPreviousImage();
                else showNextImage();
                return true;
            }
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
