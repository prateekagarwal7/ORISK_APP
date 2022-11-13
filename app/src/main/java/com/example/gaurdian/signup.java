package com.example.gaurdian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    Button signup,login;
    TextInputLayout name_var,email_var,password_var,user_var,phone_var;
    FirebaseDatabase firebaseDatabase;// it is instance of the reference
    DatabaseReference reference; // in firebase data is stored in form of tree data structure

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.signup_signup);
        login = findViewById(R.id.signup_login);
        name_var  = findViewById(R.id.name);
        email_var = findViewById(R.id.email);
        password_var = findViewById(R.id.pass);
        user_var =findViewById(R.id.user);
        phone_var = findViewById(R.id.phone);


        String str_name = name_var.getEditText().getText().toString();
        String str_email = email_var.getEditText().getText().toString();
        String str_pass = password_var.getEditText().getText().toString();
        String str_user = user_var.getEditText().getText().toString();
        String str_phone = phone_var.getEditText().getText().toString();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!str_name.isEmpty())
                {     name_var.setError(null);
                    name_var.setErrorEnabled(false);
                    if(!str_user.isEmpty())
                {user_var.setError(null);
                    user_var.setErrorEnabled(false);
                    if(!str_email.isEmpty())
                {  email_var.setError(null);
                    email_var.setErrorEnabled(false);
                    if(!str_phone.isEmpty())
                    {  phone_var.setError(null);
                        phone_var.setErrorEnabled(false);
                        if(!str_pass.isEmpty())
                        {
                            password_var.setError(null);
                            password_var.setErrorEnabled(false);
                            if(str_email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
                            {
                              if(str_pass.matches("/^\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[A-Z])(?=\\S*[a-z])(?=\\S*[!@#$%^&*? ])\\S*$/"))
                              {
                                  firebaseDatabase = FirebaseDatabase.getInstance();
                                  reference = firebaseDatabase.getReference("user");

                                  String str_name_d = name_var.getEditText().getText().toString();
                                  String str_email_d = email_var.getEditText().getText().toString();
                                  String str_pass_d = password_var.getEditText().getText().toString();
                                  String str_user_d = user_var.getEditText().getText().toString();
                                  String str_phone_d = phone_var.getEditText().getText().toString();
                                   signupdata storingdata = new signupdata(str_name_d,str_user_d,str_email_d,str_phone_d,str_pass_d);
                                  //making object of the class

                                  reference.child(str_user_d).setValue(storingdata);// child is created in the reference by name of username

                                  Toast.makeText(signup.this, "Registeration successful", Toast.LENGTH_SHORT).show();



                                  Intent i2 = new Intent(getApplicationContext(),DashBoard.class);
                                  startActivity(i2);
                                  finish();
                              }else{
                                  password_var.setError("Please enter the strong password");
                              }
                            }else{
                                 email_var.setError("Please enter valid email");
                            }
                        }else {
                            password_var.setError("Please enter the password");
                        }
                    }else {
                        phone_var.setError("Please enter a phone number");
                    }
                }{
                    email_var.setError("Please enter the email address");
                }

                }else{
                    user_var.setError("Please enter the username");
                }

                }else{
                    name_var.setError("Please enter the full name");
                }

            }
        });
    }
}