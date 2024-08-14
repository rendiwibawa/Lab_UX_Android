package com.example.lab_ux.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.lab_ux.HomePage.HomeActivity;
import com.example.lab_ux.ItemPage.MainActivity;
import com.example.lab_ux.Login.LoginActivity;
import com.example.lab_ux.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // Setup toolbar with back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView usernameTextView = findViewById(R.id.emailUser);
        TextView emailTextView = findViewById(R.id.email);

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Guest");
        String email = sharedPreferences.getString("username", "Guest");

        usernameTextView.setText(username);
        emailTextView.setText(email + "@gmail.com");

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
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
                Intent homeIntent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.nav_items:
                Intent itemsIntent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(itemsIntent);
                return true;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(ProfileActivity.this, ProfileActivity.class);
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
                v.startAnimation(AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.flip_up_out));
                dialog.dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.flip_up_out));
                dialog.dismiss();
                // Handle logout action here, e.g., navigate to login screen
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dialog.show();
        dialog.findViewById(R.id.dialog_container).startAnimation(AnimationUtils.loadAnimation(this, R.anim.flip_up_in));
    }


}