package com.example.supschool.supinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class Connexion extends AppCompatActivity {
    private EditText txtUsername,txtPassword;
    private Button btnLogin,btnSignup;
    private String username,password;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.waiting));
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
                   String url ="http://192.168.56.1:30/sup_school/connexion.php?login="+username+"&password="+password;
                    loginServer ls = new loginServer();
                    ls.execute(url);
                }
            }
        });

    }
    protected class loginServer extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(params[0]);
                ResponseHandler<String> buffer = new BasicResponseHandler();
              String result = client.execute(get,buffer);
                 return  result;
            }catch (Exception e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s)  {
            dialog.dismiss();
                if (s == null) {
                    Toast.makeText(Connexion.this, getString(R.string.errorServer), Toast.LENGTH_SHORT).show();
                    return;
                }
            //JSONObject jsonObject = new JSONObject();
                try {
                    JSONObject json = new JSONObject(s);
                    String status = json.getString("status");
                    if (status.equals("KO")){
                        Toast.makeText(Connexion.this, getString(R.string.errorParameters), Toast.LENGTH_SHORT).show();
                    }else{
                        String firstname = json.getString("first_name");
                        String lastname = json.getString("last_name");
                        String welcome = getString(R.string.welcome)+" "+firstname+" "+lastname;
                        Toast.makeText(Connexion.this, welcome, Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(Connexion.this,Home.class);
                        startActivity(intent);

                    }
                } catch (Exception e) {

                }

        }
    }
}
