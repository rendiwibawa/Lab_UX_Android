package com.example.lab_ux.ItemPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.lab_ux.HomePage.HomeActivity;
import com.example.lab_ux.Login.LoginActivity;
import com.example.lab_ux.Profile.ProfileActivity;
import com.example.lab_ux.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // Setup toolbar with back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Asset");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        androidData = new DataClass("Brick Kit", R.string.BrickKit, "$ 5.00", R.drawable.asset1,
                "Versatile marble game asset pack");
        dataList.add(androidData);

        androidData = new DataClass("Waterfall Kit", R.string.WaterCraftKit, "$ 10.00", R.drawable.asset2,
                "Comprehensive watercraft game asset pack.");
        dataList.add(androidData);

        androidData = new DataClass("Survival Kit", R.string.SurvivalKit, "$ 20.00", R.drawable.asset3,
                "Essential survival game asset pack.");
        dataList.add(androidData);

        androidData = new DataClass("Platformer Kit", R.string.PlatformerKit, "$ 15.00", R.drawable.asset4,
                "Complete platformer game asset pack.");
        dataList.add(androidData);

        androidData = new DataClass("Marble Kit", R.string.MarbleKit, "$ 19.00", R.drawable.asset5,
                "High-quality marble game assets.");
        dataList.add(androidData);

        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }


//    MENUU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
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
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.nav_items:
                Intent itemsIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(itemsIntent);
                return true;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
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
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.flip_up_out));
                dialog.dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.flip_up_out));
                dialog.dismiss();
                // Handle logout action here, e.g., navigate to login screen
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dialog.show();
        dialog.findViewById(R.id.dialog_container).startAnimation(AnimationUtils.loadAnimation(this, R.anim.flip_up_in));
    }

}
