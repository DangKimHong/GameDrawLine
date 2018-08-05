package android.dkh.com.oopsproject;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignupActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnOk;
    private TextView tvDangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        AnhXa();
        tvDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (username.equals("") || password.equals("")){
                    Toast.makeText(SignupActivity.this, "Please enter your full information!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // post requset
                    new AsyncSignup().execute(username, password);
                }
            }
        });
    }

    public void AnhXa(){
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnOk = (Button) findViewById(R.id.btnDangky);
        tvDangnhap = (TextView) findViewById(R.id.tvDangnhap);
    }

    private class AsyncSignup extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params)
        {
            URL url;
            HttpURLConnection conn = null;
            try {
                String urlGet =  Common.UrlGetData + "Game";

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();
                urlGet += "?" + query;

                url = new URL(urlGet);
                Log.d("AA", url + " url");
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");

                int response_code = conn.getResponseCode();
                // Check if successful connection made
                Log.d("AA", "Ko OK" + response_code + urlGet );

                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    Log.d("AA", "OK " + result.toString() + "-" + urlGet);

                    return(result.toString());
                } else {
                    return("unsuccessful");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("AA", "error 1 => " + e.getMessage().toString());
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    // conn.disconnect();
                }
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("1"))
            {
                Toast.makeText(getApplicationContext(), "\n" + "Username already exists", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(SignupActivity.this, "Your registion is successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
