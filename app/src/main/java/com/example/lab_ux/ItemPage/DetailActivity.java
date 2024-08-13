package com.example.lab_ux.ItemPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lab_ux.R;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailPrice;
    ImageView detailImage;
    EditText inputEmail;
    Button buttonSubmit;
    ImageButton buttonBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Detail Asset");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        detailPrice = findViewById(R.id.detailPrice);
        inputEmail = findViewById(R.id.input_email);
        buttonSubmit = findViewById(R.id.button_submitPayment);
        buttonBack = findViewById(R.id.button_backItemPage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getInt("Desc"));
            detailImage.setImageResource(bundle.getInt("Image"));
            detailTitle.setText(bundle.getString("Title"));
            detailPrice.setText(bundle.getString("Price"));
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                if (email.isEmpty()) {
                    inputEmail.setError("Email tidak boleh kosong");
                } else if (!email.contains("@gmail.com")) {
                    inputEmail.setError("Email harus mengandung @gmail.com");
                } else {
                    // Email valid, lanjutkan dengan proses submit
                    inputEmail.setError(null); // Hapus pesan error jika ada
                    Toast.makeText(DetailActivity.this, "Email valid, proses submit dilanjutkan", Toast.LENGTH_SHORT).show();

                    // Lakukan tindakan submit di sini (misalnya, kirim data ke server)
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}