package com.example.implicit_intents;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;
    private EditText mPhoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mWebsiteEditText = findViewById(R.id.editTextText);
        mLocationEditText = findViewById(R.id.editTextText2);
        mShareTextEditText = findViewById(R.id.editTextText3);
        mPhoneEditText = findViewById(R.id.editTextText4);

        Button buttonWebsite = findViewById(R.id.button);
        Button buttonLocation = findViewById(R.id.button2);
        Button buttonShare = findViewById(R.id.button3);
        Button buttonCall = findViewById(R.id.button4);

        buttonWebsite.setOnClickListener(v -> openWebsite());
        buttonLocation.setOnClickListener(v -> openLocation());
        buttonShare.setOnClickListener(v -> shareText());
        buttonCall.setOnClickListener(v -> callPhone());
    }

    public void openWebsite() {
        String inputUrl = mWebsiteEditText.getText().toString().trim();
        if (inputUrl.isEmpty()) {
            Toast.makeText(this, "Vnesite URL spletne strani", Toast.LENGTH_SHORT).show();
            return;
        }
        String formattedUrl = (inputUrl.startsWith("http://") || inputUrl.startsWith("https://"))
                ? inputUrl
                : "https://" + inputUrl;
        Uri webpage = Uri.parse(formattedUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("ImplicitIntents", "Ni mogoče odpreti spletne strani", e);
            Toast.makeText(this, "Aplikacija za brskanje ni najdena", Toast.LENGTH_SHORT).show();
        }
    }

    public void openLocation() {
        String location = mLocationEditText.getText().toString().trim();
        if (location.isEmpty()) {
            Toast.makeText(this, "Vnesite lokacijo", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri addressUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("ImplicitIntents", "Ni mogoče odpreti lokacije", e);
            Toast.makeText(this, "Aplikacija za zemljevide ni najdena", Toast.LENGTH_SHORT).show();
        }
    }

    public void shareText() {
        String text = mShareTextEditText.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(this, "Vnesite besedilo za deljenje", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        try {
            startActivity(Intent.createChooser(intent, "Deli besedilo z:"));
        } catch (ActivityNotFoundException e) {
            Log.e("ImplicitIntents", "Ni mogoče deliti besedila", e);
            Toast.makeText(this, "Aplikacija za deljenje ni najdena", Toast.LENGTH_SHORT).show();
        }
    }

    public void callPhone() {
        String phoneNumber = mPhoneEditText.getText().toString().trim();
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Vnesite telefonsko številko", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri phoneUri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, phoneUri);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("ImplicitIntents", "Ni mogoče odpreti klicatelja", e);
            Toast.makeText(this, "Aplikacija za klicanje ni najdena", Toast.LENGTH_SHORT).show();
        }
    }
}
