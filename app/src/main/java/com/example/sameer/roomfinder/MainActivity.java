package com.example.sameer.roomfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

   // private EditText passTxt,userTxt;
    private static String TAG = "==>";
    private EditText uname,psw;
    private Button btsignUp,btLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.btLogin= (Button)findViewById(R.id.BLogin);
        this.uname =(EditText)findViewById(R.id.TFusername);
        this.psw = (EditText)findViewById(R.id.TFpassword);
        final BackgroundTask backgroundTask = new BackgroundTask(this);
        this.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname1=uname.getText().toString();
                String psw1= psw.getText().toString();
                String method ="login";
                if((uname1.equals(null))||(psw1.equals(null)))
                {
                    Toast.makeText(MainActivity.this, "Username or Password Invalid", Toast.LENGTH_SHORT).show();
                }
                else {
                    backgroundTask.execute(method, uname1, psw1);

                    Intent i = new Intent(MainActivity.this, login.class);
                    i.putExtra("Username", uname1);
                    startActivity(i);
                    finish();
                }
            }
        });

        this.btsignUp= (Button) findViewById(R.id.Bsignup);
        this.btsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
        });

    }

}