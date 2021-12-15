package com.iqbal.example.pab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class RegisterPage extends AppCompatActivity {
    EditText inputEmail;
    EditText inputPassword;
    EditText inputName;
    TextView btnSigIn;
    MaterialButton regBtn;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        inputEmail = findViewById(R.id.email_reg);
        inputPassword = findViewById(R.id.password_reg);
        inputName = findViewById(R.id.name_reg);
        regBtn =findViewById(R.id.btn_reg);
        btnSigIn = findViewById(R.id.nav_signIn);

        preferences = getSharedPreferences("UserInfo", 0);

        regBtn.setOnClickListener((v) -> {
            String name = inputName.getText().toString();
            String password = inputPassword.getText().toString();
            String email = inputEmail.getText().toString();

            if(name.length() > 1) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", name);
                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(RegisterPage.this, "User Registered", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(RegisterPage.this, "Enter Value in Fields", Toast.LENGTH_SHORT).show();
            }
        });

        btnSigIn.setOnClickListener((v) -> {
            emptyField();
            finish();
        });

    }

    public void emptyField() {
        inputEmail.setText("");
        inputName.setText("");
        inputPassword.setText("");
    }
}