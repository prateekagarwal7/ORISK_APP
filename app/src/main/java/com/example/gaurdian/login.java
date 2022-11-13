package com.example.gaurdian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
     Button signupbtn,loginbtn;
     TextInputLayout username_var, pass_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        signupbtn = (Button) findViewById(R.id.login_signup);
        loginbtn = findViewById(R.id.login_login);
        username_var = findViewById(R.id.login_username);
        pass_var = findViewById(R.id.login_password);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username_var.getEditText().getText().toString();
                String pass = pass_var.getEditText().getText().toString();
                if(!user.isEmpty())
                {   username_var.setError(null);
                    username_var.setErrorEnabled(false);
                    if(!pass.isEmpty())
                    {pass_var.setError(null);
                        pass_var.setErrorEnabled(false);

                        final String username_data = username_var.getEditText().getText().toString();
                        final String pass_data = pass_var.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("user");//same path as given in firebase

                        Query checkusername = databaseReference.orderByChild("username").equalTo(username_data);

                        checkusername.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                if(snapshot.exists()){
                                username_var.setError(null);
                                username_var.setErrorEnabled(false);//for user can retype the username
                                    String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);
                                    if(passwordcheck.equals(pass_data))
                                    { pass_var.setError(null);
                                        pass_var.setErrorEnabled(false);
                                        Toast.makeText(login.this, "login successful", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(),DashBoard.class);
                                        startActivity(i);
                                        finish();


                                    }else{
                                        pass_var.setError("Wrong Password");
                                    }
                                }else{
                                       username_var.setError("user does not exist");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }else{
                        pass_var.setError("Please enter the password");
                    }
                }else{
                    username_var.setError("please enter the username");
                }
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),signup.class);
                startActivity(intent);
                finish();//if a person press thee back button he will be directed out of application
            }
        });
    }
}