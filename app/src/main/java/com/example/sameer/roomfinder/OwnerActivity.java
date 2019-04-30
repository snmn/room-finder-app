package com.example.sameer.roomfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OwnerActivity extends AppCompatActivity  {

    private EditText username,contact,address,ward;

    private Button ownerRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Owner Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.username = (EditText) findViewById(R.id.ownerUser);
        this.contact = (EditText) findViewById(R.id.ownerContact);
        this.address = (EditText) findViewById(R.id.ownerAddress);
        this.ward = (EditText) findViewById(R.id.ownerWard);
        this.ownerRegister = (Button) findViewById(R.id.ownerRegister);
        this.ownerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processFormValidation();
            }
        });

    }


    private void processFormValidation(){

        String usernamestr =username.getText().toString();
        if(usernamestr.length()  < 3)
        {
            username.setError("please enter valid username");
            username.requestFocus();
            return;
        }else{
            username.setError(null);
        }

        String contactstr =contact.getText().toString();
        if(contactstr.length()  < 3)
        {
            contact.setError("please enter valid contact");
            contact.requestFocus();
            return;
        }else{
            contact.setError(null);
        }


        String addressstr=address.getText().toString();
        if(addressstr.length()  < 3)
        {
            address.setError("please enter valid address");
            address.requestFocus();
            return;
        }else{
            address.setError(null);
        }


        String wardstr =ward.getText().toString();
        if(wardstr.length()  < 1)
        {
            ward.setError("please enter valid ward no");
            ward.requestFocus();
            return;
        }


        //TODO :we can submit now
        String method= "register1";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,usernamestr,contactstr,addressstr,wardstr);
        Intent i =new Intent(OwnerActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }






    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        int id = item.getItemId();
        switch (id) {



            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);   //yeta samma ho

    }

}