package com.example.sameer.roomfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class AddRoomActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("ADD ROOM");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            if (item.getItemId() == android.R.id.home) {
                finish();
            }
            return super.onOptionsItemSelected(item);
        }
    public void onButtonClick(View v) {

        if (v.getId() == R.id.next) {
            Intent i = new Intent(AddRoomActivity.this, UploadImageActivity.class);
            startActivity(i);
        }
    }


    }
