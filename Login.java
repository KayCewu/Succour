package com.example.succour;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn, btnReg;
    TextView mCreateBtn;
    private Button button;
    private Button btnDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // This is the "register" button. It opens up the registration page
        button = (Button) findViewById(R.id.btnRegisterK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        //This button opens the dashpage
        btnDash = (Button) findViewById(R.id.registerBtn);
        btnDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
    }
    // Method to open the registration page
    public void openActivity2() {
        Intent intent = new Intent(this, RegPage.class);
        startActivity(intent);
    }
    // This method opens the dashboard page
    public void openActivity3() {
        Intent intent = new Intent(this, DashPage.class);
        startActivity(intent);
    }

}
