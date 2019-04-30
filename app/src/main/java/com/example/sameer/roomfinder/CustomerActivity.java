package com.example.sameer.roomfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerActivity extends AppCompatActivity {

    private EditText username,contact,address;
    private Button registerbuttoncustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Customer Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.username = (EditText) findViewById(R.id.CustomUser);
        this.contact = (EditText)findViewById(R.id.CustomContact);
        this.address = (EditText) findViewById(R.id.CustomAddress);
        this.registerbuttoncustomer = (Button) findViewById(R.id.registerbuttoncustomer);
        this.registerbuttoncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processFormValidation();
            }
        });

    }

    private void processFormValidation() {
        //TODO; do the necessary form validation Task

        String usernamestr = username.getText().toString();
        if(usernamestr.length() < 3) {
            username.setError("Please enter valid username");
            username.requestFocus();
            return;
        }else{
            username.setError(null);
        }


        String contactstr =contact.getText().toString();
        if(contactstr.length() <3)
        {
            contact.setError("Please enter contact Number");
            contact.requestFocus();
            return;
        }else{
            contact.setError(null);
        }


        String addressstr = address.getText().toString();
        if(addressstr.length() < 3)
        {
            address.setError("please Enter address");
            address.requestFocus();
            return;
        }

        //TODO: we can submit the data

        String method= "register1";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,usernamestr,contactstr,addressstr,"0");
        Intent i = new Intent(CustomerActivity.this,MainActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
