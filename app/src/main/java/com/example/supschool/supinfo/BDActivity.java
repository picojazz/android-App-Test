package com.example.supschool.supinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class BDActivity extends AppCompatActivity {
    private EditText txtLogin,txtPassword;
    private Button add,update,delete,all;
    private String login,password;
    private DBSchool db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);
        txtLogin = (EditText)findViewById(R.id.login);
        txtPassword = (EditText)findViewById(R.id.password);
        add = (Button) findViewById(R.id.add);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        all = (Button) findViewById(R.id.all);
        db = new DBSchool(BDActivity.this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = txtLogin.getText().toString();
                password = txtPassword.getText().toString();
                if(db.addUser(login,password))
                    Toast.makeText(BDActivity.this, " ajouté", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BDActivity.this, " échoué", Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = txtLogin.getText().toString();
                password = txtPassword.getText().toString();
                if(db.updateUser(login,password))
                    Toast.makeText(BDActivity.this, " modifié", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BDActivity.this, " échoué", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = txtLogin.getText().toString();
                password = txtPassword.getText().toString();
                if(db.deleteUser(login))
                    Toast.makeText(BDActivity.this, " supprimé", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BDActivity.this, " échoué", Toast.LENGTH_SHORT).show();
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res="";
                List<String> l = db.getUsers();
                for (int i = 0 ; i < l.size() ; i++) {
                    res += l.get(i)+"\n";

                }
                Toast.makeText(BDActivity.this, res, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
