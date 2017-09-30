package com.example.supschool.supinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connexion extends AppCompatActivity {
    private EditText txtUsername,txtPassword;
    private Button btnLogin,btnSignup;
    private String username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btnLogin = (Button)findViewById(R.id.btnLogIn);
        btnSignup = (Button)findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(Connexion.this, getString(R.string.errorEmptyField), Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Connexion.this,Home.class);
                    intent.putExtra("LOGIN",username);
                    startActivity(intent);
                }
            }
        });
    }
}
