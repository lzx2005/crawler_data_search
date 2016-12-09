package com.lzx2005;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        }
    }
}
