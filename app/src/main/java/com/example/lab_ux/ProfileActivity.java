package com.example.lab_ux;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

    }

    public ProfileActivity() {
    }

    public ProfileActivity(int contentLayoutId) {
        super(contentLayoutId);
    }
}
