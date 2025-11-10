package com.example.stitouringapp.ui.Tour;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stitouringapp.R; // ✅ Correct import for your app resources

public class TourFragment extends Fragment {

    private ImageButton btnNext, btnPrev;
    private ImageView previewImage;
    private TextView locationText;

    // Replace with your actual drawable names in res/drawable/
    private final int[] images = {
            R.drawable.ic_stilogo,
            R.drawable.otp_sticker,
            R.drawable.ic_stilogo,
            R.drawable.otp_sticker
    };

    private final String[] locations = {
            "Building 1",
            "Building 2",
            "Building 3",
            "Building 4"
    };

    private int currentIndex = 0;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // ✅ Make sure your layout file name matches this (change if needed)
        View view = inflater.inflate(R.layout.fragment_tour, container, false);

        // Initialize views
        btnNext = view.findViewById(R.id.arrow_next);
        btnPrev = view.findViewById(R.id.arrow_back);
        previewImage = view.findViewById(R.id.previewImage);
        locationText = view.findViewById(R.id.location);

        updateUI();

        // Next button click
        btnNext.setOnClickListener(v -> {
            if (currentIndex < images.length - 1) {
                currentIndex++;
                updateUI();
            }
        });

        // Previous button click
        btnPrev.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateUI();
            }
        });

        return view;
    }

    private void updateUI() {
        // Update image and location label
        previewImage.setImageResource(images[currentIndex]);
        locationText.setText(locations[currentIndex]);

        // Define your tint colors (make sure these exist in colors.xml)
        int blue = requireContext().getColor(R.color.blue);
        int gray = requireContext().getColor(R.color.gray);

        // Update arrow tints based on position
        if (currentIndex == 0) {
            btnPrev.setColorFilter(gray, PorterDuff.Mode.SRC_IN);
            btnNext.setColorFilter(blue, PorterDuff.Mode.SRC_IN);
        } else if (currentIndex == images.length - 1) {
            btnNext.setColorFilter(gray, PorterDuff.Mode.SRC_IN);
            btnPrev.setColorFilter(blue, PorterDuff.Mode.SRC_IN);
        } else {
            btnPrev.setColorFilter(blue, PorterDuff.Mode.SRC_IN);
            btnNext.setColorFilter(blue, PorterDuff.Mode.SRC_IN);
        }
    }
}










//private FragmentTourBinding binding;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//            ViewGroup container, Bundle savedInstanceState) {
//        DashboardViewModel dashboardViewModel =
//                new ViewModelProvider(this).get(DashboardViewModel.class);
//
//    binding = FragmentTourBinding.inflate(inflater, container, false);
//    View root = binding.getRoot();
//
//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }



//@Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}