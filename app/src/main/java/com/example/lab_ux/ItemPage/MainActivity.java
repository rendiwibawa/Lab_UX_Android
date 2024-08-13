package com.example.lab_ux.ItemPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.lab_ux.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;


    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}