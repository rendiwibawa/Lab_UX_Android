package com.example.lab_ux.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lab_ux.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView usernameTextView = findViewById(R.id.emailUser);
        TextView emailTextView = findViewById(R.id.email);

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Guest");
        String email = sharedPreferences.getString("username", "Guest");

        usernameTextView.setText(username);
        emailTextView.setText(email + "@gmail.com");

    }
}