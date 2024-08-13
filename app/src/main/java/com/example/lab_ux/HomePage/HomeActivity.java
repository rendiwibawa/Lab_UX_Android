package com.example.lab_ux.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab_ux.ItemPage.MainActivity;
import com.example.lab_ux.Login.LoginActivity;
import com.example.lab_ux.Profile.ProfileActivity;
import com.example.lab_ux.R;

public class HomeActivity extends AppCompatActivity {

    private  Button buttonProfile, buttonItemPage;

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        return super.onCreatePanelMenu(featureId, menu);
    }

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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(this, HomeActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.nav_items:
                Intent itemsIntent = new Intent(this, MainActivity.class);
                startActivity(itemsIntent);
                return true;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
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
                v.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.flip_up_out));
                dialog.dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.flip_up_out));
                dialog.dismiss();
                // Handle logout action here, e.g., navigate to login screen
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dialog.show();
        dialog.findViewById(R.id.dialog_container).startAnimation(AnimationUtils.loadAnimation(this, R.anim.flip_up_in));
    }




}