package com.example.sameer.roomfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sameer on 6/22/2016.
 */
public class login extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);

             //TO DO menu bottom banaune
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setTitle("ROOMFINDER");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            String username =getIntent().getStringExtra("Username");    // welcome pa6adi ko txt lina lai
            TextView tv = (TextView)findViewById(R.id.TVusername);
            tv.setText(username);
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

            case R.id.add_room:
                Intent i1 =new Intent(login.this,AddRoomActivity.class);
                finish();
               startActivity(i1);

                // startActivity(new Intent(this,MainActivity.class));  //Yesle new activity ma jane kaam gar6
            //    Toast.makeText(login .this, "Logout clicked", Toast.LENGTH_SHORT).show(); //Toast msg din6
                break;

            case R.id.view_profile:

                Intent i = new Intent(login.this,SignUp.class);
                finish();
                   startActivity(i);

                // startActivity(new Intent(this,SignUp.class));

                // Toast.makeText(login.this, "View profile clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:

                    Intent i2 = new Intent(login.this,MainActivity.class);
                 finish();
                   startActivity(i2);

                // startActivity(new Intent(this,SignUp.class));

               // Toast.makeText(login.this, "View profile clicked", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);   //yeta samma ho

    }

}

