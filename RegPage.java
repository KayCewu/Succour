package com.example.succour;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://succuorreg-default-rtdb.firebaseio.com/");
    EditText UserName, UserSurname,UserPhoneNo,UserEmail,Userpassword,UserPassConfirm;
    private ProgressBar PBar;


    Button RegButton;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_page);
        UserName= findViewById(R.id.firstname);
        UserSurname=findViewById(R.id.lastname);
        UserPhoneNo=findViewById(R.id.number);
        UserEmail=findViewById(R.id.emailaddress);
        Userpassword=findViewById(R.id.password);
        UserPassConfirm=findViewById(R.id.passwordconfirm);
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();
            }
        });
        RegButton=findViewById(R.id.register);
        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = UserName.getText().toString().trim();
                String lastname = UserSurname.getText().toString().trim();
                String number = UserPhoneNo.getText().toString().trim();
                String emailaddress = UserEmail.getText().toString().trim();
                String password = Userpassword.getText().toString().trim();
                String passwordconfirm = UserPassConfirm.getText().toString().trim();

                //validation function and passing of all parameters
                boolean check = validateinfo(firstname, lastname, number, emailaddress, password, passwordconfirm);
                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is valid", Toast.LENGTH_SHORT).show();
                    PBar.setVisibility(View.GONE);
                    Login();
                }
            }
        });

    }
    private Boolean validateinfo(String firstname, String lastname, String number, String emailaddress, String password, String passwordconfirm) {
        if(firstname.length()==0){
            UserName.requestFocus();
            UserName.setError("Field cannot be left empty");
            return false;
        }
        else if (!firstname.matches("[a-zA-Z]+")){
            UserName.requestFocus();
            UserName.setError("Enter alphabets only");
            return false;
        }
        else if (!lastname.matches("[a-zA-Z]+")) {
            UserSurname.requestFocus();
            UserSurname.setError("Enter alphabets only");
            return false;
        }
        else if (number.length()==0) {
            UserPhoneNo.requestFocus();
            UserPhoneNo.setError("Please enter phone number here");
            return false;
        }
        else if (number.matches("^[+][0-9]{10,13}$")) {
            UserPhoneNo.requestFocus();
            UserPhoneNo.setError("Field cannot be left empty");
            return false;
        }
        else if (emailaddress.length()==0){
            UserEmail.requestFocus();
            UserEmail.setError("Field cannot be left empty");
            return false;
        }
        else if (!emailaddress.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            UserEmail.requestFocus();
            UserEmail.setError("Email address not in correct format");
            return false;
        }
        else if (password.length()<=5) {
            Userpassword.requestFocus();
            Userpassword.setError("Password too short");
            return false;
        }
        else if (passwordconfirm.length() <=5) {
            UserPassConfirm.requestFocus();
            UserPassConfirm.setError("Password too short");
            return false;
        }
        else if (!password.matches(passwordconfirm)){
            UserPassConfirm.requestFocus();
            UserPassConfirm.setError("Does not match Password");
            return false;
        }
        else{
            mAuth.createUserWithEmailAndPassword(emailaddress, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                UserCred user=new UserCred(firstname,lastname,number,emailaddress, password);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    });

            return true;
        }

    }
    public void GoBack() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void Login(){
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }
}