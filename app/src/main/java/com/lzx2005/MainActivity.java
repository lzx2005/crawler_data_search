package com.lzx2005;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private EditText host;
    private EditText query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        host = (EditText) findViewById(R.id.edit_host);
        query = (EditText) findViewById(R.id.edit_query);
        button.setOnClickListener(new SubmitButtonOnClick());
    }

    private class SubmitButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Log.d("host",host.getText().toString());
            Log.d("query",query.getText().toString());
            String s = "host:" + host.getText().toString() + ",query:" + query.getText().toString();

            Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
            toast.show();

            CallAPI callAPI = new CallAPI();
            callAPI.execute("http://112.74.33.145:8050/find?url=http://www.lzx2005.com");
        }
    }


    private class CallAPI extends AsyncTask<String, String, String> {
        public CallAPI() {
            //set context variables if required
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0]; // URL to call
            String resultToDisplay = "";
            InputStream in = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
            try {
                resultToDisplay = IOUtils.toString(in, "UTF-8");
                //to [convert][1] byte stream to a string
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultToDisplay;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("result",result);
            //query.setText("test");
            //Update the UI
        }
    }
}
