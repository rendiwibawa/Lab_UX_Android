package com.example.lab_ux.ItemPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lab_ux.HomePage.HomeActivity;
import com.example.lab_ux.R;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailPrice;
    ImageView detailImage;
    EditText inputEmail;
    Button buttonSubmit;
    ImageButton buttonBack;
    Spinner paymentMethodSpinner;
    AlertDialog.Builder builderDialog;
    AlertDialog alertDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Setup toolbar with back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Pilih Jadwal");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailPrice = findViewById(R.id.detailPrice);
        inputEmail = findViewById(R.id.input_email);
        buttonSubmit = findViewById(R.id.button_submitPayment);
        paymentMethodSpinner = findViewById(R.id.paymentMethodSpinner);

        paymentMethodSpinner.setSelection(0);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getInt("Desc"));
            detailImage.setImageResource(bundle.getInt("Image"));
            getSupportActionBar().setTitle(bundle.getString("Title"));
            detailPrice.setText(bundle.getString("Price"));
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                if (email.isEmpty()) {
                    showAlertDialog(R.layout.failed_empty_email_dialog, false);
                } else if (!email.contains("@gmail.com")) {
                    showAlertDialog(R.layout.failed_containt_email_dialog, false);
                } else {
                    showAlertDialog(R.layout.success_dialog, true);

                }
            }
        });

//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog(int myLayout, final boolean isSuccessful){
        builderDialog = new AlertDialog.Builder(this);
        View layoutView = getLayoutInflater().inflate(myLayout, null);

        AppCompatButton dialogButton = layoutView.findViewById(R.id.buttonOKDialog);
        builderDialog.setView(layoutView);
        alertDialog = builderDialog.create();
        alertDialog.show();

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (isSuccessful){
                    Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}