package com.example.lab_ux.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lab_ux.Login.LoginActivity;
import com.example.lab_ux.Profile.ProfileActivity;
import com.example.lab_ux.R;

public class HomeActivity extends AppCompatActivity {

    private  Button buttonProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonProfile = findViewById(R.id.buttonProfile);

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}