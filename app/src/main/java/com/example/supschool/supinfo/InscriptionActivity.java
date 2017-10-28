package com.example.supschool.supinfo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InscriptionActivity extends AppCompatActivity {
    private EditText txtFirstName,txtLastName;
    private CheckBox chBac,chBachelor,chMaster;
    private Button btnSave;
    private String firstname,lastname,degres,formation;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        formation = getIntent().getStringExtra("FORMATION");
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.waiting));
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
                /*String result =firstname+" "+lastname+" "+degres;
                Toast.makeText(InscriptionActivity.this, result, Toast.LENGTH_SHORT).show();*/
                if (firstname.isEmpty() || lastname.isEmpty()){
                    Toast.makeText(InscriptionActivity.this, getString(R.string.errorEmptyField), Toast.LENGTH_SHORT).show();
                }else {
                    String url = "http://192.168.56.1:30/sup_school/inscription.php";
                    InscriptionServer inscriptionServer = new InscriptionServer();
                    inscriptionServer.execute(url);

                }

            }
        });

    }
    protected class InscriptionServer extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(params[0]);
                List<NameValuePair> form = new ArrayList<>();
                form.add(new BasicNameValuePair("first_name",firstname));
                form.add(new BasicNameValuePair("last_name",lastname));
                form.add(new BasicNameValuePair("degres",degres));
                form.add(new BasicNameValuePair("formation",formation));
                post.setEntity(new UrlEncodedFormEntity(form, HTTP.UTF_8));
                ResponseHandler<String> buffer = new BasicResponseHandler();
                String result = client.execute(post,buffer);
                return result;

            }catch (Exception e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            if (s == null) {
                Toast.makeText(InscriptionActivity.this, getString(R.string.errorServer), Toast.LENGTH_SHORT).show();
                return;
            }

            try{
                JSONObject json = new JSONObject(s);
                String status = json.getString("status");
                if(status.equals("KO")){
                    Toast.makeText(InscriptionActivity.this, getString(R.string.inscriptionKO), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InscriptionActivity.this, getString(R.string.inscriptionOK), Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){

            }
        }
    }
}
