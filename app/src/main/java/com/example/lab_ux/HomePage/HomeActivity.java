package com.example.lab_ux.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

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
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.TextView;

import com.example.lab_ux.ItemPage.DataClass;
import com.example.lab_ux.ItemPage.DetailActivity;
import com.example.lab_ux.ItemPage.MainActivity;
import com.example.lab_ux.Login.LoginActivity;
import com.example.lab_ux.Profile.ProfileActivity;
import com.example.lab_ux.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView welcome;
    private ViewFlipper carousel;
    private ImageView prev;
    private ImageView next;
    private Button buttonProfile;
    private Button buttonItemPage;
    private List<DataClass> dataList;
    private RecyclerView recyclerView;
    private HorizontalAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private FragmentAdapter fragmentAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Setup toolbar with back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home");
        }

        welcome = findViewById(R.id.welcome);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String username = bundle.getString("Username");
            welcome.setText("Welcome, " + username);
        }

        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        carousel = findViewById(R.id.carousel);

        // Daftar data
        dataList = new ArrayList<>();
        dataList.add(new DataClass("Brick Kit", R.string.BrickKit, "$ 5.00", R.drawable.asset1, "Versatile marble game asset pack"));
        dataList.add(new DataClass("Waterfall Kit", R.string.WaterCraftKit, "$ 10.00", R.drawable.asset2, "Comprehensive watercraft game asset pack."));
        dataList.add(new DataClass("Survival Kit", R.string.SurvivalKit, "$ 20.00", R.drawable.asset3, "Essential survival game asset pack."));
        dataList.add(new DataClass("Platformer Kit", R.string.PlatformerKit, "$ 15.00", R.drawable.asset4, "Complete platformer game asset pack."));
        dataList.add(new DataClass("Marble Kit", R.string.MarbleKit, "$ 19.00", R.drawable.asset5, "High-quality marble game assets."));

        // Menambahkan gambar ke ViewFlipper dan mengatur OnClickListener
        for (final DataClass data : dataList) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.carousel_border);
            imageView.setImageResource(data.getDataImage());

            carousel.addView(imageView);
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
                carousel.startFlipping();
                carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_right);
                carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_left);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.trendingItems);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new HorizontalAdapter(HomeActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Terms"));
        tabLayout.addTab(tabLayout.newTab().setText("Conditions"));

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.custom_tab);
                TextView tabTextView = tab.getCustomView().findViewById(R.id.custom_tab_text);
                if (tabTextView != null) {
                    tabTextView.setText(tab.getText()); // Set the text to the custom view
                }
            }
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(fragmentAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabLayout.selectTab(tab);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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


