package com.example.bananaandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {
    private Button signUp;
    private Button signIn;
    private EditText name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = findViewById(R.id.sign_up_button);
        signIn = findViewById(R.id.sign_in_button);
        name = findViewById(R.id.username);

        signUp.setOnClickListener(v -> {
            String nameStr = name.getText().toString();
            if (nameStr.isEmpty()) {
                Toast.makeText(getApplicationContext(), "name is empty", Toast.LENGTH_LONG).show();
            } else if (ServerConnection.getInstance().register(nameStr)) {
                Toast.makeText(getApplicationContext(), "register succeeded", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "register failed", Toast.LENGTH_LONG).show();
            }
        });

        signIn.setOnClickListener(v -> {
            String nameStr = name.getText().toString();
            if (nameStr.isEmpty()) {
                Toast.makeText(getApplicationContext(), "name is empty", Toast.LENGTH_SHORT).show();
            } else if (ServerConnection.getInstance().connect(nameStr)) {
                Toast.makeText(getApplicationContext(), "sign in succeeded", Toast.LENGTH_SHORT).show();;
            } else {
                Toast.makeText(getApplicationContext(), "sign in failed", Toast.LENGTH_SHORT).show();;
            }
        });
    }
}
