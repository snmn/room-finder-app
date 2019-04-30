package com.example.sameer.roomfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Sameer on 6/23/2016.
 */
public class SignUp extends Activity {
    private static final String TAG = "==>MainActivity";

    private EditText firstName, lstname, email, password, verifyPassword;
    private Button nextButton;
    private RadioButton maleRB,femaleRB, ownerRb,customerRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.firstName = (EditText) findViewById(R.id.TFname);
        this.lstname = (EditText) findViewById(R.id.TFlastname);
        this.email = (EditText) findViewById(R.id.TFemail);
        this.password = (EditText) findViewById(R.id.TFpass);
        this.verifyPassword = (EditText) findViewById(R.id.TFpass1);

        this.maleRB = (RadioButton) findViewById(R.id.Bmale);
        this.femaleRB = (RadioButton) findViewById(R.id.Bfemale);
        this.customerRb = (RadioButton) findViewById(R.id.Bcustom);
        this.ownerRb = (RadioButton) findViewById(R.id.Broom);

        this.nextButton = (Button) findViewById(R.id.nextButton);
        this.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processFormValidation();
            }
        });
    }

    private void processFormValidation() {
        //TODO : do the necessary form validation task

        String firstname = firstName.getText().toString();
        if (firstname.length() < 3) {
            firstName.setError("Not a valid Name!");
            firstName.requestFocus();
            return;
        } else {
            firstName.setError(null);
        }

        String lastname = lstname.getText().toString();
        if (lastname.length() < 3) {
            lstname.setError("Not a valid Name!");
            lstname.requestFocus();
            return;
        } else {
            lstname.setError(null);
        }

        String emailVal = email.getText().toString();
        if (emailVal.length() < 3) {
            email.setError("Not a valid email!");
            email.requestFocus();
            return;
        } else {
            if (emailVal.contains("@") && emailVal.indexOf("@") > 2) {
                //is a email
                email.setError(null);
            } else {
                email.setError("Not a valid email!");
                email.requestFocus();
                return;
            }
        }

        String passMain = password.getText().toString();
        if (passMain.length() < 4) {
            password.setError("Not a valid password!");
            password.requestFocus();
            return;
        } else {
            password.setError(null);
        }

        String verifyPass = verifyPassword.getText().toString();
        if (!verifyPass.equals(passMain)) {
            verifyPassword.setError("Passwords dont match!");
            verifyPassword.requestFocus();
            return;
        }

        String gender;
        if (maleRB.isChecked() || femaleRB.isChecked()) {
            if (maleRB.isChecked()) {
                gender = "M";
            } else {
                gender = "F";
            }
        } else {
            //error
            Toast.makeText(getApplicationContext(), "Gender not selected. Are yo a transgender!", Toast.LENGTH_SHORT).show();
            return;
        }

        String type;
        if (customerRb.isChecked() || ownerRb.isChecked()) {
            if (customerRb.isChecked()) {
                type = "C";
            } else {
                type = "O";
            }
        } else {
            //error
            Toast.makeText(getApplicationContext(), "Type not defined!", Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO: we an submit the data
       // Toast.makeText(getApplicationContext(), "Everything is fine!", Toast.LENGTH_LONG).show();
        String method= "register";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,firstname,lastname,gender,emailVal,passMain,type);

        if(customerRb.isChecked())
        {
            Intent intent = new Intent(SignUp.this,CustomerActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intentm = new Intent(getApplicationContext(),OwnerActivity.class);
            startActivity(intentm);
            finish();

        }


    }


    public void onRadioButtonClicked(View view){
        //Is the button now checked
        boolean checked =((RadioButton) view).isChecked();

        //Check which radio butto was clicked

        switch(view.getId()){
            case R.id.Bmale:
                if(checked)
                    //Bmale is clicked
                    break;

            case R.id.Bfemale:
                if(checked)
                    //Bfemale is clicked
                    break;

            case R.id.Broom:
                if(checked)
                    //Bfemale is clicked
                    break;

            case R.id.Bcustom:
                if(checked)
                    //Bfemale is clicked
                    break;


        }


    }


}













