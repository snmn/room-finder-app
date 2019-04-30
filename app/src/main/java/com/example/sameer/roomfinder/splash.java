package com.example.sameer.roomfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

//import com.squareup.picasso.Picasso;

/**
 * Created by Sameer on 6/26/2016.
 */
public class splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

      //  ImageView splashLogo = (ImageView) findViewById(R.id.imageView);

        //Picasso.with(this).load(R.drawable.room_splash).into(splashLogo);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent i = new Intent(splash.this, MainActivity.class);
                    startActivity(i);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        myThread.start();
    }
}
