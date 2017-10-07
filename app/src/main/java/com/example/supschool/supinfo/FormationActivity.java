package com.example.supschool.supinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FormationActivity extends AppCompatActivity {

    private String formation;
    private String details;
    private ListView listFormation;
    private String[] tabFormation,tabDetailFormation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation);

        tabFormation = getResources().getStringArray(R.array.tabFormation);
        tabDetailFormation = getResources().getStringArray(R.array.tabDetailFormation);

        listFormation =(ListView) findViewById(R.id.listFormation);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(FormationActivity.this,android.R.layout.simple_list_item_1,tabFormation);
        listFormation.setAdapter(listAdapter);
        listFormation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                formation = tabFormation[position];
                details = tabDetailFormation[position];

                AlertDialog.Builder dialog = new AlertDialog.Builder(FormationActivity.this);
                dialog.setTitle(formation);
                dialog.setMessage(details);
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setPositiveButton(getString(R.string.sign_up), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(FormationActivity.this,InscriptionActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

            }
        });
    }
}
