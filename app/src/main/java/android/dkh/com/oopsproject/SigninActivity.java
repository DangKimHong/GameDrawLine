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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SigninActivity extends AppCompatActivity {

    public static EditText edtUsername;
    public static EditText edtPassword;
    private Button btnOk;
    private TextView tvDangky;
    private ImageView image;
    //public static ArrayList<User> arrayUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
//        image.setImageResource(R.drawable.logosmall);

        AnhXa();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (username.equals("") || password.equals("")){
                    Toast.makeText(SigninActivity.this, "Please enter your full information!", Toast.LENGTH_SHORT).show();
                }else{
                    new AsyncSignin().execute(username,password);
                }
            }
        });

        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AnhXa(){
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnOk = (Button) findViewById(R.id.btnOk);
        tvDangky = (TextView) findViewById(R.id.tvDangky);
        image = (ImageView) findViewById(R.id.image);

    }

    public class AsyncSignin extends AsyncTask<String, String, String>
    {
        String urlGet;
        public static final int CONNECTION_TIMEOUT=10000;
        public static final int READ_TIMEOUT=15000;
        HttpURLConnection conn;
        URL url = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {

            try {
                urlGet = Common.UrlGetData + "Game";
                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();
                urlGet += "?" + query;
                Log.d("AA", query);

                // Enter URL address where your php file resides
                url = new URL(urlGet);
                Log.d("AA", url +"");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.e("AA", "AA" +e);
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                //conn.setReadTimeout(READ_TIMEOUT);
                //conn.setConnectTimeout(CONNECTION_TIMEOUT);
//            conn.setRequestMethod("GET");

                // setDoInput and setDoOutput method depict handling of both send and receive
//            conn.setDoInput(true);
//            conn.setDoOutput(true);


                // Open connection for sending data
//            OutputStream os = conn.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));

//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("Email",params[1]);
//            jsonObject.put("Password",params[2]);

//            writer.write(url.toString());
//            writer.flush();
//            writer.close();
//            os.close();
//            conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                Log.e("AA", "AA" + e1);
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("AA1", e.getMessage());
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //this method will be running on UI thread

            Log.d("AA", "AA:" +result);

            if(result.equals("[]") || result.equals(""))
            {
            /* Here launching another activity when login successful. If you persist login state
            use sharedPreferences of Android. and logout button to clear sharedPreferences.*/
                Log.i("II", "ihi");
                // If username and password does not match display a error message
                Toast.makeText(SigninActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
            }
            else{
                Log.i("AA", "ihi");
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
                SigninActivity.this.finish();
            }
        }
    }
}
