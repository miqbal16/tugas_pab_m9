package com.iqbal.example.pab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginPage extends AppCompatActivity {
    EditText inputEmail;
    EditText inputPassword;
    TextView btnRegis;
    MaterialButton loginBtn;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnRegis = findViewById(R.id.nav_signUp);
        loginBtn =findViewById(R.id.btn_login);

        preferences = getSharedPreferences("UserInfo", 0);

        loginBtn.setOnClickListener((v) -> {

            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();

            String regisEmail = preferences.getString("email", "");
            String regisPassword = preferences.getString("password", "");
            String regisName = preferences.getString("name", "");

            if(email.isEmpty() || password.isEmpty()) {

                Toast.makeText(LoginPage.this, "Email or Password is empty", Toast.LENGTH_SHORT).show();
            }else {
                String message = "Login Success";
                if (email.equals(regisEmail) && password.equals(regisPassword)) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginPage.this)
                            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                            .setContentTitle("Notification Login")
                            .setContentText(message)
                            .setAutoCancel(true);
                    Intent intent = new Intent(LoginPage.this, HomePage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("name", regisName);
                    intent.putExtra("email", regisEmail);
                    intent.putExtra("password", regisPassword);
                    PendingIntent pendingIntent = PendingIntent.getActivity(LoginPage.this, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0, builder.build());
                }else {
                    message = "Username or Password is Wrong!";
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginPage.this)
                            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                            .setContentTitle("Notification Login")
                            .setContentText(message)
                            .setAutoCancel(true);
                    NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0, builder.build());
                }
            }

        });


        btnRegis.setOnClickListener((v) -> {
            Intent regisScreen = new Intent(LoginPage.this, RegisterPage.class);
            startActivity(regisScreen);
        });

    }
}