package com.example.lab_ux;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class HomeActivity extends AppCompatActivity {

    private ViewFlipper carousel;
    private ImageView prev;
    private ImageView next;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        carousel = findViewById(R.id.carousel);
        int[] images = {R.drawable.asset1,
            R.drawable.asset2,
            R.drawable.asset3,
            R.drawable.asset4,
            R.drawable.asset5};

        for(int image : images){
            ImageView imageObj = new ImageView(HomeActivity.this);
            imageObj.setBackgroundResource(R.drawable.carousel_border);
            imageObj.setImageResource((image));
//            imageObj.setOnTouchListener((View.OnTouchListener) new OnSwipe(){});
            carousel.addView(imageObj);
        }

//        gestureDetector = new GestureDetector(this, new OnSwipeTouchListener(this, carousel));

        // Previous Button Click
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_left);
                carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_right);
                carousel.showPrevious();
                carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_right);
                carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_left);
            };

        });

        // Next Button Click
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carousel.showNext();
            }
        });

        carousel.setOnTouchListener(new OnSwipeTouchListener(HomeActivity.this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                carousel.showNext();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_left);
                carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_right);
                carousel.showPrevious();
                carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_right);
                carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_left);
            }
        });
    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
//    }
}