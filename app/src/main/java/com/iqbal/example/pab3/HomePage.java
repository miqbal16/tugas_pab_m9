package com.iqbal.example.pab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        TextView usrName = findViewById(R.id.name_usr);
        TextView usrEmail = findViewById(R.id.email_usr);
        TextView usrPassword = findViewById(R.id.password_usr);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");

        usrName.setText("Name: " + name);
        usrEmail.setText("Email: " + email);
        usrPassword.setText("Password: " + password);

    }
}