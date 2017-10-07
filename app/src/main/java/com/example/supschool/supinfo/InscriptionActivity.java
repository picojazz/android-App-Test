package com.example.supschool.supinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {
    private EditText txtFirstName,txtLastName;
    private CheckBox chBac,chBachelor,chMaster;
    private Button btnSave;
    private String firstname,lastname,degres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        txtFirstName =(EditText) findViewById(R.id.txtFirstName);
        txtLastName =(EditText) findViewById(R.id.txtLastName);
        chBac =(CheckBox) findViewById(R.id.chBac);
        chBachelor =(CheckBox) findViewById(R.id.chBachelor);
        chMaster=(CheckBox) findViewById(R.id.chMaster);
        btnSave=(Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = txtFirstName.getText().toString();
                lastname = txtLastName.getText().toString();
                degres = "";
                if (chBac.isChecked()){
                    degres += chBac.getText()+" ";
                }
                if (chBachelor.isChecked()){
                    degres += chBachelor.getText()+" ";
                }
                if (chMaster.isChecked()){
                    degres += chMaster.getText()+" ";
                }
                String result =firstname+" "+lastname+" "+degres;
                Toast.makeText(InscriptionActivity.this, result, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
