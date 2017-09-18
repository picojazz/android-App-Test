package com.example.supschool.supinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Connexion extends AppCompatActivity {
    private EditText username,password;
    private Button logIn,signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        username = (EditText)findViewById(R.id.txtUsername);
        password = (EditText)findViewById(R.id.txtPassword);
        logIn = (Button)findViewById(R.id.btnLogIn);
        signUp = (Button)findViewById(R.id.btnSignUp);
    }
}
