package com.example.succour;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashPage extends AppCompatActivity {
    //Variable declarations, below are the certain buttons we will be using
    Button btnBack, btnPolice,btnAmbulance,btnFire,btnViolence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_page);
        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBackButton();
            }
        });
        btnPolice = (Button) findViewById(R.id.police);
        btnPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallHelp();
            }
        });
        btnFire = (Button) findViewById(R.id.Fire);
        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallHelp();
            }
        });
        btnAmbulance = (Button) findViewById(R.id.HOSPITAL);
        btnAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallHelp();
            }
        });
        btnViolence = (Button) findViewById(R.id.violence);
        btnViolence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallHelp();
            }
        });
    }
    public void GoBackButton() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    //This function calls on the CurrLocation class to send the user an alert
    public void CallHelp() {
        Intent intent = new Intent(this, CurrLocation.class);//MyCurrLocation
        startActivity(intent);
    }
}
