package com.example.lab_ux.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.widget.TextView;

import com.example.lab_ux.ItemPage.DataClass;
import com.example.lab_ux.ItemPage.MainActivity;
import com.example.lab_ux.Login.LoginActivity;
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

        // Setup toolbar with back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home");
        }

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

    //    MENUU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.navigation_menu, menu);
//
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(item.getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, spanString.length(), 0);
            item.setTitle(spanString);
        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.nav_items:
                Intent itemsIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(itemsIntent);
                return true;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                return true;
            case R.id.nav_logout:
                showLogoutDialog();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showLogoutDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_logout_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView cancelButton = dialog.findViewById(R.id.cancelButton);
        TextView okButton = dialog.findViewById(R.id.okButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.flip_up_out));
                dialog.dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.flip_up_out));
                dialog.dismiss();
                // Handle logout action here, e.g., navigate to login screen
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dialog.show();
//        dialog.findViewById(R.id.dialog_container).startAnimation(AnimationUtils.loadAnimation(this, R.anim.flip_up_in));
    }
}