package com.example.lab_ux.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.lab_ux.ItemPage.DataClass;
import com.example.lab_ux.ItemPage.MainActivity;
import com.example.lab_ux.Profile.ProfileActivity;
import com.example.lab_ux.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ViewFlipper carousel;
    private ImageView prev;
    private ImageView next;
    private GestureDetector gestureDetector;
    private Button buttonProfile;
    private Button buttonItemPage;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonProfile = findViewById(R.id.buttonProfile);
        buttonItemPage = findViewById(R.id.buttonItemPage);

        buttonItemPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        carousel = findViewById(R.id.carousel);
        List<DataClass> datalist = new ArrayList<>();
        int[] images = {R.drawable.asset1,
                R.drawable.asset2,
                R.drawable.asset3,
                R.drawable.asset4,
                R.drawable.asset5};

        for (int image : images) {
            ImageView imageObj = new ImageView(HomeActivity.this);
            imageObj.setBackgroundResource(R.drawable.carousel_border);
            imageObj.setImageResource((image));
            carousel.addView(imageObj);
        }

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_left);
                carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_right);
                carousel.showPrevious();
                carousel.stopFlipping();
                carousel.setFlipInterval(3000);
                carousel.startFlipping();
                carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_right);
                carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_left);
            }

            ;

        });

        // Next Button Click
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carousel.showNext();
                carousel.stopFlipping();
                carousel.setFlipInterval(3000);
                carousel.startFlipping();
            }
        });

        carousel.setOnTouchListener(new OnSwipeTouchListener(HomeActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                carousel.showNext();
                carousel.stopFlipping();
                carousel.setFlipInterval(3000);
                carousel.startFlipping();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_left);
                carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_right);
                carousel.showPrevious();
                carousel.stopFlipping();
                carousel.setFlipInterval(3000);
                carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_right);
                carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_left);
                carousel.startFlipping();
            }
        });
    }
}